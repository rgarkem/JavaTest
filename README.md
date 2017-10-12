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

