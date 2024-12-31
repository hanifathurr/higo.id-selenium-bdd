package pageobjects.blogpage;

import helpers.AssertionHelper;
import helpers.GeneralHelper;
import helpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.config.PropertyFileReader;

import java.util.List;

public class BlogPagePO {
    AssertionHelper hardAssert;
    WebDriver driver;
    GeneralHelper selenium;
    WaitHelper seleniumWait;
    private static final PropertyFileReader config = new PropertyFileReader();
    // Elements
    @FindBy(id = "(//span[contains(text(),'Blog')])[1]")
    WebElement blogMenu;
    @FindBy(xpath = "//h1")
    WebElement openedArticleTitle;
    @FindBy(xpath = ("//input[@placeholder='Cari Artikel']"))
    WebElement inputSearchBar;
    @FindBy(xpath = "//button[@aria-label='Cari Artikel']")
    WebElement searchIcon;
    @FindBy(xpath = "//div[@class='grid grid-flow-row content-start gap-2']")
    WebElement searchedArticleTitle;
    @FindBy(xpath = "//div[@class='@2xl/section:place-self-center']")
    WebElement invalidSearchLabel;
    @FindBy(xpath = "//div[@class='grid gap-4']")
    WebElement articleBodyContent;
    @FindBy(xpath = "//section[@class='grid grid-flow-row content-start gap-4 rounded-lg bg-blue-50 p-3 @3xl/page:p-6']")
    WebElement articleTag;

    public BlogPagePO(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.selenium = new GeneralHelper(driver);
        this.hardAssert = new AssertionHelper(driver);
        this.seleniumWait = new WaitHelper(driver,5);
    }
    public void navigateToBlogPage(){
        selenium.navigateToUrl(config.getProperty("blogPageUrl"));
    }
    public int getArticleCount() {
        List<WebElement> articleTitle = driver.findElements(By.xpath("//div[@class='grid grid-flow-row content-start gap-2'] //h6"));
        return articleTitle.size();
    }
    public String getArticleTitleByIndex(int index) {
        String articleTitleXpath = "(//div[@class='grid grid-flow-row content-start gap-2'])[" + index + "] //h6";
        WebElement articleTitleElement = seleniumWait.waitForVisibilityOfElement(By.xpath(articleTitleXpath));
        return articleTitleElement.getText();
    }
    public void openArticleByIndex(int index) {
        String articleTitleXpath = "(//div[@class='grid grid-flow-row content-start gap-2'])[" + index + "] //h6";
        WebElement articleTitleElement = seleniumWait.waitForVisibilityOfElement(By.xpath(articleTitleXpath));
        selenium.click(articleTitleElement);
    }
    public void verifyArticleTitle(String expectedTitle) {
        hardAssert.assertElementContainsText(openedArticleTitle,expectedTitle);
    }
    public void inputSearchBar(String keyword){
        selenium.fillText(inputSearchBar,keyword);
        selenium.click(searchIcon);
    }
    public void verifySearchedTitle(String expectedKeyword){
        hardAssert.assertSearchResults(expectedKeyword,searchedArticleTitle);
    }
    public void verifyInvalidSearch(String keyword){
        hardAssert.assertElementDisplayed(invalidSearchLabel);
        hardAssert.assertElementContainsText(invalidSearchLabel,keyword);
    }
    public void searchIconIsDisabled(){
        hardAssert.assertElementDisabled(searchIcon);
    }
    public void verifyArticleContent(){
        hardAssert.assertElementDisplayed(openedArticleTitle);
        hardAssert.assertElementDisplayed(articleBodyContent);
        hardAssert.assertElementDisplayed(articleTag);
    }
}
