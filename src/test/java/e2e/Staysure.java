package e2e;

import support.BaseClass;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import support.DataPOJO;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

public class Staysure extends BaseClass {

    private static final String BASE_URL = "https://travelinsurance.staysure.co.uk/quote/policy-details";
    private static final String BASE_URL_15 = "https://quote.staysure.co.uk";

    @DataProvider
    public Object[][] testData() throws FileNotFoundException {
        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/java/fixtures/test-data.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("testData");
        List<DataPOJO> testData = new Gson().fromJson(dataSet, new TypeToken<List<DataPOJO>>() {}.getType());
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }

    @Test (dataProvider = "testData")
    public void quoteJourney(DataPOJO testData) {
        commonPageObjects.navigateTo(BASE_URL);
        commonPageObjects.waitForTripPage();
        commonPageObjects.acceptAllCookies();

        tripPage.selectTypeOfCover(testData.getTypeOfCover());
        tripPage.selectCruiseCover(testData.getCruiseCover());
        tripPage.selectTravellingFrom(testData.getTravellingFrom());
        tripPage.enterTravelDestination(testData.getTravellingTo());
        tripPage.enterDepartureDate(testData.getDepartureDateLead());
        tripPage.enterReturnDate(testData.getReturnDateLead());
        tripPage.selectCoverFor(testData.getCoverFor());
        tripPage.enterTravellerAges(testData.getTravellerAge1());
    }

}