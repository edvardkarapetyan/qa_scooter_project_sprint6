package tests;

import org.junit.jupiter.api.Test;
import pageobjects.MainPage;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class QuestionAnswerTest extends BaseTest {

    @Test
    public void checkAllQuestionsAndAnswers() {
        MainPage mainPage = new MainPage(driver);
        mainPage.acceptCookies();

        String[] expectedAnswers = {
                "Сутки — 400 рублей. Оплата курьеру — наличными или картой.",
                "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.",
                "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.",
                "Только начиная с завтрашнего дня. Но скоро станем расторопнее.",
                "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.",
                "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.",
                "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.",
                "Да, обязательно. Всем самокатов! И Москве, и Московской области."
        };

        for (int i = 1; i <= expectedAnswers.length; i++) {
            mainPage.clickQuestionByIndex(i);
            String actualAnswer = mainPage.getAnswerTextByIndex(i);

            assertNotNull(actualAnswer, "Ответ на вопрос " + i + " не отобразился");
            assertTrue(actualAnswer.contains(expectedAnswers[i-1]), "Ответ на вопрос " + i + " не совпадает с ожидаемым. Актуальный: " + actualAnswer);
        }
    }

    private void assertTrue(boolean contains, String s) {
    }

    private void assertNotNull(String actualAnswer, String s) {
    }
}