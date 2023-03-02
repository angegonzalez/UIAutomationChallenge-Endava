package common;

import base.DriverFactory;
import base.DriverType;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Hooks {
    private WebDriver driver;

    @Before
    public void before() {
        DriverFactory driverFactory = new DriverFactory();
        driver = driverFactory.createDriver(DriverType.CHROME);
    }
    @After(order = 0)
    public void quitBrowser(){
        driver.quit();
    }

    @After(order = 1)
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            Date now = new Date();
            String timestamp = now.toString();
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile,
                        new File("./src/test/resources/screenshots/" + scenario.getName() + " [" + timestamp + "].png"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
