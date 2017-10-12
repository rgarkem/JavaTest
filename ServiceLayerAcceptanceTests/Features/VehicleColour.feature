@tag
Feature: Validate Vehicle Colours from DVLA Site
	I want to validate that the DVLA site returns correct Vehicle colours

@tag1
Scenario: Title of your scenario
Given I have CSV or XLSX files in target Directory 
	And I extract vehicle details from the files 
When I Navigate to the website "https://www.gov.uk/get-vehicle-information-from-dvla"
	And I check vehicle colours for each vehicle in file
Then I validate that colours match the file for each vehicle
