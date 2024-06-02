package project.steps;

import project.pages.MainPage;

import static org.junit.jupiter.api.Assertions.*;

public class MainPageSteps extends BaseSteps {

    private final MainPage popupPage = new MainPage();

    public MainPageSteps waitingWhileNewFrameAppear() {
        waitForElement(popupPage.getIframe());
        return this;
    }

    public MainPageSteps moveToNewFrame() {
        switchTo(popupPage.getIframe());
        return this;
    }

    public MainPageSteps returnToMainFrame() {
        driver.switchTo().parentFrame();
        return this;
    }

    public void verifyReceiveCopyButtonPresenceInFrame() {
        assertTrue(elementIsDisplayed(popupPage.getReceiveCopy()),
                "The button 'I want to receive my copy' should be visible in the popup");
        assertEquals(getElementText(popupPage.getReceiveCopy()), "I want to receive my copy");
    }

    public MainPageSteps closeNewFrame() {
        clickOn(popupPage.getCloseFrame());
        return this;
    }

    public void verifyThatIframeClosed() {
        elementIsNotDisplayedCheck(popupPage.getIframe());
    }
}
