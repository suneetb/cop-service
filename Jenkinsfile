openshift.withCluster() {
  env.NAMESPACE= $msname
}

pipeline {
  agent any
  stages {
    stage('Create Namespace') {
      when {
        expression {
          openshift.withCluster() {
            return !openshift.selector('namespace', env.NAMESPACE).exists();
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.create('namespace', env.NAMESPACE)
          }
        }  
      }
    }
    stage('Create Configmap') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject(env.NAMESPACE) {
            return !openshift.selector('configmap', env.NAMESPACE).exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject(env.NAMESPACE) {
              openshift.create('configmap', env.NAMESPACEe , "--from-file=confg/config.properties")
          }
        }
       }
      }
     }
    stage('S2I Build and Deploy') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject(env.NAMESPACE) {
            return !openshift.selector('bc', env.NAMESPACE).exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('cop-service') {
              openshift.create(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
