package sitecore.testrunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;


@CucumberOptions(
		features	= "src/test/resources/FeatureFiles",
		glue		= {"sitecore.stepDefinition"},
		dryRun		= false,
		monochrome	= true,
		publish     	= true
		)

public class TestRunner extends AbstractTestNGCucumberTests  {
	
}