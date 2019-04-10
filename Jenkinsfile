pipeline {
  agent any
  stages {
    stage('Project') {
      step {
        sh '''
          oc new-project test3
          '''
      }
    }
    stage('Create Configmap') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject('test3') {
            return !openshift.selector('configmap', 'cmp-cop-service').exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('test3') {
              openshift.create('configmap', 'cmp-cop-service', "--from-file=confg/config.properties")
          }
        }
       }
      }
     }
    stage('S2I Build and Deploy') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject('test3') {
            return !openshift.selector('bc', 'cop-service3').exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('test3') {
              openshift.create(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
