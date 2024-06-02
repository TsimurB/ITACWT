import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import project.enums.Url;
import project.steps.MainPageSteps;

public class PopupTest extends BaseTest {
    MainPageSteps mainPageSteps = new MainPageSteps();

    @BeforeEach
    public void beforeClass() {
        open(Url.HOMEPAGE.getUrl());
    }

    @Test
    public void testReceiveButtonPresentInPopup() {
        mainPageSteps
                // Ожидание появления iframe
                .waitingWhileNewFrameAppear()
                // Переключение в контекст iframe
                .moveToNewFrame()
                .verifyReceiveCopyButtonPresenceInFrame();
    }

    @Test
    public void testCloseButtonWork() {
        mainPageSteps
                // Ожидание появления iframe
                .waitingWhileNewFrameAppear()
                // Переключение в контекст iframe
                .moveToNewFrame()
                // Закрытие iframe
                .closeNewFrame()
                // Возвращение на MainFrame
                .returnToMainFrame()
                .verifyThatIframeClosed();
    }
}
