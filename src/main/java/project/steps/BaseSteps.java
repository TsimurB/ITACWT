package project.steps;

import framework.driver.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

import static framework.utils.PropsReader.getPropertyNumber;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BaseSteps {

    protected WebDriver driver;
    protected FluentWait<WebDriver> wait;
    private static final int EXPLICIT_TIMEOUT = getPropertyNumber("EXPLICIT_TIMEOUT");
    private static final int POLLING_TIMEOUT = getPropertyNumber("POLLING_TIMEOUT");

    public BaseSteps() {
        driver = DriverSingleton.getDriver();
        driver.manage().window().maximize();
        wait = new FluentWait<>(driver).withTimeout(Duration.ofSeconds(EXPLICIT_TIMEOUT))
                .pollingEvery(Duration.ofMillis(POLLING_TIMEOUT))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
    }

    protected void switchTo(WebElement element) {
        driver.switchTo().frame(element);
    }

    protected void clickOn(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    protected void waitForElement(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected String getElementText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element))
                .getText();
    }

    public boolean elementIsDisplayed(WebElement locator) {
        return locator.isDisplayed();
    }

    public void elementIsNotDisplayedCheck(WebElement element) {
        try {
            boolean isInvisible = wait.until(ExpectedConditions.invisibilityOf(element));
            assertTrue(isInvisible, "Элемент отображается на странице, хотя не должен");
        } catch (Exception e) {
            System.err.println("Произошла ошибка при проверке видимости элемента: " + e.getMessage());
        }
    }
}
