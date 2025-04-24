import org.openqa.selenium.*;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class cateringTest {
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
        profile.setPreference("geo.prompt.testing", true);
        profile.setPreference("geo.prompt.testing.allow", true);
        profile.setPreference("geo.enabled", true);
        profile.setPreference("permissions.default.geo", 1);

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);
        return options;
    }

    @Test
    public void navigateToHomePage() {
        driver.get("https://www.publix.com");
    }

    @Test(dependsOnMethods = "navigateToHomePage")
    public void selectPlattersAndCatering() {
        WebElement plattersBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[aria-label='Platters & Catering']")));
        scrollIntoView(plattersBtn);
        plattersBtn.click();
        System.out.println("Navigated to Platters & Catering page");
    }

    @Test(dependsOnMethods = "selectPlattersAndCatering")
    public void viewAllSandwichPlatters() {
        // Scroll down to ensure visibility of all elements
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 400);");
        WebElement viewAllBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[.//span[contains(text(),'View all')]]")));
        viewAllBtn.click();
        System.out.println("Clicked 'View all' under Sandwich Platters");
    }

    @Test(dependsOnMethods = "viewAllSandwichPlatters")
    public void clickViewMap() {
        WebElement viewMapBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[data-qa-automation='button-View map']")));
        viewMapBtn.click();
        System.out.println("Clicked View Map for store locator");
    }

    @Test(dependsOnMethods = "clickViewMap")
    public void enterStoreLocatorAddress() {
        WebElement storeInput = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("input[data-qa-automation='locations-search-input']")));
        storeInput.clear();
        storeInput.sendKeys("20311 Grande Oak Shoppes Blvd, Estero, FL 33928");
        System.out.println("Entered store address");
    }

    @Test(dependsOnMethods = "enterStoreLocatorAddress")
    public void allowLocationButton () {
        WebElement allowLocationButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-qa-automation='button-Allow access to current location']")));
        allowLocationButton.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Test(dependsOnMethods = "enterStoreLocatorAddress")
    public void clickSearchButton() {
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-qa-automation='locations-search-submit-button']")));
        searchBtn.click();
        System.out.println("Clicked search for store");
    }

    @Test(dependsOnMethods = "clickSearchButton")
    public void testAllowLocationOnPublix() {
        // Click the Publix location button (not browser popup)
        WebElement allowButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-qa-automation='button-Allow access to current location']")));
        allowButton.click();

        // Manually click the "Search" button if it doesn’t auto-trigger
        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[data-qa-automation='button-Search']")));
        searchButton.click();

        // Wait for store result
        WebElement store = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//*[contains(text(),'The Shoppes at Grande Oak')]")));
        store.click(); // or assert selection
    }

    @Test(dependsOnMethods = "testAllowLocationOnPublix")
    public void selectStoreFromSearchResults() throws InterruptedException {
        // Choose Store
        Thread.sleep(2000);

        try {
            // Wait for the results container to be visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.cssSelector("div[data-qa-automation='store-search-results']")));

            // Find the specific store with the name "The Shoppes at Grande Oak"
            WebElement chooseStoreBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[@data-qa-automation='button-Choose store' and contains(@aria-label, 'The Shoppes at Grande Oak')]")
            ));
            //button[contains(@aria-label, 'Choose store:') and contains(@aria-label, 'Grande Oak')]")
            // Scroll into view and click the correct store
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", chooseStoreBtn);
            // ((JavascriptExecutor) driver).executeScript("arguments[0].click();", chooseStoreBtn);
            chooseStoreBtn.click();
            System.out.println("Successfully selected The Shoppes at Grande Oak");
        } catch (Exception e) {
            System.out.println("FAILED to select store from search results: " + e.getMessage());
            throw e;
        }
    }


    @Test(dependsOnMethods = "selectStoreFromSearchResults")
    public void selectBoxedMealsFromMenuNav() {
        WebElement nav = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.className("menu-left-nav")));

        WebElement boxedMealsBtn = nav.findElement(
                By.xpath(".//li/button[contains(@aria-label, 'Boxed Meals')]"));

        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", boxedMealsBtn);
        boxedMealsBtn.click();
        System.out.println("Navigated to Boxed Meals");
    }

    @Test(dependsOnMethods = "selectBoxedMealsFromMenuNav")
    public void selectBoxedMealItem() {
        WebElement boxedMealItem = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[contains(text(), 'Publix Deli Sub Sandwich Box Meal')]")));
        boxedMealItem.click();
        System.out.println("Selected Boxed Meal item");
    }

    @Test(dependsOnMethods = "selectBoxedMealItem")
    public void addCateringToOrder() {
        WebElement addToOrderBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Add to order')]")));
        addToOrderBtn.click();
        System.out.println("Clicked 'Add to order'");

        WebElement continueShoppingBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(), 'Continue shopping')]")));
        continueShoppingBtn.click();
        System.out.println("Clicked 'Continue shopping'");
    }

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
