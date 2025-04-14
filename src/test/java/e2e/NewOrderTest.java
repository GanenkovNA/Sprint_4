package e2e;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.POMs.NewOrderPOM;
import ru.yandex.praktikum.resources.DriverFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static ru.yandex.praktikum.resources.Config.BROWSER;

@RunWith(Parameterized.class)
public class NewOrderTest {
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

    public NewOrderTest(String rentalPeriod, String scooterColour) {
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
    public void NewOrderTest() {
        NewOrderPOM newOrderObject = new NewOrderPOM(driver);
        // Открываем домашнюю страницу
        newOrderObject.openNewOrderPage();

        /*
        Отложено для другого теста
        // Нажимаем на кнопку "Заказать"
        orderObject.clickOnOrderButton();
        // Проверяем URL страницы, на которую перешли
        assertEquals(ORDER_URL, driver.getCurrentUrl());
         */

        // Заполняем первую часть заказа
        fillingOrderFields_1(newOrderObject);
        // Нажимаем на кнопку "Далее"
        newOrderObject.clickOnNextButton();

        //Проверяем, что вторая страница загрузилась
        newOrderObject.isNewOrderSecondPageLoaded();
        //Заполняем вторую часть заказа
        fillingOrderFields_2(newOrderObject);
        //Нажимаем на кнопку "Заказать" под формой
        newOrderObject.clickOnMakeOrderButton();

        // Подтверждаем заказ в поп-апе ("Да")
        newOrderObject.clickOnConfirmOrderButton();

        //Проверяем, был ли создан заказ, и выводим его ID
        System.out.println("ID заказа: " + newOrderObject.getOrderId());

        // Переходим на страницу просмотра статуса заказа
        newOrderObject.clickOnviewStatusButton();

    }

    @After
    public void closeBrowser() {
        driver.quit();
    }

    private void fillingOrderFields_1(NewOrderPOM newOrderObject) {
        newOrderObject.setFirstName(firstName);
        newOrderObject.setLastName(lastName);
        newOrderObject.setAddress(address);
        newOrderObject.setStation(stationId);
        newOrderObject.setPhoneNumber(phoneNumber);
    }

    private void fillingOrderFields_2(NewOrderPOM newOrderObject) {
        newOrderObject.setRentalStartDater(rentalStartDate);
        newOrderObject.setRentalPeriod(rentalPeriod);
        newOrderObject.setScooterColour(scooterColour);
    }

    private String tomorrowDate() {
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return formatter.format(tomorrow);
    }
}
