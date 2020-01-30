package ru.stqa.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CurrentBoardPageHelper extends PageBase {
    String name;

    @FindBy(css = ".placeholder")
    WebElement addListButton;

    @FindBy(xpath = "//span[@dir='auto']")
    WebElement currentBoardName;

    @FindBy(css = ".list-name-input")
    WebElement inputNameField;

//    @FindBy (xpath = "//input[@type='submit']")
    @FindBy(xpath = "//input[@class='primary mod-list-add-button js-save-edit']")
WebElement submitAddListButton;

    @FindBy(css = "a.js-cancel-edit")
    WebElement theXButton;

    @FindBy(xpath = "//h2/../textarea")
    List <WebElement> listNamesOfAllLists;

    @FindBy(xpath = "//h2")
    List <WebElement> listOfAllLists;

    @FindBy(xpath = "//button[@data-test-id = 'header-member-menu-button']")
    WebElement openProfileMenuButton;

    @FindBy(xpath = "//span[contains(text(),'Profile and Visibility')]")
    WebElement profileAndVisibilityButton;

    @FindBy(css = ".js-open-list-menu")
    WebElement listActionsButton;

    @FindBy(css = ".js-close-list")
    WebElement archiveListButton;

    @FindBy(xpath = "//span[@class= 'js-add-another-card']")
    List <WebElement> listOfAddAnotherCardButton;

    @FindBy(xpath = "//span[@class = 'js-add-a-card']")
    List <WebElement> listOfAddACardButton;

    @FindBy(xpath = "//input[@class='primary confirm mod-compact js-add-card']")
    WebElement submitAddCardButton;

    @FindBy(xpath = "//textarea[@placeholder='Enter a title for this card…']")
    WebElement cardNameField;

    @FindBy(css = "a.js-cancel")
    WebElement theXAddCardButton;

    public CurrentBoardPageHelper(WebDriver driver) {
        super(driver);
    }

    public void setName (String name){
        this.name = name;
    }

    @Override
    public void waitUntilPageIsLoaded() {
        log.startTestCase("waitUntilPageIsLoaded, class CurrentBoardPageHelper");
        log.info("Wait until element is clickable");
        waitUntilElementIsClickable(addListButton,30);
    }

    public boolean titleVerification(){
        log.startTestCase("titleVerification, class CurrentBoardPageHelper");
        log.verify("CurrentBoardName equals name");
        return currentBoardName.getText().equals(name);
    }

    public void createNewList(String name){
        addListButton.click();
        waitUntilElementIsVisible(inputNameField,10);
        inputNameField.sendKeys(name);
        submitAddListButton.click();
        waitUntilElementIsClickable(theXButton,10);
        theXButton.click();
    }

    public String getAddButtonName() {
        return addListButton.getText();
    }

    public boolean existsList(String nameList) {
        boolean exitName = false;
        for(WebElement element: listNamesOfAllLists){
                if(element.getText().equals(nameList)) exitName = true;
            }
        return exitName;
    }

    public int getQuantityLists() {
        return listOfAllLists.size();
    }

    public void deleteList() {
        waitUntilElementIsClickable(listActionsButton,10);
        listActionsButton.click();
        waitUntilElementIsClickable(archiveListButton,10);
        archiveListButton.click();
    }

    public int getQuantityAddAnotherCard() {
        waitUntilAllElementsAreVisible(listOfAddAnotherCardButton, 10);
        return  listOfAddAnotherCardButton.size();
    }

    public int getQuantityAddACard() {
        waitUntilAllElementsAreVisible(listOfAddACardButton,15);
        return  listOfAddACardButton.size();
    }

    public void addFirstCardForNewList(String cardName) {
        listOfAddACardButton.get(getQuantityAddACard() - 1).click();    //Get the last 'Add card' button (in a New List)
        waitUntilElementIsClickable(submitAddCardButton, 10);
        cardNameField.sendKeys(cardName);
        submitAddCardButton.click();
        waitUntilElementIsClickable(theXAddCardButton, 10);
        theXAddCardButton.click();
//        lastAddCardButton.click();
//        waitUntilElementIsClickable(By.xpath("//input[@class='primary confirm mod-compact js-add-card']"),10);
//        driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']")).sendKeys("text");
//        driver.findElement(By.xpath("//input[@class='primary confirm mod-compact js-add-card']")).click();
//        waitUntilElementIsClickable(By.cssSelector("a.js-cancel"),10);
//        driver.findElement(By.cssSelector("a.js-cancel")).click();
    }

    public void createACopyList(int numberOfList) {
        createNewList(listNamesOfAllLists.get(numberOfList-1).getText());
    }

    public boolean existsCopyOfAnyList (){
        boolean exitName = false;
        for (int i = 0; i < listNamesOfAllLists.size(); i++) {
            for (int j = i+1; j < listNamesOfAllLists.size(); j++){
                if(listNamesOfAllLists.get(i).getText().equals(listNamesOfAllLists.get(j).getText())){
                    exitName = true;
                    break;
                }
            }
        }
        return exitName;
    }

    public boolean existsCopyOfList (int numberOfList){
        boolean exitName = false;
        for (int i = 0; i < listNamesOfAllLists.size(); i++) {
            if(listNamesOfAllLists.get(i).getText().equals(listNamesOfAllLists.get(numberOfList-1).getText())){
                exitName = true;
                break;
            }

        }
        return exitName;
    }

    public void openUserProfile() {
        openProfileMenuButton.click();
        waitUntilElementIsClickable(profileAndVisibilityButton,10);
        profileAndVisibilityButton.click();
    }



}
