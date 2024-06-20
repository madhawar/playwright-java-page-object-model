package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class CommonPageObjects {
    private final Page page;

    private final Locator acceptAllCookies;
    private final Locator manageCookies;

    private final static String TRIP_PAGE_TITLE = "Staysure™ Travel Insurance – It's Worth Doing Right";

    public CommonPageObjects(Page page) {
        this.page = page;

        this.acceptAllCookies = page.locator("//button[@id='onetrust-accept-btn-handler']");
        this.manageCookies = page.locator("//button[@id='onetrust-pc-btn-handler']");
    }

    public void waitForTripPage() {
        assertThat(page).hasTitle(TRIP_PAGE_TITLE);
    }

    public void acceptAllCookies() {
        acceptAllCookies.click();
    }

    public void manageCookies() {
        manageCookies.click();
    }
}