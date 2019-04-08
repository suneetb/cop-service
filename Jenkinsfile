pipeline {
  agent any
  stages {
    stage('Add Project') {
      steps {
        script {
          openshift.withCluster() {
            openshift.create(readfile(file:'projects.yml'))
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
