package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArtcilePageObject extends ArticlePageObject
{
    static {
        TITLE = "xpath://*[contains(@name,'Java (programming language)')]";
        FOOTER_ELEMENT = "xpath://*[contains(@text,'View article in browser')]";
        OPTIONS_ADD_TO_MY_LIST_BUTTON = "id:Save for later";
        CLOSE_ARTICLE_BUTTON = "id:Back";
        MY_CREATED_FOLDER = "id:Back";
    }

    public IOSArtcilePageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
