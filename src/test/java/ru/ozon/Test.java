package ru.ozon;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Test {

    private FirefoxDriver driver;
    private int amount;
    private String priceFirst;
    private String priceSecond;
    private String nameFirst;
    private String nameSecond;

    @BeforeSuite
    public void first() {
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");

        driver = new FirefoxDriver();
        driver.get("https://www.ozon.ru");
    }

    @org.testng.annotations.Test(priority = 1)
    public void findMusic() {

        driver.findElementByCssSelector(".catalog-button-text").click();
        driver.findElementByCssSelector("a.top-level-section:nth-child(19) > span:nth-child(1)").click();

    }

    @org.testng.annotations.Test(priority = 2)
    public void lp() {

        driver.findElementByCssSelector("div.products-carousel:nth-child(3) > a:nth-child(1)").click();
    }

    @org.testng.annotations.Test(priority = 3)
    public void truePage() {

        List<WebElement> elements = driver.findElementsByXPath("//div[@class = 'tile' and @index and @id]");
        amount = elements.size();
        System.out.println("количество товара на странице " + amount);
    }

    @org.testng.annotations.Test(priority = 4)
    public void randomClick() {

        String xpath = "//div[@index = '" + Util.rand(amount) + "']/a";
        driver.findElementByXPath(xpath).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElementByCssSelector(".close-icon").click();
    }

    @org.testng.annotations.Test(priority = 5)
    public void save() {
        nameFirst = driver.findElementByXPath("//div[@class = 'bDetailPanel']/h1[text()]").getText();
        priceFirst = driver.findElementByXPath("//span[@class = 'eOzonPrice_main']").getText();

        System.out.println(priceFirst);
        System.out.println(nameFirst);
    }

    @org.testng.annotations.Test(priority = 6)
    public void inBasket() {
        driver.findElementByCssSelector(".bSaleBlockButton").click();
    }

    @org.testng.annotations.Test(priority = 7)
    public void back() {
        driver.navigate().back();
    }

    @org.testng.annotations.Test(priority = 8)
    public void randomClickNext() {
        String xpath = "//div[@index = '" + Util.rand(amount) + "']/a";
        driver.findElementByXPath(xpath).click();
    }

    @org.testng.annotations.Test(priority = 9)
    public void saveSecond() {
        nameSecond = driver.findElementByXPath("//div[@class = 'bDetailPanel']/h1[text()]").getText();
        priceSecond = driver.findElementByXPath("//span[@class = 'eOzonPrice_main']").getText();

        System.out.println(priceSecond);
        System.out.println(nameSecond);
    }

    @org.testng.annotations.Test(priority = 10)
    public void inBasketSecond() {
        driver.findElementByCssSelector(".bSaleBlockButton").click();

    }

    @org.testng.annotations.Test(priority = 11)
    public void check() {

        String attribute = driver.findElementByXPath("//div[@class = 'bCount' and @data-count]").getAttribute("data-count");
        System.out.println(attribute);

        Assert.assertEquals("1", attribute);
    }

    @org.testng.annotations.Test(priority = 12)
    public void toBasket() {
        driver.findElementByCssSelector("div.eMyOzon_Item:nth-child(4) > a:nth-child(1)").click();
    }

    @org.testng.annotations.Test(priority = 13)
    public void checkBasket() {
        List<WebElement> elements = driver.findElementsByXPath("//div/a[@data-test-id = 'cart-item-title']");
        List<String> list = new ArrayList<String>();
        for (WebElement element : elements) {
            list.add(element.getText());
        }
        Assert.assertEquals(nameFirst, list.get(1));
        Assert.assertEquals(nameSecond, list.get(0));

        String priceInBasket = driver.findElementByCssSelector("div.row:nth-child(3) > div:nth-child(2) > span:nth-child(1) > span:nth-child(1) > span:nth-child(1)").getText();

        Assert.assertEquals(Util.parseNum(priceFirst) + Util.parseNum(priceSecond), Util.parseNum(priceInBasket));
    }

    @org.testng.annotations.Test(priority = 14)
    public void clearBasket() {
        driver.findElementByCssSelector("button.header-btn:nth-child(2)").click();
        driver.findElementByCssSelector(".blue").click();
    }

    @org.testng.annotations.Test(priority = 15)
    public void nextCheckBasket() {
        String text = driver.findElementByXPath("//div[@class = 'empty-cart']/h1[text()]").getText();
        Assert.assertEquals("Корзина пуста", text);
    }


    @org.testng.annotations.Test(priority = 16)
    public void afterAll() {
        driver.quit();
    }
}

