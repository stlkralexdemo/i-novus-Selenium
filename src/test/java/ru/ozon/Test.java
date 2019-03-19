package ru.ozon;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;

import java.util.ArrayList;
import java.util.List;


public class Test {

    public FirefoxDriver driver;

    @BeforeSuite
    public void first() {
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");

        driver = new FirefoxDriver();
        driver.get("https://www.ozon.ru");
    }

    @org.testng.annotations.Test
    public void findMusic() {

        driver.findElementByCssSelector(".catalog-button-text").click();
        driver.findElementByCssSelector("a.top-level-section:nth-child(19) > span:nth-child(1)").click();

    }

    @org.testng.annotations.Test
    public void lp(){

        driver.findElementByCssSelector("div.products-carousel:nth-child(3) > a:nth-child(1)").click();
    }

//    @org.testng.annotations.Test
//    public void list() {
//
//        String title = driver.getTitle();
//        Assert.assertEquals(title, "Маркетплейс Беру - большой ассортимент товаров из интернет-магазинов с быстрой доставкой и по выгодным ценам");
//
//    }
//
//    @org.testng.annotations.Test
//    public void amount() {
//
//        List<WebElement> amount = driver.findElementsByXPath("//a[@class='grid-snippet__react-link link']");
//
//        System.out.println(amount.size());
//    }


//    @After
//    public void afterAll() {
//
//        driver.quit();
//    }
}

