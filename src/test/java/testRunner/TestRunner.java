package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features=".//src/test/resources/Features/FindADoctor.feature",
	glue={"stepDefinitions","hooks"},
	tags="@postive or @negative",
	dryRun=false,
	monochrome=true,
    plugin = {"pretty", "listenerClasses.ExtentListener"
				    }
			    )
public class TestRunner {

	
}
