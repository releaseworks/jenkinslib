def call(Map config) {
  
  if (!config.containsKey('webhookUrl')) {
    throw new Exception("Missing webhookUrl")
  }

  message = config.message.replaceAll("&", "&amp;")
  message = message.replaceAll("\"", "&quote;")

  sh """
  curl -XPOST -s -d 'payload={ \
    \"color\": \"${config.get('color', 'good')}\", \
    \"channel\": \"${config.get('channel', '')}\", \
    \"username\": \"${config.get('username', 'Jenkins')}\", \
    \"text\": \"${message}\" \
    }' ${config.webhookUrl}
  """
}
