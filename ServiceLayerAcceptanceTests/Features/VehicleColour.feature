@tag
Feature: Validate Vehicle Colours from DVLA Site
	I want to validate that the DVLA site returns correct Vehicle colours

@tag1
Scenario: Title of your scenario
Given I have a CSV file in target Directory 
	And I extract vehicle details from the file 
When I Navigate to the website "https://www.gov.uk/get-vehicle-information-from-dvla"
	And I check vehicle colours for each vehicle in file
Then I validate that colours match the file for each vehicle
