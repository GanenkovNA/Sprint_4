package units.header;

import org.junit.Test;
import ru.yandex.praktikum.TestBase;
import ru.yandex.praktikum.pom.HomePOM;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.resources.Config.ORDER_URL;

public class VerifyOrderButtonHeaderTestCase extends TestBase {
    @Test
    public void VerifyOrderButtonHeaderTest(){
        HomePOM homeObject = new HomePOM(driver);
        // Открываем домашнюю страницу
        homeObject.openHomePage();
        // Нажимаем на кнопку "Заказать" в середине страницы
        homeObject.clickOnOrderButtonHeader();
        // Проверяем URL страницы, на которую перешли
        assertEquals(ORDER_URL, driver.getCurrentUrl());
    }
}
