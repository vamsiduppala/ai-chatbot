pipeline {
    agent any

    environment {
        WAR_NAME = "smart-chatbot-system.war"
        DEPLOY_DIR = "/opt/tomcat/webapps/"
    }

    tools {
        maven 'Maven 3.8.1'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/vamsiduppala/ai-chatbot.git' 
            }
        }

        stage('Build WAR') {
            steps {
                sh 'mvn clean package'
            }
        }

        stage('Deploy WAR') {
            steps {
                sh "cp target/${WAR_NAME} ${DEPLOY_DIR}"
            }
        }
    }

    post {
        success {
            echo '!!! Application deployed successfully !!!'
        }
        failure {
            echo 'X Deployment failed X'
        }
    }
}