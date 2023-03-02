package common;

import base.DriverManagerFactory;
import base.DriverType;
import base.GlobalVariables;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class Hooks extends DriverHook {

    @Before
    public void before() {
        driverManager = DriverManagerFactory.getManager(DriverType.SAFARI);
        driver = driverManager.getDriver();
        driver.manage().window().maximize();
        driver.navigate().to(GlobalVariables.BASE_URL);
    }

    @After
    public void after(Scenario scenario) {
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
        driverManager.quitDriver();
    }
}
