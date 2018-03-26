package apichecks

import org.scalatest.Matchers
import scalaj.http._

case class BotResponse(
                        files: Map[String, String],
                        form: Map[String, String],
                        args: Map[String, String],
                        headers: Map[String, String],
                        cookies: Map[String, String]
                      )

class ApiChecks extends org.scalatest.FunSuite with Matchers {

  test("Service is alive") {
    val serviceUrl = "https://jsonplaceholder.typicode.com/posts/1"

    def get(url: String) = scala.io.Source.fromURL(url).mkString

    val resp = get(serviceUrl)
    assert(!resp.isEmpty(), "Empty response!")

  }

  test("Check basic response status"){

    val request: HttpRequest = Http("https://jsonplaceholder.typicode.com/posts/1").header("content-type", "application/json")
    val response = request.asString


    response.code shouldBe(200)
    response.is2xx shouldBe(true)
    println(response.body.toString())
//Check header content
    for ((k,v) <- response.headers) println(s"key:   $k\nvalue: $v\n")
    response.body should include("\"userId\": 1")

  }

}