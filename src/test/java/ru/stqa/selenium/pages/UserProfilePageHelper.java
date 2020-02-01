package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class UserProfilePageHelper extends PageBase {
    @FindBy(xpath = "//input[@name='initials']")
    WebElement initialsField;

    @FindBy(xpath = "//div[@title='lanaioffe (lanaioffe2)']/span")
    List <WebElement> listInitialsToVerify;

    @FindBy(xpath = "//button[contains(text(),'Save')]")
    WebElement saveButton;

    @FindBy (xpath = "//input[@name='username']")
    WebElement userName;

    @FindBy (xpath = "//textarea[@class='_32NOj1SsaXIXYw']")
    WebElement bioField;

    public UserProfilePageHelper(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        waitUntilElementIsClickable(initialsField,30);
    }

    public String getInitialsFromInitialsField() {
        return initialsField.getAttribute("value");
    }


    public boolean verifyIfInitialsDisplayedCorrectly(String initials) {
        int counter = 0;
        if (listInitialsToVerify.size()==2) counter++;
        if (listInitialsToVerify.get(0).getText().equals(initials)) counter++;
        if (listInitialsToVerify.get(1).getText().equals(initials)) counter++;
        return counter == 3;
    }

//    public void changeInitials(String value) throws InterruptedException {
//        enterValueToTheField(initialsField, value);
//        Thread.sleep(10000);
//    }

    public UserProfilePageHelper changeInitials(String value) {
        //enterValueToTheField(initialsField,value);
        enterValueToAutoCompleteField(initialsField,value);
        return this;
    }

    public UserProfilePageHelper saveProfile() {
        saveButton.click();
        /*try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        return this;
    }

    public UserProfilePageHelper changeUserName(String value) {
        enterValueToAutoCompleteField(userName, value);
        return this;
    }

    public UserProfilePageHelper changeBio(String value) {
        enterValueToTheField(bioField,value);
        return this;
    }

    public UserProfilePageHelper waitInitials(String value) {
        waitUntilTextToBeInElement(listInitialsToVerify.get(0), value, 15);
        return this;
    }

}
