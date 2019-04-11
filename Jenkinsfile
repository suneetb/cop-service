pipeline {
  agent any
  stages {
    stage {
      steps('deploy') {
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
