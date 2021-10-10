pipeline {
    agent any
    stages {
        stage('Build and deploy project (jar) on Nexus') {
        steps{
                 {
                    sh 'mvn clean install'
                }
            }
        }
}
}