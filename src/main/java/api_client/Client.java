package api_client;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import owner.WebConfig;
import urls.ConstantsURLs;

import static io.restassured.RestAssured.config;



public class Client {
     protected RequestSpecification getSpec() {
        return new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(ConstantsURLs.MAIN_URL)
                .build();
    }
}
