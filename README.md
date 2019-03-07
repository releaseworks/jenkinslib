# Releaseworks Jenkins Libraries
This repository includes helper functions for making life easier with Jenkins and Jenkinsfiles.

## Usage
Place this at the top of your Jenkinsfile:
```
@Library('github.com/releaseworks/jenkinslib') _
```
_(Note the trailing underscore!)_

## Function Reference
### SlackMessage
This function posts a message to a Slack channel from a Jenkinsfile using a Slack Incoming Webhook. Create a Slack incoming webhook with these instructions: https://get.slack.help/hc/en-gb/articles/115005265063-Incoming-webhooks-for-Slack

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

## Contributing
All pull requests are very welcome.