@regression
Feature: Home page

  Scenario: Verify the homepage url and title
    Given The user navigates to the homepage
    Then The current URL should be "https://higo.id/"
    And The page title should be "HIGO | Solusi Menyeluruh Pemasaran Digital dengan WiFi & Customer Insight"

  Scenario: Verify the logo is displayed
    Given The user navigates to the homepage
    Then The logo should be visible

  Scenario: Verify the navigation bar is displayed
    Given The user navigates to the homepage
    Then The navigation bar should be visible
    And It should contain correct menus:
      | Tentang HIGO    |
      | Layanan HIGO    |
      | Studi Kasus     |
      | Blog            |
      | Digital Reports |
      | Hubungi HIGO    |

  Scenario: Verify the header text is correct
    Given The user navigates to the homepage
    Then The main heading should display "Solusi Menyeluruh Pemasaran Digital dengan WiFi & Customer Insight"

  Scenario Outline: Open the page through web banner
    Given The user navigates to the homepage
    When The user clicks the "<web-banner>"
    Then The current URL should be "<page-url>"
    And The page title should be "<page-title>"
    Examples:
      | web-banner                | page-url                                  | page-title                        |
      | WiFi Advertising          | https://higo.id/wifi-advertising          | HIGO \| WiFi Advertising          |
      | HIGOspot                  | https://higo.id/higospot                  | HIGO \| HIGOspot                  |
      | Integrated Digital Agency | https://higo.id/integrated-digital-agency | HIGO \| Integrated Digital Agency |

  Scenario Outline: Open the page through link text in the footer
    Given The user navigates to the homepage
    When The user opens link text "<footer-link-text>"
    Then The current URL should be "<page-url>"
    And The page title should be "<page-title>"
    Examples:
      | footer-link-text          | page-url                                                 | page-title                                                                 |
      | WiFi Advertising          | https://higo.id/wifi-advertising                         | HIGO \| WiFi Advertising                                                   |
      | HIGOspot                  | https://higo.id/higospot                                 | HIGO \| HIGOspot                                                           |
      | Integrated Digital Agency | https://higo.id/integrated-digital-agency                | HIGO \| Integrated Digital Agency                                          |
      | Tentang HIGO              | https://higo.id/about-us                                 | HIGO \| Tentang HIGO                                                       |
      | Karir                     | https://www.linkedin.com/company/pt-higo-fitur-indonesia | HIGO \| LinkedIn                                                           |
      | Terms & Conditions        | https://higo.id/terms-of-use-higospot                    | HIGO \| Solusi Menyeluruh Pemasaran Digital dengan WiFi & Customer Insight |
      | Privacy & Policy          | https://higo.id/privacy-policy-higospot                  | HIGO \| Solusi Menyeluruh Pemasaran Digital dengan WiFi & Customer Insight |

  Scenario Outline: Open the page through link text in the footer
    Given The user navigates to the homepage
    When The user clicks icons "<footer-icon>" in the footer
    Then The new tab is opened
    And The current URL should be "<page-url>"
    And The page title should be "<page-title>"
    Examples:
      | footer-icon | page-url                                                 | page-title                                              |
      | Linkedin    | https://www.linkedin.com/company/pt-higo-fitur-indonesia | HIGO \| LinkedIn                                |
      | Facebook    | https://www.facebook.com/HIGOSPOTINDONESIA               | HIGOspot \| Facebook                                    |
      | Instagram   | https://www.instagram.com/higo.id/                       | HIGO Indonesia (@higo.id) • Instagram photos and videos |
      | X           | https://x.com/HIGO_spot?mx=2                             | Log in to X / X                                         |
