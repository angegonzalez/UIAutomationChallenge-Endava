package base;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Date;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected JavascriptExecutor js;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.TIMEOUT));
        this.js = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public WebElement mapToElement(By locator) {
        return driver.findElement(locator);
    }

    public List<WebElement> mapToElements(By locator) {
        return driver.findElements(locator);
    }

    public void waitForElementPresent(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
