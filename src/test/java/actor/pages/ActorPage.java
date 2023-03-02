package actor.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ActorPage extends BasePage {

    By lblDepartment = By.xpath("//span[contains(text(),\"Department\")]/..");
    By ddlActing = By.xpath("//a[contains(text(),\"Acting\")]/..");
    By tblActing = By.xpath("//table[@class=\"card credits\"]");
    public ActorPage(WebDriver driver) {
        super(driver);
    }

    public ActorPage filterActingDepartment(){
        waitForElementPresent(lblDepartment);
        js.executeScript("arguments[0].click();",mapToElement(lblDepartment));
        waitForElementPresent(ddlActing);
        js.executeScript("arguments[0].click();",mapToElement(ddlActing));
        return this;
    }

    public String getTimelineText(){
        waitForElementPresent(tblActing);
        return mapToElement(tblActing).getText();
    }
}
