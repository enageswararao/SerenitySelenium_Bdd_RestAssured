Feature: SiteListAPI Testing
    @sc1
  Scenario: Verify sitelistAPI Response
    Given a user makes a GET request to retrieve sitelist details
    Then the response status code should  200
    When the location id  is extract from response for Location Name "Tulloch Bridge"
    When a user makes a GET request to retrieve locations details
    Then the response status code should  200
    Then verify the response parameter ‘S’ has a description of "Wind Speed"  for this location "CROYDON"
