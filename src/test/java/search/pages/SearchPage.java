package search.pages;

import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class SearchPage extends BasePage {

    private final Logger logger = LogManager.getLogger(SearchPage.class);
    private final By txtSearch = By.xpath("//input[@id=\"inner_search_v4\"]");
    private final By btnSearch = By.xpath("//input[@type=\"submit\" and @value=\"Search\"]");
    private final By searchResults = By.cssSelector(".card.v4.tight .details a");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage inputMovieTitle(String title) {
        logger.info("Entering movie title...");
        mapToElement(txtSearch).sendKeys(title);
        return this;
    }

    public SearchPage clickSearchButton() {
        logger.info("Clicking search button...");
        waitForElementPresent(btnSearch);
        mapToElement(btnSearch).click();
        return this;
    }

    public SearchPage waitForSectionResultsSection() {
        waitForElementPresent(searchResults);
        return this;
    }

    public ArrayList<String> getResults() {
        logger.info("Retrieving search results...");
        ArrayList<String> results = new ArrayList<>();
        mapToElements(searchResults).forEach((WebElement element) -> {
            results.add(element.getText());
        });
        return results;
    }


}
