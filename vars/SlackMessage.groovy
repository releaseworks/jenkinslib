def call(Map config) {
  def endpoint = new URL(config.webhookUrl)
  def queryString = "c=${config.channel}"
  sh "curl -s -XPOST -d '${queryString}' ${config.webhookUrl}"
}
