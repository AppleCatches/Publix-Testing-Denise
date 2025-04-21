import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class support {
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
    public void contactUs() throws InterruptedException {
        driver.get("https://www.publix.com/");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.publix.com/contact");
        Thread.sleep(1000);

        WebElement contactUsButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[3]/div[3]/div[4]/div/div[2]/div/a/span")
        ));
        contactUsButton.click();
        Thread.sleep(500);


        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("form-field-64")
        ));
        firstName.sendKeys("Billy-Bob");
        Thread.sleep(500);

        WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("form-field-68")
        ));
        lastName.sendKeys("Thornton");
        Thread.sleep(500);

        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 2; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(1000);

        WebElement suffix = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("form-field-72")
        ));
        Select select = new Select(suffix);
        select.selectByValue("Dr.");
        Thread.sleep(500);
        select.selectByValue("Ms.");
        Thread.sleep(500);
        select.selectByValue("Mr.");
        Thread.sleep(500);

        WebElement radio = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[3]/div/form/div[1]/div[2]/fieldset/div/div[2]/label[2]")
        ));
        radio.click();
        Thread.sleep(1000);

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("form-field-304")
        ));
        email.sendKeys("bob@gmail.com");
        Thread.sleep(500);

        WebElement zipCode = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("form-field-113")
        ));
        zipCode.sendKeys("33993");
        Thread.sleep(500);

        WebElement questionButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[3]/div/form/div[2]/div/fieldset/div[1]/label")
        ));
        questionButton.click();
        Thread.sleep(500);

        WebElement storeExp = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[3]/div/form/div[3]/div/fieldset/div[1]/label")
        ));
        storeExp.click();

        WebElement chooseLocButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[3]/div/form/div[4]/div[2]/div[1]/div/div/div/div/div/button")
        ));
        chooseLocButton.click();
        Thread.sleep(500);
        //choose store
        WebElement zipCodeEntry = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[1]/form/input")
        ));
        zipCodeEntry.sendKeys("33993");
        Thread.sleep(500);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[1]/form/button")
        ));
        searchButton.click();
        Thread.sleep(500);

        WebElement chooseStore = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[2]/ul/li[1]/div/div/div[2]/div[1]/div/button/span")
        ));
        chooseStore.click();
        Thread.sleep(2000);

        WebElement message = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("form-field-335")
        ));
        message.sendKeys("good sandwich will shop again");
        Thread.sleep(500);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[3]/div/form/button/span")
        ));
        submitButton.click();
        Thread.sleep(2000);



    }


    @Test (priority = 2)
    public void publixPolicies() throws InterruptedException {
        driver.get("https://www.publix.com/");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.publix.com/pages/policies");
        Thread.sleep(1000);

        WebElement couponPolicy = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[4]/div/div[1]/div/a")
        ));
        couponPolicy.click();
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(2000);

        driver.get("https://www.publix.com/pages/policies");
        Thread.sleep(1000);
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(500);

        WebElement publixGuarantee = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[4]/div/div[5]/div/a")
        ));
        publixGuarantee.click();
        Thread.sleep(1000);
        //spanish
        driver.findElement(By.xpath("/html/body/div/header/div[2]/div/a"))
                .click();
        Thread.sleep(1000);
        //english
        driver.findElement(By.xpath("/html/body/div/header/div[2]/div/a"))
                .click();
        Thread.sleep(1000);

        WebElement rainCheck = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[1]/div[3]/ul/li[2]/a")
        ));
        rainCheck.click();
        Thread.sleep(1000);

    }


    @Test (priority = 3)
    public void publixFAQ() throws InterruptedException {
        driver.get("https://www.publix.com/");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://www.publix.com/faq");
        Thread.sleep(1000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }

        WebElement giftCards = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[1]/div[3]/ul/li[11]/a")
        ));
        giftCards.click();
        Thread.sleep(1000);

        WebElement physicalEGift = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[5]/ul/li[1]/a")
        ));
        physicalEGift.click();
        Thread.sleep(1000);

        WebElement firstExpand = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[4]/div[1]")
        ));
        firstExpand.click();
        Thread.sleep(500);
        WebElement secondExpand = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[4]/div[4]")
        ));
        secondExpand.click();
        Thread.sleep(500);
        WebElement thirdExpand = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[4]/div[21]")
        ));
        thirdExpand.click();
        Thread.sleep(500);

        driver.get("https://www.publix.com/faq");
        Thread.sleep(1000);

        WebElement nutrition = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[5]/ul/li[13]/a")
        ));
        nutrition.click();
        Thread.sleep(1000);

        WebElement nutritionExpand = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[3]/div[5]")
        ));
        nutritionExpand.click();
        Thread.sleep(500);
    }

    @Test (priority = 4)
    public void clubPublix() throws InterruptedException {
        driver.get("https://www.publix.com/");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://clubpublix.com/?_gl=1*1thf2hj*_gcl_au*MjMxOTk4NjU3LjE3NDQ2MDE0NDM.*FPAU*MjMxOTk4NjU3LjE3NDQ2MDE0NDM.*_ga*MTYwNzE2MjU4My4xNzQ0NjAxNDQz*_ga_1DWX30JN6C*MTc0NDYwMTQ0My4xLjEuMTc0NDYwNTAzMy4wLjAuMTA2MzgwMDMzOQ..*_fplc*QnQzNnglMkZzYTdoNVdXVzdtU3NHSjhpeG9NSW1rbXFxWVJuSVN6eFcwZ0FoQmhhU1hXdloxZkdRSDh5WVpOQWhvZ29aMiUyRkFTRzFGMjllUTRMYW1PREdXVE9QOTQ3Wkd1cjM1dyUyRko4bzNqTElYSkJtN0E5MVF5azV0blNlZkZ3JTNEJTNE");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 30; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(500);

        WebElement learnMore = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/main/div/h2/a")
        ));
        learnMore.click();
        Thread.sleep(500);
        for (int i = 0; i < 30; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(500);

        WebElement general = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/main/div/section/div[1]/div[1]/span")
        ));
        general.click();
        Thread.sleep(500);

        WebElement whatIs = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/main/div/section/div[1]/div[2]/div/section/div[1]/div[1]/span")
        ));
        whatIs.click();
        Thread.sleep(500);
        whatIs.click();
        Thread.sleep(500);

        general.click();
        Thread.sleep(1000);

        js = (JavascriptExecutor) driver;
        for (int i = 0; i < 2; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        WebElement appFAQExpand = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/main/div/section/div[4]/div[1]/span")
        ));
        Thread.sleep(500);
        appFAQExpand.click();
        Thread.sleep(500);

    }

    @Test (priority = 5)
    public void services () throws InterruptedException {
        driver.get("https://www.publix.com/");
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.publix.com/pages/guides-and-articles/shelf-tags-icons");
        Thread.sleep(2000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 30; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(500);

        WebElement madeWithout = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[3]/div/div[12]/p/a")
        ));
        madeWithout.click();
        Thread.sleep(500);
        for (int i = 0; i < 30; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(500);

        driver.get("https://www.publix.com/pages/guides-and-articles/shelf-tags-icons");
        Thread.sleep(2000);

        WebElement usdaOrganic = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[3]/div/div[8]/p/a")
        ));
        usdaOrganic.click();
        Thread.sleep(500);
        for (int i = 0; i < 30; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(500);

        driver.get("https://www.publix.com/");
        Thread.sleep(2000);
        driver.get("https://www.publix.com/publix-milestones/weddings");
        Thread.sleep(2000);
        for (int i = 0; i < 30; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(500);

        WebElement flowers = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[6]/div/div/div/p[2]/a")
        ));
        flowers.click();
        Thread.sleep(500);
        for (int i = 0; i < 50; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100);
        }
        Thread.sleep(500);

        WebElement weddingCakes = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[15]/div/div[1]/div/a")
        ));
        weddingCakes.click();
        Thread.sleep(1000);

    }

}
