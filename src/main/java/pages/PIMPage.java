package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class PIMPage extends BasePage
{
    private static int PIM_MENU_INDEX = 2;

    public PIMPage(WebDriver driver)
    {
        super(driver);
    }

    @Step("Click on PIM Menu")
    public void clickPIMMenu()
    {
        clickMenuItem(PIM_MENU_INDEX);
    }

    @Step("Search for Employee")
    public void searchEmployee(String employeeId)
    {
        searchRecord(employeeId);
    }

    @Step("Verify PIM record")
    public boolean verifyRecordDisplayed()
    {
        return isRecordDisplayed();
    }

}
