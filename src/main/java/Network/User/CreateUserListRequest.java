package Network.User;

import Entities.User;

import Network.NetworkRequest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import java.util.List;

@AllArgsConstructor
public class CreateUserListRequest implements NetworkRequest<ResponseStatus> {
    List<User> userList;

    @Override
    public Response response(RequestSpecification specification) {
        return specification
                .basePath("/user/createWithList")
                .contentType(ContentType.JSON)
                .body(userList)
                .when()
                .post();
    }

    @Override
    public Class<ResponseStatus> getResponseObjectClass() {
        return ResponseStatus.class;
    }
}
