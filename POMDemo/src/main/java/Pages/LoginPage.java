package Pages;

import Utilities.ConfigReader;
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

    ConfigReader configReader;

    public LoginPage(WebDriver driver) {
        super(driver);
        configReader = new ConfigReader();
        PageFactory.initElements(driver, this);
    }

    @Override
    public void navigate() {
        driver.get(configReader.getProperty("url"));
    }

    public void login() {
        String username = configReader.getProperty("username");
        String password = configReader.getProperty("password");
        login(username, password);
    }

    public void login(String name, String password) {
        wait.until(ExpectedConditions.visibilityOf(usernameTextField));
        type(usernameTextField, name);
        type(passwordTextField, password);
        click(loginButton);
    }
}
