package units.header;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.POMs.HeaderPOM;
import ru.yandex.praktikum.resources.DriverFactory;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static ru.yandex.praktikum.resources.Config.*;

public class VerifyYandexLogo {
    WebDriver driver;

    @Before
    public void startBrowser() {
        driver = DriverFactory.getDriver(BROWSER);
    }

    @Test
    public void checkYandexLogo() {
        HeaderPOM headerObject = new HeaderPOM(driver);
        //Открываем домашнюю страницу
        headerObject.openHomePage();
        // Проверяем ссылку, содержащуюся в логотипе
        assertEquals(YANDEX_HOME_URL, headerObject.getYandexLogoUrl());
        // Получаем хэндл текущей вкладки перед кликом
        String originalWindow = driver.getWindowHandle();
        // Нажимаем на логотип
        headerObject.clickOnYandexLogo();
        // Ждём пока не откроется новая вкладка
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME)).until(numberOfWindowsToBe(2));
        // Переключаемся на новую вкладку
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
        // Проверяем URL новой вкладки
        assertEquals(YANDEX_REDIRECT_URL, driver.getCurrentUrl());
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}
