package Network;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Network {
    private final String baseUri;

    private RequestSpecification given() {
        return RestAssured.given()
                .baseUri(baseUri);
    }

    public Response perform(NetworkRequest request) {
        return request.response(given());
    }

    public <T> T object(NetworkRequest<T> request) {
        return request.response(given())
                .then()
                .extract()
                .jsonPath()
                .getObject("", request.getResponseObjectClass());
    }
}
