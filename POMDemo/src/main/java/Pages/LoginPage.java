package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    @FindBy(xpath = "//input[@name='username']")
    private WebElement usernameTextField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordTextField;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @Override
    public void navigate() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
    }

    public void login() {
        login("Admin", "admin123");
    }

    public void login(String name, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameTextField));
        type(usernameTextField, name);
        //usernameTextField.sendKeys(name);
        type(passwordTextField, password);
        //passwordTextField.sendKeys(password);
        click(loginButton);
    }
}
