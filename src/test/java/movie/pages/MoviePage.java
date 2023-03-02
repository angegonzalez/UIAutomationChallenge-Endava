package movie.pages;

import actor.pages.ActorPage;
import base.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class MoviePage extends BasePage {

    Logger logger = LogManager.getLogger(MoviePage.class);
    By spanGenres = By.xpath("//span[@class=\"genres\"]");
    By topBilledCast = By.xpath("//ol[@class=\"people scroller\"]/li[@class=\"card\"]");
    By titleMovie = By.cssSelector(".title>h2>a");
    public MoviePage(WebDriver driver) {
        super(driver);
    }

    public String getGenres(){
        logger.info("Retrieving genres info...");
        waitForElementPresent(spanGenres);
        return mapToElement(spanGenres).getText();
    }

    public String getMovieName(){
        logger.info("Retrieving movie name info...");
        waitForElementPresent(titleMovie);
        return mapToElement(titleMovie).getText();
    }
    public MoviePage goToMoviePage(String url){
        logger.info("Navigating to movie page...");
        driver.navigate().to(url);
        return this;
    }

    public ActorPage selectActorBilledCast(){
        logger.info("Selecting actor from the top billed cast...");
        waitForElementPresent(topBilledCast);
        List<WebElement> actorsBilledCast = mapToElements(topBilledCast);
        Random random =  new Random();
        int selectedIndex = random.ints(0, actorsBilledCast.size()).findFirst().getAsInt();
        actorsBilledCast.get(selectedIndex).click();
        return new ActorPage(driver);
    }

}
