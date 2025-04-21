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

public class orderAhead {
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

    @Test(priority = 1)
    public void orderAheadDD() throws InterruptedException {
        Thread.sleep(2000);
        driver.get("https://www.publix.com/");

        //testing subs and more
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/div/div[2]/div/ul/li[2]/div/button")
        ));
        dropdown.click();
        dropdown.findElement(By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/div/div[2]/div/ul/li[2]/div/div/ul/li[1]/a/span[2]"))
                .click();
        //store entry
        WebElement zipCodeEntry = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[1]/form/input")
        ));
        zipCodeEntry.sendKeys("33965");
        Thread.sleep(2500);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[1]/form/button")
        ));
        Thread.sleep(1500);
        searchButton.click();
        Thread.sleep(1000);

        WebElement chooseStore = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[2]/ul/li[1]/div/div/div[2]/div[1]/div/button/span")
        ));
        chooseStore.click();
        Thread.sleep(2000);


        //testing meals
        driver.get("https://www.publix.com/");

        WebElement orderAhead2 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/div/div[2]/div/ul/li[2]/div/button")
        ));
        orderAhead2.click();
        WebElement meals = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/div/div[2]/div/ul/li[2]/div/div/ul/li[6]/a/span[2]")
        ));
        meals.click();
        Thread.sleep(2000);


        //testing meat and cheese
        driver.get("https://www.publix.com/");

        WebElement orderAhead3 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/div/div[2]/div/ul/li[2]/div/button")
        ));
        orderAhead3.click();
        WebElement meatAndCheese = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div[2]/header/div[1]/div/div[3]/div/div/div[2]/div/ul/li[2]/div/div/ul/li[2]/a/span[2]")
        ));
        meatAndCheese.click();
        Thread.sleep(2000);

    }


    @Test(priority = 2)
    public void pubsubOrder() throws InterruptedException {
        driver.get("https://www.publix.com/menu-subs-and-wraps?nav=header");
        Thread.sleep(1000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        /*
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
        */

        //pubsub order
        driver.get("https://www.publix.com/pd/publix-chicken-tender-sub/BMO-DSB-100011");
        Thread.sleep(1000);

        WebElement halfSub = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div/div[1]/div[2]/fieldset[1]/div[2]/div[1]/label")
        ));
        halfSub.click();
        Thread.sleep(1000);


        //add to order
        WebElement addToOrderButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div/div[1]/div[2]/div[1]/div/div[2]/button/span")
        ));
        addToOrderButton.click();
        Thread.sleep(1500);

        WebElement reviewOrder = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div[2]/button/span")
        ));
        reviewOrder.click();
        Thread.sleep(500);

        WebElement confirmStore = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/div[2]/div/div[3]/div/div/button[2]/span[2]")
        ));
        confirmStore.click();
        Thread.sleep(5000);


    }

    @Test(priority = 3)
    public void meatAndCheeseOrder() throws InterruptedException {
        driver.get("https://www.publix.com/mc/order-ahead/order-deli-meat-and-cheese?nav=header");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        /*
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


        */
        //ham order
        driver.get("https://www.publix.com/pd/boars-head-maple-glazed-honey-coat-ham/RIO-DSM-100244?origin=collections3");
        Thread.sleep(2000);

        //thickness dropdown
        WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("form-field-180")
        ));
        Select dd = new Select(dropdown);

        dd.selectByValue("3-65");
        Thread.sleep(500);
        dd.selectByValue("3-52");
        Thread.sleep(500);
        dd.selectByValue("3-64");
        Thread.sleep(500);

        //weight dropdown
        WebElement dropdown2 = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("form-field-202")
        ));
        Select dd2 = new Select(dropdown2);

        dd2.selectByValue("4-47");
        Thread.sleep(500);
        dd2.selectByValue("4-906");
        Thread.sleep(500);
        dd2.selectByValue("4-50");
        Thread.sleep(500);

        //add to order
        WebElement addToOrderButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div/div[2]/div[2]/div[2]/div/div[3]/div/div/button/span")
        ));
        addToOrderButton.click();
        Thread.sleep(1000);
        WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/a/span")
        ));
        continueShopping.click();
        Thread.sleep(1000);

        //cheese order
        driver.get("https://www.publix.com/mc/order-ahead/order-deli-meat-and-cheese?nav=header");
        Thread.sleep(2000);
        driver.get("https://www.publix.com/pd/publix-deli-american-cheese-white/RIO-DSC-100216?origin=collections9");
        Thread.sleep(2000);

        WebElement addToOrderCheese = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div/div[2]/div[2]/div[2]/div/div[3]/div/div/button/span")
        ));
        addToOrderCheese.click();
        Thread.sleep(500);

        WebElement reviewOrder = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div[2]/button/span")
        ));
        reviewOrder.click();
        Thread.sleep(500);
