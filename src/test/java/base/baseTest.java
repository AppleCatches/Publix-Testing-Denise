package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.time.Duration;

public class baseTest {
    protected WebDriver driver;

    @BeforeClass
    public void setUp() {
        // You can switch between drivers if needed
        // Example: System.setProperty("webdriver.gecko.driver", "path/to/geckodriver");
        // driver = new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver", "/home/ryan/Java_Dependicies/Webdrivers/chromedriver");
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
