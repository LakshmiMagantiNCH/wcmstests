Feature: Form

  Scenario Outline: Successful GME application form
    Given user opens "<url>"
    Then user select start application form button
    Then fill required fields with "<request_rotation>" "<sponsoring_inst>" "<current_program>"
    When select next screen

    Examples: 
      | url      | request_rotation   | sponsoring_inst  | current_program |
      | gme.form | Allergy Immunology | CLEVELAND CLINIC | DO_PEDIATRICS   |
