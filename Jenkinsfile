openshift.withCluster() {
  env.namespace= "${env.msname}"
}

pipeline {
  agent any
  stages {
    stage('Create Namespace') {
      when {
        expression {
          openshift.withCluster() {
            return !openshift.selector('namespace', env.namespace).exists();
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.create('namespace', env.namespace)
          }
        }  
      }
    }
    stage('Create Configmap') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject(env.namespace) {
            return !openshift.selector('configmap', 'cmp-cop-service').exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject(env.namespace) {
              openshift.create('configmap', 'cmp-cop-service', "--from-file=confg/config.properties")
          }
        }
       }
      }
     }
    stage('S2I Build and Deploy') {
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject(env.namespace) {
              openshift.apply(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
