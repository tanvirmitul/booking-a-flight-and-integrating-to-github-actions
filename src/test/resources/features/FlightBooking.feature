Feature: Login feature
  @bookingFlight
  Scenario: User will successfully book a flight and compare prices
    Given user navigate to url
    When user book a flight from Chittagong to Dhaka
    And user click on search button
    And user filter flight by clicking on us bangla airlines
    And user select book ticket of last flight
    Then validate modal appear and match total price with modal price
    When user click on continue button
    Then compare us bangla airlines and novoair prices



