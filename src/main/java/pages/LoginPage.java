package pages;

import Utilities.ConfigReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginPage extends BasePage
{
    ConfigReader configReader;
    private static String LOGIN_URL = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";

    private By usernameField = By.xpath("//input[@name='username']");
    private By passwordField = By.xpath("//input[@name='password']");
    private By loginButton = By.xpath("//button[text()=' Login ']");
    private By invalidCredsText = By.xpath("//p[@class='oxd-text oxd-text--p oxd-alert-content-text' and text()='Invalid credentials']");

    public LoginPage(WebDriver driver)
    {
        super(driver);
        configReader = new ConfigReader();
    }

    @Step("Navigate to URL")
    public void load()
    {
        driver.get(LOGIN_URL);
        attachScreenshot("Login Page");
    }

    public void login() {
        String username = configReader.getProperty("username");
        String password = configReader.getProperty("password");
        login(username, password);
    }

    private void login(String username, String password)
    {
        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Login with valid credentials")
    public void loginUser(String username,String password) {
        login(username, password);
    }

    @Step("Login with incorrect credentials")
    public void verifyUnsuccessfulLogin(String username,String password)
    {
        login(username, password);
        Assert.assertTrue(isElementDisplayed(invalidCredsText), "Login Error message should be displayed.");
        attachScreenshot("Unsuccessful Login: Invalid credentials");
    }

    @Step("Login using Config credentials")
    public void verifyConfigUser() {
        login();
    }
}
