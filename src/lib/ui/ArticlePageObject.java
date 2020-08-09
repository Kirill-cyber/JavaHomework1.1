package lib.ui;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            FOOTER_ELEMENT = "//*[@text='View page in browser']",
            OPTIONS_BUTTON = "//android.widget.ImageView[@content-desc='More options']",
            OPTIONS_ADD_TO_MY_LIST_BUTTON = "/hierarchy/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ListView/android.widget.LinearLayout[3]/android.widget.RelativeLayout/android.widget.TextView",
            ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
            MY_LIST_NAME_INPUT = "org.wikipedia:id/text_input",
            MY_LIST_OK_BUTTON = "//*[@text='OK']",
            CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton[@content-desc='Navigate up']",
            MY_CREATED_FOLDER = "//android.widget.TextView[@text='Learning programming']";

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }
    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }
    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }
    public void swipeToFooter()
    {
        this.swipeUpToFindElement(
                By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of the article",
                20
        );
    }
    public void addArticleToMyList(String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find More Options button",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find Add to reading button",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find GOT IT tip overlay",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of article folder",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot put text into article folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot find OK button",
                5
        );
    }
    public void addArticleToMyCreatedList()
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find More Options button",
                5
        );
        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find Add to reading button",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_CREATED_FOLDER),
                "Cannot find GOT IT tip overlay",
                5
        );
    }
    public void closeArticle()
    {
        this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5
        );
    }
    public void checkTitleFromArticleWithoutWaiting()
    {
        assertElementPresent(
                By.id(TITLE),
                "Cannot find title of article"
        );
    }
    public String getArticleTitleWithWaiting()
    {
        return waitForElementAndGetAttribute(
                By.id(TITLE),
                "text",
                "Cannot get text attribute from title",
                15
        );
    }
}