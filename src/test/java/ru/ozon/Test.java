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

    @org.testng.annotations.Test(priority = 1, description = "Открыть в браузере сайт https://www.ozon.ru/. Если откроется всплывающее окно – закрыть\n" +
            "его.")
    public void first() {

        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\drivers\\geckodriver.exe");

        driver = new FirefoxDriver();
        driver.get("https://www.ozon.ru");
    }

    @org.testng.annotations.Test(priority = 2, description = "В меню \"Все разделы\" выбрать категорию \"Музыка\".")
    public void findMusic() {

        driver.findElementByCssSelector(".catalog-button-text").click();
        driver.findElementByCssSelector("a.top-level-section:nth-child(19) > span:nth-child(1)").click();
    }

    @org.testng.annotations.Test(priority = 3, description = "С открывшейся страницы перейти на страницу \"Виниловые пластинки\".")
    public void lp() {

        driver.findElementByCssSelector("div.products-carousel:nth-child(3) > a:nth-child(1)").click();
    }

    /**
     * Если на странице много элементов с атрибутами index и id - значит открылся именно список товаров. Конкретно в этом случае, если их больше 20. Число может быть и другим.
     */
    @org.testng.annotations.Test(priority = 4, description = "Проверить, что открылся список товаров.")
    public void truePage() {
        List<WebElement> elements = driver.findElementsByXPath("//div[@class = 'tile' and @index and @id]");

        Assert.assertTrue(elements.size() > 20);
    }

    /**
     * Считаем количество элементов в списке с атрибутами index и id. Полученное число присваиваем переменной класса amount, оно ещё пригодится.
     */
    @org.testng.annotations.Test(priority = 5, description = "Получить количество товаров на странице.")
    public void totalAmount() {

        List<WebElement> elements = driver.findElementsByXPath("//div[@class = 'tile' and @index and @id]");
        amount = elements.size();
        System.out.println("количество товара на странице " + amount);
    }

    /**
     * Вычисляем рандомное число с помощью метода из Util класса.
     */
    @org.testng.annotations.Test(priority = 6, description = "Сгенерировать случайное число в диапазоне от 1 до количества товаров, полученного в п.5")
    public void rnd() {

        firstRandomNumber = Util.rand(amount);
    }

    @org.testng.annotations.Test(priority = 7, description = "Выбрать товар под номером, полученным в п.6. ( Перейти на страницу товара )")
    public void randomClick() {

        String xpath = "//div[@index = '" + firstRandomNumber + "']/a";
        driver.findElementByXPath(xpath).click();

        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        if (driver.findElementByCssSelector(".close-icon").isDisplayed()) ;
        {
            driver.findElementByCssSelector(".close-icon").click();
        }
    }

    /**
     * Получаем цену и наименование товара в строковом виде, сохраняем их в переменных класса firstName и firstPrice. Вывод в консоль не обязателен , нужен мне для визуального контроля.
     */
    @org.testng.annotations.Test(priority = 8, description = "Запомнить стоимость и название данного товара.")
    public void save() {

        firstName = driver.findElementByXPath("//div[@class = 'bDetailPanel']/h1[text()]").getText();
        firstPrice = driver.findElementByXPath("//span[@class = 'eOzonPrice_main']").getText();

        System.out.println(firstPrice);
        System.out.println(firstName);
    }

    @org.testng.annotations.Test(priority = 9, description = "Добавить товар в корзину.")
    public void inBasket() {

        driver.findElementByCssSelector(".bSaleBlockButton").click();
    }

    /**
     * Сравниваем имя товара из корзины с сохраненным ранее в переменной класса firstName.
     */
    @org.testng.annotations.Test(priority = 10, description = "Проверить то, что в корзине появился добавленный в п.9 товар. ( Проверка данных\n" +
            "определенного товара. Необходим переход в корзину для этого. )")
    public void firstCheckBasket() {

        driver.findElementByCssSelector("a.bSaleBlockButton").click();
        String checkName = driver.findElementByCssSelector(".title").getText();
        System.out.println("this first" + checkName);
        Assert.assertEquals(checkName, firstName);
    }

    /**
     * Дважды жмём кнопку назад в браузере.
     */
    @org.testng.annotations.Test(priority = 11, description = "Вернуться на страницу \"Виниловые пластинки\".")
    public void back() {

        driver.navigate().back();
        driver.navigate().back();
    }

    /**
     * Вычисляем новое рандомное число с помощью метода из Util класса.
     */
    @org.testng.annotations.Test(priority = 12, description = "Сгенерировать случайное число в диапазоне от 1 до количества товаров, полученного в\n" +
            "п.5")
    public void secondRnd() {

        secondRandomNumber = Util.rand(amount);
        if (secondRandomNumber == firstRandomNumber) {
            secondRnd();
        }
    }

    @org.testng.annotations.Test(priority = 13, description = "Выбрать товар под номером, полученным в п.12. ( Перейти на страницу товара )")
    public void randomClickNext() {

        String xpath = "//div[@index = '" + secondRandomNumber + "']/a";
        driver.findElementByXPath(xpath).click();
    }

    /**
     * Получаем цену и наименование товара в строковом виде, сохраняем их в переменных класса secondPrice и secondName.
     */
    @org.testng.annotations.Test(priority = 14, description = "Запомнить стоимость и название данного товара.")
    public void saveSecond() {

        secondName = driver.findElementByXPath("//div[@class = 'bDetailPanel']/h1[text()]").getText();
        secondPrice = driver.findElementByXPath("//span[@class = 'eOzonPrice_main']").getText();

        System.out.println(secondPrice);
        System.out.println(secondName);
    }

    @org.testng.annotations.Test(priority = 15, description = "Добавить товар в корзину.")
    public void inBasketSecond() {

        driver.findElementByCssSelector(".bSaleBlockButton").click();
    }

    /**
     * Проверяем значение текстового поля с атрибутом class = 'count', не открывая корзину. Сравниваем это число с числом добавленных товаров в корзину.
     */
    @org.testng.annotations.Test(priority = 16, description = "Проверить то, что в корзине два товара. ( Проверка количества товаров в корзине. Может\n" +
            "быть произведена без открытия корзины, а проверяя значение в header сайта, где указано\n" +
            "количество товаров в корзине )")
    public void check() {

        driver.navigate().back();
        String attribute = driver.findElementByCssSelector(".count").getText();

        Assert.assertEquals(attribute, "2");
    }

    @org.testng.annotations.Test(priority = 17, description = "Открыть корзину.")
    public void toBasket() {

        driver.get("https://www.ozon.ru/context/cart");
    }

    /**
     * Проверяем текстовые значения именоавний товаров в корзине с ранее сохраненными в переменных класса. Для вычисления общей стоимости используем метод класса Util. Сравниваем вычисленную стоимость со значением из корзины.
     */
    @org.testng.annotations.Test(priority = 18, description = "Проверить то, что в корзине раннее выбранные товары и итоговая стоимость по двум\n" +
            "товарам рассчитана верно.")
    public void checkBasket() {

        List<WebElement> elements = driver.findElementsByXPath("//div/a[@data-test-id = 'cart-item-title']");
        List<String> list = new ArrayList<String>();
        for (WebElement element : elements) {
            list.add(element.getText());
        }
        Assert.assertEquals(list.get(1), firstName);
        Assert.assertEquals(list.get(0), secondName);

        String priceInBasket = driver.findElementByXPath("//div[@data-test-id = 'total-price-block']/span/span[@class = 'price-number']/span[@class = 'main']").getText();

        Assert.assertEquals(Util.parseNum(priceInBasket), Util.parseNum(firstPrice) + Util.parseNum(secondPrice));
    }

    @org.testng.annotations.Test(priority = 19, description = "Удалить из корзины все товары.")
    public void clearBasket() {

        driver.findElementByCssSelector("button.header-btn:nth-child(2)").click();
        driver.findElementByCssSelector(".blue").click();
    }

    /**
     * Ищем на странице текст - Корзина пуста.
     */
    @org.testng.annotations.Test(priority = 20, description = "Проверить, что корзина пуста.")
    public void nextCheckBasket() {

        String text = driver.findElementByXPath("//div[@class = 'empty-cart']/h1[text()]").getText();
        Assert.assertEquals(text, "Корзина пуста");
    }


    @org.testng.annotations.Test(priority = 21, description = "Закрыть браузер.")
    public void afterAll() {

        driver.quit();
    }
}

