openshift.withCluster() {
  env.NAMESPACE= "${env.msname}"
}

pipeline {
  agent any
  stages {
    stage('Create Namespace') {
      when {
        expression {
          openshift.withCluster() {
            return !openshift.selector('namespace', 'cop_service').exists();
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.create('namespace', 'cop_service')
          }
        }  
      }
    }
    stage('Create Configmap') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject('cop_service') {
            return !openshift.selector('configmap', 'cmp-cop-service').exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('cop_service') {
              openshift.create('configmap', 'cmp-cop-service' , "--from-file=confg/config.properties")
          }
        }
       }
      }
     }
    stage('S2I Build and Deploy') {
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('cop_service') {
              openshift.apply(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
