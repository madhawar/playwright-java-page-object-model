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
import java.lang.reflect.Type;
import java.util.List;

public class Staysure extends BaseClass {

    private static final String BASE_URL = "https://travelinsurance.staysure.co.uk/quote/policy-details";
    private static final String BASE_URL_15 = "https://quote.staysure.co.uk";

//    @DataProvider
//    public Object[][] testData() throws FileNotFoundException {
//        JsonElement jsonData = new JsonParser().parse(new FileReader("src/test/java/fixtures/test-data.json"));
//        JsonElement dataSet = jsonData.getAsJsonObject().get("testData");
//        List<DataPOJO> testData = new Gson().fromJson(dataSet, new TypeToken<List<DataPOJO>>() {}.getType());
//        Object[][] returnValue = new Object[testData.size()][1];
//        int index = 0;
//        for (Object[] each : returnValue) {
//            each[0] = testData.get(index++);
//        }
//        return returnValue;
//    }

//    @DataProvider(name = "testData")
//    public Object[][] testData() throws FileNotFoundException {
//        JsonElement jsonData = JsonParser.parseReader(new FileReader("src/test/java/fixtures/test-data.json"));
//        JsonElement dataSet = jsonData.getAsJsonObject().get("testData");
//        Type listType = new TypeToken<List<DataPOJO>>() {}.getType();
//        List<DataPOJO> testData = new Gson().fromJson(dataSet, listType);
//        Object[][] returnValue = new Object[testData.size()][1];
//        int index = 0;
//        for (Object[] each : returnValue) {
//            each[0] = testData.get(index++);
//        }
//        return returnValue;
//    }

    @DataProvider(name = "testData")
    public Object[][] testData() throws FileNotFoundException {
        JsonElement jsonData = JsonParser.parseReader(new FileReader("src/test/java/fixtures/test-data.json"));
        JsonElement dataSet = jsonData.getAsJsonObject().get("testData");
        Type listType = new TypeToken<List<DataPOJO>>() {}.getType();
        List<DataPOJO> testData = new Gson().fromJson(dataSet, listType);
        Object[][] returnValue = new Object[testData.size()][1];
        int index = 0;
        for (Object[] each : returnValue) {
            each[0] = testData.get(index++);
        }
        return returnValue;
    }

    public void travellerAges(List<String> ages) {
        int travellers = 0;
        for (String age : ages) {
            travellers++;
            tripPage.enterTravellerAges(page, age, travellers);
        }
    }

    @Test (dataProvider = "testData")
    public void quoteJourney(DataPOJO testData) {
        commonPageObjects.navigateTo(BASE_URL);
        commonPageObjects.acceptAllCookies();

        tripPage.verifyUserInTripPage(page);
        tripPage.selectTypeOfCover(testData.getTypeOfCover());
        tripPage.selectCruiseCover(testData.getCruiseCover());
        tripPage.selectTravellingFrom(testData.getTravellingFrom());
        tripPage.enterTravelDestination(testData.getTravellingTo());
        tripPage.enterDepartureDate(testData.getDepartureDateLead());
        tripPage.enterReturnDate(testData.getReturnDateLead());
        tripPage.selectCoverFor(testData.getCoverFor());

//        int travellers = 0;
//        for (String age : testData.getTravellers().getAge()) {
//            travellers++;
//            tripPage.enterTravellerAges(page, age, travellers);
//        }

        travellerAges(testData.getTravellers().getAge());

        tripPage.enterLeadTravellerDetails(testData.getOrganiser().getTitle(), testData.getOrganiser().getFirstName(),
                testData.getOrganiser().getLastName(), testData.getOrganiser().getEmailAddress(),
                testData.getOrganiser().getTelephoneNumber(), testData.getOrganiser().getPostCode());
        tripPage.clickContinueButton();

        travellersPage.verifyUserInTravellerPage(page);
    }

}