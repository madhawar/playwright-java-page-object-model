package pages.one;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import support.Log;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class TripPage {
    protected LocalDateTime currentDateAndTime = LocalDateTime.now();
    protected DateTimeFormatter travelDates = DateTimeFormatter.ofPattern("dd/MM/uuuu");

    private final static String XPATH_TRAVELLER_AGE_START = "//input[@id='traveler_age_";
    private final static String XPATH_TRAVELLER_AGE_END = "']";

    private final Locator typeOfCoverST;
    private final Locator typeOfCoverAMT;
    private final Locator cruiseCoverYes;
    private final Locator cruiseCoverNo;
    private final Locator fromUnitedKingdom;
    private final Locator fromIsleOfMan;
    private final Locator fromGuernsey;
    private final Locator fromJersey;
    private final Locator travelDestination;
    private final Locator travelDestinationList;
    private final Locator dateDeparture;
    private final Locator dateReturn;
    private final Locator coverForIndividual;
    private final Locator coverForCouple;
    private final Locator coverForFamily;
    private final Locator coverForFamilyParentOne;
    private final Locator coverForFamilyParentTwo;
    private final Locator coverForOther;

    private final Locator addOrganiserTitle;
    private final Locator addOrganiserFirstName;
    private final Locator addOrganiserLastname;
    private final Locator addOrganiserEmail;
    private final Locator addOrganiserTelephone;
    private final Locator addOrganiserPostcode;

    private final Locator btnContinue;

    public TripPage(Page page) {
        this.typeOfCoverST = page.locator("//span[@id='type-st']");
        this.typeOfCoverAMT = page.locator("//span[@id='type-amt']");
        this.cruiseCoverYes = page.locator("//span[@id='cruise-yes']");
        this.cruiseCoverNo = page.locator("//span[@id='cruise-no']");
        this.fromUnitedKingdom = page.locator("//span[@id='departure-UK1']");
        this.fromIsleOfMan = page.locator("//span[@id='departure-UK3']");
        this.fromGuernsey = page.locator("//span[@id='departure-UK4']");
        this.fromJersey = page.locator("//span[@id='departure-UK5']");
        this.travelDestination = page.locator("//input[@id='countrySearchInput']");
        this.travelDestinationList = page.locator("//div[@id='countrySearchResult']");
        this.dateDeparture = page.locator("//input[@id='datepicker-departure-text']");
        this.dateReturn = page.locator("//input[@id='datepicker-return-text']");

        this.coverForIndividual = page.locator("//span[@id='cover-individual-btn']");
        this.coverForCouple = page.locator("//span[@id='cover-couple-btn']");
        this.coverForFamily = page.locator("//span[@id='cover-family-btn']");
        this.coverForFamilyParentOne = page.locator("//input[@id='fld-singleParent-yes']");
        this.coverForFamilyParentTwo = page.locator("//input[@id='fld-singleParent-no']");
        this.coverForOther = page.locator("//span[@id='cover-other-btn']");

        this.addOrganiserTitle = page.locator("//select[@id='organiserTitle']");
        this.addOrganiserFirstName = page.locator("//input[@id='firstname']");
        this.addOrganiserLastname = page.locator("//input[@id='lastname']");
        this.addOrganiserEmail = page.locator("//input[@id='email']");
        this.addOrganiserTelephone = page.locator("//input[@id='dayTimeTelephone']");
        this.addOrganiserPostcode = page.locator("//input[@id='postcode']");

        this.btnContinue = page.locator("//input[@id='btnSubmit']");
    }

    public void verifyUserInTripPage(Page page) {
        assertThat(page).hasURL(Pattern.compile(".*/quote/policy-details"));
    }

    public void selectTypeOfCover(String tripCover) {
        if (tripCover.equals("ST")) {
            typeOfCoverST.click();
        }
        else if (tripCover.equals("AMT")) {
            typeOfCoverAMT.click();
        }
        else {
            Log.error("Unexpected value for parameter: tripCover");
        }
        Log.info("[TRIP] Selected Type of Cover: " + tripCover);
    }

    public void selectCruiseCover(String cruiseCover) {
        if (cruiseCover.equals("YES")) {
            cruiseCoverYes.click();
        }
        else if (cruiseCover.equals("NO")) {
            cruiseCoverNo.click();
        }
        else {
            Log.error("Unexpected value for parameter: cruiseCover");
        }
        Log.info("[TRIP] Selected Cruise Cover: " + cruiseCover);
    }

    public void selectTravellingFrom(String travellingFrom) {
        switch (travellingFrom) {
            case "UK1" -> fromUnitedKingdom.click();
            case "UK3" -> fromIsleOfMan.click();
            case "UK4" -> fromGuernsey.click();
            case "UK5" -> fromJersey.click();
            default -> Log.error("Unexpected value for parameter: travellingFrom");
        }
        Log.info("[TRIP] Selected Travel Start Location: " + travellingFrom);
    }

    public void enterTravelDestination(String travellingTo) {
        travelDestination.fill(travellingTo);
        travelDestination.press("ArrowRight");
        travelDestination.press("Enter");
        Log.info("[TRIP] Selected Travel Destination: " + travellingTo);
    }

    public void enterDepartureDate(int departureLead) {
        String departureDate = currentDateAndTime.plusDays(departureLead).format(travelDates);
        dateDeparture.fill(departureDate);
        dateDeparture.press("Tab");
        Log.info("[TRIP] Selected Departure Date: " + departureDate);
    }

    public void enterReturnDate(int returnLead) {
        String returnDate = currentDateAndTime.plusDays(returnLead).format(travelDates);
        dateReturn.fill(returnDate);
        Log.info("[TRIP] Selected Return Date: " + returnDate);
    }

    public void selectCoverFor(String coverFor) {
        switch (coverFor) {
            case "INDIVIDUAL" -> coverForIndividual.click();
            case "COUPLE" -> coverForCouple.click();
            case "FAMILY" -> {
                coverForFamily.click();
                coverForFamilyParentTwo.click();
            }
            case "SINGLE_PARENT_FAMILY" -> {
                coverForFamily.click();
                coverForFamilyParentOne.click();
            }
            case "OTHER" -> coverForOther.click();
            default -> Log.error("Unexpected value for parameter: coverFor");
        }
        Log.info("[TRIP] Selected Party Type: " + coverFor);
    }

    public void enterTravellerAges(Page page, String travellerAge, int travellers) {
        String travellerCount = String.valueOf(travellers);

        Locator travellerAgeInput = page.locator(XPATH_TRAVELLER_AGE_START + travellerCount + XPATH_TRAVELLER_AGE_END);
        travellerAgeInput.fill(travellerAge);

        Log.info("[TRIP] Entered Age for Traveller " + travellerCount + " as: " + travellerAge);
    }

    public void enterLeadTravellerDetails(String organiserTitle, String organiserFirstName,
                                          String organiserLastName, String organiserEmail, String organiserTelephone,
                                          String organiserPostcode) {
        Instant now = Instant.now();
        long unixTimestamp = now.getEpochSecond();
        String modifiedEmail = "madhawa+" + unixTimestamp + "@intervest.lk";

        addOrganiserTitle.selectOption(organiserTitle);
        addOrganiserFirstName.fill(organiserFirstName);
        addOrganiserLastname.fill(organiserLastName);
        addOrganiserEmail.fill(modifiedEmail);
        addOrganiserTelephone.fill(organiserTelephone);
        addOrganiserPostcode.fill(organiserPostcode);

        Log.info("[TRIP] Entered Organiser Details");
    }

    public void clickContinueButton() {
        btnContinue.click();
        Log.info("[TRIP] Clicked Continue Button");
    }

}