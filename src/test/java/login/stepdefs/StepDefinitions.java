package login.stepdefs;

import base.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.cdimascio.dotenv.Dotenv;
import login.pages.LoginPage;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {
    private final Dotenv dotenv = Dotenv.load();
    private final LoginPage loginPage = new LoginPage(DriverFactory.getDriver());;
    private final String username = dotenv.get("USERNAME");
    private final String validPassword = dotenv.get("VALID_PASSWORD");
    private final String invalidPassword = dotenv.get("INVALID_PASSWORD");

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
