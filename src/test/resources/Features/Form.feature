Feature: Form

  Scenario Outline: Successful GME application form
    Given user opens "<url>"
    When user select start application form button
    Then fill required fields with "<request_rotation>" "<sponsoring_inst>" "<current_program>"
    Then select next screen
    Then fill applicant form with required fields with "<email>" "<pgy_rotation>" "<ssn>"
    Then select next screen on applicant form
    Then fill emergency contact info
    Then select next on emergency contact page
    When we fill med school info
    Then select medschl next screen
    Then fill pgy info
    Then select next screen on pgy
    Then accept all the info is accurate

    Examples: 
      | url      | request_rotation   | sponsoring_inst  | current_program | email          | pgy_rotation | ssn         |
      | gme.form | Allergy Immunology | CLEVELAND CLINIC | DO_PEDIATRICS   | test@gmail.com |  1           | 111-22-2345 |
