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
            openshift.create(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            
          }
        }
      }
    }
  }
}
