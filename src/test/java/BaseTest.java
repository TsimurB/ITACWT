import framework.driver.DriverSingleton;
import org.junit.jupiter.api.AfterEach;

public class BaseTest {

//    public void open(String url) {
//        DriverSingleton.getDriver().get(url);
//    }
    public void open(String url) {
        DriverSingleton.getDriver().get(url);
    }

//    private WebDriver driver;
//
//    @BeforeEach
//    public void setUp() {
//        WebDriverManager.chromedriver().setup();
//        driver = new ChromeDriver();
//        driver.get("https://systeme.io/blog/make-money-home");
//    }
//
//    @AfterEach
//    public void tearDown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }

//    @AfterTest(alwaysRun = true)
    @AfterEach()
    public void afterClass() {
        DriverSingleton.closeDriver();
    }
}
