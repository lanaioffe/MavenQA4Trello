package ru.stqa.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.util.LogLog4j;

import java.util.List;
import java.util.Random;

public abstract class PageBase {
    public static LogLog4j log = new LogLog4j();
    WebDriver driver;

    public PageBase (WebDriver driver){
        this.driver = driver;
    }

    public abstract void waitUntilPageIsLoaded();

    public void waitUntilElementIsClickable(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsClickable(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilElementIsVisible(WebElement element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilTextToBeInElement(WebElement element, String text, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions.textToBePresentInElement(element,text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible(By locator, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions
                    .visibilityOfAllElementsLocatedBy(locator));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void waitUntilAllElementsAreVisible (List<WebElement> element, int time) {
        try {
            new WebDriverWait(driver,time).until(ExpectedConditions
                    .visibilityOfAllElements(element));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String genRandomString(int num){
        String str = "";
        int number;
        Random gen = new Random();
        for(int i=0; i<num; i++){
            number = '!' + gen.nextInt('z' - '!' +1);
            str = str + (char)number;
        }
        return str;
    }

    public static String stringWithRandomNumber(int num,String str){
        Random gen = new Random();
        return str + gen.nextInt(num);
    }

    public void enterValueToTheField(WebElement field, String value) {
        field.click();
        field.clear();
        field.sendKeys(value);
    }

    public void enterValueToAutoCompleteField(WebElement field, String value){
        field.click();
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].value='';", field);
        field.sendKeys(value);
    }
}
