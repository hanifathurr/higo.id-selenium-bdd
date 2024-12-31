package pageobjects.homepage;

import helpers.AssertionHelper;
import helpers.GeneralHelper;
import helpers.WaitHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.config.PropertyFileReader;

public class HomepagePO {
    AssertionHelper hardAssert;
    WebDriver driver;
    GeneralHelper selenium;
    WaitHelper seleniumWait;
    private static final PropertyFileReader config = new PropertyFileReader();
    // Elements
    @FindBy(css = "a[aria-label='HIGO']")
    WebElement logo;
    @FindBy (xpath = "//div[@class='relative text-white @container/header grid-content']")
    WebElement navigationBar;
    @FindBy (xpath = "//h1[contains(text(),'Solusi Menyeluruh Pemasaran Digital dengan WiFi & ')]")
    WebElement headerLabel;
    @FindBy(xpath = "//div[@class='relative grid w-full grid-rows-t-fill overflow-clip rounded-md bg-primary @2xl/inner:grid-cols-2 @2xl/inner:grid-rows-none @2xl/popup:min-h-screen-3/4']")
    WebElement popUpFrame;

    public HomepagePO(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
        this.selenium = new GeneralHelper(driver);
        this.hardAssert = new AssertionHelper(driver);
        this.seleniumWait = new WaitHelper(driver,5);
    }
    public void verifyHomePageUrl(String homePageUrl) {
        seleniumWait.waitForUrlToContain(homePageUrl);
        hardAssert.assertUrlContains(homePageUrl);
    }
    public void verifyHomePageTitle(String homePageTitle) {
        seleniumWait.waitForTitleToContain(homePageTitle);
        hardAssert.assertPageTitle(homePageTitle);
    }
    public void verifyLogoIsVisible(){
        hardAssert.assertElementDisplayed(logo);
    }
    public void verifyAllMenuDisplayed(String menuText) {
        String menuXpath = "(//span[contains(text(),'" + menuText + "')])[1]";
        WebElement menuElement = driver.findElement(By.xpath(menuXpath));
        hardAssert.assertElementDisplayed(menuElement);
    }
    public void verifyNavigationBarDisplayed(){
        hardAssert.assertElementDisplayed(navigationBar);
    }
    public void verifyHeaderText(String headerText){
        hardAssert.assertElementText(headerLabel,headerText);
    }
    public void clickWebBanner(String webBannerOption){
        String webBannerXpath = "//img[@alt='" + webBannerOption + "']";
        WebElement webBannerElement = driver.findElement(By.xpath(webBannerXpath));
        seleniumWait.waitForElementToBeClickable(webBannerElement);
        selenium.click(webBannerElement);
    }
    public void closePopUpWindow(){
        Actions actions = new Actions(driver);
        actions.moveByOffset(10, 10).click().perform();
    }
    public void clickLinkTextInFooter(String linkText){
        WebElement linkTextElement = driver.findElement(By.linkText(linkText));
        selenium.click(linkTextElement);
    }
    public void clickIconInFooter(String iconName){
        String iconXpath = "//a[@aria-label='" + iconName + "']";
        WebElement iconElement = driver.findElement(By.xpath(iconXpath));
        selenium.click(iconElement);
    }
}
