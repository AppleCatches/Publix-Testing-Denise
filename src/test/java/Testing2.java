

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.Scanner;



public class Testing2 {

    WebDriver driver;
    Actions actions;
    WebDriverWait wait;


    final String URL = "https://www.publix.com/mc/order-ahead/order-bakery-cakes";

    @BeforeMethod
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "/home/ryan/Java_Dependicies/Webdrivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().window().maximize();/*
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        actions = new Actions(driver);
*/

    }

    @Test(priority = 2)
    public void testPublixCakeOrderFlow() throws Exception {
        driver.get(URL);

        enterZipCode();
        //selectStore();
        Thread.sleep(500);
        driver.findElement(By.xpath("//*[@id=\"sidebar-close-button\"]/span[1]"))
                        .click();
        Thread.sleep(1000);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 2; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        selectCakeCategory();
        selectCake();
        chooseFlavour();
        addToOrder();
        addMessage();
        clickcontinueShopping();
        addToCart();
        ReviewOrder();
        takeScreenshot();

    }
    @Test(priority = 1)
    public void testPublixDeliveryAddToCartAndCheckout() throws InterruptedException {
        driver.get("https://delivery.publix.com/store/publix/storefront");

        selectPickup();
        confirmPickup();

        searchAndAddItem("rice", "//*[@id=\"store-wrapper\"]/div/div/div[5]/ul/li[3]/div/div/h3/div/div/div/div/button");
        searchAndAddItem("water", "//html/body/div[2]/div[1]/div/div[2]/div[3]/div/div/div[5]/ul/li[1]/div/div/h3/div/div[3]/div/div/button");
        searchAndAddItem("juice", "//*[@id=\"store-wrapper\"]/div/div/div[5]/ul/li[1]/div/div/h3/div/div[2]/div/div/button");

        openCart();
        proceedToCheckout1 ();
        Thread.sleep(1000);
        login();



        Thread.sleep(3000);

        System.out.println("Automation completed!");




    }

    public void selectPickup() throws InterruptedException {
        WebElement pickupButton = driver.findElement(By.xpath("//*[@id=\"id-24\"]/div[1]/div[2]/button[1]"));
        pickupButton.click();
        Thread.sleep(2000);
    }

    public void confirmPickup() throws InterruptedException {
        WebElement confirmPickupButton = driver.findElement(By.xpath("//*[@id=\"id-24\"]/div[2]/button"));
        confirmPickupButton.click();
        Thread.sleep(2000);
    }

    public void searchAndAddItem(String item, String addItemXpath) throws InterruptedException {
        WebElement searchBar = driver.findElement(By.xpath("//*[@id=\"search-bar-input\"]"));

        // Click into search bar to focus
        searchBar.click();

        // Clear previous text properly
        searchBar.sendKeys(Keys.chord(Keys.CONTROL, "a"));  // Select all
        searchBar.sendKeys(Keys.BACK_SPACE);                // Delete
        searchBar.sendKeys(Keys.BACK_SPACE);
        searchBar.sendKeys(Keys.BACK_SPACE);
        searchBar.sendKeys(Keys.BACK_SPACE);
        searchBar.sendKeys(Keys.BACK_SPACE);
        searchBar.sendKeys(Keys.BACK_SPACE);
        Thread.sleep(1000);                                 // tiny wait after clear

        // Enter new item and search
        searchBar.sendKeys(item);
        searchBar.sendKeys(Keys.ENTER);

        Thread.sleep(3000);  // Wait for search results to load

        // driver.findElement(By.xpath(addItemXpath)).click();
        // Thread.sleep(1000);  // tiny wait after add to cart

        // Dynamically click first Add to Cart button found
        WebElement firstAddButton = driver.findElement(By.xpath("(//button[contains(.,'Add') or contains(.,'Add to Cart')])[1]"));
        firstAddButton.click();
        Thread.sleep(1000);
    }


    public void openCart() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"js-app\"]/div[1]/header/div[3]/div[2]/span/button")).click();
        Thread.sleep(1000);
    }

    public void proceedToCheckout1 () throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"cart_dialog\"]/div/footer/button")).click();
        Thread.sleep(1000); // Optional visual wait
    }
    public void login() throws InterruptedException {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Click Login Button
        driver.findElement(By.xpath("//*[@id=\"store-wrapper\"]/div/div/div[1]/div/div/div[2]/div/div/div[2]/button[2]")).click();
        Thread.sleep(5000);

        // Handle dynamic email input
        WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("input[id*='react-aria']")));

        emailInput.click();
        emailInput.clear();
        emailInput.sendKeys("clintonoho72@gmail.com");  // replace with your email

        // Click Continue
        driver.findElement(By.cssSelector("#store > div.ReactModalPortal > div > div > div > div > div.e-1s0zv29 > div.e-ywjr9e > form > div:nth-child(2) > button")).click();
        Thread.sleep(3000);
    }





    public void enterZipCode() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[1]/form/input")
        ));

        //input.clear(); // Make sure no ZIP is already there
        input.sendKeys("33965");


        WebElement submit = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id='navBar']/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[1]/form/button")
        ));

        submit.click();
    }


    public void selectStore() throws InterruptedException {
        driver.findElement(By.cssSelector("#choose_847 > span")).click();
        Thread.sleep(2000);
    }

    public void selectCakeCategory() throws InterruptedException {
        driver.findElement(By.cssSelector("#main > div.search-page-content.search-results-super-container.mar-top-md.merchandising-collection-page.v4 > div.visual-navigation-grid > div > nav > ul > li:nth-child(1) > a > div.circle-wrapper > div > div > img")).click();
        //actions.sendKeys(Keys.PAGE_DOWN).perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
    }

    public void selectCake() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id='main']/div[1]/div[2]/div[2]/div[2]/div[2]/div/div/div[1]/div/div[2]/div[1]/div/a")).click();
        //actions.sendKeys(Keys.PAGE_DOWN).perform();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
    }

    public void chooseFlavour() throws InterruptedException {
        Thread.sleep(1000);
        driver.findElement(By.xpath("//*[@id=\"main\"]/div[2]/div/div/div[1]/div[2]/div[1]/div[5]/div/fieldset/div[2]/div[1]/label/div/p/span")).click();
    }

    public void addToOrder() throws InterruptedException {
        driver.findElement(By.xpath("//*[@id=\"builder-add-to-order-btn-sticky\"]/span")).click();
        Thread.sleep(2000);
    }

    public void addMessage() throws InterruptedException {
        WebElement msgBox = driver.findElement(By.xpath("/html/body/div[1]/section/div[2]/div/div/div[1]/div[2]/div[1]/div[7]/div[2]/div/div[2]/div[2]/div/div/form/div/div[2]/div/textarea"));
        msgBox.click();
        msgBox.sendKeys("Happy Birthday!");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='builder-add-message-btn']/span")).click();
        Thread.sleep(2000);
    }

    public void clickcontinueShopping () throws InterruptedException {

        Thread.sleep(1000);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait until the  button is clickable
        WebElement Button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"navBar\"]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div[2]")
        ));
        Button.click();
    }

    public void addToCart() throws InterruptedException {
        Thread.sleep(1000); // tiny wait after scroll
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement Button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/div[2]/div/div[3]/div/div/button[2]")
        ));
        Button.click();

    }

    public void ReviewOrder () throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"two-column-container\"]/div[2]/div/div/div/div[3]/button")).click();
        Thread.sleep(5000);
    }

    public void confirmStore() throws Exception {

        driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div")).click();
        driver.findElement(By.cssSelector("#navBar div.search-container form input[type=search]")).sendKeys("33965");
        WebElement confirmstoreButton = driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[2]/ul/li[1]/div/div/div[2]/div[1]/div/button"));

        JavascriptExecutor js = (JavascriptExecutor) driver;

// Scroll into view just in case
        js.executeScript("arguments[0].scrollIntoView(true);", confirmstoreButton);

        Thread.sleep(500); // tiny pause if needed

// Force Click using JS (bypasses overlays)
        js.executeScript("arguments[0].click();", confirmstoreButton);
        driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[1]/div/button")).click();

    }
    public void proceedToCheckout() throws Exception {
        Thread.sleep(1000);
        driver.findElement(By.xpath("/html/body/div[1]/section/div[2]/div/div[2]/div/div/div/div[3]/button")).click();
        Thread.sleep(2000);
    }
    public void takeScreenshot() throws IOException {
        File scrShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "PublixCakeOrder.png";
        Files.copy(scrShot.toPath(), new File(path).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Assert.assertTrue(new File(path).exists(), "Screenshot not saved");

        // Final success log
        System.out.println("Automation completed ");

    }


    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
