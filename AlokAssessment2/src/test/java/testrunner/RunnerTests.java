package testrunner;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = {"src/test/resources/features",},
		glue = {"steps"},
		tags = "@urlValidatebyJSExecuter or @screenshot or @LoginValidate or @displayConfigFileContent or @Excel",
		plugin = {"pretty", 
				"html:target/html/htmlreport.html",
				"json:target/json/file.json",},
		publish = true, dryRun = false // to tell whether to test
)

public class RunnerTests extends AbstractTestNGCucumberTests {

}