/*
        WebElement confirmStore = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/div[2]/div/div[3]/div/div/button[2]/span[2]")
        ));
        confirmStore.click();

 */
        Thread.sleep(2000);

    }

    @Test (priority = 4)
    public void mealCartManagement () throws InterruptedException {
        driver.get("https://www.publix.com/mc/order-ahead/hot-prepared-meals?nav=header");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        /*
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
        */


        //meal order
        driver.get("https://www.publix.com/pd/publix-deli-crispy-chicken-sandwich-box/RIO-SHI-287041");
        Thread.sleep(2000);

        //fries
        WebElement fries = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div/div[2]/div[2]/div[2]/div/div[2]/fieldset/div[2]/div[1]/label")
        ));
        fries.click();
        Thread.sleep(500);
        //onion rings
        WebElement onionRings = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div/div[2]/div[2]/div[2]/div/div[2]/fieldset/div[2]/div[2]/label")
        ));
        onionRings.click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        for (int i = 0; i < 2; i++) {
            js.executeScript("window.scrollBy(0, 100);");
            Thread.sleep(100); // adjust delay for smoothness
        }
        Thread.sleep(3000);
        //add one
        WebElement addOne = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div/div[2]/div[2]/div[2]/div/div[4]/div/div[2]/div/div[3]/button")
        ));
        Thread.sleep(500);
        addOne.click();
        Thread.sleep(2000);
        //add to order
        WebElement addToOrder = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("add-to-order-btn")
        ));
        addToOrder.click();
        Thread.sleep(500);
        //review order
        WebElement reviewOrder = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div[2]/button/span")
        ));
        reviewOrder.click();
        Thread.sleep(500);
        /*
        //pickup at this store
        WebElement pickupAtThisStore = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/div[2]/div/div[3]/div/div/button[2]/span[2]")
        ));
        pickupAtThisStore.click();
        Thread.sleep(500);

         */
        //duplicate
        WebElement duplicate = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div[1]/div[2]/ul/li[1]/div/div[5]/div/button[1]/span")
        ));
        duplicate.click();
        Thread.sleep(500);
        // -1 on top order
        WebElement minus1 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div[1]/div[2]/ul/li[1]/div/div[4]/form/div/div[1]/button/span")
        ));
        minus1.click();
        Thread.sleep(500);
        //remove
        WebElement remove = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div[1]/div[2]/ul/li[1]/div/div[5]/div/button[2]/span")
        ));
        remove.click();
        Thread.sleep(500);
        //change store
        WebElement changeStore = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div[1]/div[1]/div/div/div[2]/div[2]/div/div/button/span")
        ));
        changeStore.click();
        Thread.sleep(500);
        //santa barbara centre
        WebElement santaBarbara = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div/div/div[2]/ul/li[2]/div/div/div[2]/div[1]/div/button/span")
        ));
        santaBarbara.click();
        Thread.sleep(5000);

    }

    @Test (priority = 5)
    public void fishAndCheckout () throws InterruptedException {
        driver.get("https://www.publix.com/mc/order-ahead/seafood-menu?nav=header");
        Thread.sleep(2000);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        /*
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
*/


        //mahi mahi order
        driver.get("https://www.publix.com/pd/salmon-select-cuts-fresh-responsibly-sourced-farmed/RIO-FNU-591951");
        Thread.sleep(2000);
        //custom order amount
        WebElement quantityEntry = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("form-field-82")
        ));
        quantityEntry.clear();
        quantityEntry.sendKeys("5");
        Thread.sleep(500);
        quantityEntry.clear();
        quantityEntry.sendKeys("37");
        Thread.sleep(500);

        //add to order
        WebElement addToOrder = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div/div[2]/div[2]/div[2]/div/div[2]/div[2]/div/button/span")
        ));
        addToOrder.click();
        Thread.sleep(500);
        //review order
        WebElement reviewOrder = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/header/div[1]/div/div[3]/div/div/div[1]/div[2]/div/div/div/div[2]/div[2]/div/div[1]/div[2]/div/div[2]/button/span")
        ));
        reviewOrder.click();
        Thread.sleep(500);
        /*
        //pick up at this store
        WebElement pickupAtThisStore = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/div[2]/div/div[3]/div/div/button[2]/span[2]")
        ));
        pickupAtThisStore.click();
        Thread.sleep(500);

         */
        //checkout
        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[2]/div/div[2]/div/div/div/div[3]/button/span")
        ));
        Thread.sleep(500);
        checkout.click();
        Thread.sleep(1000);
        //first name
        WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("input_FirstName70")
        ));
        firstName.sendKeys("Trey");
        Thread.sleep(500);
        //last name
        WebElement lastName = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("input_LastName71")
        ));
        lastName.sendKeys("Anastasio");
        Thread.sleep(500);
        //phone number
        WebElement phoneNumber = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("input_ContactPhone72")
        ));
        phoneNumber.sendKeys("4018675309");
        Thread.sleep(500);
        //email
        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("input_Email73")
        ));
        email.sendKeys("PhishGuy@gmail.com");
        Thread.sleep(500);
        //next
        WebElement next = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[1]/div[1]/div[4]/div[2]/form/div[3]/button")
        ));
        next.click();
        Thread.sleep(1000);
        //pickup date
        WebElement pickupDate = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("input_pickupDate130")
        ));
        pickupDate.click();
        Thread.sleep(500);
        //30th
        WebElement chooseDay = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[1]/div[1]/div[5]/div[2]/form/div[2]/div[1]/div/div/div[2]/div/table/tbody/tr[5]/td[4]/button")
        ));
        chooseDay.click();
        Thread.sleep(500);
        //pickup time dropdown
        WebElement timeDD = wait.until(ExpectedConditions.elementToBeClickable(
                By.id("input_pickupTime131")
        ));
        Select time = new Select(timeDD);
        time.selectByValue("2025-04-30T11:15:00");
        Thread.sleep(2000);
        //next
        WebElement next2 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("/html/body/div/section/div[1]/div[1]/div[5]/div[2]/form/div[3]/div/button")
        ));
        next2.click();
        Thread.sleep(500);


    }

}