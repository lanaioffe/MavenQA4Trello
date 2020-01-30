package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BoardsPageHelper extends PageBase {
    @FindBy(xpath = "//button[@data-test-id='header-boards-menu-button']")
    WebElement boardsIcon;

    @FindBy(xpath = "//h3[@class='boards-page-board-section-header-name']")
    WebElement personalBoardsHeader;


    public BoardsPageHelper(WebDriver driver) {
        super(driver);
    }

    @Override
    public void waitUntilPageIsLoaded() {
        log.info("Start: method waitUntilPageIsLoaded(), class BoardsPageHelper");
        log.info("Wait until Boards icon is clickable");
        waitUntilElementIsClickable(boardsIcon,30);
    }

    public boolean verifyIfBoardsIconIsDisplayed(){
        log.info("Start: method verifyIfBoardsIconIsDisplayed()");
        log.info("Verify that Boards icon is displayed on the screen");
        return boardsIcon.isDisplayed();
    }

    public boolean verifyIfPersonalBoardsHeaderIsDisplayed(){
        log.info("Start: method verifyIfPersonalBoardsHeaderIsDisplayed()");
        log.info("Verify that text of the element personal boards is 'Personal Boards'");
        return personalBoardsHeader.getText().equals("Personal Boards");
    }

    public void openBoard (String boardName){
        log.startTestCase("openBoard");
        log.info("Wait until element " + boardName + " is visible");
        waitUntilElementIsVisible(By.xpath("//div[@title='" + boardName + "']/.."),20);
        log.info("Open board " + boardName);
        driver.findElement(By.xpath("//div[@title='" + boardName + "']/..")).click();
    }
}
