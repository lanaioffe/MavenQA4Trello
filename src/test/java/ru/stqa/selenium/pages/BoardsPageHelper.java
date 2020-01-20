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
        waitUntilElementIsClickable(boardsIcon,30);
    }

    public boolean verifyIfBoardsIconIsDisplayed(){
        return boardsIcon.isDisplayed();
    }

    public boolean verifyIfPersonalBoardsHeaderIsDisplayed(){
        return personalBoardsHeader.getText().equals("Personal Boards");
    }

    public void openBoard (String boardName){
        waitUntilElementIsVisible(By.xpath("//div[@title='" + boardName + "']/.."),20);
        driver.findElement(By.xpath("//div[@title='" + boardName + "']/..")).click();
    }
}
