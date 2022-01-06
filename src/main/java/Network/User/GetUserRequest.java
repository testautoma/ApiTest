package Network.User;

import Entities.User;
import Network.NetworkRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetUserRequest implements NetworkRequest<User> {
    String userName;

    @Override
    public Response response(RequestSpecification specification) {
        return specification
                .basePath("/user/" + userName)
                .contentType(ContentType.JSON)
                .when()
                .get();
    }

    @Override
    public Class<User> getResponseObjectClass() {
        return User.class;
    }
}
