package ru.yandex.praktikum;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.resources.DriverFactory;

import static ru.yandex.praktikum.resources.Config.BROWSER;

public abstract class TestBase {
    protected WebDriver driver;

    @Before
    public void startBrowser() {
        driver = DriverFactory.getDriver(BROWSER);
    }

    @After
    public void closeBrowser() {
        DriverFactory.closeDriver(driver);
    }
}
