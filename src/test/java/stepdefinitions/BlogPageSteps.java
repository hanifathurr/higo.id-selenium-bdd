package stepdefinitions;

import helpers.AlertHelper;
import helpers.AssertionHelper;
import helpers.GeneralHelper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import pageobjects.blogpage.BlogPagePO;
import pageobjects.homepage.HomepagePO;
import utilities.config.PropertyFileReader;
import utilities.drivermanager.GetDriverManager;
import utilities.pageobjectmanager.PageObjectManager;

import java.util.List;

public class BlogPageSteps {
    GeneralHelper selenium;
    HomepagePO homePage;
    BlogPagePO blogPage;
    WebDriver driver;
    AssertionHelper hardAssert;
    private static final Logger logger = LogManager.getLogger(AlertHelper.class);

    PageObjectManager pageObjectManager;
    private static final PropertyFileReader config = new PropertyFileReader();

    public BlogPageSteps() {
        this.driver = GetDriverManager.getDriver(config.getBrowser());
        this.pageObjectManager = new PageObjectManager(driver);
        this.homePage = pageObjectManager.getHomepage();
        this.blogPage = pageObjectManager.getBlogPage();
        this.selenium = new GeneralHelper(driver);

    }
    @Given("The user navigates to the Blog page")
    public void theUserNavigatesToTheBlogPage() {
        blogPage.navigateToBlogPage();
    }

    @When("The user opens every article")
    public void theUserOpensEveryArticle() {
        int articleCount = blogPage.getArticleCount();
        for (int index = 1; index <= articleCount; index++) {
            String title = blogPage.getArticleTitleByIndex(index);
            blogPage.openArticleByIndex(index);
            blogPage.verifyArticleTitle(title);
            selenium.backBrowser();
        }
    }

    @Then("The article title same as the page")
    public void theArticleTitleSameAsThePage() {
        logger.info("All articles have been successfully verified.");
    }

    @When("The user enters {string} in the search bar")
    public void theUserEntersInTheSearchBar(String keyword) {
        blogPage.inputSearchBar(keyword);
    }

    @Then("The search results should display articles related to {string}")
    public void theSearchResultsShouldDisplayArticlesRelatedTo(String keyword) {
        blogPage.verifySearchedTitle(keyword);
    }

    @Then("The search results should display a message Artikel yang terkait dengan {string} tidak ditemukan")
    public void theSearchResultsShouldDisplayAMessageArtikelYangTerkaitDenganTidakDitemukan(String keyword) {
        blogPage.verifyInvalidSearch(keyword);
    }

    @Then("The search icon should be disabled")
    public void theSearchIconShouldBeDisabled() {
        blogPage.searchIconIsDisabled();
    }

    @When("The user clicks on the first featured article")
    public void theUserClicksOnTheFirstFeaturedArticle() {
        blogPage.openArticleByIndex(1);
    }

    @Then("The article should have a title, content, and a list of categories\\/tags")
    public void theArticleShouldHaveATitleContentAndAListOfCategoriesTags() {
        blogPage.verifyArticleContent();
    }
}
