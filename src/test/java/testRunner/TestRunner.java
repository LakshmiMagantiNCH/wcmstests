package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features=".//src/test/resources/Features/NationwideChildrens.feature",
	glue={"stepDefinitions","hooks"},
	dryRun=false,
	monochrome=true,
    plugin = {"pretty", "listenerClasses.ExtentListener"
				    }
			    )
public class TestRunner {

	
}
