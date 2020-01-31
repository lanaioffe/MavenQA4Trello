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
    public void loginToTrelloPositive() {
        log.startTestCase("loginToTrelloPossitive");
        log.info("-- Open login page");
        homePage.openLoginPage();
        log.info("-- Wait until login page is loaded");
        loginPage.waitUntilPageIsLoaded();
        log.info("-- Enter Atlassian login/password - " + LOGIN + "/" + PASSWORD);
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD);
        log.info("-- Wait until boards page is loaded");
        boardsPage.waitUntilPageIsLoaded();
        log.verify("'Boards' icon is displayed");
        Assert.assertTrue(boardsPage.verifyIfBoardsIconIsDisplayed());
        log.verify("'Personal board header is displayed");
        Assert.assertTrue(boardsPage.verifyIfPersonalBoardsHeaderIsDisplayed());
        log.endTestCase();
    }


    @Test(dataProviderClass = DataProviders.class, dataProvider = "dataProviderFirstPsw")
    public void loginIncorrectPassNegative(String login, String psw, String message)  {
        log.startTestCase("loginIncorrectPassNegative");
        log.info("--Open login page");
        homePage.openLoginPage();
        log.info("-- Wait until login page is loaded");
        loginPage.waitUntilPageIsLoaded();
//        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD + "1");
        log.info("-- Enter Atlassian login/password - " + login + "/" + psw);
        loginPage.loginToTrelloAsAtlassian(login, psw);
        log.info("-- Wait until error is displayed");
        loginPage.waitUntilPasswordError();
        log.verify("Error message is '" + message + "'");
        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(), "Error message is not correct");
        log.endTestCase();
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
    public void loginIncorrectLoginNegative1(String login, String psw, String message) {
        log.startTestCase("loginIncorrectLoginNegative1()");
        log.info("--- login - " + login + ", password - " + psw + "---");
        log.info("--- message - " + message + "---");
        log.info("-- Open login page");
        homePage.openLoginPage();
        log.info("-- Wait until login page is loaded");
        loginPage.waitUntilPageIsLoaded();
        log.info("-- Login to Trello, not Atlassian, login/password - " + login + "/" + psw);
        loginPage.loginToTrello(login, psw);
        log.info("-- Wait until error is displayed");
        loginPage.waitUntilLoginError();
        log.verify("Error message is '" + message + "'");
        Assert.assertEquals(message, loginPage.getLoginError());
        log.endTestCase();
    }

}