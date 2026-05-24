import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.AdminPage;
import pages.LoginPage;
import pages.PIMPage;

import java.time.Duration;

public class OrangeHRM {
    WebDriver driver;

    private LoginPage loginPage;
    private AdminPage adminPage;
    private PIMPage pimPage;

    @BeforeMethod
    @Step("Setup browser and initialize object")
    @Description("DRIVER SETUP | Wait | Maximize")
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        loginPage = new LoginPage(driver);
        adminPage = new AdminPage(driver);
        pimPage = new PIMPage(driver);
    }

    @Description("Verify Admin user")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 1)
    public void VerifyAdmin() {
        loginPage.load();
        loginPage.loginUser("Admin", "admin123");
        adminPage.clickAdminMenu();
        adminPage.searchUser("Admin");
        adminPage.verifyRecordDisplayed();
        adminPage.logout();
    }

    @Description("Verify PIM user")
    @Severity(SeverityLevel.CRITICAL)
    @Test(priority = 2)
    public void VerifyPIM() {
        loginPage.load();
        loginPage.loginUser("Admin", "admin123");
        pimPage.clickPIMMenu();
        pimPage.searchEmployee("123");
        pimPage.verifyRecordDisplayed();
        pimPage.logout();
    }

    @Description("Verify Multiple PIM users")
    @Severity(SeverityLevel.NORMAL)
    @Test(priority = 2)
    public void VerifyMultipleRecords() {
        loginPage.load();
        loginPage.login();
        pimPage.clickPIMMenu();
        pimPage.searchMultipleEmployees();
        pimPage.logout();
    }

    @AfterMethod
    @Step("Close Browser")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
