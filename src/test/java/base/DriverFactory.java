package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverFactory {
    public static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

    public WebDriver createDriver(DriverType browser) {
        switch (browser) {
            case SAFARI -> threadLocalDriver.set(new SafariDriver());
            case EDGE -> threadLocalDriver.set(new EdgeDriver());
            case CHROME -> threadLocalDriver.set(new ChromeDriver());
        }
        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        getDriver().navigate().to(GlobalVariables.BASE_URL);
        return getDriver();
    }

    public static synchronized WebDriver getDriver() {
        return threadLocalDriver.get();
    }


}
