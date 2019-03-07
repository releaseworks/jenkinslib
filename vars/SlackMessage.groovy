def call(Map config) {
  def endpoint = new URL(config.webhookUrl)
  sh "curl -XPOST -d 'payload={ \"color\": \"good\", \"text\": \"${config.message}\" }' ${config.webhookUrl}"
}
