package pages;

import Utilities.ExcelReader;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PIMPage extends BasePage
{
    ExcelReader excelReader;

    private static int PIM_MENU_INDEX = 2;

    public PIMPage(WebDriver driver)
    {
        super(driver);
        excelReader = new ExcelReader();
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

    @Step("Search Multiple Employees")
    public void searchMultipleEmployees() {
        String[] ids = excelReader.getData("Sheet2");

        for (String id: ids) {
            searchEmployee(id);
        }
    }

    @Step("Verify PIM record")
    public boolean verifyRecordDisplayed()
    {
        return isRecordDisplayed();
    }

}
