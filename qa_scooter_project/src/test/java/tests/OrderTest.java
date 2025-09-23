package tests;

import org.junit.jupiter.api.Test;
import pageobjects.MainPage;
import pageobjects.OrderFormPage;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderTest extends BaseTest {

    @Test
    public void testOrderFromTopButton() {
        MainPage mainPage = new MainPage(driver);
        OrderFormPage orderFormPage = new OrderFormPage(driver);

        mainPage.acceptCookies();
        mainPage.clickOrderButtonTop();

        orderFormPage.fillFirstStep("Эдвард", "Карапетян", "ул. Тестовая, д. 123", "Сокольники", "+79999876543");
        orderFormPage.fillSecondStep("15.05.2025", "трое суток", "black", "Позвонить за час");
        orderFormPage.confirmOrder();

        assertTrue(orderFormPage.isOrderSuccessModalDisplayed(), "Окно успешного заказа не отобразилось");
    }

    @Test
    public void testOrderFromBottomButton() {
        MainPage mainPage = new MainPage(driver);
        OrderFormPage orderFormPage = new OrderFormPage(driver);

        mainPage.acceptCookies();
        mainPage.clickOrderButtonBottom();

        orderFormPage.fillFirstStep("Карапетян", "Эдвард", "пр. Примерный, д. 456", "Лубянка", "+79991234567");
        orderFormPage.fillSecondStep("20.05.2025", "двое суток", "grey", "Не звонить, сплю");
        orderFormPage.confirmOrder();

        assertTrue(orderFormPage.isOrderSuccessModalDisplayed(), "Окно успешного заказа не отобразилось");
    }
}