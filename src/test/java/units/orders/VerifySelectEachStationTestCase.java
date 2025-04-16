package units.orders;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import ru.yandex.praktikum.TestBase;
import ru.yandex.praktikum.pom.NewOrderPOM;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class VerifySelectEachStationTestCase extends TestBase {
    private final int stationId;

    public VerifySelectEachStationTestCase(int stationId) {
        this.stationId = stationId;
    }

    @Parameters(name = "ID станции: {0}")
    public static Collection<Object[]> data() {
        List<Object[]> stations = new ArrayList<>();
        for (int i = 1; i <= 237; i++) {
            if (i < 80 || i > 91) {  // Исключаем станции с ID от 80 до 91
                stations.add(new Object[]{i});
            }
        }
        return stations;
    }

    @Test
    public void checkStationTest() {
        NewOrderPOM newOrderObject = new NewOrderPOM(driver);
        // Открываем домашнюю страницу
        newOrderObject.openNewOrderPage();
        // Нажимаем на кнопку "Заказать"
        newOrderObject.clickOnOrderButtonHeader();
        //
        newOrderObject.setStation(stationId);
    }
}