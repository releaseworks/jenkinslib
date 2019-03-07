package org.releaseworks

def webhookUrl = 'https://release.works'

def message(channel, text) {
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
