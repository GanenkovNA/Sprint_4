package ru.yandex.praktikum.POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.resources.Config.DEFAULT_WAIT_TIME;

public class OrderPOM extends HeaderPOM {
    // Локаторы первой части заказа
    private final By firstNameFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Имя')]");
    private final By lastNameFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Фамилия')]");
    private final By addressFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Адрес')]");;
    private final By stationDropDownListLocator = By.xpath("//input[@class='select-search__input']");
    private final By phoneNumberFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Телефон')]");
    private final By nextButtonLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");

    // Локаторы второй части заказа
    private final By rentalStartDateFieldLocator = By.xpath("//input[contains(@class, 'Input_Input__1iN_Z') and contains(@placeholder, 'Когда')]");
    private final By rentalPeriodFieldLocator = By.xpath("//div[@class='Dropdown-control']");
    private final By makeOrderButtonLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");
    private final By returnButtonLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Назад']");

    // Локаторы всплывающего окна подтверждения создания заказа
    private final By closePopupButtonLocator = By.xpath("//button[text()='Нет']");
    private final By confirmOrderButtonLocator = By.xpath("//button[text()='Да']");

    // Локатор всплывающего окна оформленного заказа
    private final By viewStatusButtonLocator = By.xpath("//button[text()='Посмотреть статус']");

    public OrderPOM(WebDriver driver) {
        super(driver);
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

        By stationLocator = By.xpath("//button[@value='" + stationID + "']");

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

    public void clickOnConfirmOrderButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(confirmOrderButtonLocator))
                .click();
    }

    public void clickOnviewStatusButton(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(viewStatusButtonLocator))
                .click();
    }

}