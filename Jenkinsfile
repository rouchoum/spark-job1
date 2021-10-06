pipeline {
    agent any
    stages {
        stage('Build and deploy project (jar) on Nexus') {
            steps {
                 {
                    sh 'make build_deploy'
                }
            }
        }
        stage('Packaging') {
            steps {
                 {
                    script {
                        sh 'HTTP_PROXY=${JENKINS_HTTP_PROXY} \
                        make package'
                    }
                }
            }
        }}}
