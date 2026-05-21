import Pages.*;
import Utilities.ExcelReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OrangeHRMTest {

    WebDriver driver;
    ExcelReader excelReader;

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        excelReader = new ExcelReader();
    }

    @Test
    public void VerifyAdmin() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.selectAdmin();

        String[] admins = excelReader.getData("Sheet1");

        for (String user: admins) {
            AdminPage adminPage = new AdminPage(driver);
            adminPage.verifyAdmin(user);
            Thread.sleep(2000);
        }

        Thread.sleep(3000);
        LogoutPage logoutPage = new LogoutPage(driver);
        logoutPage.logout();
    }

    @Test
    public void VerifyPIM() throws InterruptedException {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login();

        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.selectPIM();

        String[] ids = excelReader.getData("Sheet2");

        for (String id: ids) {
            PIMPage pimPage = new PIMPage(driver);
            pimPage.verifyPIM(id);
            Thread.sleep(2000);
        }
    }

    @AfterMethod
    public void teardown() {
        driver.quit();
    }
}
