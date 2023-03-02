package movie.stepdefs;

import actor.pages.ActorPage;
import base.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import movie.pages.MoviePage;

import static org.assertj.core.api.Assertions.assertThat;

public class StepDefinitions {

    private final MoviePage moviePage = new MoviePage(DriverFactory.getDriver());
    private ActorPage actorPage;

    String movieName;

    @Given("the user selects a {string} with actors")
    public void userSelectsMovie(String url){
        moviePage.goToMoviePage(url);
        movieName = moviePage.getMovieName();
    }

    @And("the user selects an actor from the top billed cast")
    public void userSelectsActorFromTopBilledCast(){
        actorPage = moviePage.selectActorBilledCast();
    }
    @When("the user checks the actor's acting timeline")
    public void userChecksActorTimeline(){
        actorPage.filterActingDepartment();
    }
    @Then("the title of the movie should be in the timeline")
    public void titleOfMovieInTimeline(){
        String timelineText = actorPage.getTimelineText();
        assertThat(timelineText).contains(movieName);
    }
}
