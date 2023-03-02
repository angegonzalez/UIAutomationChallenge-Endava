package top_rated.stepdefs;

import base.DriverFactory;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import movie.pages.MoviePage;
import top_rated.pages.TopRatedPage;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

public class StepsDefinitions{

    private final TopRatedPage topRatedPage = new TopRatedPage(DriverFactory.getDriver());
    private MoviePage moviePage;
    private String filter;

    @Given("the user wants to see the top-rated movies")
    public void userWantsToSeeTopRatedMovies() {
        topRatedPage.goToTopRatedPage();
    }

    @Given("the user wants to sort top-rated movies by their date")
    public void userWantsToSortTopRatedMovies() {
        topRatedPage.goToTopRatedPage();
    }

    @And("the user wants to filter for {string} movies")
    public void userFilterMovies(String filter) {
        this.filter = filter.substring(0, 1).toUpperCase() + filter.substring(1);
    }

    @When("the user applies the \"action\" filter")
    public void userAppliesFilter() {
        topRatedPage.openFiltersMenu().selectGenre(this.filter).clickSearchButton().waitForPageLoads();
    }

    @When("the user sorts by date on ascending order")
    public void userSortsByDateAscending() {
        topRatedPage.sortByDateAscending().waitForPageLoads();
    }

    @And("the user selects any movie")
    public void userSelectsAnyMovie() {
        moviePage = topRatedPage.selectAnyMovie();
    }

    @Then("the user should see the genre of the movie includes \"action\"")
    public void userSeesGenreOfTheMovie() {
        String movieGenres = moviePage.getGenres();
        assertThat(movieGenres).contains(filter);
    }

    @Then("the user should see the dates of the first {int} movies in ascending order")
    public void userSeeDatesOfMoviesInAscendingOrder(int number) {
        ArrayList<LocalDate> movieDates = topRatedPage.getMoviesTitlesOrderedAscending(number);
        assertThat(movieDates).isSorted();
    }


}
