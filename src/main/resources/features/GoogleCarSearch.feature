Feature: Google Search Lands on Gumtree
  Search Google for cars in specific location
  @wip
  Scenario: Google Car search links to Gumtree search results page
    Given  I am on Google homepage
    When I search for "Cars in London"
    And Gumtree links in search result is greater than 0
    When I click through each Gumtree link
    Then the title is displayed and the number of results is greater than 0
