package stepDefinitions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Assert;

import SUT.DirectoryDAOImpl;
import SUT.DirectoryServiceLayer;
import SUT.FileInformation;
import SUT.VehicleInformation;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageObjects.*;

public class VehicleColoursStepDefinitions {
	
	private DirectoryServiceLayer directoryServiceLayer;
	private FileInformation fileInformation;
	private List<FileInformation> fileInformationList;
	
	private List<VehicleInformation> vehiclesListFromFile;
	private List<VehicleInformation> vehiclesListFromSite;
	private LandingPage landingPage;
	private SearchPage searchPage;
	private SearchResultsPage searchResultsPage;
	
	@Before
	public void Setup() {
		Browser.getDriver();
	}
	
	@After
	public void TearDown() {
		Browser.Quit();
	}
	
	@Given("^I have CSV or XLSX files in target Directory$")
	public void i_have_CSV_or_XLSX_files_in_target_Directory() throws Throwable {
		directoryServiceLayer = new DirectoryServiceLayer(new DirectoryDAOImpl());
		List<FileInformation> fileList = directoryServiceLayer.GetFilesInDirectory();
		List<FileInformation> filteredList = fileList.stream().filter(
				f -> f.getExtension().toLowerCase().equals("xlsx") || 
				f.getExtension().toLowerCase().equals("csv"))
				.collect(Collectors.toList());
		Assert.assertFalse(filteredList.isEmpty());
		//fileInformation = filteredList.get(0);
		fileInformationList = filteredList;
	}

	@Given("^I extract vehicle details from the files$")
	public void i_extract_vehicle_details_from_the_files() throws Throwable {
		vehiclesListFromFile = new ArrayList<VehicleInformation>();
		
		for (FileInformation info : fileInformationList) {
			switch (info.getExtension().toLowerCase()) {
			case "csv":
				vehiclesListFromFile.addAll(FileUtil.readFromCsv(info));
				break;
			case "xlsx":
				vehiclesListFromFile.addAll(FileUtil.readFromExcel(info));
				break;
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
