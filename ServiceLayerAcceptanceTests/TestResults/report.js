$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("VehicleColour.feature");
formatter.feature({
  "line": 2,
  "name": "Validate Vehicle Colours from DVLA Site",
  "description": "I want to validate that the DVLA site returns correct Vehicle colours",
  "id": "validate-vehicle-colours-from-dvla-site",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@tag"
    }
  ]
});
formatter.before({
  "duration": 2740436310,
  "status": "passed"
});
formatter.scenario({
  "line": 6,
  "name": "Title of your scenario",
  "description": "",
  "id": "validate-vehicle-colours-from-dvla-site;title-of-your-scenario",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 5,
      "name": "@tag1"
    }
  ]
});
formatter.step({
  "line": 7,
  "name": "I have CSV or XLSX files in target Directory",
  "keyword": "Given "
});
formatter.step({
  "line": 8,
  "name": "I extract vehicle details from the files",
  "keyword": "And "
});
formatter.step({
  "line": 9,
  "name": "I Navigate to the website \"https://www.gov.uk/get-vehicle-information-from-dvla\"",
  "keyword": "When "
});
formatter.step({
  "line": 10,
  "name": "I check vehicle colours for each vehicle in file",
  "keyword": "And "
});
formatter.step({
  "line": 11,
  "name": "I validate that colours match the file for each vehicle",
  "keyword": "Then "
});
formatter.match({
  "location": "VehicleColoursStepDefinitions.i_have_CSV_or_XLSX_files_in_target_Directory()"
});
formatter.result({
  "duration": 101012119,
  "status": "passed"
});
formatter.match({
  "location": "VehicleColoursStepDefinitions.i_extract_vehicle_details_from_the_files()"
});
formatter.result({
  "duration": 778262365,
  "status": "passed"
});
formatter.match({
  "arguments": [
    {
      "val": "https://www.gov.uk/get-vehicle-information-from-dvla",
      "offset": 27
    }
  ],
  "location": "VehicleColoursStepDefinitions.i_Navigate_to_the_website(String)"
});
formatter.result({
  "duration": 2455974082,
  "status": "passed"
});
formatter.match({
  "location": "VehicleColoursStepDefinitions.i_check_vehicle_colours_for_each_vehicle_in_file()"
});
formatter.result({
  "duration": 4094229791,
  "status": "passed"
});
formatter.match({
  "location": "VehicleColoursStepDefinitions.i_validate_that_colours_match_the_file_for_each_vehicle()"
});
formatter.result({
  "duration": 1297963,
  "status": "passed"
});
formatter.after({
  "duration": 691444935,
  "status": "passed"
});
});