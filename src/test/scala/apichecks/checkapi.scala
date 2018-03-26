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



}