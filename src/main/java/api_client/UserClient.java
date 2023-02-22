package api_client;

import io.restassured.response.ValidatableResponse;
import urls.ConstantsURLs;

import static io.restassured.RestAssured.given;

public class UserClient extends Client {

    public ValidatableResponse createUser(User user) {
        return given().log().all()
                .spec(getSpec())
                .body(user)
                .when()
                .post(ConstantsURLs.REGISTER_URL)
                .then();

    }

    public ValidatableResponse loginUser(UserCredentials credentials) {
        return given().log().all()
                .spec(getSpec())
                .body(credentials)
                .when()
                .post(ConstantsURLs.LOGIN_URL)
                .then().log().body();
    }


    public ValidatableResponse deleteUser(String accessToken) {
        return given().log().all()
                .header("Authorization", accessToken)
                .spec(getSpec())
                .when()
                .delete(ConstantsURLs.DELETE_PROFILE_URL)
                .then().log().body();
    }


}
