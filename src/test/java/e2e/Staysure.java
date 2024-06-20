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
        commonPageObjects.waitForTripPage();
        commonPageObjects.acceptAllCookies();

        tripPage.selectTypeOfCover(testData.getTypeOfCover());
        tripPage.selectCruiseCover(testData.getCruiseCover());
        tripPage.selectTravellingFrom(testData.getTravellingFrom());
    }

}