package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AdminPage extends BasePage {

    @FindBy(xpath = "(//input[@class='oxd-input oxd-input--active'])[2]")
    public WebElement usernameTextField;

    @FindBy(xpath = "//button[text()=' Search ']")
    public WebElement searchButton;

    @FindBy(xpath = "//button[@class='oxd-button oxd-button--medium oxd-button--ghost']")
    public WebElement resetButton;

    @FindBy(xpath = "(//span[@class='oxd-text oxd-text--span'])[1]")
    public WebElement result;

    public AdminPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyAdmin(String name) {
        click(resetButton);
        type(usernameTextField, name);
        click(searchButton);
        result.isDisplayed();
    }
}
