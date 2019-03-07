def call(Map config) {
  sh "curl -XPOST -s -d 'payload={ \"color\": \"good\", \"channel\": \"${config.channel}\", \"text\": \"${config.message}\" }' ${config.webhookUrl}"
}
