package pages;

import corecomponents.GenericClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static constant.Reader.getPropertyValue;

public class BookingFlight {
    @FindBy(xpath = "//div[contains(@class,'form_select')]")
    public List<WebElement> selectOption;
    @FindBy(xpath = "(//div[contains(@class,'form_select')])[1]")
    public WebElement flyingFrom;
    @FindBy(xpath = "(//div[contains(@class,'form_select')])[2]")
    public WebElement flyingTo;
    @FindBy(xpath = "(//div[contains(@class,'form_select')])[3]")
    public WebElement journeyDate;
    @FindBy(xpath = "(//div[contains(@class,'form_select')])[5]")
    public WebElement travellerClass;
    @FindBy(xpath = "//div[@id='one-tab-pane']//input[@placeholder='Leaving From']")
    public WebElement inputLeavingFrom;
    @FindBy(xpath = "//div[@id='one-tab-pane']//input[@placeholder='Arrival To']")
    public WebElement inputArrivalTo;

    @FindBy(xpath = "(//div[@class='item'])[1]")
    public WebElement dropdownOptionChittagong;
    @FindBy(xpath = "(//div[@class='item'])[2]")
    public WebElement dropdownOptionDhaka;
    @FindBy(xpath = "(//button[@aria-label='Next Month'])[9]")
    public WebElement btnNextMonth;
    @FindBy(xpath = "(//div[contains(text(),'23')])[9]")
    public WebElement date23;
    @FindBy(xpath = "(//button[text()='Premium Economy'])[2]")
    public WebElement btnPremiumEconomy;
    @FindBy(xpath = "(//button[normalize-space()='Done'])[6]")
    public WebElement btnDone;
    @FindBy(xpath = "(//button[normalize-space()='Search'])[4]")
    public WebElement btnSearch;
    @FindBy(xpath = "(//input[@class='traveler-btn-plus'])[2]")
    public WebElement btnAddAdult;

    @FindBy(xpath = "//button[normalize-space()='CONTINUE']")
    public WebElement btnContinue;
    @FindBy(xpath = "(//label[normalize-space()='US-Bangla Airlines (7)'])[1]/preceding-sibling::input")
    public WebElement checkBoxUSBanglaAirlines;
    @FindBy(xpath = "(//label[normalize-space()='Novoair (1)'])[1]/preceding-sibling::input")
    public WebElement checkBoxNovoairAirlines;
    @FindBy(xpath = "(//button[normalize-space()='BOOK TICKET'])[7]")
    public WebElement btnBookTicketOfLastFlight;
    @FindBy(xpath = "(//span[@class='Flight_line_spacing__INn4m'])[7]")
    public WebElement priceOfLastFlight;
    @FindBy(xpath = "(//span[@class='Flight_line_spacing__INn4m'])")
    public List<WebElement> pricesOfLastFlight;
    @FindBy(xpath = "//div[@class='total_a']")
    public WebElement priceOfLastFlightInModal;
    @FindBy(xpath = "//h5[normalize-space()='Review fare to Dhaka']")
    public WebElement titleReviewFareToDhaka;
    WebDriver driver;
    GenericClass coreMethods = new GenericClass();

