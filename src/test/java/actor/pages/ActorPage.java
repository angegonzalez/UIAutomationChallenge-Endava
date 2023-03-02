package actor.pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActorPage extends BasePage {

    Logger logger = LogManager.getLogger(ActorPage.class);
    By lblDepartment = By.xpath("//span[contains(text(),\"Department\")]/..");
    By ddlActing = By.xpath("//a[contains(text(),\"Acting\")]/..");
    By tblActing = By.xpath("//table[@class=\"card credits\"]");
    public ActorPage(WebDriver driver) {
        super(driver);
    }

    public ActorPage filterActingDepartment(){
        logger.info("Selecting acting timeline...");
        waitForElementPresent(lblDepartment);
        js.executeScript("arguments[0].click();",mapToElement(lblDepartment));
        waitForElementPresent(ddlActing);
        js.executeScript("arguments[0].click();",mapToElement(ddlActing));
        return this;
    }

    public String getTimelineText(){
        logger.info("Retrieving actor's acting timeline info...");
        waitForElementPresent(tblActing);
        return mapToElement(tblActing).getText();
    }
}
