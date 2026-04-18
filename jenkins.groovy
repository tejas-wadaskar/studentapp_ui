pipeline {
  agent {
        label 'webserver'
  }
    stages {
        stage('pull') {
            steps {
                git 'https://github.com/tejas-wadaskar/studentapp_ui'
            }
        }
        stage('build') {
            steps {
                sh '''mvn clean package
                    echo "this is sample build"'''
            }
        }
        stage('test') {
            steps {
                sh '''mvn clean verify sonar:sonar \\
                    -Dsonar.projectKey=studentapp \\
                    -Dsonar.host.url=http://52.66.220.95:9000 \\
                    -Dsonar.login=sqp_b005e900a8fb729bb9365d29af4bd2d3534ac6c5
                    echo "we are in test stage"'''
            }
        }
        stage('deploy') {
            steps {
                sh 'echo "this is deploy stage"'
            }
        }


    }


}