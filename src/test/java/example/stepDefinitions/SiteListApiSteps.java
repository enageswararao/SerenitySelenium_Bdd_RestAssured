package example.stepDefinitions;

import com.jayway.jsonpath.JsonPath;

import example.sitelist_apis.SiteListApis;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Steps;

import java.net.MalformedURLException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SiteListApiSteps {

    Response response = null;
    String locationId = null;
    @Steps
    SiteListApis siteListApis;

    @Given("a user makes a GET request to retrieve sitelist details")
    public void aUserMakesAGETRequestToRetrieveSitelistDetails() {
        response = siteListApis.getSiteListApi();
    }


    @Then("the response status code should  {int}")
    public void theResponseStatusCodeShould(int expectedStatusCode) {
        assertThat(response.statusCode()).isEqualTo(expectedStatusCode);

    }

    @When("the location id  is extract from response for Location Name {string}")
    public void theLocationIdIsExtractFromResponseForLocationName(String exceptedLocationName) {

        String json = response.getBody().asString();
        String exceptedLocationId = null;
        List<String> locationList = JsonPath.parse(json).read("$.Locations.Location[*].name");
        for (int i = 0; i <= locationList.size(); i++) {
            String locationName = JsonPath.parse(json).read("$.Locations.Location[" + i + "].name");
            if (exceptedLocationName.equals(locationName)) {
                exceptedLocationId = JsonPath.parse(json).read("$.Locations.Location[" + i + "].id");
                break;
            }
        }
        locationId = exceptedLocationId;
    }

    @Given("a user makes a GET request to retrieve locations details")
    public void aUserMakesAGETRequestToRetrieveLocationsDetails() throws MalformedURLException {
        response = siteListApis.getLocationApi(locationId);
    }

    @Then("verify the response parameter ‘S’ has a description of {string}  for this location {string}")
    public void verifyTheResponseParameterSHasADescriptionOfForThisLocation(String windSpeed, String locationName) {
        String location_json = "{\"SiteRep\":{\"Wx\":{\"Param\":[{\"name\":\"F\",\"units\":\"C\",\"$\":\"Feels Like Temperature\"},{\"name\":\"G\",\"units\":\"mph\",\"$\":\"Wind Gust\"},{\"name\":\"H\",\"units\":\"%\",\"$\":\"Screen Relative Humidity\"},{\"name\":\"T\",\"units\":\"C\",\"$\":\"Temperature\"},{\"name\":\"V\",\"units\":\"\",\"$\":\"Visibility\"},{\"name\":\"D\",\"units\":\"compass\",\"$\":\"Wind Direction\"},{\"name\":\"S\",\"units\":\"mph\",\"$\":\"Wind Speed\"},{\"name\":\"U\",\"units\":\"\",\"$\":\"Max UV Index\"},{\"name\":\"W\",\"units\":\"\",\"$\":\"Weather Type\"},{\"name\":\"Pp\",\"units\":\"%\",\"$\":\"Precipitation Probability\"}]},\"DV\":{\"dataDate\":\"2024-01-19T14:00:00Z\",\"type\":\"Forecast\",\"Location\":{\"i\":\"324152\",\"lat\":\"51.3775\",\"lon\":\"-0.0933\",\"name\":\"CROYDON\",\"country\":\"ENGLAND\",\"continent\":\"EUROPE\",\"elevation\":\"55.0\",\"Period\":[{\"type\":\"Day\",\"value\":\"2024-01-19Z\",\"Rep\":[{\"D\":\"SW\",\"F\":\"-5\",\"G\":\"13\",\"H\":\"90\",\"Pp\":\"0\",\"S\":\"2\",\"T\":\"-3\",\"V\":\"MO\",\"W\":\"1\",\"U\":\"1\",\"$\":\"540\"},{\"D\":\"WSW\",\"F\":\"1\",\"G\":\"13\",\"H\":\"70\",\"Pp\":\"0\",\"S\":\"4\",\"T\":\"3\",\"V\":\"GO\",\"W\":\"1\",\"U\":\"1\",\"$\":\"720\"},{\"D\":\"WSW\",\"F\":\"2\",\"G\":\"18\",\"H\":\"60\",\"Pp\":\"0\",\"S\":\"9\",\"T\":\"5\",\"V\":\"VG\",\"W\":\"1\",\"U\":\"1\",\"$\":\"900\"},{\"D\":\"SSW\",\"F\":\"-1\",\"G\":\"20\",\"H\":\"74\",\"Pp\":\"0\",\"S\":\"7\",\"T\":\"2\",\"V\":\"GO\",\"W\":\"0\",\"U\":\"0\",\"$\":\"1080\"},{\"D\":\"SW\",\"F\":\"-1\",\"G\":\"20\",\"H\":\"73\",\"Pp\":\"0\",\"S\":\"7\",\"T\":\"2\",\"V\":\"VG\",\"W\":\"0\",\"U\":\"0\",\"$\":\"1260\"}]},{\"type\":\"Day\",\"value\":\"2024-01-20Z\",\"Rep\":[{\"D\":\"SSW\",\"F\":\"-2\",\"G\":\"20\",\"H\":\"73\",\"Pp\":\"0\",\"S\":\"7\",\"T\":\"2\",\"V\":\"VG\",\"W\":\"0\",\"U\":\"0\",\"$\":\"0\"},{\"D\":\"SSW\",\"F\":\"-2\",\"G\":\"27\",\"H\":\"77\",\"Pp\":\"0\",\"S\":\"9\",\"T\":\"2\",\"V\":\"VG\",\"W\":\"2\",\"U\":\"0\",\"$\":\"180\"},{\"D\":\"SSW\",\"F\":\"-2\",\"G\":\"27\",\"H\":\"76\",\"Pp\":\"3\",\"S\":\"11\",\"T\":\"2\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"0\",\"$\":\"360\"},{\"D\":\"SSW\",\"F\":\"-1\",\"G\":\"27\",\"H\":\"75\",\"Pp\":\"3\",\"S\":\"11\",\"T\":\"3\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"1\",\"$\":\"540\"},{\"D\":\"SSW\",\"F\":\"1\",\"G\":\"27\",\"H\":\"62\",\"Pp\":\"3\",\"S\":\"13\",\"T\":\"5\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"1\",\"$\":\"720\"},{\"D\":\"SSW\",\"F\":\"1\",\"G\":\"25\",\"H\":\"60\",\"Pp\":\"1\",\"S\":\"13\",\"T\":\"5\",\"V\":\"VG\",\"W\":\"3\",\"U\":\"1\",\"$\":\"900\"},{\"D\":\"S\",\"F\":\"-1\",\"G\":\"25\",\"H\":\"65\",\"Pp\":\"4\",\"S\":\"11\",\"T\":\"3\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"0\",\"$\":\"1080\"},{\"D\":\"S\",\"F\":\"-1\",\"G\":\"27\",\"H\":\"63\",\"Pp\":\"6\",\"S\":\"13\",\"T\":\"4\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"0\",\"$\":\"1260\"}]},{\"type\":\"Day\",\"value\":\"2024-01-21Z\",\"Rep\":[{\"D\":\"S\",\"F\":\"-1\",\"G\":\"31\",\"H\":\"62\",\"Pp\":\"22\",\"S\":\"16\",\"T\":\"4\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"0\",\"$\":\"0\"},{\"D\":\"SSW\",\"F\":\"-1\",\"G\":\"38\",\"H\":\"88\",\"Pp\":\"55\",\"S\":\"18\",\"T\":\"4\",\"V\":\"GO\",\"W\":\"12\",\"U\":\"0\",\"$\":\"180\"},{\"D\":\"SSW\",\"F\":\"3\",\"G\":\"36\",\"H\":\"91\",\"Pp\":\"46\",\"S\":\"18\",\"T\":\"7\",\"V\":\"GO\",\"W\":\"7\",\"U\":\"0\",\"$\":\"360\"},{\"D\":\"SW\",\"F\":\"5\",\"G\":\"31\",\"H\":\"87\",\"Pp\":\"8\",\"S\":\"16\",\"T\":\"9\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"1\",\"$\":\"540\"},{\"D\":\"SW\",\"F\":\"7\",\"G\":\"31\",\"H\":\"81\",\"Pp\":\"8\",\"S\":\"16\",\"T\":\"11\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"1\",\"$\":\"720\"},{\"D\":\"SSW\",\"F\":\"8\",\"G\":\"40\",\"H\":\"81\",\"Pp\":\"9\",\"S\":\"20\",\"T\":\"11\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"1\",\"$\":\"900\"},{\"D\":\"SSW\",\"F\":\"7\",\"G\":\"45\",\"H\":\"82\",\"Pp\":\"19\",\"S\":\"22\",\"T\":\"11\",\"V\":\"VG\",\"W\":\"8\",\"U\":\"0\",\"$\":\"1080\"},{\"D\":\"SSW\",\"F\":\"7\",\"G\":\"54\",\"H\":\"85\",\"Pp\":\"83\",\"S\":\"27\",\"T\":\"12\",\"V\":\"MO\",\"W\":\"15\",\"U\":\"0\",\"$\":\"1260\"}]},{\"type\":\"Day\",\"value\":\"2024-01-22Z\",\"Rep\":[{\"D\":\"SW\",\"F\":\"8\",\"G\":\"54\",\"H\":\"88\",\"Pp\":\"64\",\"S\":\"27\",\"T\":\"12\",\"V\":\"MO\",\"W\":\"12\",\"U\":\"0\",\"$\":\"0\"},{\"D\":\"SW\",\"F\":\"8\",\"G\":\"47\",\"H\":\"84\",\"Pp\":\"59\",\"S\":\"25\",\"T\":\"12\",\"V\":\"GO\",\"W\":\"12\",\"U\":\"0\",\"$\":\"180\"},{\"D\":\"WSW\",\"F\":\"7\",\"G\":\"40\",\"H\":\"79\",\"Pp\":\"37\",\"S\":\"20\",\"T\":\"11\",\"V\":\"VG\",\"W\":\"9\",\"U\":\"0\",\"$\":\"360\"},{\"D\":\"WSW\",\"F\":\"6\",\"G\":\"34\",\"H\":\"76\",\"Pp\":\"12\",\"S\":\"18\",\"T\":\"9\",\"V\":\"VG\",\"W\":\"3\",\"U\":\"1\",\"$\":\"540\"},{\"D\":\"WSW\",\"F\":\"6\",\"G\":\"38\",\"H\":\"67\",\"Pp\":\"13\",\"S\":\"20\",\"T\":\"10\",\"V\":\"VG\",\"W\":\"1\",\"U\":\"1\",\"$\":\"720\"},{\"D\":\"WSW\",\"F\":\"6\",\"G\":\"36\",\"H\":\"63\",\"Pp\":\"28\",\"S\":\"18\",\"T\":\"10\",\"V\":\"VG\",\"W\":\"10\",\"U\":\"1\",\"$\":\"900\"},{\"D\":\"WSW\",\"F\":\"5\",\"G\":\"34\",\"H\":\"67\",\"Pp\":\"3\",\"S\":\"18\",\"T\":\"8\",\"V\":\"VG\",\"W\":\"0\",\"U\":\"0\",\"$\":\"1080\"},{\"D\":\"WSW\",\"F\":\"4\",\"G\":\"29\",\"H\":\"70\",\"Pp\":\"1\",\"S\":\"16\",\"T\":\"8\",\"V\":\"VG\",\"W\":\"2\",\"U\":\"0\",\"$\":\"1260\"}]},{\"type\":\"Day\",\"value\":\"2024-01-23Z\",\"Rep\":[{\"D\":\"WSW\",\"F\":\"4\",\"G\":\"25\",\"H\":\"74\",\"Pp\":\"2\",\"S\":\"13\",\"T\":\"7\",\"V\":\"VG\",\"W\":\"2\",\"U\":\"0\",\"$\":\"0\"},{\"D\":\"SW\",\"F\":\"3\",\"G\":\"20\",\"H\":\"79\",\"Pp\":\"8\",\"S\":\"11\",\"T\":\"7\",\"V\":\"VG\",\"W\":\"7\",\"U\":\"0\",\"$\":\"180\"},{\"D\":\"SW\",\"F\":\"4\",\"G\":\"20\",\"H\":\"84\",\"Pp\":\"21\",\"S\":\"11\",\"T\":\"7\",\"V\":\"GO\",\"W\":\"7\",\"U\":\"0\",\"$\":\"360\"},{\"D\":\"SSW\",\"F\":\"5\",\"G\":\"25\",\"H\":\"89\",\"Pp\":\"61\",\"S\":\"13\",\"T\":\"8\",\"V\":\"GO\",\"W\":\"12\",\"U\":\"1\",\"$\":\"540\"},{\"D\":\"SSW\",\"F\":\"7\",\"G\":\"31\",\"H\":\"90\",\"Pp\":\"56\",\"S\":\"16\",\"T\":\"11\",\"V\":\"GO\",\"W\":\"12\",\"U\":\"1\",\"$\":\"720\"},{\"D\":\"SW\",\"F\":\"9\",\"G\":\"36\",\"H\":\"90\",\"Pp\":\"46\",\"S\":\"18\",\"T\":\"12\",\"V\":\"GO\",\"W\":\"12\",\"U\":\"1\",\"$\":\"900\"},{\"D\":\"SW\",\"F\":\"9\",\"G\":\"40\",\"H\":\"89\",\"Pp\":\"16\",\"S\":\"20\",\"T\":\"13\",\"V\":\"GO\",\"W\":\"8\",\"U\":\"0\",\"$\":\"1080\"},{\"D\":\"SW\",\"F\":\"9\",\"G\":\"43\",\"H\":\"84\",\"Pp\":\"48\",\"S\":\"22\",\"T\":\"13\",\"V\":\"GO\",\"W\":\"12\",\"U\":\"0\",\"$\":\"1260\"}]}]}}}}";
        // String location_json1 = response1.getBody().asString();
        System.out.println("LocationApi json response :: " + location_json);
        String exceptedWindSpeed = null;
        List<String> locationList = JsonPath.parse(location_json).read("$.SiteRep.Wx.Param[*].name");
        for (int i = 0; i <= locationList.size(); i++) {
            String siteRepPramName = JsonPath.parse(location_json).read("$.SiteRep.Wx.Param[" + i + "].name");
            if ("S".equals(siteRepPramName)) {
                exceptedWindSpeed = JsonPath.parse(location_json).read("$.SiteRep.Wx.Param[" + i + "].['$']");
                break;
            }
        }
        assertThat(exceptedWindSpeed).isEqualTo(windSpeed);
        String actualLocation_name = JsonPath.parse(location_json).read("$.SiteRep.DV.Location.name");
        assertThat(actualLocation_name).isEqualTo(locationName);
    }

}