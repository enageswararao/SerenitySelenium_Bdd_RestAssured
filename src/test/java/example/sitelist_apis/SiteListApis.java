package example.sitelist_apis;

import example.endpoints.SiteListApisEndpoint;
import io.restassured.response.Response;
import net.serenitybdd.annotations.Step;
import net.serenitybdd.rest.SerenityRest;

import java.net.MalformedURLException;

public class SiteListApis {

    Response response;

    @Step
    public Response getSiteListApi() {
        return response = SerenityRest.given()
                .when()
                .get(SiteListApisEndpoint.GET_SITELIST);

    }

    @Step
    public Response getLocationApi(String locationId) throws MalformedURLException {
        String get_locationApi = String.format(SiteListApisEndpoint.GET_LOCATIONAPI, locationId);
        System.out.println("after param of location url" + get_locationApi );
                response = SerenityRest.given()
                .when()
                .get(get_locationApi);
        return response;
    }


}
