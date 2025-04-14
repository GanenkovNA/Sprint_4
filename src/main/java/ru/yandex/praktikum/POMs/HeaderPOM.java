package ru.yandex.praktikum.POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static ru.yandex.praktikum.resources.Config.BASE_URL;
import static ru.yandex.praktikum.resources.Config.DEFAULT_WAIT_TIME;

public class HeaderPOM {

    // Локаторы логотипов
    protected final By logoYandexLocator = By.xpath("//a[@class='Header_LogoYandex__3TSOI']");
    protected final By logoScooterLocator = By.xpath("//a[@class='Header_LogoScooter__3lsAR']");
    protected final By headerDisclaimerLocator = By.xpath("//div[@class='Header_Disclaimer__3VEni']");
    // Локаторы кнопок в шапке
    protected final By orderButtonLocator = By.xpath("//button[@class='Button_Button__ra12g']");
    protected final By orderStatusButtonLocator = By.xpath("//button[@class='Header_Link__1TAG7']");
    protected final By cookieButtonLocator = By.xpath("//button[@class='App_CookieButton__3cvqF']");
    // Локаторы элементов для проверки заказа
    protected final By orderInputFieldLocator = By.xpath("//input[contains(@class, 'Header_Input__xIoUq')]");
    protected final By checkOrderButtonLocator = By.xpath("//button[contains(@class, 'Header_Button__28dPO')]");
    // Локатор фонового изображения главной страницы
    protected final By backgroundImgLocator = By.xpath("//img[@alt='Scooter blueprint']");
    protected WebDriver driver;

    public HeaderPOM(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnYandexLogo() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(logoYandexLocator))
                .click();
    }

    public String getYandexLogoUrl(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(logoYandexLocator));
        return driver.findElement(logoYandexLocator).getAttribute("href");
    }


    public void clickOnScooterLogo() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(logoScooterLocator))
                .click();
    }

    public String getScooterLogoUrl() {
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(logoScooterLocator));
        return driver.findElement(logoScooterLocator).getAttribute("href");
    }

    public void isHomePageLoaded() {
        // Ждём загрузки фона страницы (для подтверждения того, что страница загружена)
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.visibilityOfElementLocated(backgroundImgLocator));
    }

    public void openHomePage(){
        // Открываем в браузере домашнюю страницу
        driver.get(BASE_URL);

        // Ждём загрузки фона страницы (для подтверждения того, что страница загружена)
        isHomePageLoaded();

        // Нажимаем на кнопку согласия куки (чтобы не мешалась в firefox)
        clickOnCookieButtonLocator();
    }

    public void clickOnOrderButton(){
        // Нажимаем на кнопку "Заказать"
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(orderButtonLocator))
                .click();
    }

    public void clickOnCookieButtonLocator() {
        // Нажимаем на кнопку согласия с куки
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(cookieButtonLocator))
                .click();
    }

    public void checkOrder(String orderID) {
        // Нажимаем на кнопку "Статус заказа"
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(orderStatusButtonLocator))
                .click();

        // Вводим id заказа
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(orderInputFieldLocator))
                .sendKeys(orderID);

        // Нажимаем на кнопку "Go!"
        driver.findElement(checkOrderButtonLocator).click();
    }
}
