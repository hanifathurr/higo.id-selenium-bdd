@regression
Feature: Blog page

Background:
  Given The user navigates to the Blog page

  Scenario: Verify the Blog url and title
    Then The current URL should be "https://blog.higo.id/"
    And The page title should be "Artikel Seputar Teknologi, Digital Marketing, dan Tips Bisnis"

  Scenario: Verify every article title is correct when the article opened
    When The user opens every article
    Then The article title same as the page

  Scenario: Verify the search functionality with valid input
    When The user enters "WiFi Advertising" in the search bar
    Then The search results should display articles related to "WiFi Advertising"

  Scenario: Verify the search functionality with invalid input
    When The user enters "@#$%^&*" in the search bar
    Then The search results should display a message Artikel yang terkait dengan "@#$%^&*" tidak ditemukan

  Scenario: Verify the search functionality with empty input
    Then The search icon should be disabled

  Scenario: Verify the ability to read an article
    When The user clicks on the first featured article
    Then The article should have a title, content, and a list of categories/tags

