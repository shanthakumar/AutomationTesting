package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PIMPage extends BasePage {

    @FindBy(xpath = "(//*[@placeholder='Type for hints...'])[1]")
    private WebElement employeeSearchField;

    @FindBy(xpath = "//button[normalize-space()='Search']")
    public WebElement searchButton;

    @FindBy(xpath = "//div[@class='orangehrm-horizontal-padding orangehrm-vertical-padding']//child::span")
    public WebElement result;

    public PIMPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    public void verifyPIN(String name) {
        type(employeeSearchField, name);
        click(searchButton);
        result.isDisplayed();
    }
}
