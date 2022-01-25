Feature: Front End Test	
	@FrontEndTest
  Scenario: Validate card contains most expensive item
  	Given User is at "https://www.etsy.com"
  	When Search for "Sketchbook"
  	And Sort results by price in ascending
  	Then Validate Results are sorted
  	When Most expensive item is added to cart
  	Then Validate expected item