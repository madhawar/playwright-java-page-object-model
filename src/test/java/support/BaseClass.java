package support;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.CommonPageObjects;
import pages.one.TripPage;



public class BaseClass {
    private static final String BASE_URL = "https://travelinsurance.staysure.co.uk/quote/policy-details";

    private Browser browser;

    protected CommonPageObjects commonPageObjects;
    protected TripPage tripPage;

    @BeforeMethod
    public void setUp(){
        browser = Playwright
                .create()
                .chromium()
                .launch(new BrowserType.LaunchOptions().setHeadless(false));

        Page page = browser.newPage();
        page.setViewportSize( 1920, 1080);
        page.navigate(BASE_URL);

        tripPage = new TripPage(page);
        commonPageObjects = new CommonPageObjects(page);
    }

    @AfterMethod
    public void tearDown(){
        browser.close();
    }


}