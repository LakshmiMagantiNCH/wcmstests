Feature: Form

  Scenario Outline: Successful GME application form
    Given user opens "<url>"
    Then user select start application form button
    Then fill required fields with "<start_date>" "<end_date>" "<request_rotation>" "<sponsoring_inst>" "<current_program>"
    When select next screen

    Examples: 
      | url      | start_date | end_date   | request_rotation   | sponsoring_inst  | current_program |
      | gme.form | 04/10/2025 | 04/05/2026 | Allergy Immunology | CLEVELAND CLINIC | DO_PEDIATRICS   |
