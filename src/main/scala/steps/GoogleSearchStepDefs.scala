package steps

import cucumber.api.scala.{EN, ScalaDsl}

class GoogleSearchStepDefs extends ScalaDsl with EN {

  Given("""^I am on Google homepage$"""){ () =>
    println("Ready to write the real test")

  }
  When("""^I search for "([^"]*)"$"""){ (arg0:String) =>
    println("Pass")
  }
  When("""^Gumtree links in search result is greater than (\d+)$"""){ (arg0:Int) =>
    println("Pass")
  }
  When("""^I click through each Gumtree link$"""){ () =>
    println("Pass")
  }
  Then("""^the title is displayed$"""){ () =>
    println("Pass")
  }
  Then("""^the number of results is greater than (\d+)$"""){ (arg0:Int) =>
println("Pass")
  }


}
