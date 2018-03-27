package steps

import cucumber.api.scala.{EN, ScalaDsl}
import org.scalatest.Matchers
import org.scalatest.selenium.WebBrowser


trait BaseDefs extends  ScalaDsl with WebBrowser with EN with Matchers {

}
