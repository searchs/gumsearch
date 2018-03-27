package searches.spec.pagemodel

import org.openqa.selenium.{WebDriver, WebElement}
import org.scalatest.selenium.Page

object GoogleHomepage extends Page{
  override val url: String = "http://www.google.com"


}

class GoogleSearchResultsPage extends Page {
  override val url: String = GoogleHomepage.url

//  def getGoogleSearchResults(): List[WebElement] ={
//    val results: WebElement =Try(driver.findElement(By.id("tab-hotel-tab"))).getOrElse(null)
//    click on hotelTab
//  }


}
