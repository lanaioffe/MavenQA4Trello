package ru.stqa.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.selenium.pages.*;
import ru.stqa.selenium.util.DataProviders;


import java.util.List;
import java.util.Random;

public class CurrentBoardPageTests extends TestBase{
    HomePageHelper homePage;
    LoginPageHelper loginPage;
    BoardsPageHelper boardsPage;
    CurrentBoardPageHelper qa4AutoBoard;

    @BeforeMethod
    public void initTest(){
        homePage = PageFactory.initElements(driver, HomePageHelper.class);
        loginPage = PageFactory.initElements(driver, LoginPageHelper.class);
        boardsPage = PageFactory.initElements(driver, BoardsPageHelper.class);
//        homePage = new HomePageHelper(driver);
//        loginPage = new LoginPageHelper(driver);
//        boardsPage = new BoardsPageHelper(driver);
//        qa4AutoBoard = PageFactory.initElements(driver, CurrentBoardPageHelper.class);
        qa4AutoBoard = PageFactory.initElements(driver, CurrentBoardPageHelper.class);
        qa4AutoBoard.setName("QA 4 Auto");

        homePage.openLoginPage();
        loginPage.waitUntilPageIsLoaded();
        loginPage.loginToTrelloAsAtlassian(LOGIN, PASSWORD);
        boardsPage.waitUntilPageIsLoaded();
    }

