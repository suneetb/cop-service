pipeline {
  agent any
  stages {
    stage('Create Configmap') {
      steps{
        script {
          openshift.withCluster() {
            openshift.withProject('clock-service') {
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
            openshift.withProject('clock-service') {
            return !openshift.selector('bc', 'cop-service').exists();
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
