pipeline {
    agent any
    stages {
        stage('Build') {
            steps {
                echo 'Starting the Build stage'
                // Your build steps here
                echo 'Build stage completed'
            }
        }
        stage('Test') {
            steps {
                echo 'Starting the Test stage'
                // Your test steps here
                echo 'Test stage completed'
            }
        }
    }
    post {
        success {
            echo 'Pipeline executed successfully'
            // Additional steps for successful builds
        }
        failure {
            echo 'Pipeline execution failed'
            // Additional steps for failed builds
        }
    }
}

