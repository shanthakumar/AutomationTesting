package Utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.function.Function;

public class WaitHelper {

    private final WebDriverWait wait;
    private Wait<WebDriver> fluentWait;

    public WaitHelper(WebDriver driver) {
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(45));

        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(Duration.ofSeconds(30))          // Max time to wait
                .pollingEvery(Duration.ofSeconds(5))          // Frequency of checks
                .ignoring(NoSuchElementException.class)      // Exceptions to skip during wait
                .withMessage("Custom Timeout: Element not found!"); // Optional error message
    }

    public boolean isElementDisplayed(By path) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(path)).isDisplayed();
    }

    public boolean isElementLoaded(By path) {
        return wait.until(new Function<WebDriver, Boolean>() {
            public Boolean apply(WebDriver driver) {
                return driver.findElement(path).isDisplayed();
            }
        });
    }
}
