import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"/login/stepdefs", "/common", "/search/stepdefs", "/top_rated/stepdefs", "movie/stepdefs"},
        plugin = {"pretty", "json:target/cucumber/cucumber.json"},
        tags = "@Login"
)
public class Runner extends AbstractTestNGCucumberTests {

}
