        import io.appium.java_client.AppiumDriver;
        import io.appium.java_client.android.AndroidDriver;
        import org.junit.After;
        import org.junit.Assert;
        import org.junit.Before;
        import org.junit.Test;
        import org.openqa.selenium.By;
        import org.openqa.selenium.WebElement;
        import org.openqa.selenium.remote.DesiredCapabilities;
        import org.openqa.selenium.support.ui.ExpectedConditions;
        import org.openqa.selenium.support.ui.WebDriverWait;

        import java.net.URL;

public class FirstTest {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "nexus");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "C:\\Users\\user\\Desktop\\JavaHomework1.1\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void firstTest()
    {

        waitForElementPresentByXpath(
                "//*[contains(@text,'In the news')]",
                "Cannot find element on the screen",
                5
        );
        assertElementHasText(
                "//*[contains(@text,'In the news')]",
                "In the news",
                "Unexpected text"
        );


    }

    @Test
    public  void secondTest()
    {
        waitForElementAndClick(
                "//*[contains(@text,'Search Wikipedia')]",
                "Cannot find search Wiki input",
                5
        );
        waitForElementPresentByXpath(
                "//*[contains(@text,'Search…')]",
                "Cannot find element on the screen",
                5
        );
        assertElementHasText(
                "//*[contains(@text,'Search…')]",
                "Search…",
                "Unexpected text"
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message)
    {
        return waitForElementPresentByXpath(xpath, error_message, 5);
    }

    private WebElement waitForElementAndClick(String xpath, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(String xpath, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private void assertElementHasText(String xpath, String expectedText, String error_message) {
        WebElement text_element = driver.findElementByXPath(String.valueOf(xpath));


        String actual_text = text_element.getText();
        Assert.assertEquals(
                error_message,
                expectedText,
                actual_text
        );

    }


}

