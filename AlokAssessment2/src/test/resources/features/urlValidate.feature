@urlValidatebyJSExecuter
Feature: Validate Urls
 
  Scenario: Validating urls
    Given go to the webpage 
    And  store urls from page
    Then validate the status code
   