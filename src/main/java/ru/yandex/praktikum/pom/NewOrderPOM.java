package ru.yandex.praktikum.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.resources.Config.DEFAULT_WAIT_TIME;
import static ru.yandex.praktikum.resources.Config.ORDER_URL;

public class NewOrderPOM extends HeaderPOM {
    // Локаторы первой части заказа
    private static final By newOrderHeaderLocator = By.xpath("//div[@class='Order_Header__BZXOb' and contains(text(), 'Для кого самокат')]");
    private static final By firstNameFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Имя')]");
    private static final By lastNameFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Фамилия')]");
    private static final By addressFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Адрес')]");
    private static final By stationDropDownListLocator = By.xpath("//input[@class='select-search__input']");
    private static final String stationElementXpath = "//button[@value='%d']";
    private static final By phoneNumberFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Телефон')]");
    private static final By nextButtonLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");

    // Локаторы второй части заказа
    private static final By newOrderSecondHeaderLocator = By.xpath("//div[@class='Order_Header__BZXOb' and contains(text(), 'Про аренду')]");
    private static final By rentalStartDateFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Когда')]");
    private static final By rentalPeriodFieldLocator = By.xpath("//div[@class='Dropdown-control']");
    private static final By makeOrderButtonLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private static final By returnButtonLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Назад']");

    // Локаторы всплывающего окна подтверждения создания заказа
    private static final By popupHeaderLocator = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and contains(text(), 'Хотите оформить заказ?')]");
    private static final By closePopupButtonLocator = By.xpath("//button[text()='Нет']");
    private static final By confirmOrderButtonLocator = By.xpath("//button[text()='Да']");

    // Локаторы всплывающего окна оформленного заказа
    private static final By orderFormedHeaderLocator = By.xpath("//div[@class='Order_ModalHeader__3FDaJ' and contains(text(), 'Заказ оформлен')]");
    private static final By orderIdTextLocator = By.xpath("//div[contains(@class, 'Order_Text__2broi')]");
    private static final By viewStatusButtonLocator = By.xpath("//button[text()='Посмотреть статус']");

    public NewOrderPOM(WebDriver driver) {
        super(driver);
    }

    public void isNewOrderPageLoaded(){
        // Ждём загрузки заголовка страницы (для подтверждения того, что страница загружена)
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(newOrderHeaderLocator));
    }

    public void openNewOrderPage(){
        // Открываем в браузере домашнюю страницу
        driver.get(ORDER_URL);

        // Ждём загрузки заголовка страницы (для подтверждения того, что страница загружена)
        isNewOrderPageLoaded();

        // Нажимаем на кнопку согласия куки (чтобы не мешалась в firefox)
        clickOnCookieButtonLocator();
    }

    public void setFirstName(String firstName){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
            .until(ExpectedConditions.elementToBeClickable(firstNameFieldLocator))
            .sendKeys(firstName);
    }

    public void setLastName(String lastName){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(lastNameFieldLocator))
                .sendKeys(lastName);
    }

    public void setAddress(String address){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(addressFieldLocator))
                .sendKeys(address);
    }

    public void setStation(int stationID){
        // Раскрываем выпадающий список со станциями
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(stationDropDownListLocator))
                .click();

        By stationLocator = By.xpath(String.format(stationElementXpath, stationID));

        // Прокручиваем список до нужной станции
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(stationLocator));

        // Нажимаем на выбранную станцию
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(stationLocator))
                .click();
    }

    public void setPhoneNumber (String phoneNumber){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(phoneNumberFieldLocator))
                .sendKeys(phoneNumber);
    }

    public void clickOnNextButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(nextButtonLocator))
                .click();
    }

    public void isNewOrderSecondPageLoaded(){
        // Ждём загрузки второй страницы создания заказа (для подтверждения того, что страница загружена)
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(newOrderSecondHeaderLocator));
    }

    public void setRentalStartDater (String date){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(rentalStartDateFieldLocator))
                .sendKeys(date);
        // Нажимаем Enter для закрытия календаря
        driver.findElement(rentalStartDateFieldLocator).sendKeys(Keys.ENTER);
    }

    public void setRentalPeriod(String rentalPeriod){
        // Раскрываем выпадающий список
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriodFieldLocator))
                .click();

        By rentalPeriodVarLocator = By.xpath("//div[@class='Dropdown-option' and text()='" + rentalPeriod + "']");

        // Прокручиваем список до нужного периода
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(rentalPeriodVarLocator));

        // Нажимаем на выбранный период
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(rentalPeriodVarLocator))
                .click();
    }

    public void setScooterColour(String colour){
        By scooterColourLocator = By.xpath("//input[@id='" + colour + "']");
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(scooterColourLocator))
                .click();
    }

    public void clickOnMakeOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(makeOrderButtonLocator))
                .click();
    }

    public void isPopupLoaded(){
        // Ждём загрузки заголовка поп-апа (для подтверждения того, что страница загружена)
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(popupHeaderLocator));
    }

    public void clickOnConfirmOrderButton(){
        //Проверяем, что находимся на шаге подтверждения заказа
        isPopupLoaded();
        //Нажимаем на кнопку "Да"
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(confirmOrderButtonLocator))
                .click();
    }

    public void isOrderFormed(){
        // Ждём загрузки заголовка всплывающего окна (для подтверждения того, что страница загружена)
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(orderFormedHeaderLocator));
    }

    public String getOrderId(){
        //Проверяем, что находимся в окне оформленного заказа
        isOrderFormed();
        // Выделяем номер заказа и возвращаем его
        return driver.findElement(orderIdTextLocator).
                getText().
                split("\\D+")[1]; //разбиваем строку по нечисловым символам, возвращая массив ["", "422157", ...].
    }

    public void clickOnViewStatusButton(){
        //Проверяем, что находимся в окне оформленного заказа
        isOrderFormed();
        // Нажимаем на кнопку "Посмотреть статус"
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(viewStatusButtonLocator))
                .click();
    }

}