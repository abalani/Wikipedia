package steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features="src/test/resources/Features", glue = {"steps"},
        monochrome = true, plugin = {"pretty", "html:target/HtmlReports/report.html"}, tags = "@smoke")
// plugin = {"pretty", "json:target/JSONReports/report.json"}
// plugin = {"pretty", "junit:target/JUnitReports/report.xml"}

public class TestRunner {
}
