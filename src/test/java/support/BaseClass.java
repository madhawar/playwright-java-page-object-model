package support;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CommonPageObjects;
import pages.one.TravellersPage;
import pages.one.TripPage;

public class BaseClass {
    private static final String BASE_URL = "https://travelinsurance.staysure.co.uk/quote/policy-details";
    private static final String BASE_URL_15 = "https://quote.staysure.co.uk";

    private Browser browser;

    protected Page page;
    protected CommonPageObjects commonPageObjects;
    protected TripPage tripPage;
    protected TravellersPage travellersPage;

    @BeforeMethod
    public void setUp(){
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
        page.setViewportSize( 1920, 1080);

        commonPageObjects = new CommonPageObjects(page);
        tripPage = new TripPage(page);
        travellersPage = new TravellersPage(page);
    }

    @AfterMethod
    public void tearDown(){
        browser.close();
    }

}