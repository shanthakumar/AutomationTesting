import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import testdata.LoginTestData;

public class LoginTest {

    WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    @Step("Setup browser and initialize object")
    @Description("DRIVER SETUP | Wait | Maximize")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
    }

    @Description("Verify Login")
    @Test(priority = -2)
    public void VerifyLogin() {
        loginPage.load();
        loginPage.login();
    }

    @Description("Verify Login Using Config Credentials")
    @Test(priority = -1)
    public void VerifyLoginUsingConfig() {
        loginPage.load();
        loginPage.verifyConfigUser();
    }

    @Description("Verify Login Using DDT")
    @Test(priority = 200, dataProvider = "testAdmins", dataProviderClass = LoginTestData.class)
    public void VerifyLoginUsingDDT(String username, String password) {
        loginPage.load();
        loginPage.loginUser(username, password);
    }

    @Description("Verify Invalid Login Credentials")
    @Test(priority = 201, dataProvider = "invalidUserCredentials", dataProviderClass = LoginTestData.class)
    public void VerifyInvalidLogin(String username, String password) {
        loginPage.load();
        loginPage.verifyUnsuccessfulLogin(username, password);
    }

    @AfterMethod
    @Step("Close Browser")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
