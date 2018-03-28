package searches.spec.pagemodel

import org.openqa.selenium.{WebDriver, WebElement}
import org.scalatest.{FlatSpec, Matchers}
import org.scalatest.selenium.{Page, WebBrowser}

object GoogleHomepage extends Page{
  override val url: String = "http://www.google.com"


}

class GoogleSearchResultsPage extends FlatSpec with WebBrowser with Matchers {
   val url: String = GoogleHomepage.url

}
