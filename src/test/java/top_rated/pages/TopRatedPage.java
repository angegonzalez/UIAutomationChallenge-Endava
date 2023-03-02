package top_rated.pages;

import base.BasePage;
import base.GlobalVariables;
import movie.pages.MoviePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class TopRatedPage extends BasePage {
    Logger logger = LogManager.getLogger(TopRatedPage.class);
    By divFilters = By.xpath("//h2[text()=\"Filters\"]/parent::div");
    By anchorAction;
    By btnSearch = By.xpath("//a[contains(text(),\"Search\")]");
    By lblGenres = By.xpath("//h3[contains(text(),\"Genres\")]");
    By ddlSortBy = By.xpath("//h3[contains(text(), \"Sort Results By\")]/following-sibling::span");
    By lblAscending = By.xpath("//li[contains(text(), \"Release Date Ascending\")]");
    By lblMoviesDates = By.xpath("//div[@class=\"card style_1\"]/div[@class=\"content\"]/p");
    By htmlTag = By.tagName("html");

    By imgsMoviesFiltered = By.xpath("//div[@class=\"card style_1\"]//a[@class=\"image\"]");

    public TopRatedPage(WebDriver driver) {
        super(driver);
    }

    public TopRatedPage goToTopRatedPage() {
        logger.info("Navigating to the top-rated movies page...");
        driver.navigate().to(GlobalVariables.TOP_RATED_URL);
        return this;
    }

    public TopRatedPage sortByDateAscending() {
        logger.info("Selecting sorting filter...");
        waitForElementPresent(ddlSortBy);
        mapToElement(ddlSortBy).click();
        js.executeScript("arguments[0].click();", mapToElement(lblAscending));
        js.executeScript("arguments[0].click();", mapToElements(btnSearch).get(0));
        return this;
    }

    public ArrayList<LocalDate> getMoviesTitlesOrderedAscending(int number) {
        logger.info("Retrieving movies release date data...");
        ArrayList<LocalDate> moviesDates = new ArrayList<>();
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern("MMM dd, yyyy").
                toFormatter(Locale.ENGLISH);
        waitForElementPresent(lblMoviesDates);

        List<WebElement> moviesDatesList = mapToElements(lblMoviesDates);

        for (int i = 0; i < number; i++) {
            String stringDate = moviesDatesList.get(i).getText();
            LocalDate date = LocalDate.parse(stringDate, formatter);
            moviesDates.add(date);
        }

        return moviesDates;
    }

    public TopRatedPage openFiltersMenu() {
        logger.info("Opening filters menu...");
        waitForElementPresent(divFilters);
        mapToElement(divFilters).click();
        return this;
    }

    public TopRatedPage selectGenre(String genre) {
        logger.info("Selecting genre "+genre);
        waitForElementPresent(lblGenres);
        anchorAction = By.xpath(String.format("//a[contains(text(),\"%s\")]/..", genre));
        waitForElementPresent(anchorAction);
        js.executeScript("arguments[0].click();", mapToElement(anchorAction));
        return this;
    }

    public TopRatedPage clickSearchButton() {
        logger.info("Clicking search button...");
        waitForElementPresent(btnSearch);
        js.executeScript("arguments[0].click();", mapToElements(btnSearch).get(0));
        return this;
    }

    public TopRatedPage waitForPageLoads() {
        wait.until(ExpectedConditions.not(
                ExpectedConditions.attributeContains(htmlTag, "class", "nprogress-busy")));
        return this;
    }

    public MoviePage selectAnyMovie() {
        logger.info("Selecting any movie...");
        waitForElementPresent(imgsMoviesFiltered);
        List<WebElement> moviesFiltered = mapToElements(imgsMoviesFiltered);
        Random random = new Random();
        int selectedIndex = random.ints(0, moviesFiltered.size()).findFirst().getAsInt();
        moviesFiltered.get(selectedIndex).click();
        return new MoviePage(driver);
    }
}
