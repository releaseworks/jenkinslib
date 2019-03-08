def call(command) {
    try {
        docker.image('alpine:latest')
    } catch( ClassNotFoundException e ) {
        throw new Exception("Docker Pipeline plugin required")
    }

    docker.image("releaseworks/awscli:latest").inside("--entrypoint \"\" -e AWS_ACCESS_KEY_ID -e AWS_SECRET_ACCESS_KEY -e AWS_DEFAULT_REGION") {
        sh "aws ${command}"
    }
}