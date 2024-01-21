@UI
Feature: Visa checks

  Background:
    Given I am on the Check UK visa website
    And I start visa check

  Scenario: An Australian coming to the UK for Tourism.
    When I select a nationality of 'Australia'
    And I select reason 'Tourism'
    Then I will be informed 'You will not need a visa to come to the UK'

  Scenario: A Chilean coming to the UK for Work and plans on staying for longer than 6 months.
    When I select a nationality of 'Chile'
    And I select reason 'Work'
    And I state I am intending to stay for 'more' than 6 months
    And I select reason 'Health'
    Then I will be informed 'You need a visa to work in health and care'

  Scenario: A Azerbaijan national coming to the UK to join a partner, who is a British citizen.
    When I select a nationality of 'Azerbaijan'
    And I select reason 'Family'
    And I select reason 'Yes'
    Then I will be informed 'You’ll need a visa to join your family or partner in the UK'