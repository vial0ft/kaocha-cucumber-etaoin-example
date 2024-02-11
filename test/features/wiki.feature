Feature: Use etaoin for visit wikipedia

  I may find a page about clojure in wikipedia

  Background:
    Given Browser firefox is showed
    And Start with page 'https://www.google.com'
      
  Scenario: Go to wikipedia and find "Clojure" page
    When I search 'wikipedia main page'
    And I click to 'Wikipedia, the free encyclopedia'
    And I search 'Clojure' topic
    Then I should see text 'Hickey spent about two and a half years working on Clojure before releasing it publicly in October 2007'
    Then I close browser