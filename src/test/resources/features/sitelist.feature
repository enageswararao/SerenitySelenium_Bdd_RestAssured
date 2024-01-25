Feature: Met Office DataPoint API  Testing
    @sc1
  Scenario: Verify parameter ‘S’ value of Location using SiteList API Responses LocationId
    Given a user makes a GET request to retrieve sitelist details
    Then the response status code should  200
    When the location id  is extract from response for Location Name "Tulloch Bridge"
    When a user makes a GET request to retrieve locations details
    Then the response status code should  200
    Then verify the response parameter ‘S’ has a descr|iption of "Wind Speed"  for this location "Tulloch Bridge"


Feature: Met Office DataPoint API  Testing
  @sc1
  Scenario Outline: : Verify parameter ‘S’ value of Location using SiteList API Responses LocationId
    Given a user makes a GET request to retrieve sitelist details
    Then the response status code should  200
    When the location id  is extract from response for Location Name "<Location>"
    When a user makes a GET request to retrieve locations details
    Then the response status code should  200
    Then verify the response parameter ‘S’ has a description of "<sparam>"  for this location "<Location>"
    Examples:
    |Location           |sparam         |
    |Tulloch Bridge     |Wind Speed     |
    |CROYDON            |Wind Speed     |