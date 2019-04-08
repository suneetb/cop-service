pipeline {
  agent any
  stages {
    stage('Build') {
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
            openshift.process(readFile(file:'openjdk-basic-template.yml'), "-p", "PARAM-FILE=cop-param.txt")
          }
        }
      }
    }
  }
}
