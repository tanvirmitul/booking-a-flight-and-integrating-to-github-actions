package stepDefinitions;

import constant.ProjectConfig;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pages.BookingFlight;
import setup.Setup;

import java.io.IOException;

public class FlightBookingStepDefinition {

    private WebDriver driver;
    private BookingFlight bookingFlight;

    @Given("user navigate to url")
    public void userNavigateToUrl() {
        Setup.initializeDriver();
        driver = Setup.getDriver();
        bookingFlight = new BookingFlight(driver);
        driver.get(ProjectConfig.WEBSITE_URL);
    }

    @When("user book a flight from Chittagong to Dhaka")
    public void userBookAFlightFromChittagongToDhaka() throws InterruptedException {
        bookingFlight.bookFlight( "Chattogram", "Dhaka");
    }

    @And("user click on search button")
    public void userClickOnSearchButton() {
        bookingFlight.clickSearchButton();
    }

    @And("user filter flight by clicking on us bangla airlines")
    public void userFilterFlightByClickingOnUsBanglaAirlines() throws InterruptedException {
        bookingFlight.clickOnUsBanglaCheckBox();

    }

    @And("user select book ticket of last flight")
    public void userSelectBookTicketOfLastFlight() throws IOException, InterruptedException {
        bookingFlight.clickBookTicketOfLastFlight();

    }

    @Then("validate modal appear and match total price with modal price")
    public void validateModalAppearAndMatchTotalPriceWithModalPrice() throws InterruptedException {
        bookingFlight.validateModalAppearAndMatchTotalPriceWithModalPrice();

    }

    @When("user click on continue button")
    public void userClickOnContinueButton() throws InterruptedException {
        bookingFlight.clickContinueButton();

    }

    @Then("compare us bangla airlines and novoair prices")
    public void compareUsBanglaAirlinesAndNovoairPrices() throws IOException, InterruptedException {
        bookingFlight.compareUsBanglaAirlinesAndNovoairPrices();
    }
}
