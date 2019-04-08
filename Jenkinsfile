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
            openshift.newApp('redhat-openjdk18-openshift:1.3~https://github.com/sunnyf21/cop-service.git')
          }
        }
      }
    }
  }
}
