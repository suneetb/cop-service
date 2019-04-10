pipeline {
  agent any
  stages {
    stage('Create Namespace') {
      steps{
        script {
          openshift.withCluster('insecure-skip-tls-verify: true') {
            openshift.withProject() {
              openshift.create('namespace', 'cop-service3') 
           }  
         }
        }
       }
      }
    stage('Create Configmap') {
      steps{
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
            return !openshift.selector('bc', 'cop-service3').exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('clock-service') {
              openshift.create(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
