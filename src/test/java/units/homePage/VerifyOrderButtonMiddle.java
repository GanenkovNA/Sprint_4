package units.homePage;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.POMs.HomePOM;
import ru.yandex.praktikum.resources.DriverFactory;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.resources.Config.BROWSER;
import static ru.yandex.praktikum.resources.Config.ORDER_URL;

public class VerifyOrderButtonMiddle {
    WebDriver driver;

    @Before
    public void startBrowser() {
        driver = DriverFactory.getDriver(BROWSER);
    }

    @Test
    public void VerifyOrderButtonMiddleTest(){
        HomePOM homeObject = new HomePOM(driver);
        // Открываем домашнюю страницу
        homeObject.openHomePage();
        // Нажимаем на кнопку "Заказать" в середине страницы
        homeObject.clickOnOrderButtonMiddle();
        // Проверяем URL страницы, на которую перешли
        assertEquals(ORDER_URL, driver.getCurrentUrl());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
