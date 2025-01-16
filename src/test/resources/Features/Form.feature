Feature: Form

  @positive @firefox
  Scenario Outline: Successful GME application form
    Given user opens "<url>"
    When user select start application form button
    Then fill required fields on rotation request form
    Then select next screen
    Then fill applicant form with required fields with "<ssn>"
    Then select next screen on applicant form
    Then fill emergency contact info
    Then select next on emergency contact page
    When we fill med school info
    Then select medschl next screen
    Then fill pgy info
    Then select next screen on pgy
    Then accept all the info is accurate
    Then application submitted successfully
    Examples: 
      | url      |  ssn         |
      | gme.form |  111-22-2345 |

  @negative @firefox
  Scenario: Missing Required fields on GME application form
    Given user opens "gme.form"
    When user select start application form button
    Then fill required fields with empty value
    #Then verify expected error messages displayed on page
