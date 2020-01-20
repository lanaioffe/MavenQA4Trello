package ru.stqa.selenium;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.BoardsPageHelper;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.pages.LoginPageHelper;


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


    @Test
    public void loginIncorrectPassNegative()  {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD + "1")
                .waitUntilPasswordError();

        Assert.assertTrue(loginPage.verifyIfPasswordErrorIsCorrect(), "Error message is not correct");

    }

    @Test
    public void loginIncorrectLoginNegative() {
        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
//        loginPage.loginToTrello(LOGIN + "1", PASSWORD);
        loginPage.loginToTrello("abc@mai.com", "hjdhj")
                .waitUntilLoginError();
        Assert.assertTrue(loginPage.verifyIfLoginErrorIsCorrect(), "Error login message is not correct");
    }


}