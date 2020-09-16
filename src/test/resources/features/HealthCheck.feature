@ui @healthcheck
Feature: E-commerce Project Web Site Health Check

  Scenario: User is able to open the browser, navigate to the URL and search for the product
    Given User opened browser
    And User navigated to the home appilation url
    When User search for product "laptop"
    Then Search result is displayed
    
    @t
  Scenario: User  is click on the product and check the product details
    Given User opened browser
    And User navigated to the home appilation url
    And User search for product "laptop"
    When User click on any product
    Then Product Description is displayed in new tab

 
