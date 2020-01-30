package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public  class HomePageHelper extends PageBase {
    @FindBy(xpath = "//a[@class='btn btn-sm btn-link text-white']")
    WebElement loginIcon;

    public HomePageHelper (WebDriver driver){
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(loginIcon,40);
    }

    public void openLoginPage(){
        log.info("Start: method openLoginPage");
        log.info("Click on the login button");
        loginIcon.click();
    }

}

