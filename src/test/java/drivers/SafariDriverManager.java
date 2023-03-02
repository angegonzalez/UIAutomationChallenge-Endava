package drivers;

import base.DriverManager;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverManager extends DriverManager {

    @Override
    protected void createDriver() {
        driver = new SafariDriver();
    }
}
