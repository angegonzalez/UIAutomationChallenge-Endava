package drivers;

import base.DriverManager;
import org.openqa.selenium.edge.EdgeDriver;

public class EdgeDriverManager extends DriverManager {
    @Override
    protected void createDriver() {
        driver = new EdgeDriver();
    }
}
