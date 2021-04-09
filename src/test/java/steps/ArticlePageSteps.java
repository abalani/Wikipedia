package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import pages.ArticlePage;

public class ArticlePageSteps {
    WebDriver driver;
    ArticlePage articlePage;

    @Given("^user is on the article page$")
    public void navigateToPage() {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver");
        driver = new ChromeDriver();
        driver.navigate().to("https://en.wikipedia.org/wiki/Metis_(mythology)");
    }

    @Then("user verifies content box headings match page headings")
    public void verifyContentHeadings() {
        articlePage = new ArticlePage(driver);
        articlePage.verifyContentAndPageHeadings();
    }

    @Then("user verifies content headings are functioning links")
    public void verifyHyperlinks() {
        articlePage = new ArticlePage(driver);
        articlePage.verifyContentHyperlinks();
    }

    @Then("user verifies Nike has expected pop-up content")
    public void verifyContent() {
        articlePage = new ArticlePage(driver);
        articlePage.verifyNikeContent();
    }

    @Then("^user clicks (.*) under personified concepts$")
    public void clickButton(String pageLink) {
        articlePage = new ArticlePage(driver);
        articlePage.clickNike(pageLink);
    }

    @Then("user verifies Nike page has a family tree")
    public void verifyFamilyTree() {
        articlePage = new ArticlePage(driver);
        articlePage.verifyNikeFamilyTree();
    }
}
