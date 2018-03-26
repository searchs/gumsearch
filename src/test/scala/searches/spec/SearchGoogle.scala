package searches.spec

import scala.collection.mutable.ListBuffer
import org.openqa.selenium.{By, TimeoutException, WebDriver, WebElement}
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.selenium.WebBrowser
import org.scalatest.{FlatSpec, Matchers}

class SearchGoogle extends FlatSpec with Matchers with WebBrowser {
  implicit val webDriver: WebDriver = new FirefoxDriver()
  val host = "https://www.google.com"

  def checkCarsCountOnGumtreePage(implicit driver: WebDriver): String = {
    try {
      val carCount = driver.findElement(By.tagName("h1")).findElement(By.className("h1-responsive"))
      val carCountText = carCount.getText
      val wordsList = carCountText.split(" ")
      wordsList(0)
    } catch {
      case e: Exception => e.getMessage
    }

  }


  go to host
  textField("q").value = "Cars in London"
  submit()

  try {
    val wait = new WebDriverWait(webDriver, 5)
    wait.until(ExpectedConditions.elementToBeClickable(By.className("r")))
  } catch {
    case e: TimeoutException => e
    case b: Exception => b
    case n: NullPointerException => n
  }

  pageTitle contains ("Cars in London")
  textField("q").value shouldBe ("Cars in London")

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
      pageTitle contains ("Gumtree")
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

}
