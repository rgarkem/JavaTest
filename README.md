# JavaTest

This is the solution for a Java coding test. The test details are as below 

## Test details
IDENTITY E2E – Test 4 - Java Exercise

Part 1:

Write a Service layer bean to do the following:

Scan configured directory in file system which will return this information --> filename, file mime type, file size, file extension

Use a directory containing a reasonably large number of files, minimum 10.

Provide a way to retrieve certain supported mime type files: configure excel and csv are supported currently

Part 2:

Write a selenium/cucumber framework to do the following:

Use the above service layer bean to get supported files (excel or csv are supported, from input directory)

Go through the file and read vehicle registration details in the file.

Open webpage : https://www.gov.uk/get-vehicle-information-from-dvla and go through all vehicles from excel/csv file.

On the Vehicle details page assert the details (Make/Color) match with expected output in excel/csv file.




Key Requirements: 

Write appropriate Junit tests
Use of design patterns where appropriate.
Contain clear and precise logging/comments. 
Provide screenshot of output when you run Part 2 test locally.
Update all code / documentation to your GitHub account


## Solution details 

The preopistory has 2 projects 
1. SUT: This contains the Service layer which can retrieve the files from directory 
2. ServiceLayerAcceptanceTests : Cucumber test project to use the SUT to extract the files, and then use the data in files to search details on DVLA website

### SUT
This project contains the following artefacts. 
1. DirectoryServiceLayer.java: This is the service layer bean for accessing the details of files from a target directory. It relies on its DirectoryDAO to perform the actual extraction 
2. DirectoryDAO.java : This is an interface for the DAO class. 
3. DirectoryDAOImpl.java: The concrete implementation of the DAO class. THis has the actual extraction logic. The purpose of having both an interface and concrete implementation, is to enable unit testing 
4. FileInformation.java: Model for the information about each file in the target directory. It includes the files name, size, mimetype, extension and the full path 
5. VehicleInformation.java: Model for the Vehicle information. This encapsulates information like vehicle reg no and colour, which are the only pieces of information relevant for this exercise 
6. Runner.java: A simple console app for checking the extraction logic. It was built for testing, and I have left it in the solution as it is a useful debugging tool
7. config.properties: this is the config file which can be used to point the service layer to a target directory. It can also be used to modify the allowed file types. currently this value (in filesFilter param) is csv,xlsx. Change to csv to only support CSV. Change to * to support all file types
8. DirectoryServiceLayerTests.java: These are unit tests using Mockito to mock the DirectoryDAO interface, and stub away the actual file system interaction. 
9. Dependencies: folder with all external jars

### ServiceLayerAcceptanceTests
This is the required cucumber test, using selenium to validate the contents of the file against the DVLA website. This has the following artefacts 
1. TestRunner.java: It is the main cucumber test runner, and links the feature file with the step definitions
2. BasePage.java: Abstract generic functionality used by all page objects
3. LandingPage.java: Page object for the first landing page
4. SearchPage.java: Page Object for the search page, with input field 
5. SearchResultsPage: PAge Object for the search results page, showing vehicle details
6. VehicleColour.feature: This is the Gherkin file containing the test scenario 
7. VehicleColoursStepDefinitions.java: THis is the implementation of the test steps. It will interact with the DirectoryServiceLayer and the Page objects to run the scenario. 
8. FileUtil.java: This is a util class for extracting the vehicle info from xlsx and CSV files 
9. Dependencies: contains all external JARs 
10. TestResults: This folder will have the html output of the latest run (index.html) 

In addition, I have also included a TestData folder, which the config.properties is pointed to. it contains a sample excel and a sample CSV file. 




