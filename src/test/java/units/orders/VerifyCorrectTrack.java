package units.orders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.POMs.HomePOM;
import ru.yandex.praktikum.resources.DriverFactory;

import static ru.yandex.praktikum.resources.Config.BROWSER;

@RunWith(Parameterized.class)
public class VerifyCorrectTrack {
    WebDriver driver;

    @Before
    public void startBrowser() {
        driver = DriverFactory.getDriver(BROWSER);
    }

    @Test
    public void checkCorrectTrack(){
        String orderID = "726522";

        HomePOM homeObject = new HomePOM(driver);
        homeObject.openHomePage();
        homeObject.checkOrder(orderID);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}