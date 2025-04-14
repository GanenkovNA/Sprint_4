package units.orders;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.POMs.NewOrderPOM;
import ru.yandex.praktikum.resources.DriverFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static ru.yandex.praktikum.resources.Config.BROWSER;

@RunWith(Parameterized.class)
public class VerifySelectEachStationTest {
    private final int stationId;
    WebDriver driver;

    public VerifySelectEachStationTest(int stationId) {
        this.stationId = stationId;
    }

    @Parameters(name = "Station ID: {0}")
    public static Collection<Object[]> data() {
        List<Object[]> stations = new ArrayList<>();
        for (int i = 1; i <= 237; i++) {
            if (i < 80 || i > 91) {  // Исключаем станции с ID от 80 до 91
                stations.add(new Object[]{i});
            }
        }
        return stations;
    }

    @Before
    public void startBrowser() {
        driver = DriverFactory.getDriver(BROWSER);
    }

    @Test
    public void checkStation() {
        NewOrderPOM newOrderObject = new NewOrderPOM(driver);
        // Открываем домашнюю страницу
        newOrderObject.openNewOrderPage();
        // Нажимаем на кнопку "Заказать"
        newOrderObject.clickOnOrderButtonHeader();
        //
        newOrderObject.setStation(stationId);
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }
}