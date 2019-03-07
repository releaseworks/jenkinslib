# Releaseworks Jenkins Libraries
## Usage
Place this to the top of your Jenkinsfile:
```
@Library('github.com/releaseworks/jenkinslib') _
```
## Functions
### SlackMessage
Example:
```
SlackMessage(webhookUrl: "https://hook.slack.com/XXXX",
             channel: "#ci",
             message: "Build succeeded!")
```