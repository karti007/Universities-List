Feature: Test the functionality of List Universities by Country API

@smoke @regression
Scenario Outline: Verify if the list of Universities are returned based on Country
  Given User initiates call to "List Universities"
   When User makes the GET call for "<Country>"
   Then API call is success with status code 200
    And Assert "[0].country" to be equal to "<Country>"
    And Assert "[0].alpha_two_code" to be equal to "<Two Byte Code>"
    And Search if University name "<University>" is present in the response
        
Examples:
|Country      |Two Byte Code|University              |
|United States|US           |Harvard University      |
|Canada       |CA           |University of Toronto   |