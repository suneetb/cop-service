pipeline {
  agent {
      label 'maven'
  }
  stages {
    stage('Build App') {
      steps {
        sh "mvn -B clean install -DskipTests=true -f ./pom.xml"
      }
    }
  } 
}
