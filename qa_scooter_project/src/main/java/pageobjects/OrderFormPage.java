package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderFormPage {
    private final WebDriver driver;

    // Локаторы для ПЕРВОГО шага формы ("Для кого самокат")
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // Локаторы для ВТОРОГО шага формы ("Про аренду")
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodDropdown = By.xpath(".//div[text()='* Срок аренды']");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath(".//button[text()='Заказать' and (@class='Button_Button__ra12g Button_Middle__1CSJM')]");

    // Локаторы для цветов самоката
    private final By blackCheckbox = By.id("black");
    private final By greyCheckbox = By.id("grey");

    // Локаторы для модального окна подтверждения
    private final By confirmationModal = By.xpath(".//div[contains(@class, 'Order_Modal')]");
    private final By yesButton = By.xpath(".//button[text()='Да']");
    private final By orderSuccessHeader = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");

    public OrderFormPage(WebDriver driver) {
        this.driver = driver;
    }

    // Заполнение ПЕРВОГО шага формы
    public void fillFirstStep(String name, String lastName, String address, String metroStation, String phone) {
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);
        setMetroStation(metroStation);
        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
    }

    private void setMetroStation(String stationName) {
        driver.findElement(metroField).click();
        WebElement stationElement = driver.findElement(By.xpath(String.format(".//div[text()='%s']", stationName)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", stationElement);
        stationElement.click();
    }

    // Заполнение ВТОРОГО шага формы
    public void fillSecondStep(String date, String rentalPeriod, String color, String comment) {
        setDeliveryDate(date);
        setRentalPeriod(rentalPeriod);
        setScooterColor(color);
        driver.findElement(commentField).sendKeys(comment);
        driver.findElement(orderButton).click();
    }

    private void setDeliveryDate(String date) {
        driver.findElement(dateField).sendKeys(date);
        driver.findElement(rentalPeriodDropdown).click();
    }

    private void setRentalPeriod(String period) {
        driver.findElement(rentalPeriodDropdown).click();
        driver.findElement(By.xpath(String.format(".//div[text()='%s']", period))).click();
    }

    private void setScooterColor(String color) {
        if (color.equals("black")) {
            driver.findElement(blackCheckbox).click();
        } else if (color.equals("grey")) {
            driver.findElement(greyCheckbox).click();
        }
    }

    // Подтверждение заказа в модальном окне
    public void confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmationModal));
        driver.findElement(yesButton).click();
    }

    // Проверка успешного создания заказа
    public boolean isOrderSuccessModalDisplayed() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderSuccessHeader));
        return driver.findElement(orderSuccessHeader).isDisplayed();
    }
}