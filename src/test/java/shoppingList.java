import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class shoppingList {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        FirefoxProfile profile = new FirefoxProfile();

        // Block location request
        profile.setPreference("permissions.default.geo", 2); // 1 = Allow, 2 = Block, 0 = Ask

        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(profile);

        // Initialize driver
        driver = new FirefoxDriver(options);
        driver.manage().window().maximize();
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test (priority = 1)
    public void weeklyAd() throws InterruptedException {
        driver.get("https://www.publix.com/");

        // Clicks Shopping List
        driver.findElement(By.id("userShoppingList"))
                .click();
        // Clicks Weekly Ad
        driver.findElement(By.xpath("/html/body/div/section/div[2]/div[2]/div/div[2]/div/a[1]/span"))
                .click();
        Thread.sleep(500);

        // hits and fills in text box
        driver.findElement(By.xpath("/html/body/div/section/div[4]/div/div[3]/div/div/div[2]/div/div/div[1]/form/input"))
                .sendKeys("33965");
        // hits search
        driver.findElement(By.xpath("/html/body/div/section/div[4]/div/div[3]/div/div/div[2]/div/div/div[1]/form/button"))
                .click();
        Thread.sleep(500);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Wait until the next button is clickable
        WebElement Button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[4]/div/div[3]/div/div/div[2]/div/div/div[2]/ul/li[1]/div/div/div[2]/div[1]/div/button/span")
        ));
        //  click
        Thread.sleep(1000);
        Button.click();


        Thread.sleep(1000);


        // Wait until the next button is clickable
        Button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"listing\"]")
        ));
        //  click
        Thread.sleep(1000);
        Button.click();
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(2000);


        Button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@data-qa-automation='button-Add to list']")
        ));
        Button.click();
        Thread.sleep(2000);
    }

    @Test (priority = 2)
    public void searchAdd() throws InterruptedException {
        Thread.sleep(3000);
        driver.get("https://www.publix.com/shopping-list?nav=header");
        driver.findElement(By.id("sl-add-item-input"))
                .sendKeys("Frosted Flakes");

        driver.findElement(By.xpath("/html/body/div/section/div[2]/div[1]/div[2]/form/div/div[2]/div/button"))
                .click();

        Thread.sleep(2000);
    }

    @Test (priority = 4)
    public void updateQuantity() throws InterruptedException {
        driver.get("https://www.publix.com/shopping-list?nav=header");
        Thread.sleep(400);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div[2]/table/tbody/tr/tr/td[2]/div[3]/div/div/div[3]/button")
        ));
        button.click();

        Thread.sleep(1000);
    }

    @Test (priority = 3)
    public void addNote() throws InterruptedException {
        Thread.sleep(1500);
        driver.get("https://www.publix.com/shopping-list?nav=header");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement textBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div[2]/div/form/div[1]/div[2]")
        ));
        textBox.click();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }

        WebElement notesBox = driver.findElement(
                By.cssSelector("textarea[data-qa-automation='shopping-list-notes-input-area']")
        );
        notesBox.sendKeys("Resist the siren call of the snack aisle.");


        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"save-note-btn\"]")
        ));
        button.click();
        Thread.sleep(500);
    }

    @Test (priority = 5)
    public void clearList() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(2000);
        driver.get("https://www.publix.com/shopping-list?nav=header");
        Thread.sleep(1000);

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div[2]/table/thead/tr/td[1]/span")
        ));
        button.click();

        Thread.sleep(500);

        button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div[2]/table/tr/th")
        ));
        button.click();


        driver.findElement(By.id("clearList-clear-btn"))
                .click();

        Thread.sleep(1000);

    }


}
