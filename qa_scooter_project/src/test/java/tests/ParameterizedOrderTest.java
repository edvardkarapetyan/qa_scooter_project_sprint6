package tests;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import pageobjects.MainPage;
import pageobjects.OrderFormPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.params.provider.Arguments.arguments;

public class ParameterizedOrderTest extends BaseTest {

    private static Stream<Object[]> testData() {
        return Stream.of(
                new Object[]{"Иван", "Иванов", "ул. Пушкина, 1", "Черкизовская", "+79001112233", "10.05.2025", "сутки", "black", "Тестовый заказ 1"},
                new Object[]{"Мария", "Петрова", "ул. Лермонтова, 2", "Сокольники", "+79004445566", "12.05.2025", "пятеро суток", "grey", "Тестовый заказ 2"}
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    public void testOrderWithDifferentData(String name, String lastName, String address, String metro, String phone,
                                           String date, String period, String color, String comment) {
        MainPage mainPage = new MainPage(driver);
        OrderFormPage orderFormPage = new OrderFormPage(driver);

        mainPage.acceptCookies();
        mainPage.clickOrderButtonTop();

        orderFormPage.fillFirstStep(name, lastName, address, metro, phone);
        orderFormPage.fillSecondStep(date, period, color, comment);
        orderFormPage.confirmOrder();

        assertTrue(orderFormPage.isOrderSuccessModalDisplayed(), "Окно успешного заказа не отобразилось для набора данных: " + name + " " + lastName);
    }
}