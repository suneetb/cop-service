pipeline {
  agent any
  stages {
    stage('Create Namespace') {
      when {
        expression {
          openshift.withCluster() {
            return !openshift.selector('namespace', $msname).exists();
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.create('namespace', $msname)
          }
        }  
      }
    }
    stage('Create Configmap') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject($msname) {
            return !openshift.selector('configmap', 'cmp-cop-service').exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject($msname) {
              openshift.create('configmap', $msname , "--from-file=confg/config.properties")
          }
        }
       }
      }
     }
    stage('S2I Build and Deploy') {
      when {
        expression {
          openshift.withCluster() {
            openshift.withProject($msname) {
            return !openshift.selector('bc', $msname).exists();
            }
          }
        }
      }
      steps {
        script {
          openshift.withCluster() {
            openshift.withProject('cop-service') {
              openshift.create(openshift.process(readFile(file:'openjdk-basic-template.yml'), "--param-file=cop-param.txt"))
            }  
          }
        }
      }
    }
  }
}
