package units.header;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.POMs.HeaderPOM;
import ru.yandex.praktikum.resources.DriverFactory;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.resources.Config.*;

public class VerifyScooterLogoTest {
    WebDriver driver;

    @Before
    public void startBrowser() {
        driver = DriverFactory.getDriver(BROWSER);
    }

    @Test
    public void VerifyScooterLogo(){
        HeaderPOM headerObject = new HeaderPOM(driver);
        // Открываем страницу заказа (чтобы перейти на домашнюю)
        driver.get(ORDER_URL);
        // Проверяем ссылку, содержащуюся в логотипе
        assertEquals(BASE_URL, headerObject.getScooterLogoUrl());
        // Нажимаем на логотип
        headerObject.clickOnScooterLogo();
        // Проверяем, что мы на домашней странице
        assertEquals(BASE_URL, driver.getCurrentUrl());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
