package steps;

import base.BaseUtil;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class Steps extends BaseUtil {
    private WebDriver driver;

    private BaseUtil baseUtil;

    public Steps(BaseUtil util) {
        this.baseUtil = util;
    }

    @Before
    public void setup() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver");
        driver = new ChromeDriver();
    }

    @Given("I am in the login page")
    @Given("I am in the login page of the Para Bank Application")
    public void iAmInTheLoginPageOfTheParaBankApplication() {

        driver.get("http://parabank.parasoft.com/parabank/index.htm");
    }

    @When("I enter valid credentials")
    public void iEnterValidCredentials() {
        driver.findElement(By.name("username")).sendKeys("tautester1");
        driver.findElement(By.name("password")).sendKeys("password1");
        driver.findElement(By.name("username")).submit();
    }

    @Then("I should be taken to the Overview page")
    public void iShouldBeTakenToTheOverviewPage() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rightPanel']/div/div/h1")));

        driver.findElement(By.cssSelector("h1[class='title']"));
        driver.findElement(By.cssSelector("h1[class='title']")).isDisplayed();
        driver.findElement(By.linkText("Log Out")).click();

    }

    @When("I enter valid {string} and Invalid {string} with {string}")
    public void iEnterValidUsernameAndInvalidPasswordWithUserFullName(String username, String password, String userFullName1) {
        baseUtil.userFullName = userFullName1;

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("username")).submit();
    }

    @When("I enter invalid credentials1")
    public void iEnterInValidCredentials(DataTable table) {
        List<String> loginForm = table.asList();

        driver.findElement(By.name("username")).sendKeys(loginForm.get(0));
        driver.findElement(By.name("password")).sendKeys(loginForm.get(1));
        driver.findElement(By.name("username")).submit();
    }

    @Then("I should be taken to the Overview page1")
    public void iShouldBeTakenToTheOverviewPage1() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='rightPanel']/div/div/h1")));

        String actualUserFullName = driver.findElement(By.cssSelector("p[class='smallText']")).getText().toString();
        System.out.println(baseUtil.userFullName.toString());
        assertTrue(actualUserFullName, actualUserFullName.contains(baseUtil.userFullName));

        driver.findElement(By.cssSelector("h1[class='title']"));
        driver.findElement(By.cssSelector("h1[class='title']")).isDisplayed();
        driver.findElement(By.linkText("Log Out")).click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }


}
