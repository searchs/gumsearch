package runner

import cucumber.api.CucumberOptions
import cucumber.api.junit.Cucumber
import org.junit.runner.RunWith

@RunWith(classOf[Cucumber])
@CucumberOptions(
  features = Array("classpath:features"),
  tags = Array("~@Wip"),
  glue = Array("steps"),
  plugin = Array("pretty", "html:target/cucumber/html"))
class RunCucumber
