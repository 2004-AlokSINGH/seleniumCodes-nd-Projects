@LoginValidate
Feature: Login validation
  Scenario: Login with username and password and validate it
    Given go to the webpage
		Then I enter the username and password
    Then I click login button
   

