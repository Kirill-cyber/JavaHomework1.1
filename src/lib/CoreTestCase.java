package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomePageObject;
import org.openqa.selenium.ScreenOrientation;
import java.time.Duration;

public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;



    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrate();
        this.skipWelcomePageForIOSApp();
    }

    @Override
    protected void tearDown() throws Exception
    {

        driver.quit();

        super.tearDown();
    }

    protected void rotateScreenPortrate()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void backgroundApp(long seconds)
    {
        driver.runAppInBackground(Duration.ofSeconds(seconds));
    }




    private void skipWelcomePageForIOSApp()
    {
        if (Platform.getInstance().isIOS())
        {
            WelcomePageObject WelcomePageObject = new WelcomePageObject(driver);
            WelcomePageObject.clickSkip();
        }
    }

}
