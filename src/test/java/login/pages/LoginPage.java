package login.pages;

import base.BasePage;
import base.GlobalVariables;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class LoginPage extends BasePage {

    Logger logger = LogManager.getLogger(LoginPage.class);
    By txtUsername = By.xpath("//input[@id='username']");
    By txtPassword = By.xpath("//input[@id='password']");
    By btnLogin = By.xpath("//input[@id='login_button']");
    By lblUsername = By.xpath("//div[@class=\"content_wrapper flex\"]/h2");
    By errorCard = By.xpath("//div[@class=\"error_status card\"]");
    By errorMessages = By.xpath("//div[@class=\"carton\"]/div/ul/li");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage goToLoginPage(){
        logger.info("Navigating to login page...");
        driver.navigate().to(GlobalVariables.LOGIN_URL);
        return this;
    }

    public LoginPage enterCredentials(String username, String password){
        logger.info("Entering credentials...");
        mapToElement(txtUsername).sendKeys(username);
        mapToElement(txtPassword).sendKeys(password);
        return this;
    }
    public LoginPage clickLoginButton(){
        logger.info("Clicking login button...");
        waitForElementPresent(btnLogin);
        mapToElement(btnLogin).click();
        return this;
    }

    public LoginPage waitsForTitleExists(){
        waitForElementPresent(lblUsername);
        return this;
    }

    public boolean getErrorMessage(){
        waitForElementPresent(errorCard);
        return mapToElement(errorCard).isDisplayed();
    }
    public ArrayList<String> getMoreErrorMessages(){
        ArrayList<String> messages = new ArrayList<>();
        mapToElements(errorMessages).forEach((WebElement element)->{
            messages.add(element.getText());
        });
        return messages;
    }
    public String getLoggedUsername(){
        return mapToElement(lblUsername).getText();
    }

}
