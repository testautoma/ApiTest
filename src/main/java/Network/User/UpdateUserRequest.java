package Network.User;

import Entities.User;
import Network.NetworkRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class UpdateUserRequest implements NetworkRequest<ResponseStatus> {
    User user;

    @Override
    public Response response(RequestSpecification specification) {
        return specification
                .basePath("/user/" + user.getUsername())
                .contentType(ContentType.JSON)
                .body(user)
                .when()
                .put();
    }

    @Override
    public Class<ResponseStatus> getResponseObjectClass() {
        return ResponseStatus.class;
    }
}
