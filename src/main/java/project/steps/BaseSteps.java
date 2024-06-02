package project.steps;

import framework.driver.DriverSingleton;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

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


    protected void waitForPresence(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    protected void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    protected void switchFrame(WebElement element) {
        scrollIntoView(element);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
    }

    protected void switchTo(WebElement element) {
        driver.switchTo().frame(element);
    }

    protected void clickOn(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element))
                .click();
    }

    protected void clickOn(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator))
                .click();
    }

    protected void clear(WebElement element) {
        waitForElement(element).clear();
    }

    protected void fillIn(WebElement element, String textToFillIn) {
        waitForElement(element).sendKeys(textToFillIn);
    }

    protected void fillIn(By locator, String textToFillIn) {
        waitForElement(locator).sendKeys(textToFillIn);
    }

    protected WebElement waitForElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void waitForElementToBeDisabled(By locator) {
        wait.until(driver -> !driver.findElement(locator).isEnabled());
    }

    protected String getElementText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element))
                .getText();
    }

    protected List<String> getTextOfAllElementsLocatedBy(By locator) {
        List<String> elementsText = new LinkedList<>();
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator))
                .forEach(element -> elementsText.add(element.getText()));
        return elementsText;
    }

    protected void waitForScriptsToLoad() {
        wait.until(driver -> ((JavascriptExecutor) driver)
                .executeScript("return document.readyState").equals("complete"));
    }

    public List<WebElement> waitForAllIdenticalElements(By xpathLocator) {
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(xpathLocator));
    }

    public boolean elementIsPresent(By locator) {
        int implicitTimeout = getPropertyNumber("IMPLICIT_TIMEOUT");
        driver.manage().timeouts().implicitlyWait(implicitTimeout, TimeUnit.SECONDS);
        boolean elementIsFound = !driver.findElements(locator).isEmpty();
        driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
        return elementIsFound;
    }

    public boolean elementIsDisplayed(WebElement locator) {
        return locator.isDisplayed();
    }

    public boolean elementIsDisplayed(By locator) {
        return driver.findElement(locator).isDisplayed();
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
