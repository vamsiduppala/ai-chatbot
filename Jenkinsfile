pipeline {
    agent any

    environment {
        JAVA_HOME = 'C:\\Users\\vamsi\\.p2\\pool\\plugins\\org.eclipse.justj.openjdk.hotspot.jre.full.win32.x86_64_21.0.6.v20250130-0529\\jre'
        PATH = "${JAVA_HOME}\\bin:${env.PATH}"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build WAR') {
            steps {
                script {
                    // You can now run your build command
                    bat 'mvn clean package'
                }
            }
        }

        stage('Deploy WAR') {
            steps {
                script {
                    // Deploy your WAR file here
                }
            }
        }
    }
}
