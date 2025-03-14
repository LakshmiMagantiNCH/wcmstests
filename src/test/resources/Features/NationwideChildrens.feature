Feature: NationwideChildrens

  @positive
  Scenario Outline: Succefull scenario of finding a doctor
    Given the user hovers over the "<url>" link on main page
    Then navigate to "find_a_doc" page
    Then verify landed on the right "<label>" with "<text>"
    Then user "search_field" with "<value>"
    Then perform the "search"
    Then verify landed on the right "success" with "<value>"

    Examples: 
      | url | label            | text          | value         |
      | nch | find_a_doc_label | Find a Doctor | Erin L. Gross |

  @negative
  Scenario Outline: Failure scenario of finding a doctor
    Given the user hovers over the "<url>" link on main page
    Then navigate to "<tab>" page
    Then verify landed on the right "find_a_doc_label" with "Find a Doctor"
    Then user "search_field" with "<value>"
    Then perform the "search"
    Then verify landed on the right "not_found_page" with "<text>"

    Examples: 
      | url | tab        | value    | text                                           |
      | nch | find_a_doc | notfound | There are no matching results for your search. |

  @positive
  Scenario Outline: finding a location with zipcode
    Given the user hovers over the "<url>" link on main page
    Then navigate to "location" page
    Then verify landed on the right "<label>" with "<text>"
    Then user "zip_code_text_field" with "<value>"
    Then perform the "go_button"
    Then verify landed on the right "locations_page" with "There are"

    Examples: 
      | url | label               | text            | value |
      | nch | location_page_label | Find a Location | 43016 |

  @positive
  Scenario Outline: finding a location with location name
    Given the user hovers over the "<url>" link on main page
    Then navigate to "location" page
    Then verify landed on the right "<label>" with "<text>"
    Then user "location_name" with "<value>"
    Then perform the "go_button"
    Then verify landed on the right "locations_page" with "There are"

    Examples: 
      | url | label               | text            | value    |
      | nch | location_page_label | Find a Location | columbus |

  #Currently using uat link to test this, so captcha is appearing which is blocking to run the full test
  @test1
  Scenario Outline: requesting an appointment
    Given the user hovers over the "<url>" link on main page
    Then navigate to "request_appointment" page
    Then verify landed on the right "<label>" with "<text>"
    Then navigate to "parent_or_guardian" page
    Then navigate to "new_patient" page
    Then navigate to "primary_care" page
    Then perform the "new_patient_radio_button"
    Then perform the "continue_button"
    Then fill patient details form
    Then fill patient contact info form
    Then fill patient parent or guardian form
    Then user "reason_for_appt" with "TEST"
    Then perform the "appointment_next"
    Then fill patient insurance details form
    #Then verify landed on the right "appoint_conf_page" with "Thank you for your appointment request"
    Examples: 
      | url | label                  | text                   | value    |
      | nch | request_app_page_title | Request an Appointment | columbus |

  @positive @test
  Scenario Outline: finding info using search option
    Given the user hovers over the "<url>" link on main page
    Then user "search_bar" with "<value>"
    Then perform the "search_buttn"
    Then verify landed on the right "search_header" with "<text>"

    Examples: 
      | url | text           | value         |
      | nch | Search Results | Patrick Gross |
      | nch | Search Results | adhd          |
