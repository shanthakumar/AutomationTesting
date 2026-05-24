package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class GooglePage extends BasePage {

    private static String Google_URL = "https://www.google.com";

    private final String googleIconPath = "svg[class=lnXdpd]";

    @FindBy(css = googleIconPath) WebElement googleIcon;

    public GooglePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void load() {
        driver.navigate().to(Google_URL);
        Assert.assertTrue(waitHelper.isElementLoaded(By.cssSelector(googleIconPath)));
        attachScreenshot("Google Page");
    }

}
