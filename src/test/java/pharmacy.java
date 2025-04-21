import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.time.Duration;
import java.util.List;

public class pharmacy {

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
    public void appointmentBooking () throws InterruptedException {
        driver.get("https://www.publix.com/pharmacy?nav=secondary_nav_pharmacy");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 20; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }

        // Clicks Book Appointment
        driver.findElement(By.xpath("/html/body/div/section/div[9]/div/div/a"))
                .click();

        // Clicks Appointment
        driver.findElement(By.xpath("/html/body/div/section/div[4]/p[4]/a"))
                .click();
        Thread.sleep(1000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

// Waits for the COVID Option to be clickable and then clicks it
        WebElement covidOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[1]/div[1]/div[2]/div/div/div[1]/label/span[1]")
        ));
        covidOption.click();

        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[1]/div[1]/div[2]/div/div/div[3]/label/span[1]")
        ));
        button.click();


        // Scolls down
        for (int i = 0; i < 20; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100);
        }
        // Clicks Continue
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[2]/div/div/button"))
                .click();

        // Handles Text
        WebElement temp = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div/div/div/div/div/input"));
        temp.sendKeys("33965");

        Thread.sleep(500);

        // Click Search
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[1]/div/div/div/div/span/button"))
                .click();

         js = (JavascriptExecutor) driver;
        for (int i = 0; i < 2; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(3500);
        // Clicks store
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[2]/div[1]/div/div[1]/div[2]/label/span"))
                .click();

        // Scolls down
        for (int i = 0; i < 20; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100);
        }

        // Clicks Continue
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div[3]/div/div/button"))
                .click();

        Thread.sleep(4000);
        // Clicks Calandar
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div/div/div/span/button"))
                .click();

        // Selects date
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div/div[2]/div/div/div[1]/div/div[2]/section/div/div[5]/span[1]"))
                .click();

        // Clicks time box
        driver.findElement(By.xpath("//*[@id=\"hourSelector\"]"))
                .click();

        // Clicks the time
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[2]/div/div[2]/div/div/div[2]/div/select/option[26]"))
                .click();

        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div[2]/div[3]/div/div/button"))
                .click();

        // Cant do further due to bot check
        Thread.sleep(1000);
    }

    @Test (priority = 2)
    public void locator() throws InterruptedException {
        Thread.sleep(2000);
        driver.get("https://www.publix.com/pharmacy?nav=secondary_nav_pharmacy");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 20; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100);
        }

        // Clicks Find a Pharmacy
        driver.findElement(By.xpath("/html/body/div/section/div[10]/div/div/a"))
                .click();

        // Selects and sends zip code to search box
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div/input"))
                .sendKeys("33965");
        // Clicks Search
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div/div/div/div/div/span/button"))
                .click();

        Thread.sleep(3500);

        // Focuses Map
        WebElement map = driver.findElement(By.xpath("//div[contains(@class, 'gm-style')]")); // Google Map container
        // Moves Map Around
        new Actions(driver)
                .moveToElement(map)
                .clickAndHold()
                .moveByOffset(-200, -200) // drag up-left
                .pause(Duration.ofMillis(200))
                .moveByOffset(50, -100)
                .pause(Duration.ofMillis(200))
                .release()
                .perform();

        // Clicks Map Tools
        driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div[2]/div/div/div[3]/div[12]/div/gmp-internal-camera-control/button"))
               .click();

        // Finds and hit zoom out btn
        WebElement temp = driver.findElement(By.xpath("/html/body/div[3]/div/div/div[2]/div[2]/div[2]/div/div/div[3]/div[12]/div/gmp-internal-camera-control/menu/li[6]/button"));
        for (int i = 0; i < 4; i++) {
            temp.click();
        }

        WebElement searchList = driver.findElement(By.id("searchList"));
        for (int i = 0; i < 5; i++) {
          js.executeScript("arguments[0].scrollTop += 100;", searchList);
          Thread.sleep(500);
         }

        Thread.sleep(1000);
    }

    @Test (priority = 3)
    public void disposal() throws InterruptedException {
        driver.get("https://www.publix.com/pharmacy?nav=secondary_nav_pharmacy");
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 30; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100);

        }

        // Clicks disposal
        driver.findElement(By.xpath("/html/body/div/section/div[14]/div/div[5]/div/a/div/h4"))
                .click();
        // scrolls down
        for (int i = 0; i < 5; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100);

        }
        // clicks locator
        driver.findElement(By.xpath("/html/body/div/section/div[4]/p[3]/a"))
                .click();

        Thread.sleep(500);
        // clicks and enters zip in box
        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[1]/form/input"))
                .sendKeys("33965");
        // Hits Search
        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[1]/form/button"))
                .click();


        Thread.sleep(500);
        // Clicks store details
        driver.findElement(By.xpath("/html/body/div/section/div[2]/div/div[1]/div[2]/ul/li[1]/div/div/div[2]/div[2]/a"))
                .click();

        Thread.sleep(500);
        List<WebElement> detailsDivs = driver.findElements(By.cssSelector("div.details"));
        for (WebElement div : detailsDivs) {
            System.out.println("===== DETAILS BLOCK START =====");
            System.out.println(div.getText());
            System.out.println("===== DETAILS BLOCK END =====\n");
        }

    }

    @Test (priority = 4)
    public void helpDrop () throws InterruptedException {
        driver.get("https://rx.publix.com/en/myaccount/faqs");
        Thread.sleep(500);

        WebElement help = driver.findElement(By.id("help"));

// Step 1: Expand all main FAQ sections (h4)
        List<WebElement> mainSections = help.findElements(By.cssSelector("h4.help-header[data-toggle='collapse']"));
        for (WebElement section : mainSections) {
            String target = section.getAttribute("data-target");
            if (target != null && !target.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", section);
                section.click();
                Thread.sleep(300); // let it expand
            }
        }

// Step 2: Expand all nested FAQ answers (h5)
        List<WebElement> subHeaders = help.findElements(By.cssSelector("h5.help-subheader[data-toggle='collapse']"));
        for (WebElement subHeader : subHeaders) {
            String answerTarget = subHeader.getAttribute("data-target"); // e.g., #SectionAnswer_0
            if (answerTarget != null && !answerTarget.isEmpty()) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", subHeader);
                subHeader.click();
                Thread.sleep(300); // let it expand

                // Optional: Print the answer content
                try {
                    WebElement answer = driver.findElement(By.cssSelector(answerTarget));
                    System.out.println("Answer from " + answerTarget + ":");
                    System.out.println(answer.getText());
                    System.out.println("--------------------------------------");
                } catch (Exception e) {
                    System.out.println("Could not locate content for " + answerTarget);
                }
            }
        }

        // Scrolls Up
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 100; i++) {
            js.executeScript("window.scrollBy(0, -100);");
            Thread.sleep(100); // adjust delay for smoothness
        }

    }

    @Test (priority = 5)
    public void dogMed() throws InterruptedException {
    driver.get("https://www.publix.com/pharmacy/pharmacy-services/pet-meds");
    JavascriptExecutor js = (JavascriptExecutor) driver;

    // Scroll down to trigger lazy loading (optional)
    for (int i = 0; i < 15; i++) {
        js.executeScript("window.scrollBy(0, 100);");
        Thread.sleep(100);
    }

    // Create screenshot directory if not exists
    File screenshotDir = new File("screenshots");
    if (!screenshotDir.exists()) {
        screenshotDir.mkdirs();
    }

    // Find and interact with all expand-buttons
    List<WebElement> expandButtons = driver.findElements(By.cssSelector(".expand-button"));

    for (WebElement button : expandButtons) {
        try {
            js.executeScript("arguments[0].scrollIntoView(true);", button);
            String sectionName = button.getText().trim();

            // Fallback name if no visible text
            if (sectionName.isEmpty()) {
                sectionName = "Section_" + System.currentTimeMillis();
            }

            // Make file-safe
            sectionName = sectionName.replaceAll("[^a-zA-Z0-9\\-_]", "_");

            button.click();
            Thread.sleep(500); // wait for animation to finish

            // Optional: print content
            try {
                WebElement parent = button.findElement(By.xpath(".."));
                WebElement expandedContent = parent.findElement(By.xpath("following-sibling::*[1]"));
                System.out.println("Expanded Content:");
                System.out.println(expandedContent.getText());
                System.out.println("--------------------------------------");
            } catch (Exception inner) {
                System.out.println("Could not extract content for: " + sectionName);
            }

            // Take screenshot of full page (or crop later)
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("screenshots/" + sectionName + ".png"));

            System.out.println("Screenshot saved: " + sectionName + ".png");

        } catch (Exception e) {
            System.out.println("Error expanding or capturing section: " + e.getMessage());
        }
    }
}

}




