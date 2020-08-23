package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class IOSMyListsPageObject extends MyListsPageObject
{
    static {
        //ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeApplication[@name=\"Wikipedia\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeCollectionView/XCUIElementTypeCell/XCUIElementTypeOther[2]";
        DELETE_SAVED_ARTICLE_AFTER_SWIPE_BUTTON = "id:swipe action delete";
        CLOSE_MODAL_WINDOW_IN_MY_ARTICLES = "xpath://XCUIElementTypeButton[@name='Close']";
        ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[@name='{TITLE}']";
        ARTICLE_SUBTITLE = "id:Island of Indonesia";
        //ARTICLE_BY_TITLE_TPL = "xpath://XCUIElementTypeStaticText[contains(@name='{TITLE}')]";
    }

    public IOSMyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }
}
