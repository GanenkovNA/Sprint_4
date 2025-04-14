package e2e;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.POMs.OrderPOM;
import ru.yandex.praktikum.resources.DriverFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static ru.yandex.praktikum.resources.Config.BROWSER;
import static ru.yandex.praktikum.resources.Config.ORDER_URL;

@RunWith(Parameterized.class)
public class NewOrder {
    WebDriver driver;
    // Переменные для первой части
    private final String firstName = "Скотт";
    private final String lastName = "Пилигрим";
    private final String address = "Альберта Авеню, 65";
    private final int stationId = 1;
    private final String phoneNumber = "+79777777777";
    // Переменные для второй части
    private final String rentalStartDate = tomorrowDate();
    private final String rentalPeriod;
    private final String scooterColour;

    public NewOrder(String rentalPeriod, String scooterColour) {
        this.rentalPeriod = rentalPeriod;
        this.scooterColour = scooterColour;
    }

    @Parameterized.Parameters
    public static Object[][] answers() {
        return new Object[][]{
                {"сутки", "grey"},
                {"сутки", "black"},
                {"двое суток", "grey"},
                {"двое суток", "black"},
                {"трое суток", "grey"},
                {"трое суток", "black"},
                {"четверо суток", "grey"},
                {"четверо суток", "black"},
                {"пятеро суток", "grey"},
                {"пятеро суток", "black"},
                {"шестеро суток", "grey"},
                {"шестеро суток", "black"},
                {"семеро суток", "grey"},
                {"семеро суток", "black"},
        };
    }

    @Before
    public void startBrowser() {
        driver = DriverFactory.getDriver(BROWSER);
    }

    @Test
    public void orderCreation() {
        OrderPOM orderObject = new OrderPOM(driver);
        // Открываем домашнюю страницу
        orderObject.openHomePage();
        // Нажимаем на кнопку "Заказать"
        orderObject.clickOnOrderButton();
        // Проверяем URL страницы, на которую перешли
        assertEquals(ORDER_URL, driver.getCurrentUrl());
        // Заполняем первую часть заказа
        fillingOrderFields_1(orderObject);
        // Нажимаем на кнопку "Далее"
        orderObject.clickOnNextButton();
        //Заполняем вторую часть заказа
        fillingOrderFields_2(orderObject);
        //Нажимаем на кнопку "Заказать" под формой
        orderObject.clickOnMakeOrderButton();
        // Подтверждаем заказ в поп-апе ("Да")
        orderObject.clickOnConfirmOrderButton();
        // Переходим на страницу просмотра статуса заказа
        orderObject.clickOnviewStatusButton();
    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    private void fillingOrderFields_1(OrderPOM orderObject) {
        orderObject.setFirstName(firstName);
        orderObject.setLastName(lastName);
        orderObject.setAddress(address);
        orderObject.setStation(stationId);
        orderObject.setPhoneNumber(phoneNumber);
    }

    private void fillingOrderFields_2(OrderPOM orderObject) {
        orderObject.setRentalStartDater(rentalStartDate);
        orderObject.setRentalPeriod(rentalPeriod);
        orderObject.setScooterColour(scooterColour);
    }

    private String tomorrowDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(tomorrow);
    }
}
