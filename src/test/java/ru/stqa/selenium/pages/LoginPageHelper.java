package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class LoginPageHelper extends PageBase{
    @FindBy(id = "login")
    WebElement loginButton;

    @FindBy(id = "user")
    WebElement userField;

    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Log in')]")
    WebElement theSecondLoginButton;

    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Continue')]")
    WebElement continueButton;

    @FindBy(id = "password")
    WebElement passwordButton;

    @FindBy(xpath = "//div[@id = 'login-error']/span")
    WebElement passwordErrorMessage;

    @FindBy(xpath = "//p[@class = 'error-message']")
    WebElement loginErrorMessage;


    public LoginPageHelper(WebDriver driver){
        super(driver);
    }

    public void waitUntilPageIsLoaded(){
        waitUntilElementIsClickable(loginButton,30);
    }

    public void  enterAtlLogin(String login) {
        enterValueToTheField(userField, login);

    }

    public void clickLoginWithAtlassian() {
        waitUntilElementIsClickable(loginButton, 10);
        loginButton.click();
    }

    public void clickContinueButton() {
        waitUntilElementIsClickable(continueButton,30);
        continueButton.click();
    }

    public void enterAtlPasswordAndLogin(String password) {
        waitUntilElementIsClickable(passwordButton,30);
        waitUntilElementIsClickable(theSecondLoginButton,30);
        passwordButton.sendKeys(password);
        theSecondLoginButton.click();
    }

    public LoginPageHelper loginToTrelloAsAtlassian(String login, String password){
        this.enterAtlLogin(login);
        this.clickLoginWithAtlassian();
        this.clickContinueButton();
        this.enterAtlPasswordAndLogin(password);
        return this;
    }

    public LoginPageHelper waitUntilPasswordError() {
        waitUntilElementIsVisible(passwordErrorMessage,10);
        return this;
    }

    public boolean verifyIfPasswordErrorIsCorrect(){
        return passwordErrorMessage.getText().contains("Incorrect email address and / or password.");
    }

    public void enterLogin(String login){
        userField.click();
        userField.clear();
        userField.sendKeys(login);
    }

    public void enterPassword(String password){
        passwordButton.click();
        passwordButton.clear();
        passwordButton.sendKeys(password);
    }

    public void clickLogin() {
        waitUntilElementIsClickable(loginButton, 10);
        loginButton.click();
    }

    public LoginPageHelper loginToTrello (String login, String password){
        this.enterLogin(login);
        this.enterPassword(password);
        this.clickLogin();
        return this;
    }

    public LoginPageHelper waitUntilLoginError() {
        this.clickLogin();
        waitUntilElementIsVisible(loginErrorMessage,20);
        System.out.println("Error text: " + loginErrorMessage.getText());
        return this;
    }

    public boolean verifyIfLoginErrorIsCorrect(){
        return loginErrorMessage.getText().contains("There isn't an account for this email");
    }

    public String getLoginError (){
        return loginErrorMessage.getText();
    }



}
