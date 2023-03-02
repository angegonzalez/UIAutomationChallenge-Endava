package login.stepdefs;

import common.DriverHook;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.cdimascio.dotenv.Dotenv;
import login.pages.LoginPage;
import org.testng.annotations.Listeners;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions extends DriverHook {
    Dotenv dotenv = Dotenv.load();
    LoginPage loginPage = new LoginPage(driver);;
    String username = dotenv.get("USERNAME");
    String validPassword = dotenv.get("VALID_PASSWORD");
    String invalidPassword = dotenv.get("INVALID_PASSWORD");

    @Given("the user wants to login")
    public void userWantsToLogin(){
        loginPage.goToLoginPage();
    }

    @When("the user enters the credentials")
    public void userEntersTheCredentials(){
        loginPage.enterCredentials(username, validPassword).clickLoginButton();
    }

    @When("the user submits invalid credentials")
    public void userEntersInvalidCredentials(){
        loginPage.enterCredentials(username, validPassword).clickLoginButton();
    }

    @Then("the user should be able to login")
    public void userAbleToLogin(){
        loginPage.waitsForTitleExists();
        assertThat(loginPage.getLoggedUsername()).isEqualTo(username);
    }

    @Then("the user should see a red error message")
    public void userSeesRedErrorMessage(){
        assertThat(loginPage.getErrorMessage()).isEqualTo(true);
    }
    @And("the user should see two more error messages")
    public void userSeesTwoMoreErrorMessages(){
        assertThat(loginPage.getMoreErrorMessages().size()).isEqualTo(2);
    }

}
