package pages;

import Utilities.WaitHelper;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.openqa.selenium.*;

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

    @Step("Logout from application")
    public void logout()
    {
        driver.findElement(userDropdown).click();
        driver.findElement((logoutLink)).click();
        attachScreenshot("After Logout");
    }

    public void isElementDisplayed(By path) {
        waitHelper.isElementDisplayed(path);
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
