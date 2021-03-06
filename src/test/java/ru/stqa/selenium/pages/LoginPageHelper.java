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

//    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Continue')]")
    @FindBy(xpath = "//button[@id='login-submit']//span[contains(text(),'Continue')]/../../..")
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
        log.info("Start: method waitUntilPageIsLoaded(), class LoginPageHelper");
        log.info("Wait until login button is clickable");
        waitUntilElementIsClickable(loginButton,30);
    }

    public LoginPageHelper  enterAtlLogin(String login) {
        enterValueToTheField(userField, login);
        return this;
    }

    public void clickLoginWithAtlassian() {
        waitUntilElementIsClickable(loginButton, 10);
        loginButton.click();
    }

    public void clickContinueButton()  {
        waitUntilElementIsClickable(continueButton,10);
        continueButton.click();
    }

    public void enterAtlPasswordAndLogin(String password) {
        waitUntilElementIsClickable(passwordButton,30);
        waitUntilElementIsClickable(theSecondLoginButton,30);
        passwordButton.sendKeys(password);
        theSecondLoginButton.click();
    }

    public LoginPageHelper loginToTrelloAsAtlassian(String login, String password)  {
        log.info("Start: method loginToTrelloAsAtlassian(" + login + "," + password + ")");
        log.info("Enter login - " + login);
        this.enterAtlLogin(login);
        log.info("Click on 'Login with Atlassian' button");
        this.clickLoginWithAtlassian();
        log.info("Click on 'Continue' button");
        this.clickContinueButton();
        log.info("Enter password - " + password);
        this.enterAtlPasswordAndLogin(password);
        return this;
    }

    public LoginPageHelper waitUntilPasswordError() {
        log.info("Start: method waitUntilPasswordError()");
        log.info("Wait until error message is displayed");
        waitUntilElementIsVisible(passwordErrorMessage,10);
        return this;
    }

    public boolean verifyIfPasswordErrorIsCorrect(){
        log.info("Start: method verifyIfPasswordErrorIsCorrect()");
        log.info("Verify if password error is correct");
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
        log.info("Start: method loginToTrello(" + login + ", " + password + ")");
        log.info("Enter login - " + login);
        this.enterLogin(login);
        log.info("Enter password - " + password);
        this.enterPassword(password);
        log.info("Click on login button");
        this.clickLogin();
        return this;
    }

    public LoginPageHelper waitUntilLoginError() {
        log.info("Start: method waitUntilLoginError()");
        this.clickLogin();
        log.info("Wait until error message is displayed");
        waitUntilElementIsVisible(loginErrorMessage,20);
//        System.out.println("Error text: " + loginErrorMessage.getText());
        return this;
    }

    public boolean verifyIfLoginErrorIsCorrect(){
        return loginErrorMessage.getText().contains("There isn't an account for this email");
    }

    public String getLoginError (){
        log.info("Start: method getLoginError");
        log.info("Get error text - " + loginErrorMessage.getText());
        return loginErrorMessage.getText();
    }



}
