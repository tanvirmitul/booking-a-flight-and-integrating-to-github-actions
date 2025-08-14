package testRunner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to your .feature files
        glue = {"stepDefinitions"},               // Package containing step definitions
        plugin = {"pretty", "html:target/cucumber-report.html"}, // Optional: HTML report
        tags = "@bookingFlight",                  // Runs only scenarios with this tag
        monochrome = true                         // Cleaner console output
)
public class TestRunner {
}