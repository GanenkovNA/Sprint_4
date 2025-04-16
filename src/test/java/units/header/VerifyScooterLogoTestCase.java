package units.header;

import org.junit.Test;
import ru.yandex.praktikum.TestBase;
import ru.yandex.praktikum.pom.NewOrderPOM;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.resources.Config.*;

public class VerifyScooterLogoTestCase extends TestBase {
    @Test
    public void VerifyScooterLogo(){
        NewOrderPOM newOrderPOM = new NewOrderPOM(driver);
        // Открываем страницу заказа (чтобы потом перейти на домашнюю с помощью кнопки)
        newOrderPOM.openNewOrderPage();
        // Проверяем ссылку, содержащуюся в логотипе
        assertEquals(BASE_URL, newOrderPOM.getScooterLogoUrl());
        // Нажимаем на логотип
        newOrderPOM.clickOnScooterLogo();
        // Проверяем, что мы на домашней странице
        assertEquals(BASE_URL, driver.getCurrentUrl());
    }
}
