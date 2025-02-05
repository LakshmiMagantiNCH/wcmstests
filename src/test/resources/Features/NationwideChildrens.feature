Feature: NationwideChildrens

  @positive @firefox
  Scenario Outline: Succefull scenario of finding a doctor
    Given the user hovers over the "<url>" link on main page
    Then navigate to "find_a_doc" page
    Then verify landed on the right "<label>" with "<text>"
    Then user "search_field" with "<value>"
    Then perform the "search"
    Then verify landed on the right "success" with "<value>"
    Examples: 
      | url | label              | text           | value         | 
      | nch | find_a_doc_label   | Find a Doctor  | Erin L. Gross |

  @negative
  Scenario Outline: Failure scenario of finding a doctor
    Given the user hovers over the "<url>" link on main page
    Then navigate to "<tab>" page
     Then verify landed on the right "find_a_doc_label" with "Find a Doctor"
    Then user "search_field" with "<value>"
    Then perform the "search"
    Then verify landed on the right "not_found_page" with "<text>"
    Examples: 
      | url | tab          | value     | text |
      | nch | find_a_doc   | notfound  | There are no matching results for your search. |

      
  @test
  Scenario Outline: finding a location with zipcode
    Given the user hovers over the "<url>" link on main page
    Then navigate to "location" page
    Then verify landed on the right "<label>" with "<text>"
    Then user "zip_code_text_field" with "<value>"
    Then perform the "go_button"
    Then verify landed on the right "locations_page" with "There are"
    Examples: 
      | url | label                | text           | value      | 
      | nch | location_page_label  | Find a Location | 43016     |
      