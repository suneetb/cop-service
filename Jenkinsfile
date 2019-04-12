openshift.withCluster() {
  env.namespace= "${env.msname}"
}

pipeline {
  agent any
  stages {
    stage('S2I Build and Deploy') {
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject(env.namespace) {
              openshift.apply(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
