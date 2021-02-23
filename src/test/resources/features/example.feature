Feature: Wikipedia I want to open main page and search for hello world

  Scenario: Search for hello world - 1
  Given I have opened main page
  When I search for "hello world"
  Then Heading for page will be ""Hello, World!" program"

  Scenario: Search for hello world - 2
    Given I have opened main page
    When I search for "hello world"
    Then Heading for page will be ""Hello, World!" program"
