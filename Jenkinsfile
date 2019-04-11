pipeline {
  agent any
  stages {
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
