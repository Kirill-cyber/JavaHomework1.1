package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;

abstract public class MyListsPageObject extends MainPageObject{

    protected static String
        FOLDER_BY_NAME_TPL,
        CLOSE_MODAL_WINDOW_IN_MY_ARTICLES,
        DELETE_SAVED_ARTICLE_AFTER_SWIPE_BUTTON,
        ARTICLE_SUBTITLE,
        ARTICLE_BY_TITLE_TPL,
            REMOVE_FROM_SAVED_BUTTON;

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", article_title);
    }

    private static String getRemoveButtonByTitle(String article_title)
    {
        return REMOVE_FROM_SAVED_BUTTON.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(RemoteWebDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);

        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name" + name_of_folder,
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(article_xpath, "Cannot find saved article by title " + article_title, 15);
    }

    public void waitForArticleToDisappearByTitle(String article_title)
    {
        String article_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(article_xpath, "Saved article still present with title" + article_title, 15);
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_xpath = getSavedArticleXpathByTitle(article_title);

        if (Platform.getInstance().isIOS() || Platform.getInstance().isAndroid()) {
            this.swipeElementToLeft(
                    article_xpath,
                    "Cannot find saved article"
            );
//        if(Platform.getInstance().isIOS()) {
//            this.clickElementToTheRightUpperCorner(article_xpath, "Cannot find saved article");
//        }
        } else {
            String remove_locator = getRemoveButtonByTitle(article_title);
            this.waitForElementAndClick(
                    remove_locator,
                    "Cannot click button to remove article from saved",
                    10
            );
        }


        if (Platform.getInstance().isIOS()) {
            this.deleteSavedArticleAfterSwipe_forIOS();
        } else if (Platform.getInstance().isMV()) {
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
            driver.navigate().refresh();
            try
            {
                Thread.sleep(2000);
            }
            catch(InterruptedException ex)
            {
                Thread.currentThread().interrupt();
            }
        }
        this.waitForArticleToDisappearByTitle(article_title);


    }

    public void closeModalWindowInMyArticles()
    {
        this.waitForElementAndClick(CLOSE_MODAL_WINDOW_IN_MY_ARTICLES,
                "Cannot find and click close first modal window in my articles",
                7);
    }

    public void deleteSavedArticleAfterSwipe_forIOS()
    {
        this.waitForElementAndClick(DELETE_SAVED_ARTICLE_AFTER_SWIPE_BUTTON,
                "Cannot find and click delete button after swiping the article in my article",
                7);
    }

    public Boolean checkNotDeleteArticleToPresent()
    {
        this.waitForElementPresent(ARTICLE_SUBTITLE,
                "Cannot find subtitle after deleting last article",
                7);
        return true;
    }

    public String getArticleSubtitleWithWaiting()
    {
        return waitForElementAndGetAttribute(
                ARTICLE_SUBTITLE,
                "name",
                "Cannot get attribute from subtitle",
                15
        );
    }



}
