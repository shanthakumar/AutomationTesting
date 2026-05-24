import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import pages.BasePage;
import pages.GooglePage;
import pages.LoginPage;

public class CrossBrowserTest {

    WebDriver driver;

    private GooglePage googlePage;

    @BeforeMethod
    @Step("Setup browser and initialize object")
    @Parameters({"browser"})
    public void setup(String browser) {
        driver = BasePage.getDriver(browser);
        googlePage = new GooglePage(driver);
    }

    @Description("Verify Google Page")
    @Test
    public void HelloGoogle() {
        googlePage.load();
    }

    @AfterMethod
    @Step("Close Browser")
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
