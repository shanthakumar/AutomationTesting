package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class AdminPage extends BasePage
{
    private static int ADMIN_MENU_INDEX  =1;

    public AdminPage(WebDriver driver)
    {
        super(driver);
    }

    @Step("Click on Admin menu")
    public void clickAdminMenu()
    {
        clickMenuItem(ADMIN_MENU_INDEX);
    }
    @Step("Search for User")
    public void searchUser(String username)
    {
        searchRecord(username);
    }
    @Step("Verify record is Displayed")
    public boolean verifyRecordDisplayed()
    {
        return isRecordDisplayed();
    }

}
