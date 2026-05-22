package pages;

import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

// Common Functionality
public class BasePage
{
    WebDriver driver;
    WebDriverWait wait;
    private By userDropdown = By.xpath("//i[@class='oxd-icon bi-caret-down-fill oxd-userdropdown-icon']");
    private By logoutLink = By.xpath("//a[text()='Logout']");

    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @Step("Logout from application")
    public void logout()
    {
        driver.findElement(userDropdown).click();
        driver.findElement((logoutLink)).click();
        attachScreenshot("After Logout");
    }

    public void isElementDisplayed(By path) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(path)).isDisplayed();
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
