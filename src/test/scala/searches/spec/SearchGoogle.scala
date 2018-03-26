package searches.spec

import java.util

import scala.collection.mutable.ListBuffer
import org.openqa.selenium.{By, TimeoutException, WebDriver, WebElement}
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.support.ui.{ExpectedConditions, WebDriverWait}
import org.scalatest.selenium.WebBrowser
import org.scalatest.selenium.Page
import org.scalatest.time.{Seconds, Span}
import org.scalatest.{FlatSpec, Matchers}

class SearchGoogle extends FlatSpec with Matchers with WebBrowser {
  implicit val webDriver: WebDriver = new FirefoxDriver()

  go to "http://www.google.com"
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

  val results = findAll(tagName("h3")).toSeq
  var justGumtree = new ListBuffer[String]()
  assert(results.nonEmpty, "No result returned for search!")

  try {
    for (result <- results) {
      if (result.underlying.findElement(By.tagName("a")).getText.contains("Gumtree") || result.underlying.findElement(By.tagName("a")).getAttribute("href").contains("gumtree")) {
        justGumtree += result.underlying.findElement(By.tagName("a")).getAttribute("href")
      }
    }

    assert(justGumtree.nonEmpty, "No result with Gumtree Links!")

    //  3.	Navigate to each Gumtree link and confirm the title is displayed and the number of the results is greater than 0.
    def checkCarsCountOnGumtreePage(){}

    for (car <- justGumtree) {
      go to car
      Thread.sleep(2000)
      pageTitle contains ("Gumtree")
      println(currentUrl)
      goBack()
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
