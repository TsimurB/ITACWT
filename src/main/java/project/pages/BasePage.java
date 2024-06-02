package project.pages;


import framework.driver.DriverSingleton;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

@Getter
public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(DriverSingleton.getDriver(), this);
    }
}
