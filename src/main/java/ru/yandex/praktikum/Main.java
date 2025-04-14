package ru.yandex.praktikum;

import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.POMs.HomePOM;
import ru.yandex.praktikum.resources.DriverFactory;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {

        //Настройка браузера
        WebDriver driver = DriverFactory.getDriver("chrome");
        tomorrowDate();


    }

    private static void tomorrowDate(){
        LocalDate tomorrow = LocalDate.now().plusDays(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDate = formatter.format(tomorrow);
        System.out.println("Текущая дата (новый формат): " + formattedDate);
    }
}