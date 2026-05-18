package Pages;

import org.openqa.selenium.WebDriver;

public class DashboardPage extends BasePage {
    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public void navigate() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }
}
