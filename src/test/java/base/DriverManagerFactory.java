package base;

import drivers.EdgeDriverManager;
import drivers.SafariDriverManager;

public class DriverManagerFactory {

    public static DriverManager getManager(DriverType type){
        DriverManager driverManager = null;

        switch(type){
            case SAFARI -> driverManager = new SafariDriverManager();
            case EDGE -> driverManager = new EdgeDriverManager();
        }

        return driverManager;
    }

}
