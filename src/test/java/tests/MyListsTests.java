package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase
{
    private static final String name_of_folder = "Learning programming";
    private static final String
        login = "learnKirill",
        password = "Gitbashudar1";

    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("bject-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();

        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.waitDelay();
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMV()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            ArticlePageObject.waitDelay();
            Auth.clickAuthButton();
            ArticlePageObject.waitDelay();
            Auth.enterLoginData(login,password);
            ArticlePageObject.waitDelay();
            Auth.submitForm();
            ArticlePageObject.waitDelay();

            ArticlePageObject.waitForTitleElement();
            assertEquals(
                    "We are not on the same page after login",
                    article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        if(Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.openNavigation();
        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isIOS()) {
            MyListsPageObject.closeModalWindowInMyArticles();
        }
        if(Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        }
            MyListsPageObject.swipeByArticleToDelete(article_title);
    }
    @Test
    public void testSaveTwoArticleCheck() {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("Indonesian island");
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.waitForTitleElement();

        String first_article_title = "Java";
        String article_title = "Java (programming language)";


        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyList(name_of_folder);
        } else {
            ArticlePageObject.waitDelay();
            ArticlePageObject.addArticlesToMySaved();
        }

        if (Platform.getInstance().isMV()) {
            AuthorizationPageObject Auth = new AuthorizationPageObject(driver);
            ArticlePageObject.waitDelay();
            Auth.clickAuthButton();
            ArticlePageObject.waitDelay();
            Auth.enterLoginData(login,password);
            ArticlePageObject.waitDelay();
            Auth.submitForm();
            ArticlePageObject.waitDelay();

            ArticlePageObject.waitForTitleElement();
            assertEquals(
                    "We are not on the same page after login",
                    first_article_title,
                    ArticlePageObject.getArticleTitle()
            );

            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        }

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubString("bject-oriented programming language");
        ArticlePageObject.waitForTitleElement();

        if (Platform.getInstance().isAndroid()) {
            ArticlePageObject.addArticleToMyCreatedList();
        } else {
            ArticlePageObject.waitDelay();
            ArticlePageObject.addArticlesToMySaved();
        }
        ArticlePageObject.closeArticle();

        if (Platform.getInstance().isIOS()) {
            SearchPageObject.clickCancelSearch();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        if (Platform.getInstance().isMV()) {
            NavigationUI.openNavigation();
        }

        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName(name_of_folder);
        } else if (Platform.getInstance().isIOS()) {
            MyListsPageObject.closeModalWindowInMyArticles();
        }

        MyListsPageObject.swipeByArticleToDelete(article_title);

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.openFolderByName("Java");

            String expected_title_of_saved_article = "Java";

            String real_title_of_saved_article = ArticlePageObject.getArticleTitleWithWaiting();

            assertEquals(
                    "Title of the page is not equal to expected result",
                    expected_title_of_saved_article,
                    real_title_of_saved_article
            );
        } else {
            MyListsPageObject.checkNotDeleteArticleToPresent();
        }
    }
}