    @Test
    public void verifyIFLoadedBoardIsCorrect() {
        boardsPage.openBoard("QA 4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        Assert.assertTrue(qa4AutoBoard.titleVerification());
    }

    @Test
    public void createNewList()  {
        //----Open 'QA 4 Auto' board
        boardsPage.openBoard("QA 4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();

        //-----Add a new list------
        String nameList = "New List";
        System.out.println(qa4AutoBoard.getAddButtonName());
        if (qa4AutoBoard.getAddButtonName().equals("Add another list")){
            nameList = qa4AutoBoard.genRandomString(7);
            if (qa4AutoBoard.existsList(nameList)){
                nameList = qa4AutoBoard.stringWithRandomNumber(1000,nameList);
            }
        }


        int quantityListAtFirst = qa4AutoBoard.getQuantityLists();
        qa4AutoBoard.createNewList(nameList);
        int quantityListAtTheEnd = qa4AutoBoard.getQuantityLists();

        Assert.assertEquals(quantityListAtFirst+1,quantityListAtTheEnd);
//        System.out.println(qa4AutoBoard.getAddButtonName());
        Assert.assertEquals(qa4AutoBoard.getAddButtonName(),"Add another list");

//        WebElement addListButton = driver.findElement(By.cssSelector(".placeholder"));
//        String nameAddListButton = addListButton.getText();
//        addListButton.click();
//        waitUntilElementIsVisible(By.cssSelector(".list-name-input"),10);
//        String str = genRandomString(7);
//        //System.out.println("Name button - " + nameAddListButton);
//        int quantityListAtFirst = driver.findElements(By.xpath("//h2")).size();
//        if(nameAddListButton.equals("Add another list")){
//            boolean exitName = false;
//            //System.out.println("Size-" + driver.findElements(By.xpath("//h2/../textarea")).size());
//            for(WebElement element: driver.findElements(By.xpath("//h2/../textarea"))){
//                //System.out.println("Name - " + element.getText());
//                if(element.getText().equals(str)) exitName = true;
//            }
//            if(exitName) str = stringWithRandomNumber(1000,str);
//        }
//
//        driver.findElement(By.cssSelector(".list-name-input"))
//                .sendKeys(str);
//        driver.findElement(By.xpath("//input[@type='submit']")).click();
//
//        waitUntilElementIsClickable(By.cssSelector("a.js-cancel-edit"),10);
//        driver.findElement(By.cssSelector("a.js-cancel-edit")).click();
//        waitUntilElementIsVisible(By.cssSelector("span.placeholder"),10);
//        int quantityListAtTheEnd = driver
//                .findElements(By.xpath("//h2")).size();
//        Assert.assertEquals(quantityListAtFirst+1,quantityListAtTheEnd);
//        Assert.assertEquals(driver.findElement(By.cssSelector("span.placeholder")).getText(),"Add another list");

    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "createListRandomName")
    public void createNewListRandomName(String nameList)  {
        boardsPage.openBoard("QA 4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
//        String nameList = "New List";
        int quantityListAtFirst = qa4AutoBoard.getQuantityLists();
        qa4AutoBoard.createNewList(nameList);
        int quantityListAtTheEnd = qa4AutoBoard.getQuantityLists();
        Assert.assertEquals(quantityListAtFirst+1,quantityListAtTheEnd);
        Assert.assertEquals(qa4AutoBoard.getAddButtonName(),"Add another list");
    }

    @Test
    public void deleteList(){
        //----Open 'QA 4 Auto' board
        boardsPage.openBoard("QA 4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();

//        --------Add a list if doesn't exist or get quantity of lists -----
        int quantityListBegining;
        if (qa4AutoBoard.getAddButtonName().equals("Add a list")){
            qa4AutoBoard.createNewList("New List");
            quantityListBegining=1;
        }
        else {
            qa4AutoBoard.waitUntilPageIsLoaded();
            quantityListBegining = qa4AutoBoard.getQuantityLists();
//            waitUntilElementIsClickable(By.cssSelector(".js-open-list-menu"),10);
//            quantityListBegining = driver.findElements(By.cssSelector(".js-open-list-menu")).size();
        }

        //---- delete list----------------
        qa4AutoBoard.deleteList();

        int quantityListEnd = qa4AutoBoard.getQuantityLists();
        Assert.assertEquals(quantityListBegining,quantityListEnd+1, "quantityListBegining is not quantityListEnd+1");
    }

    @Test
    public void addFirstCardInNewList()  {
        //----Open 'QA 4 Auto' board
        boardsPage.openBoard("QA 4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();

        //--------Get quantity of 'Add another card' buttons at the beginning----
        int quantityAddAnotherButtonBeg = qa4AutoBoard.getQuantityAddAnotherCard();
//                driver.findElements(By.xpath("//span[@class= 'js-add-another-card']")).size();

        //-----Add a new list------
        qa4AutoBoard.createNewList("New List");
        qa4AutoBoard.waitUntilPageIsLoaded();

//        driver.findElement(By.cssSelector(".placeholder")).click();
//        waitUntilElementIsVisible(By.cssSelector(".list-name-input"),10);
//        driver.findElement(By.cssSelector(".list-name-input")).sendKeys("New List");
//        waitUntilElementIsClickable(By.xpath("//input[@type='submit']"),10);
//        driver.findElement(By.xpath("//input[@type='submit']")).click();
//        waitUntilElementIsClickable(By.cssSelector("a.js-cancel-edit"),10);
//        driver.findElement(By.cssSelector("a.js-cancel-edit")).click();
//        waitUntilElementIsVisible(By.cssSelector(".placeholder"),10);

        //----Add a first card for a new list
        qa4AutoBoard.addFirstCardForNewList("text");

        //----- Get the last 'Add card' button (in a New List)----
//        waitUntilAllElementsAreVisible(By.xpath("//span[@class = 'js-add-a-card']"),15);
//        List<WebElement> listAddCardButtons = driver.findElements(By.xpath("//span[@class = 'js-add-a-card']"));
//        int sizeLstAddCardButtons = listAddCardButtons.size();
//        WebElement lastAddCardButton = listAddCardButtons.get(sizeLstAddCardButtons-1);
//        ----Add a first card for a new list
//        lastAddCardButton.click();
//        waitUntilElementIsClickable(By.xpath("//input[@class='primary confirm mod-compact js-add-card']"),10);
//        driver.findElement(By.xpath("//textarea[@placeholder='Enter a title for this card…']")).sendKeys("text");
//        driver.findElement(By.xpath("//input[@class='primary confirm mod-compact js-add-card']")).click();
//        waitUntilElementIsClickable(By.cssSelector("a.js-cancel"),10);
//        driver.findElement(By.cssSelector("a.js-cancel")).click();

        //--------Get quantity of 'Add another card' buttons at the end----
        int quantityAddAnotherButtonEnd = qa4AutoBoard.getQuantityAddAnotherCard();

        Assert.assertEquals(quantityAddAnotherButtonBeg+1, quantityAddAnotherButtonEnd);
    }

    @Test (dataProviderClass = DataProviders.class, dataProvider = "createCardRandomName")
    public void addFirstCardRandomNameInNewList(String cardName)  {
        boardsPage.openBoard("QA 4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();
        int quantityAddAnotherButtonBeg = qa4AutoBoard.getQuantityAddAnotherCard();
        qa4AutoBoard.createNewList("New List");
        qa4AutoBoard.waitUntilPageIsLoaded();
        qa4AutoBoard.addFirstCardForNewList(cardName);
        int quantityAddAnotherButtonEnd = qa4AutoBoard.getQuantityAddAnotherCard();
        Assert.assertEquals(quantityAddAnotherButtonBeg+1, quantityAddAnotherButtonEnd);
    }

    @Test
    public void createCopyOfFirstList()  {
        //----Open 'QA 4 Auto' board
        boardsPage.openBoard("QA 4 Auto");
        qa4AutoBoard.waitUntilPageIsLoaded();

        String str = qa4AutoBoard.genRandomString(7);

//      -----Add a new list if doesn't exist------
        if (qa4AutoBoard.getAddButtonName().equals("Add a list")){
            qa4AutoBoard.createNewList(str);
        }

//        WebElement addListButton = driver.findElement(By.cssSelector(".placeholder"));
//        String nameAddListButton = addListButton.getText();
//        addListButton.click();
//        waitUntilElementIsVisible(By.cssSelector(".list-name-input"),10);
//        System.out.println("Name button - " + nameAddListButton);
//        if(nameAddListButton.equals("Add a list")){       //-----Add a new list if doesn't exist------
//            driver.findElement(By.xpath("//input[@class='list-name-input']")).sendKeys(str);//добавление нового листа
//            driver.findElement(By.xpath("//input[@class='primary mod-list-add-button js-save-edit']")).click();
//            waitUntilElementIsVisible(By.cssSelector("span.placeholder"),10);
//        }

        int quantityListAtFirst = qa4AutoBoard.getQuantityLists();

//            ------- doing a copy of First List-------
        qa4AutoBoard.createACopyList(1);

        int quantityListAtTheEnd = qa4AutoBoard.getQuantityLists();
        System.out.println("size before: " + quantityListAtFirst);
        System.out.println("size after: " + quantityListAtTheEnd);

        Assert.assertTrue(qa4AutoBoard.existsCopyOfList(1));
        Assert.assertEquals(quantityListAtFirst+1,quantityListAtTheEnd);

    }




}