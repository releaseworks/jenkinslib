def call(command, version = 'light') {
    try {
        docker.image('alpine:latest')
    } catch( ClassNotFoundException e ) {
        throw new Exception("Docker Pipeline plugin required")
    }

    docker.image("hashicorp/terraform:${version}").inside('--entrypoint ""') {
        sh command
    }
}