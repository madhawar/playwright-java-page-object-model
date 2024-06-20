package pages.one;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import support.Log;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TravellersPage {
    protected LocalDateTime currentDateAndTime = LocalDateTime.now();
    protected DateTimeFormatter travelDates = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    private final static String XPATH_TRAVELLER_AGE_START = "//input[@id='traveler_age_";
    private final static String XPATH_TRAVELLER_AGE_END = "']";

    private final Locator btnContinue;

    public TravellersPage(Page page) {
        this.btnContinue = page.locator("//input[@id='btnSubmit']");
    }

    public void verifyUserInTravellerPage(Page page) {
        assertThat(page).hasURL(Pattern.compile(".*/quote/personal-details"));
    }


}