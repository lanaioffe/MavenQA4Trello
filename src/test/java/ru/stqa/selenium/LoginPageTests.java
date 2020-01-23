package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.BoardsPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;
import ru.stqa.selenium.util.DataProviders;


public class LoginPageTests extends TestBase {

    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;

    @BeforeMethod
    public void initTests() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
    }

    @Test
    public void loginToTrelloPositive()  {

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        Assert.assertTrue(boardsPage.verifyIfBoardsIconIsDisplayed());
        Assert.assertTrue(boardsPage.verifyIfPersonalBoardsHeaderIsDisplayed());
    }


    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirstPsw")
    public void loginIncorrectPassNegative(String login, String psw, String message)  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
//        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD + "1");
        loginPage.loginToTrelloAsAtlassian(login, psw);
        loginPage.waitUntilPasswordError();
        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(), "Error message is not correct");
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirst")
    public void loginIncorrectLoginNegative(String login, String psw, String message) {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
//        loginPage.loginToTrello(LOGIN + "1", PASSWORD);
//        loginPage.loginToTrello("abc@mai.com", "hjdhj");
        loginPage.loginToTrello(login, psw);
        loginPage.waitUntilLoginError();
//        Assert.assertTrue(loginPage.verifyIfLoginErrorIsCorrect(), "Error login message is not correct");
        Assert.assertEquals(message, loginPage.getLoginError());
    }

    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderSecond")
    public void loginIncorrectLoginNegative2(String login, String psw) {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrello(login, psw);
        loginPage.waitUntilLoginError();
        Assert.assertEquals("There isn't an account for this email", loginPage.getLoginError());

    }

}