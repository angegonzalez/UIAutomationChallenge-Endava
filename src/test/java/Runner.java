import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = {"/login/stepdefs", "/common", "/search/stepdefs", "/top_rated/stepdefs", "movie/stepdefs"},
        plugin = {"pretty", "json:target/cucumber/cucumber.json"},
        tags = ""
)
public class Runner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
