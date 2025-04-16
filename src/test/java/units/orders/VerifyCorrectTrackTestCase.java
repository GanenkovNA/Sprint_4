package units.orders;

import org.junit.Test;
import ru.yandex.praktikum.TestBase;
import ru.yandex.praktikum.pom.HomePOM;

public class VerifyCorrectTrackTestCase extends TestBase {
    @Test
    public void checkCorrectTrackTest(){
        String orderID = "726522";

        HomePOM homeObject = new HomePOM(driver);
        homeObject.openHomePage();
        homeObject.checkOrder(orderID);
    }
}