package pages.one;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import support.Log;

public class TripPage {
    private final Locator typeOfCoverST;
    private final Locator typeOfCoverAMT;
    private final Locator cruiseCoverYes;
    private final Locator cruiseCoverNo;
    private final Locator fromUnitedKingdom;
    private final Locator fromIsleOfMan;
    private final Locator fromGuernsey;
    private final Locator fromJersey;

    public TripPage(Page page) {
        this.typeOfCoverST = page.locator("//span[@id='type-st']");
        this.typeOfCoverAMT = page.locator("//span[@id='type-amt']");
        this.cruiseCoverYes = page.locator("//span[@id='cruise-yes']");
        this.cruiseCoverNo = page.locator("//span[@id='cruise-no']");
        this.fromUnitedKingdom = page.locator("//span[@id='departure-UK1']");
        this.fromIsleOfMan = page.locator("//span[@id='departure-UK3']");
        this.fromGuernsey = page.locator("//span[@id='departure-UK4']");
        this.fromJersey = page.locator("//span[@id='departure-UK5']");
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
    }

    public void selectTravellingFrom(String travellingFrom) {
        switch (travellingFrom) {
            case "UK1" -> fromUnitedKingdom.click();
            case "UK3" -> fromIsleOfMan.click();
            case "UK4" -> fromGuernsey.click();
            case "UK5" -> fromJersey.click();
            default -> Log.error("Unexpected value for parameter: travellingFrom");
        }
    }




}