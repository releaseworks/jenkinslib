def call(channel, text) {
  def webhookUrl = 'https://release.works'

  def endpoint = new URL(webhookUrl)
  def queryString = 'v=test'
  def connection = endpoint.openConnection()
  connection.with {
    doOutput = true
    requestMethod = 'POST'
    outputStream.withWriter { writer ->
      writer << queryString
    }
    println content.text
  }
}
