def call(Map config) {
  def endpoint = new URL(config.webhookUrl)
  sh "curl -XPOST -d 'payload={ \"color\": \"good\", \"channel\": \"${config.channel}\" \"text\": \"${config.message}\" }' ${config.webhookUrl}"
}
