package utilities.pageobjectmanager;

import org.openqa.selenium.WebDriver;
import pageobjects.blogpage.BlogPagePO;
import pageobjects.homepage.HomepagePO;

public class PageObjectManager {
    private final WebDriver driver;
    private HomepagePO homepagePO;
    private BlogPagePO blogPagePO;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public HomepagePO getHomepage() {
        if (homepagePO == null) {
            homepagePO = new HomepagePO(driver);
        }
        return homepagePO;
    }
    public BlogPagePO getBlogPage() {
        if (blogPagePO == null) {
            blogPagePO = new BlogPagePO(driver);
        }
        return blogPagePO;
    }
}
