package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {

    @FindBy(xpath = "(//span[text()='Admin' and @class='oxd-text oxd-text--span oxd-main-menu-item--name'])")
    public WebElement adminSpan;

    @FindBy(xpath = "//span[text()='PIM' and @class='oxd-text oxd-text--span oxd-main-menu-item--name']")
    public WebElement pimSpan;

    public DashboardPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public void selectAdmin() {
        click(adminSpan);
    }

    public void selectPIM() {
        click(pimSpan);
    }
}
