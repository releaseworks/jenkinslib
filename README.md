# Jenkins Libraries
This repository includes helper functions for making life easier with Jenkins and Jenkinsfiles.

## Usage
Place this at the top of your Jenkinsfile:
```
@Library('github.com/releaseworks/jenkinslib') _
```
_(Note the trailing underscore!)_

## Function Reference
Available functions:
* [SlackMessage](#slackmessage)
* [Terraform](#terraform)
* [AWS](#slackmessage)
* [withElasticContainerRegistry](#withelasticcontainerregistry)

### SlackMessage
This function posts a message to a Slack channel from a Jenkinsfile using a Slack Incoming Webhook. Create a Slack incoming webhook with these instructions: https://get.slack.help/hc/en-gb/articles/115005265063-Incoming-webhooks-for-Slack

Usage:
```
SlackMessage(webhookUrl: 'https://hooks.slack.com/XXXX',
             channel: '',
             color: 'good',
             username: 'Jenkins',
             message: '')
```

Example:
```
SlackMessage(webhookUrl: "https://hooks.slack.com/XXXX",
             channel: "#ci",
             message: "Build succeeded!")
```

Replace the webhookUrl value with your unique webhook URL.

An advanced example for catching errors with a try-catch block:
```
try {
    def url = 'https://hooks.slack.com/XXXX'

    stage('Build app') {
        // your build steps here
    }

    stage('Post notification') {
        SlackMessage(webhookUrl: url,
                     channel: "#ci",
                     message: "Build succeeded for ${env.JOB_NAME}!")
    }

} catch(error) {
    SlackMessage(webhookUrl: url,
                 channel: "#ci",
                 message: "Build failed: $error - <${env.BUILD_URL}|view logs>")
    throw error
}
```

### Terraform
Run Terraform commands in a Jenkinsfile. See https://terraform.io

This command requires the Docker Pipeline plugin and a working Docker server.

Usage:
```
Terraform(command, version)
```

The version defaults to the latest version.

The following environment variables, if set, are passed to Terraform: `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_DEFAULT_REGION`, `TF_INPUT`, `TF_LOG`, `TF_LOG_PATH`, `TF_CLI_ARGS`.

Example:
```
Terraform("plan")
Terraform("apply -var-file=environment.tfvars")
Terraform("validate", "0.11.11")
```

### AWS
Run AWS CLI commands in a Jenkinsfile.

This command requires the Docker Pipeline plugin and a working Docker server.

Usage:
```
AWS(command)
```

The following environment variables, if set, are passed to AWS CLI: `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_DEFAULT_REGION`.

Example:
```
withEnv(["AWS_ACCESS_KEY_ID=abc", "AWS_SECRET_ACCESS_KEY=def", "AWS_DEFAULT_REGION=eu-west-1"]) {
    AWS("ec2 describe-instances")
}

AWS("s3 ls")
```

Note: Adding AWS API credentials into your pipeline code is not recommended. Use Jenkins Credentials (and a `withCredentials() { .. }` block), or IAM Roles.

### withElasticContainerRegistry
Run build steps when authenticated to an AWS Elastic Container Registry (ECR).

This command requires the Docker Pipeline plugin and a working Docker server.

Usage:
```
withElasticContainerRegistry {
    // build steps here
}
```

The following environment variables, if set, are passed to AWS CLI for authentication: `AWS_ACCESS_KEY_ID`, `AWS_SECRET_ACCESS_KEY`, `AWS_DEFAULT_REGION`.

Example:
```
withEnv(["AWS_ACCESS_KEY_ID=abc", "AWS_SECRET_ACCESS_KEY=def", "AWS_DEFAULT_REGION=eu-west-1"]) {
    withElasticContainerRegistry {
        // Build image in the current working directory
        def app = docker.build("ACCOUNT-ID.dkr.ecr.eu-west-1.amazonaws.com/app")

        // Push to ECR
        app.push("${env.BUILD_NUMBER}")
    }
}
```

Note: Adding AWS API credentials into your pipeline code is not recommended. Use Jenkins Credentials (and a `withCredentials() { .. }` block), or IAM Roles.

## Contributing
All pull requests are very welcome.
