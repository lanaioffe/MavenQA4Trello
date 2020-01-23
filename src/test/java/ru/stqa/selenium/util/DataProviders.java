package ru.stqa.selenium.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import ru.stqa.selenium.pages.HomePageHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class DataProviders {
    @DataProvider
    public static Iterator<Object[]> dataProviderFirst() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/loginIncorrectTest.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> dataProviderFirstPsw() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(
                DataProviders.class
                        .getResourceAsStream("/passwordIncorrectTest.data")));

        List<Object[]> userData = new ArrayList<Object[]>();
        String line = in.readLine();
        while (line != null) {
            userData.add(line.split(";"));
            line = in.readLine();
        }
        in.close();
        return userData.iterator();
    }

    @DataProvider
    public static Iterator<Object[]> dataProviderSecond() {
        List<Object[]> data = new ArrayList();
        data.add(new Object[]{"login1@mail.ru", "psw1"});
        data.add(new Object[]{"login2@mail.ru", "psw2"});
        data.add(new Object[]{"login3@test.ru", "psw3"});

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> dataProviderThird() {
        List<Object[]> data = new ArrayList();

        for(int i = 0; i < 3; ++i) {
            data.add(new Object[]{this.generateRandomPassword(), this.generateRandomName()});
        }

        return data.iterator();
    }


    @DataProvider
    public Iterator<Object[]> createListRandomName() {
//        WebDriver driver = new ChromeDriver();
        List<Object[]> data = new ArrayList();
//        HomePageHelper homePage = new HomePageHelper(driver);
        for(int i = 0; i < 4; ++i) {
            data.add(new Object[]{genRandomString(10)});
        }
        return data.iterator();
    }

    @DataProvider
    public Iterator<Object[]> createCardRandomName() {
        List<Object[]> data = new ArrayList();
        for(int i = 0; i < 1; ++i) {
            data.add(new Object[]{genRandomString(10)});
        }
        return data.iterator();
    }

    private Object generateRandomPassword() {

        return "pass" + (new Random()).nextInt();
    }

    private Object generateRandomName() {

        return "demo" + (new Random()).nextInt()+"@gmail.com";
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


}

