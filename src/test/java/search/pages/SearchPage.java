package search.pages;

import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class SearchPage extends BasePage {

    By txtSearch = By.xpath("//input[@id=\"inner_search_v4\"]");
    By btnSearch = By.xpath("//input[@type=\"submit\" and @value=\"Search\"]");

    By sectionResults = By.xpath("//section[@class=\"panel\"]");
    By searchResults = By.cssSelector(".card.v4.tight .details a");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage inputMovieTitle(String title) {
        mapToElement(txtSearch).sendKeys(title);
        return this;
    }

    public SearchPage clickSearchButton() {
        waitForElementPresent(btnSearch);
        mapToElement(btnSearch).click();
        return this;
    }

    public SearchPage waitForSectionResultsSection() {
        waitForElementPresent(searchResults);
        return this;
    }

    public ArrayList<String> getResults() {
        ArrayList<String> results = new ArrayList<>();
        mapToElements(searchResults).forEach((WebElement element) -> {
            results.add(element.getText());
        });
        return results;
    }


}
