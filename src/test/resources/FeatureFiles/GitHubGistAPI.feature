Feature: GitHub gists API
	@ApiTest
  Scenario: User is able to create , fetch and delete the gist
    Given User is authorized
    When User creates a gist
    And Verify that gist is created
    When User gets the created gist
    And Validate the gist with all the documents
    When User deletes the gist
    Then Validate the gist is deleted