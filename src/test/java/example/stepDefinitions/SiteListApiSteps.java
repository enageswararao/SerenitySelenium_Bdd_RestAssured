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
        System.out.println("==== ::"+json);
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
        String location_json1 = response.getBody().asString();
        System.out.println("LocationApi json response :: " + location_json1);
        String exceptedWindSpeed = null;
        List<String> locationList = JsonPath.parse(location_json1).read("$.SiteRep.Wx.Param[*].name");
        for (int i = 0; i <= locationList.size(); i++) {
            String siteRepPramName = JsonPath.parse(location_json1).read("$.SiteRep.Wx.Param[" + i + "].name");
            if ("S".equals(siteRepPramName)) {
                exceptedWindSpeed = JsonPath.parse(location_json1).read("$.SiteRep.Wx.Param[" + i + "].['$']");
                break;
            }
        }
        assertThat(exceptedWindSpeed).isEqualTo(windSpeed);
        String actualLocation_name = JsonPath.parse(location_json1).read("$.SiteRep.DV.Location.name");
        assertThat(actualLocation_name).isEqualTo(locationName);
    }

}