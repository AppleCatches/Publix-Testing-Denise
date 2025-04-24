import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class giftCardsTest {
    public WebDriver driver;
    public WebDriverWait wait;

    @BeforeClass
    public void setUp() {
        FirefoxOptions options = getFirefoxOptions();
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        driver.manage().window().maximize();
    }

    private static FirefoxOptions getFirefoxOptions() {
        FirefoxProfile profile = new FirefoxProfile();

        // Automatically allow geolocation access
        profile.setPreference("geo.prompt.testing", true);
        profile.setPreference("geo.prompt.testing.allow", true);
        profile.setPreference("geo.enabled", true);
        profile.setPreference("permissions.default.geo", 1); // 1: Allow, 2: Block

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        return options;
    }

    @Test
    public void navigateToHomePage() {
        driver.get("https://www.publix.com");
        sleep(2000);
    }

    @Test(dependsOnMethods = "navigateToHomePage")
    public void selectGiftCardsMenu() {
        WebElement giftCardsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gift Cards")));
        giftCardsLink.click();
        sleep(2000);
    }

    @Test(dependsOnMethods = "selectGiftCardsMenu")
    public void selectMailGiftCard() {
        WebElement mailGiftCardButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='/gift-cards/order/individual']")));
        mailGiftCardButton.click();
        sleep(2000);
    }

    @Test(dependsOnMethods = "selectMailGiftCard")
    public void customizeGiftCard() {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        sleep(2000);

        WebElement incrementButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label*='increase quantity']")));
        incrementButton.click(); // 1 -> 2
        sleep(2000);
        incrementButton.click(); // 2 -> 3

        WebElement amount50 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='option-label' and text()='$50']")));
        amount50.click();
        sleep(2000);

        WebElement addMessageButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.p-button.button--text.button--icon-left")));
        addMessageButton.click();
        sleep(2000);

        WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@placeholder='Type custom message']")));
        messageBox.clear();
        messageBox.sendKeys("From Grandma");
        sleep(2000);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    private void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
