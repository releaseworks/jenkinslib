def call(command, version = 'light') {
    try {
        docker.image("hashicorp/terraform:${version}").inside("--entrypoint \"\" -e AWS_ACCESS_KEY_ID -e AWS_SECRET_ACCESS_KEY -e AWS_DEFAULT_REGION -e TF_LOG -e TF_INPUT -e TF_LOG_PATH -e TF_CLI_ARGS") {
            sh "terraform ${command}"
        }
    } catch( ClassNotFoundException e ) {
        throw new Exception("Docker Pipeline plugin required")
    }
}