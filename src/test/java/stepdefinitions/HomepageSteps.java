package stepdefinitions;

import helpers.AssertionHelper;
import helpers.GeneralHelper;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobjects.homepage.HomepagePO;
import utilities.pageobjectmanager.PageObjectManager;
import utilities.config.PropertyFileReader;
import utilities.drivermanager.GetDriverManager;

import java.util.List;

public class HomepageSteps {
    GeneralHelper selenium;
    HomepagePO homePage;
    WebDriver driver;
    AssertionHelper hardAssert;

    PageObjectManager pageObjectManager;
    private static final PropertyFileReader config = new PropertyFileReader();

    public HomepageSteps() {
        this.driver = GetDriverManager.getDriver(config.getBrowser());
        this.pageObjectManager = new PageObjectManager(driver);
        this.homePage = pageObjectManager.getHomepage();
        this.hardAssert = new AssertionHelper(driver);
        this.selenium = new GeneralHelper(driver);
    }

    @Given("The user navigates to the homepage")
    public void userNavigatesToHomepage() {
        homePage.closePopUpWindow();
    }

    @Then("The current URL should be {string}")
    public void theCurrentURLShouldBe(String homePageUrl) {
        homePage.verifyHomePageUrl(homePageUrl);
    }

    @And("The page title should be {string}")
    public void thePageTitleShouldBe(String homePageTitle) {
        homePage.verifyHomePageTitle(homePageTitle);
    }

    @Then("The logo should be visible")
    public void theLogoShouldBeVisible() {
        homePage.verifyLogoIsVisible();
    }

    @Then("The navigation bar should be visible")
    public void theNavigationBarShouldBeVisible() {
        homePage.verifyNavigationBarDisplayed();
    }

    @Then("The main heading should display {string}")
    public void theMainHeadingShouldDisplay(String expectedHeaderText) {
        homePage.verifyHeaderText(expectedHeaderText);
    }

    @When("The user clicks the {string}")
    public void theUserClicksThe(String webBannerOption) {
        homePage.clickWebBanner(webBannerOption);
    }

    @When("The user opens link text {string}")
    public void theUserOpensLinkText(String linkText) {
        homePage.clickLinkTextInFooter(linkText);

    }

    @When("The user opens link text {string} in the new tab")
    public void theUserOpensLinkTextInTheNewTab(String linkText) {
        homePage.clickLinkTextInFooter(linkText);
    }

    @And("It should contain correct menus:")
    public void itShouldContainCorrectMenus(DataTable dataTable) {
        List<String> expectedMenus = dataTable.asList();
        for (String menu : expectedMenus) {
            homePage.verifyAllMenuDisplayed(menu);
        }
    }

    @When("The user clicks icons {string} in the footer")
    public void theUserClicksIconsInTheFooter(String iconName) {
        homePage.clickIconInFooter(iconName);
    }

    @Then("The new tab is opened")
    public void theNewTabIsOpened() {
        selenium.switchToNewTab();
    }
}

