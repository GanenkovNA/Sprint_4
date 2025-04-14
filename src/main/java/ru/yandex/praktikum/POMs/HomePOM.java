package ru.yandex.praktikum.POMs;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static ru.yandex.praktikum.resources.Config.BASE_URL;
import static ru.yandex.praktikum.resources.Config.DEFAULT_WAIT_TIME;

public class HomePOM extends HeaderPOM{
    private final By orderButtonMiddleLocator = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM')]");

    public HomePOM(WebDriver driver) {
        super(driver);
    }

    public void clickOnFaqElement(int serial){
        // Находим вопрос в FAQ
        By faqQuestionElement = By.xpath("//div[@id='accordion__heading-" + serial + "']");

        // Прокручиваем страницу до вопроса
        ((JavascriptExecutor)driver)
            .executeScript("arguments[0].scrollIntoView();", driver.findElement(faqQuestionElement));

        // Ждём загрузки элемента с вопросом и нажимаем на него
        // Простой метод `.click()` периодически не срабатывает в firefox
        try {
            new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                    .until(ExpectedConditions.elementToBeClickable(faqQuestionElement))
                    .click();
        } catch (Exception e) {
            ((JavascriptExecutor)driver).executeScript(
                    "arguments[0].click();",
                    driver.findElement(faqQuestionElement)
            );
        }
    }

    public String returnAnswerFromFaq (int serial){
        // Нажимаем на элемент с вопросом
        clickOnFaqElement(serial);

        By faqAnswerElement = By.xpath("//div[@id='accordion__panel-" + serial + "']");

        // Ждём раскрытия и появления ответа
        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
            .until(ExpectedConditions.visibilityOfElementLocated(faqAnswerElement));

        // Возвращаем текст ответа
        return driver.findElement(faqAnswerElement).getText();
    }

    public void clickOnOrderButtonMiddle(){
        ((JavascriptExecutor)driver)
                .executeScript("arguments[0].scrollIntoView();", driver.findElement(orderButtonMiddleLocator));

        new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_WAIT_TIME))
                .until(ExpectedConditions.elementToBeClickable(orderButtonMiddleLocator))
                .click();
    }
}