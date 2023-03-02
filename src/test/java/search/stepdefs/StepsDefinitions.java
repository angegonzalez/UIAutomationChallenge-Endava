package search.stepdefs;

import base.DriverFactory;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import search.pages.SearchPage;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;


public class StepsDefinitions {

    SearchPage searchPage = new SearchPage(DriverFactory.getDriver());
    String title;


    @Given("the user wants to search for a movie")
    public void userWantsSearchForAMovie(){
    }

    @When("the user enters the title {string}")
    public void userEntersTitle(String title){
        this.title = title;
        searchPage.inputMovieTitle(this.title).clickSearchButton();
    }

    @Then("the user should see the movie as the first result")
    public void userSeesTheMovieAsFirstResult(){
        ArrayList<String> results = searchPage.waitForSectionResultsSection().getResults();
        assertThat(results.get(0)).isEqualTo(this.title);
    }
}
