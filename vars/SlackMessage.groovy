def call(Map config) {
  
  if (!config.containsKey('webhookUrl')) {
    throw new Exception("Missing webhookUrl")
  }

  sh """
  curl -XPOST -s -d 'payload={ \
    \"color\": \"${config.get('color', 'good')}\", \
    \"channel\": \"${config.get('channel', '')}\", \
    \"username\": \"${config.get('username', 'Jenkins')}\", \
    \"text\": \"${config.message.replaceAll("&", "&amp;")}\" \
    }' ${config.webhookUrl}
  """
}
