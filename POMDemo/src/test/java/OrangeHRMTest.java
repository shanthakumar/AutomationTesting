import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHRMTest {

    WebDriver driver;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void VerifyAdmin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.selectAdmin();

        AdminPage adminPage = new AdminPage(driver);
        adminPage.verifyAdmin("Admin");

        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.logout();
        Thread.sleep(3000);
    }

    @Test
    public void VerifyPIM() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.selectPIM();

        PIMPage pimPage = new PIMPage(driver);
        pimPage.verifyPIN("1234");
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
