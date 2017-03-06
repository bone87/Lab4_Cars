Feature: Compare Cars
  Scenario: Find, save and compare cars
    Given Go to "http://www.cars.com"
    When Select item "Review a Car" from menu "Buy", find a car at random values at the "5" attempt, click to link "Model details ", select "1" trim, save the car "FIRST" for next comparing
      And Select item "Review a Car" from menu "Buy", find a car at random values at the "5" attempt, click to link "Model details ", select "1" trim, save the car "SECOND" for next comparing
      And Go to the tab "Trims", select first available modification to compare, click button "Compare Now"
      And Click button 'Add Another Car', select "FIRST" car and click button "Done"
      And Check the ComparePage for "FIRST" car from garage and "2" car from the table
      And Check the ComparePage for "SECOND" car from garage and "1" car from the table
    Then Garage cars equals table cars
