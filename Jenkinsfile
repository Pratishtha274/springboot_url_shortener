// This file defines the CI/CD pipeline for the Spring Boot application.
pipeline {
    agent any
    environment {
        JAVA_HOME = tool 'OpenJDK-17'
        PATH = "${env.JAVA_HOME}/bin:${env.PATH}"
    }
    stages {
        stage('Checkout') {
            steps {
                checkout scm
                echo "Code checked out successfully."
            }
        }
        stage('Build') {
            steps {
                sh './mvnw clean package -DskipTests'
                echo "Spring Boot application built successfully."
            }
        }
        stage('Test') {
            steps {
                sh './mvnw test'
                echo "Unit tests completed."
            }
        }
    }
    post {
        always {
            echo "Pipeline finished."
            cleanWs()
        }
        success {
            echo "Pipeline completed successfully!"
        }
        failure {
            echo "Pipeline failed. Please check the logs."
        }
    }
}
