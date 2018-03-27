package steps

import cucumber.api.scala.{EN, ScalaDsl}
import org.openqa.selenium.{By, TimeoutException, WebDriver}
import org.openqa.selenium.firefox.FirefoxDriver
import org.scalatest.FlatSpec
import org.scalatest.time.{Seconds, Span}
import org.slf4j.LoggerFactory
import pageModels.GoogleHome
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.Eventually._
import org.scalatest.selenium.WebBrowser
import org.scalatest.time.{Seconds, Span}

import scala.collection.mutable.ListBuffer

class GoogleSearchStepDefs extends ScalaDsl with EN with WebBrowser with Matchers {
  private val log = LoggerFactory.getLogger(classOf[GoogleSearchStepDefs])

  val googleHome = new GoogleHome
  implicit val webDriver: WebDriver = new FirefoxDriver()
  implicitlyWait(Span(3, Seconds))
  var justGumtree = new ListBuffer[String]()

  Given("""^I am on Google homepage$""") { () =>
    go to "http://google.com"
  }

  When("""^I search for "([^"]*)"$""") { (searchTerm: String) =>
    textField("q").value = searchTerm
    submit()
  }

  When("""^Gumtree links in search result is greater than (\d+)$""") { (gumtreeCount: Int) =>
    eventually {
      pageTitle contains "Cars in London"
    }
    val results = findAll(className("r"))
    assert(results.nonEmpty, "No result returned for search!")

  }

  When("""^I click through each Gumtree link$""") { () =>
    val results = findAll(className("r")).toList

    try {
      for (result <- results) {
        if (result.underlying.findElement(By.tagName("a")).getText.contains("Gumtree") || result.underlying.findElement(By.tagName("a")).getAttribute("href").contains("gumtree")) {
          justGumtree += result.underlying.findElement(By.tagName("a")).getAttribute("href")
        }
      }
      assert(justGumtree.nonEmpty, "No result with Gumtree Links!")
    }
  }

    Then("""^the title is displayed and the number of results is greater than (\d+)$"""){ (carCountOnPage:Int) =>
      try {
        for (car <- justGumtree) {
          go to car
          pageTitle contains "Gumtree"
          assert(checkCarsCountOnGumtreePage(webDriver).toInt > carCountOnPage, "No car listed on GumTree!")
        }
      } catch {
        case e: TimeoutException => e
        case b: Exception => b.getMessage
        case n: NullPointerException => n.getMessage
      } finally  {quit()}

    }


  def checkCarsCountOnGumtreePage(implicit driver: WebDriver): String = {
    try {

      val ele: Option[Element] = find(className("h1-responsive"))(driver)
      val carCountText = ele.get.text
      val wordsList = carCountText.split(" ").map(_.trim)
      wordsList(0)
    } catch {
      case e: Exception => e.getMessage
    }

  }

}
