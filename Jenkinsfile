node {
    def builtImage
    
    stage('Clone microservice repo') {
        checkout scm
    }

    stage('Build the code') {
        sh('./mvnw package')
    }

    stage('Build the Dockerfile') {
        docker.withRegistry("${AWS_ECR_REPO}", "${AWS_ECR_INFO}") {
            builtImage = docker.build('fishing-site-repo')
            builtImage.push()
        }
    }
    
}

