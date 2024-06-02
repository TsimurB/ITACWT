package project.pages;

import lombok.Getter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@Getter
public class MainPage extends BasePage {

    @FindBy(xpath = "//iframe[contains(@id, 'systemeio-iframe')]")
    private WebElement iframe;

    @FindBy(xpath = "//a[@data-testid = 'popup-close-icon']")
    private WebElement closeFrame;

    @FindBy(xpath = "//div[text() = 'I want to receive my copy ']")
    private WebElement receiveCopy;
}
