Feature: Login Functionality
  In order to do internet banking
  As a valid Para Bank customer
  I want to login successfully

  Scenario: Login Successful
    Given I am in the login page of the Para Bank Application
    When I enter valid credentials
    Then I should be taken to the Overview page

  Scenario Outline: Login UnSuccessful InValid Password
    Given I am in the login page
    When I enter valid <username> and Invalid <password> with <userFullName>
    Then I should be taken to the Overview page1
    Examples:
      | username    | password    | userFullName|
      | "tautester1" | "password" | "test test"|

  Scenario: Login Un Successful Invalid User Name
    Given I am in the login page of the Para Bank Application
    When I enter invalid credentials1
    |tautester|password1|
    Then I should be taken to the Overview page


