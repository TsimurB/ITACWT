package project.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class MainPage extends BasePage {

    WebDriver driver;

    @FindBy(xpath = "//div[@id = 'app']")
    private WebElement mainFrame;

    @FindBy(xpath = "//iframe[contains(@id, 'systemeio-iframe')]")
    private WebElement iframe;

    @FindBy(xpath = "//div[contains(@style, 'background:')]")
    private WebElement screenColor;

    @FindBy(xpath = "//a[@data-testid = 'popup-close-icon']")
    private WebElement closePopup;

    @FindBy(xpath = "//div[text() = 'I want to receive my copy ']")
    private WebElement receiveCopy;
}
