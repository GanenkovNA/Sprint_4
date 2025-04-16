package ru.yandex.praktikum.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.numberOfWindowsToBe;
import static ru.yandex.praktikum.resources.Config.DEFAULT_WAIT_TIME;

public class YandexPOM {
    private final WebDriver driver;
    private static final By yandexPageLocator = By.xpath("//html[contains(@class, 'zen-page')]");

    public YandexPOM(WebDriver driver) {
        this.driver = driver;
    }

    public void goToSecondWindow(String originalWindow){
        // Ожидаем, пока откроется вторая вкладка
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(numberOfWindowsToBe(2));

        // Переключаемся на открывшуюся вкладку
        for (String windowHandle : driver.getWindowHandles()) {
            if(!originalWindow.contentEquals(windowHandle)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    public boolean isYandexPageLoaded(){
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.presenceOfElementLocated(yandexPageLocator));
        return true;
    }
}
