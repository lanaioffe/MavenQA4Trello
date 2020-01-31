package ru.stqa.selenium;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import com.google.common.io.Files;
import org.openqa.selenium.*;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import ru.stqa.selenium.factory.WebDriverPool;
import ru.stqa.selenium.pages.HomePageHelper;
import ru.stqa.selenium.util.LogLog4j;

/**
 * Base class for TestNG-based test classes
 */
public class TestBase {

  protected static URL gridHubUrl = null;
  protected static String baseUrl;
  protected static Capabilities capabilities;

  public static final String LOGIN = "lanaioffe@mail.ru";
  public static final String PASSWORD = "Rainbow02";
  public static LogLog4j log = new LogLog4j();
  HomePageHelper homePage;

//  protected WebDriver driver;
  protected EventFiringWebDriver driver;

  public static class MyListener extends AbstractWebDriverEventListener{

    @Override
    public void beforeFindBy(By by, WebElement element, WebDriver driver) {
      //super.beforeFindBy(by, element, driver);
      log.info("Find element " + by);
    }

    @Override
    public void afterFindBy(By by, WebElement element, WebDriver driver) {
//      super.afterFindBy(by, element, driver);
      log.info("Element " + by + " was found");
    }

    @Override
    public void onException(Throwable throwable, WebDriver driver) {
//      super.onException(throwable, driver);
      log.error("Error " + throwable);
//      Doing screenshot on errors
      File tmp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      File screen = new File("screen - " + System.currentTimeMillis() + ".png");
      try {
        Files.copy(tmp, screen);
      } catch (IOException e) {
        e.printStackTrace();
      }
      log.info("See screen in file " + screen);
    }
  }

  @BeforeSuite
  public void initTestSuite() throws IOException {
    SuiteConfiguration config = new SuiteConfiguration();
    baseUrl = config.getProperty("site.url");
    if (config.hasProperty("grid.url") && !"".equals(config.getProperty("grid.url"))) {
      gridHubUrl = new URL(config.getProperty("grid.url"));
    }
    capabilities = config.getCapabilities();
  }

  @BeforeMethod
  public void initWebDriver() {
    //driver = WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities);
    driver =  new EventFiringWebDriver(WebDriverPool.DEFAULT.getDriver(gridHubUrl, capabilities));
    driver.register(new MyListener());
    homePage = PageFactory.initElements(driver, HomePageHelper.class);
    //===========Enter to Trello====
    driver.get(baseUrl);
//        driver.manage().window().fullscreen();
    homePage.waitUntilPageIsLoaded();
  }

  @AfterMethod(alwaysRun = true)
  public void tearDown() {
    WebDriverPool.DEFAULT.dismissAll();
  }
}
