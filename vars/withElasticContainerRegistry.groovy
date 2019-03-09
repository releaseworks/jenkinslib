def call(Closure body) {
    try {
        docker.image("releaseworks/awscli:latest").inside("--entrypoint \"\" -e AWS_ACCESS_KEY_ID -e AWS_SECRET_ACCESS_KEY -e AWS_DEFAULT_REGION -v /var/run/docker.sock:/var/run/docker.sock") {
            sh "$(aws ecr get-login | sed 's/-e none //')"
            body()
        }
    } catch( ClassNotFoundException e ) {
        throw new Exception("Docker Pipeline plugin required")
    }
}