package searches.spec

import scala.collection.mutable.ListBuffer
import org.openqa.selenium.{By, TimeoutException, WebDriver, WebElement}
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.selenium.WebBrowser
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.concurrent.Eventually._
import org.scalatest.time.{Seconds, Span}
import searches.spec.pagemodel.{GoogleHomepage, GoogleSearchResultsPage}

import scala.util.Try

class SearchGoogle extends FlatSpec with Matchers with WebBrowser {

  implicit val webDriver: WebDriver = new FirefoxDriver()
  implicitlyWait(Span(3, Seconds))
  //  val host = "http://www.google.com"
  val googleSearchResultsPage = new GoogleSearchResultsPage()
  go to GoogleHomepage.url
  textField("q").value = "Cars in London"
  submit()

  eventually {
    pageTitle contains "Cars in London"
    textField("q").value shouldBe "Cars in London"
  }

  val results = findAll(className("r")).toList
  var justGumtree = new ListBuffer[String]()
  assert(results.nonEmpty, "No result returned for search!")

  try {
    for (result <- results) {
      if (result.underlying.findElement(By.tagName("a")).getText.contains("Gumtree") || result.underlying.findElement(By.tagName("a")).getAttribute("href").contains("gumtree")) {
        justGumtree += result.underlying.findElement(By.tagName("a")).getAttribute("href")
      }
    }

    assert(justGumtree.nonEmpty, "No result with Gumtree Links!")

    for (car <- justGumtree) {
      go to car
      pageTitle contains "Gumtree"
      checkCarsCountOnGumtreePage(webDriver)
    }
  } catch {
    case e: TimeoutException => e
    case b: Exception => b.getMessage
    case n: NullPointerException => n.getMessage
  }
  finally {
    quit()
  }

  def checkCarsCountOnGumtreePage(implicit driver: WebDriver): String = {
    try {

      val ele: Option[Element] = find(className("h1-responsive"))(driver)
      println(ele.get.text)
      val carCountText = ele.get.text
      val wordsList = carCountText.split(" ").map(_.trim)
      println(wordsList.toString)
      wordsList(0)
    } catch {
      case e: Exception => e.getMessage
    }

  }


}
