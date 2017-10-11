package stepDefinitions;

import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;

import SUT.DirectoryDAOImpl;
import SUT.DirectoryServiceLayer;
import SUT.FileInformation;
import SUT.VehicleInformation;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.*;

public class VehicleColoursStepDefinitions {
	
	private DirectoryServiceLayer directoryServiceLayer;
	private FileInformation fileInformation;
	private List<VehicleInformation> vehiclesListFromFile;
	private List<VehicleInformation> vehiclesListFromSite;
	private LandingPage landingPage;
	private SearchPage searchPage;
	private SearchResultsPage searchResultsPage;
	
	
	@Given("^I have a CSV file in target Directory$")
	public void i_have_a_CSV_file_in_target_Directory() throws Throwable {
		directoryServiceLayer = new DirectoryServiceLayer(new DirectoryDAOImpl());
		List<FileInformation> fileList = directoryServiceLayer.GetFilesInDirectory();
		List<FileInformation> filteredList = fileList.stream().filter(
				f -> f.getExtension().toLowerCase().equals("csv"))
				.collect(Collectors.toList());
		Assert.assertFalse(filteredList.isEmpty());
		fileInformation = filteredList.get(0);
	}

	@Given("^I extract vehicle details from the file$")
	public void i_extract_vehicle_details_from_the_file() throws Throwable {
		vehiclesListFromFile = new ArrayList<VehicleInformation>();
		
		Path path = Paths.get(fileInformation.getFilePath());
		try (BufferedReader br = Files.newBufferedReader(path)) {
			String line;
			boolean titleLine = true;
			while ((line = br.readLine()) != null) {
				if (titleLine) {
					titleLine = false;
					continue;
				}
				String[] lineData = line.split(",");
				vehiclesListFromFile.add(new VehicleInformation() {{
					setRegNo(lineData[0]);
					setColour(lineData[1]);
				}});
			}
		}
	}

	@When("^I Navigate to the website \"([^\"]*)\"$")
	public void i_Navigate_to_the_website(String arg1) throws Throwable {
	    landingPage = new LandingPage(Browser.getDriver());
	    landingPage.launchPage();
	    Assert.assertEquals(arg1, Browser.getDriver().getCurrentUrl());
	}

	@When("^I check vehicle colours for each vehicle in file$")
	public void i_check_vehicle_colours_for_each_vehicle_in_file() throws Throwable {
	    landingPage.startSearch();
	    searchPage = new SearchPage(Browser.getDriver());
	    searchResultsPage = new SearchResultsPage(Browser.getDriver());
	    vehiclesListFromSite = new ArrayList<VehicleInformation>();		
	    
	    for (VehicleInformation info : vehiclesListFromFile) {
	    	searchPage.waitForPageLoad();
		    searchPage.searchVehicle(info.getRegNo());
		    searchResultsPage.waitForPageLoad();
		    vehiclesListFromSite.add(new VehicleInformation() {{
		    	setRegNo(info.getRegNo());
		    	setColour(searchResultsPage.getColour());
		    }});
		    searchResultsPage.backToSearchPage();
	    }	    
	}

	@Then("^I validate that colours match the file for each vehicle$")
	public void i_validate_that_colours_match_the_file_for_each_vehicle() throws Throwable {
	    for (VehicleInformation info : vehiclesListFromFile) {
	    	VehicleInformation vehicleFromSite = vehiclesListFromSite.stream().filter(
	    			v ->v.getRegNo().equals(info.getRegNo()))
	    			.collect(Collectors.toList()).get(0);
	    	Assert.assertEquals(info.getColour().toLowerCase(), vehicleFromSite.getColour().toLowerCase());
	    }
	}

}
