package units.header;

import org.junit.Test;
import ru.yandex.praktikum.pom.HomePOM;
import ru.yandex.praktikum.pom.YandexPOM;
import ru.yandex.praktikum.TestBase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ru.yandex.praktikum.resources.Config.*;

public class VerifyYandexLogoTestCase extends TestBase {

    @Test
    public void verifyYandexLogoTest() {
        HomePOM headerObject = new HomePOM(driver);
        //Открываем домашнюю страницу
        headerObject.openHomePage();
        // Проверяем ссылку, содержащуюся в логотипе
        assertEquals(YANDEX_HOME_URL, headerObject.getYandexLogoUrl());
        // Получаем хэндл текущей вкладки перед кликом
        String originalWindow = driver.getWindowHandle();
        // Нажимаем на логотип
        headerObject.clickOnYandexLogo();

        // Создаём новый объект для работы со страницей яндекса
        YandexPOM yandexObject = new YandexPOM(driver);
        // Переключаемся на новую вкладку
        yandexObject.goToSecondWindow(originalWindow);
        // Проверяем, загрузилась ли страница
        assertTrue(yandexObject.isYandexPageLoaded());
    }
}
