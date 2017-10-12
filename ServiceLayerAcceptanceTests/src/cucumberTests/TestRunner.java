package cucumberTests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * This is the main Cucumber test runner
 * The cucumber options can be used to change format of output, and to perform dry runs
 */
@RunWith(Cucumber.class)
@CucumberOptions( 
		features = "Features", 
		glue = {"stepDefinitions"}, 
		format = {"html:TestResults"},
		// dryRun = true will only check if all steps have corresponding step definitions 
		// dryRun = false will actually run the step definitions
		// this is useful for validating correct setup of cucumber artefacts, before triggering an actual run 
		dryRun = false)
public class TestRunner {

}