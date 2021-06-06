node {
    def builtImage
    
    stage('Clone microservice repo') {
        checkout scm
    }

    stage('Build the Dockerfile') {
        docker.withRegistry('https://760087176660.dkr.ecr.us-east-2.amazonaws.com', 'ecr:us-east-2:tim-aws-cred') {
            builtImage = docker.build('fishing-site-repo')
            builtImage.push()
        }
    }
    
}

