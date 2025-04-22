pipeline {
    agent any

    environment {
        WAR_NAME = "smart-chatbot-system.war"
        DEPLOY_DIR = "/opt/tomcat/webapps/"
        JAVA_HOME = 'C:\\Users\\vamsi\\.p2\\pool\\plugins\\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_21.0.6.v20250130-0529\\jre'
        PATH = "${JAVA_HOME}\\bin:${env.PATH}"
    }

    tools {
        maven 'Maven 3.8.1'
    }

    stages {
        stage('Checkout') {
            steps {
                git url: 'https://github.com/vamsiduppala/ai-chatbot.git' // or use SCM from Jenkins job
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