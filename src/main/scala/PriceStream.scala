import scala.io.{BufferedSource, Source}
import org.apache.http._
import org.apache.http.client.methods._
import org.apache.http.impl.client.{CloseableHttpClient, HttpClientBuilder}
import org.apache.http.message.BasicHeader
import org.apache.http.util.EntityUtils

object PriceStream extends App {
  val domain       = "http://stream-sandbox.oanda.com"
  val access_token = "your_token"
  val account_id   = "your_id"
  val instruments  = "USD_JPY,EUR_JPY"

  val httpClient: CloseableHttpClient = HttpClientBuilder.create().build()

  try {
    val uri = s"$domain/v1/prices?accountId=$account_id&instruments=$instruments"
    val httpGet: HttpUriRequest = new HttpGet(uri)
    httpGet.setHeader(new BasicHeader("Authorization", s"Bearer $access_token"))

    println("Executing request: " + httpGet.getRequestLine)

    val resp: HttpResponse = httpClient.execute(httpGet)
    val entity: HttpEntity = resp.getEntity

    resp.getStatusLine.getStatusCode == 200 && entity != null match {
      case true =>
        // print realtime prices
        val bs: BufferedSource = Source.fromInputStream(entity.getContent)
        bs.getLines()
          .filter(!_.contains("heartbeat"))
          .foreach(println)

      case false =>
        // print error message
        val responseString = EntityUtils.toString(entity, "UTF-8")
        println(responseString)
    }
  } finally {
    httpClient.close()
  }
}

