package pages;

import Utilities.WaitHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

// Common Functionality
public class BasePage
{
    WebDriver driver;
    WaitHelper waitHelper;

    private By userDropdown = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
    private By logoutLink = By.xpath("//a[text()='Logout']");

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        waitHelper = new WaitHelper(driver);
    }

    public static WebDriver getDriver(String name) {
        switch (name.toLowerCase()) {
            case "safari":
                WebDriverManager.safaridriver().setup();
                return new SafariDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "edge":
                WebDriverManager.edgedriver().setup();
                return new EdgeDriver();
            default:
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
        }
    }

    public void load() {
        // Override if needed
    }

    public void navigate() {
        // Override if needed
    }

    @Step("Logout from application")
    public void logout()
    {
        driver.findElement(userDropdown).click();
        driver.findElement((logoutLink)).click();
        attachScreenshot("After Logout");
    }

    public boolean isElementDisplayed(By path) {
        return waitHelper.isElementDisplayed(path);
    }

    @Step("Click on Menu Item")
    public void clickMenuItem(int index)
    {
        driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span oxd-main-menu-item--name'])[" + index + "]")).click();
        attachScreenshot("Menu Item Clicked");
    }

    @Step("Search for a record")
    public void searchRecord(String searchText)
    {
        driver.findElement(By.xpath("//button[@class='oxd-button oxd-button--medium oxd-button--ghost']")).click();
        driver.findElement(By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]")).sendKeys(searchText);
        driver.findElement(By.xpath("//button[text()=' Search ']")).click();
        attachScreenshot("Search Results");
    }

    @Step("Verify record is Displayed")
    public boolean isRecordDisplayed()
    {
        boolean displayed = driver.findElement(By.xpath("(//span[@class='oxd-text oxd-text--span'])[1]")).isDisplayed();
        attachScreenshot("Record Verification");

        return displayed;
    }

    @Attachment(value = "Screenshot: {name}",type = "image/png")
    public byte[] attachScreenshot(String name){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
