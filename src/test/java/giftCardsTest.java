// Maven_project_2 / src / test / tests / giftCardsTest.java

import base.baseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.Test;
import java.time.Duration;

public class giftCardsTest extends baseTest {
    WebDriverWait wait;
    @Test
    public void navigateToHomePage() {
        driver.get("https://www.publix.com");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            Thread.sleep(2000); // Slow down by 2 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(dependsOnMethods = "navigateToHomePage")
    public void selectGiftCardsMenu() {
        WebElement giftCardsLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Gift Cards")));
        giftCardsLink.click();
        try {
            Thread.sleep(2000); // Slow down by 2 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(dependsOnMethods = "selectGiftCardsMenu")
    public void selectMailGiftCard() {
        // Step 3: Click “Mail a gift card” using href locator
        WebElement mailGiftCardButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("a[href='/gift-cards/order/individual']")));
        mailGiftCardButton.click();
        try {
            Thread.sleep(2000); // Slow down by 2 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(dependsOnMethods = "selectMailGiftCard")
    public void customizeGiftCard() {
        // Step 4: Scroll down to ensure visibility of all elements
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, 500);");
        try {
            Thread.sleep(2000); // Slow down by 2 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Step 5: Increase quantity from 1 to 3 using increment button
        WebElement incrementButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button[aria-label*='increase quantity']")));
        incrementButton.click(); // 1 -> 2
        try {
            Thread.sleep(2000); // Slow down by 2 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        incrementButton.click(); // 2 -> 3

        // Step 6: Select $50 gift card amount
        WebElement amount50 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[@class='option-label' and text()='$50']")));
        amount50.click();
        try {
            Thread.sleep(2000); // Slow down by 2 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Step 7: Click the "Add a message" button (icon + text style)
        WebElement addMessageButton = wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("button.p-button.button--text.button--icon-left")));
        addMessageButton.click();
        try {
            Thread.sleep(2000); // Slow down by 2 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        // Step 8: Type custom message "From Grandma" in the textarea
        WebElement messageBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//textarea[@placeholder='Type custom message']")));
        messageBox.clear();
        messageBox.sendKeys("From Grandma");
        try {
            Thread.sleep(2000); // Slow down by 2 seconds
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
