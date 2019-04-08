pipeline {
  agent any
  stages {
    stage('S2I Build and Deploy') {
      when {
        expression {
          openshift.withCluster() {
            return !openshift.selector('bc', 'cop-service').exists();
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('clock_service') {
              openshift.create(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
