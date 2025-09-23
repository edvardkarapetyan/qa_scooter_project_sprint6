package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class MainPage {
    private final WebDriver driver;

    // Локаторы
    private final By orderButtonTop = By.xpath(".//button[text()='Заказать']");
    private final By orderButtonBottom = By.xpath("(.//button[text()='Заказать'])[last()]");
    private final By cookieConsentButton = By.id("rcc-confirm-button");

    // Конструктор
    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    // Методы
    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        WebElement element = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        element.click();
    }

    public void acceptCookies() {
        // Добавляем проверку на наличие элемента куки
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
            List<WebElement> cookieElements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(cookieConsentButton));

            if (!cookieElements.isEmpty() && cookieElements.get(0).isDisplayed()) {
                cookieElements.get(0).click();
            }
        } catch (Exception e) {
            // Если элемента нет или он не кликабелен, просто продолжаем выполнение
            System.out.println("Cookie banner not found or not clickable: " + e.getMessage());
        }
    }

    public void clickQuestionByIndex(int index) {
        By questionLocator = By.xpath(String.format("(//div[@data-accordion-component='AccordionItemButton'])[%d]", index));
        WebElement question = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.elementToBeClickable(question));
        question.click();
    }

    public String getAnswerTextByIndex(int index) {
        By answerLocator = By.xpath(String.format("(//div[@data-accordion-component='AccordionItemPanel'])[%d]", index));
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return driver.findElement(answerLocator).getText();
    }
}