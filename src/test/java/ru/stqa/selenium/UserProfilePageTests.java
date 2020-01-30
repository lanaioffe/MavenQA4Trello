package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.DataProviders;


import java.util.List;

public class UserProfilePageTests extends TestBase {
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa4AutoBoard;
    UserProfilePageHelper userProfile;

    @BeforeMethod
    public void initTest() {
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
        qa4AutoBoard = PageFactory.initElements(driver, CurrentBoardPageHelper.class);
        qa4AutoBoard.setName("QA 4 Auto");
        userProfile = PageFactory.initElements(driver, UserProfilePageHelper.class);

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();

        //----Open 'QA 4 Auto' board----
        boardsPage.openBoard("QA 4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();

        //----Open 'User Profile' page ----
        qa4AutoBoard.openUserProfile();
        userProfile.waitUntilPageIsLoaded();

    }

    @Test
    public void initialsVerification(){
        String initials = userProfile.getInitialsFromInitialsField();
        Assert.assertTrue(userProfile.verifyIfInitialsDisplayedCorrectly(initials),
                "Not all elements contains correct initials. It should be two elements");
    }

//    @Test
//    public void profileChangingVerification() throws InterruptedException {
//        userProfile.changeInitials("V.V.");
////        userProfile.changeUserName("lanaioffe");
////        userProfile.changeBio("some text");
//        userProfile.saveProfile();
//    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "profileChanging")
    public void profileChangingVerification(String init, String userName, String bio){
        userProfile.changeInitials(init)
                .changeUserName(userName)
                .changeBio(bio)
                .saveProfile()
                .waitInitials(init);
        Assert.assertTrue(userProfile.verifyIfInitialsDisplayedCorrectly(init),
                "Not all elements contains correct initials. It should be two elements");
    }
}
