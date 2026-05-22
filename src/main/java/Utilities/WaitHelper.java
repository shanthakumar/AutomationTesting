package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitHelper {

    private final WebDriverWait wait;

    public WaitHelper(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));
    }

    public void isElementDisplayed(By path) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(path)).isDisplayed();
    }
}
