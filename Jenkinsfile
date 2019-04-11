pipeline {
  agent any
  stages {
    stage('Project') {
      steps {
        sh '''
           oc new-project cop-service
           '''
      }
    }
    stage('Create Configmap') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject() {
            return !openshift.selector('configmap', 'cmp-cop-service').exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject() {
              openshift.create('configmap', 'cmp-cop-service', "--from-file=confg/config.properties")
          }
        }
       }
      }
     }
    stage('S2I Build and Deploy') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject() {
            return !openshift.selector('bc', 'cop-service').exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject() {
              openshift.create(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
