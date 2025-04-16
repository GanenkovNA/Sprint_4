package e2e;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import ru.yandex.praktikum.TestBase;
import ru.yandex.praktikum.pom.NewOrderPOM;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertNotNull;

@RunWith(Parameterized.class)
public class NewOrderTestCase extends TestBase {
    // Переменные для первой части
    private final static String firstName = "Скотт";
    private final static String lastName = "Пилигрим";
    private final static String address = "Альберта Авеню, 65";
    private final static int stationId = 1;
    private final static String phoneNumber = "+79777777777";
    // Переменные для второй части
    private final String rentalStartDate = tomorrowDate();
    private final String rentalPeriod;
    private final String scooterColour;

    public NewOrderTestCase(String rentalPeriod, String scooterColour) {
        this.rentalPeriod = rentalPeriod;
        this.scooterColour = scooterColour;
    }

    @Parameterized.Parameters(name = "Заказ №{index}. Продолжительность: {0}. Цвет: {1}.")
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

    @Test
    public void NewOrderTest() {
        NewOrderPOM newOrderObject = new NewOrderPOM(driver);
        // Открываем домашнюю страницу
        newOrderObject.openNewOrderPage();

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

        //Проверяем, был ли создан заказ и сохраняем его ID
        String orderID = newOrderObject.getOrderId();
        assertNotNull(orderID);

        // Переходим на страницу просмотра статуса заказа
        newOrderObject.clickOnviewStatusButton();
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