    public BookingFlight(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void bookFlight(String leavingFrom, String arrivalTo) throws InterruptedException {
        selectFlyingFrom(leavingFrom);
        selectFlyingTo(arrivalTo);
        selectJourneyDate();
        selectTravellerClass();
        clickSearchButton();
    }

    public void selectFlyingFrom(String from) throws InterruptedException {
        coreMethods.click(driver, flyingFrom);
        coreMethods.input(driver, inputLeavingFrom, from);
        sleep(3);
        coreMethods.click(driver, dropdownOptionChittagong);
    }

    public void selectFlyingTo(String to) throws InterruptedException {
        coreMethods.click(driver, flyingTo);
        coreMethods.input(driver, inputArrivalTo, to);
        sleep(3);
        coreMethods.click(driver, dropdownOptionDhaka);
    }

    public void selectJourneyDate() throws InterruptedException {
        coreMethods.click(driver, journeyDate);
        sleep(1);
        coreMethods.click(driver, btnNextMonth);
        coreMethods.click(driver, date23);
    }

    public void selectTravellerClass() throws InterruptedException {
        coreMethods.click(driver, travellerClass);
        sleep(1);
        coreMethods.click(driver, btnPremiumEconomy);
        sleep(1);
        addAdult();
        coreMethods.scrollTo(driver, 200);
        sleep(1); // Ensure the button is in view
        coreMethods.click(driver, btnDone);
    }

    public void addAdult() {
        coreMethods.click(driver, btnAddAdult);
    }
    public void clickSearchButton() {
        coreMethods.click(driver, btnSearch);
    }

    public void sleep(int seconds) throws InterruptedException {
        Thread.sleep(seconds * 1000);

    }

    public void clickOnUsBanglaCheckBox() throws InterruptedException {
        coreMethods.waitForElementToBeClickable(driver, checkBoxUSBanglaAirlines, 60);
        sleep(1);
        coreMethods.click(driver, checkBoxUSBanglaAirlines);
        sleep(2);
    }

    public void clickBookTicketOfLastFlight() throws InterruptedException, IOException {
        coreMethods.scrollTo(driver, 1000);
        sleep(1);
        writePriceOfLastFlightInPropertiesFile();
        coreMethods.click(driver, btnBookTicketOfLastFlight);
    }

    public void writePriceOfLastFlightInPropertiesFile() throws IOException {
        String filePath = "./src/test/resources/properties/common.properties";
        String priceRaw = priceOfLastFlight.getText();
        String priceClean = priceRaw.replaceAll("[^0-9]", "");
        updateProperty(filePath, "ticketPrice", priceClean);
    }

    public static void updateProperty(String filePath, String key, String value) throws IOException {
        Properties props = new Properties();

        // Load existing properties
        try (FileInputStream fis = new FileInputStream(filePath)) {
            props.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Set new property value
        props.setProperty(key, value);

        // Save updated properties
        try (FileOutputStream fos = new FileOutputStream(filePath)) {
            props.store(fos, "Updated ticket price");
        }
    }

    public void validateModalAppearAndMatchTotalPriceWithModalPrice() throws InterruptedException {
        String location = "./src/test/resources/properties/common.properties";
        coreMethods.waitForElementToBeVisible(driver, titleReviewFareToDhaka, 10);
        sleep(1);
        Assert.assertTrue(titleReviewFareToDhaka.isDisplayed());

        String priceRaw = priceOfLastFlightInModal.getText();
        String priceClean = priceRaw.replaceAll("[^0-9]", "");

        String priceFromFile = getPropertyValue(location, "ticketPrice");

        Assert.assertEquals(priceClean, priceFromFile, "Price in modal does not match the last flight price.");

    }

    public void clickContinueButton() throws InterruptedException {
        coreMethods.scrollToElement(driver, btnContinue);
        sleep(1);
        coreMethods.click(driver, btnContinue);
        sleep(1);
        closeSignInModal();

    }
    public void closeSignInModal() {
        Actions actions = new Actions(driver);
        actions.moveByOffset(10, 10).click().build().perform();
    }

    public void compareUsBanglaAirlinesAndNovoairPrices() throws InterruptedException, IOException {
        int[] usBanglaFlightPrices = storeUSBanglaFlightPricesIntoAnArray();
        coreMethods.scrollToElement(driver, checkBoxNovoairAirlines);
        clickOnUsBanglaCheckBox();
        clickOnUsNovoAirCheckBox();
        int[] novoAirFlightPrices = storeNovoAirFlightPricesIntoAnArray();

        // Compare the two arrays, assert price difference, and print the differences
        boolean priceDifferenceExists = false;

        for (int usBanglaPrice : usBanglaFlightPrices) {
            for (int novoAirPrice : novoAirFlightPrices) {
                if (usBanglaPrice != novoAirPrice) {
                    priceDifferenceExists = true;
                    System.out.println("Price difference found: US-Bangla Price = " + usBanglaPrice + ", Novoair Price = " + novoAirPrice);
                }
            }
        }

        // Assert that there is a price difference
        Assert.assertTrue(priceDifferenceExists, "No price difference found between US-Bangla and Novoair flights.");

    }

    public int[] storeUSBanglaFlightPricesIntoAnArray() throws InterruptedException {
        List<WebElement> usBanglaFlightPrices = pricesOfLastFlight;
        int[] pricesArray = new int[usBanglaFlightPrices.size()];

        for (int i = 0; i < usBanglaFlightPrices.size(); i++) {
            coreMethods.scrollToElement(driver, usBanglaFlightPrices.get(i));
            sleep(1);
            String priceRaw = usBanglaFlightPrices.get(i).getText();
            String priceClean = priceRaw.replaceAll("[^0-9]", "");
            pricesArray[i] = Integer.parseInt(priceClean);

            System.out.println("Price of US-Bangla flight " + (i + 1) + ": " + pricesArray[i]);
        }
        return pricesArray;
    }

    public int[] storeNovoAirFlightPricesIntoAnArray() throws InterruptedException {
        List<WebElement> novoAirlightPrices = pricesOfLastFlight;
        int[] pricesArray = new int[novoAirlightPrices.size()];

        for (int i = 0; i < novoAirlightPrices.size(); i++) {
            coreMethods.scrollToElement(driver, novoAirlightPrices.get(i));
            sleep(1);
            String priceRaw = novoAirlightPrices.get(i).getText();
            String priceClean = priceRaw.replaceAll("[^0-9]", "");
            pricesArray[i] = Integer.parseInt(priceClean);

            System.out.println("Price of Novo-Air flight " + (i + 1) + ": " + pricesArray[i]);
        }
        return pricesArray;
    }

    public void clickOnUsNovoAirCheckBox() throws InterruptedException {
        coreMethods.click(driver, checkBoxNovoairAirlines);
        sleep(2);
    }

}
