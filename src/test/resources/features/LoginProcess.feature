@Login
Feature: Login process of a user in the SUT

  Scenario: Successful login
    Given the user wants to login
    When the user enters the credentials
    Then the user should be able to login

  Scenario: Failed login
    Given the user wants to login
    When the user submits invalid credentials
    Then the user should see a red error message
    And the user should see two more error messages