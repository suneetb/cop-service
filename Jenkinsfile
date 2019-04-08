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
            openshift.create(openshift.process(readFile(file:'openjdk-basic-template.yml'), "-p", "APPLICATION_NAME=cop-service", "SOURCE_REPOSITORY_URL=https://github.com/sunnyf21/cop-service.git"))
          }
        }
      }
    }
  }
}
