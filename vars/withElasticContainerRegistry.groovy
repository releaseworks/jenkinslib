def call(Closure body) {
    try {
        // Need to mount the Docker socket, and host's /etc/passwd and /etc/group for sudo to work
        docker.image("releaseworks/awsdockercli:latest").inside("--entrypoint \"\" -e HOME=. -e AWS_ACCESS_KEY_ID -e AWS_SECRET_ACCESS_KEY -e AWS_DEFAULT_REGION -v /var/run/docker.sock:/var/run/docker.sock -v /etc/group:/etc/group:ro -v /etc/passwd:/etc/passwd:ro") {
            sh "sudo docker_socket_init"
            sh "\$(aws ecr get-login | sed 's/-e none //')"
            body()
        }
    } catch( ClassNotFoundException e ) {
        throw new Exception("Docker Pipeline plugin required")
    }
}