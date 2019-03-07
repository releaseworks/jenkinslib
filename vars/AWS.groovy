def call(command, accessKeyId = '', secretKey = '', region = 'eu-west-1') {
    try {
        docker.image('alpine:latest')
    } catch( ClassNotFoundException e ) {
        throw new Exception("Docker Pipeline plugin required")
    }

    docker.image("releaseworks/awscli:latest").inside("--entrypoint \"\" -e AWS_ACCESS_KEY_ID=${accessKeyId} -e AWS_SECRET_ACCESS_KEY=${secretKey} -e AWS_DEFAULT_REGION=${region}") {
        sh "aws ${command}"
    }
}