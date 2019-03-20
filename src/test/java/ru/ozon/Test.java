package ru.ozon;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Test {

    private FirefoxDriver driver;
    private int amount;
    private int firstRandomNumber;
    private int secondRandomNumber;
    private String firstPrice;
    private String secondPrice;
    private String firstName;
    private String secondName;

    @org.testng.annotations.Test(priority = 1)
    public void first() {
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");

        driver = new FirefoxDriver();
        driver.get("https://www.ozon.ru");
    }

    @org.testng.annotations.Test(priority = 2)
    public void findMusic() {

        driver.findElementByCssSelector(".catalog-button-text").click();
        driver.findElementByCssSelector("a.top-level-section:nth-child(19) > span:nth-child(1)").click();

    }

    @org.testng.annotations.Test(priority = 3)
    public void lp() {

        driver.findElementByCssSelector("div.products-carousel:nth-child(3) > a:nth-child(1)").click();
    }

    @org.testng.annotations.Test(priority = 4)
    public void truePage() {
        List<WebElement> elements = driver.findElementsByXPath("//div[@class = 'tile' and @index and @id]");

        Assert.assertTrue(elements.size() > 20);
    }

    @org.testng.annotations.Test(priority = 5)
    public void totalAmount() {

        List<WebElement> elements = driver.findElementsByXPath("//div[@class = 'tile' and @index and @id]");
        amount = elements.size();
        System.out.println("количество товара на странице " + amount);
    }

    @org.testng.annotations.Test(priority = 6)
    public void rnd() {
        firstRandomNumber = Util.rand(amount);
    }

    @org.testng.annotations.Test(priority = 7)
    public void randomClick() {

        String xpath = "//div[@index = '" + firstRandomNumber + "']/a";
        driver.findElementByXPath(xpath).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElementByCssSelector(".close-icon").click();
    }

    @org.testng.annotations.Test(priority = 8)
    public void save() {
        firstName = driver.findElementByXPath("//div[@class = 'bDetailPanel']/h1[text()]").getText();
        firstPrice = driver.findElementByXPath("//span[@class = 'eOzonPrice_main']").getText();

        System.out.println(firstPrice);
        System.out.println(firstName);
    }

    @org.testng.annotations.Test(priority = 9)
    public void inBasket() {
        driver.findElementByCssSelector(".bSaleBlockButton").click();
    }

    @org.testng.annotations.Test(priority = 10)
    public void firstCheckBasket() {
        driver.findElementByCssSelector("a.bSaleBlockButton").click();
        String checkName = driver.findElementByCssSelector(".title").getText();
        System.out.println("this first" + checkName);
        Assert.assertEquals(checkName, firstName);
    }

    @org.testng.annotations.Test(priority = 11)
    public void back() {
        driver.navigate().back();
        driver.navigate().back();

    }

    @org.testng.annotations.Test(priority = 12)
    public void secondRnd() {
        secondRandomNumber = Util.rand(amount);
    }

    @org.testng.annotations.Test(priority = 13)
    public void randomClickNext() {
        String xpath = "//div[@index = '" + secondRandomNumber + "']/a";
        driver.findElementByXPath(xpath).click();
    }

    @org.testng.annotations.Test(priority = 14)
    public void saveSecond() {
        secondName = driver.findElementByXPath("//div[@class = 'bDetailPanel']/h1[text()]").getText();
        secondPrice = driver.findElementByXPath("//span[@class = 'eOzonPrice_main']").getText();

        System.out.println(secondPrice);
        System.out.println(secondName);
    }

    @org.testng.annotations.Test(priority = 15)
    public void inBasketSecond() {
        driver.findElementByCssSelector(".bSaleBlockButton").click();

    }

    @org.testng.annotations.Test(priority = 16)
    public void check() {

        driver.navigate().back();
        String attribute = driver.findElementByCssSelector(".count").getText();
        System.out.println(attribute);

        Assert.assertEquals("2", attribute);
    }

    @org.testng.annotations.Test(priority = 17)
    public void toBasket() {
        driver.get("https://www.ozon.ru/context/cart");
    }

    @org.testng.annotations.Test(priority = 18)
    public void checkBasket() {
        List<WebElement> elements = driver.findElementsByXPath("//div/a[@data-test-id = 'cart-item-title']");
        List<String> list = new ArrayList<String>();
        for (WebElement element : elements) {
            list.add(element.getText());
        }
        Assert.assertEquals(firstName, list.get(1));
        Assert.assertEquals(secondName, list.get(0));

        String priceInBasket = driver.findElementByXPath("//span[@class = 'price']/span[@class = 'price-number']/span[@class = 'main']").getText();

        Assert.assertEquals(Util.parseNum(firstPrice) + Util.parseNum(secondPrice), Util.parseNum(priceInBasket));
    }

    @org.testng.annotations.Test(priority = 19)
    public void clearBasket() {
        driver.findElementByCssSelector("button.header-btn:nth-child(2)").click();
        driver.findElementByCssSelector(".blue").click();
    }

    @org.testng.annotations.Test(priority = 20)
    public void nextCheckBasket() {
        String text = driver.findElementByXPath("//div[@class = 'empty-cart']/h1[text()]").getText();
        Assert.assertEquals("Корзина пуста", text);
    }


    @org.testng.annotations.Test(priority = 21)
    public void afterAll() {
        driver.quit();
    }
}

