package movie.pages;

import actor.pages.ActorPage;
import base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Random;

public class MoviePage extends BasePage {

    By spanGenres = By.xpath("//span[@class=\"genres\"]");
    By topBilledCast = By.xpath("//ol[@class=\"people scroller\"]/li[@class=\"card\"]");
    By titleMovie = By.cssSelector(".title>h2>a");
    public MoviePage(WebDriver driver) {
        super(driver);
    }

    public String getGenres(){
        waitForElementPresent(spanGenres);
        return mapToElement(spanGenres).getText();
    }

    public String getMovieName(){
        waitForElementPresent(titleMovie);
        return mapToElement(titleMovie).getText();
    }
    public MoviePage goToMoviePage(String url){
        driver.navigate().to(url);
        return this;
    }

    public ActorPage selectActorBilledCast(){
        waitForElementPresent(topBilledCast);
        List<WebElement> actorsBilledCast = mapToElements(topBilledCast);
        Random random =  new Random();
        int selectedIndex = random.ints(0, actorsBilledCast.size()).findFirst().getAsInt();
        actorsBilledCast.get(selectedIndex).click();
        return new ActorPage(driver);
    }

}
