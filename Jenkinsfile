pipeline {
  agent any
  stages {
    stage('Create Configmap') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject('cop-service') {
            return !openshift.selector('configmap', 'cmp-cop-service').exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('cop-service') {
              openshift.create('configmap', 'cmp-cop-service' , "--from-file=confg/config.properties")
          }
        }
       }
      }
     }
    stage('deploy') {
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('cop-service') {
              openshift.apply(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
