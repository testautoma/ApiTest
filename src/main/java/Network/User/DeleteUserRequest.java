package Network.User;

import Network.NetworkRequest;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DeleteUserRequest implements NetworkRequest<ResponseStatus> {
    String userName;

    @Override
    public Response response(RequestSpecification specification) {
        return specification
                .basePath("/user/" + userName)
                .when()
                .delete();
    }

    @Override
    public Class<ResponseStatus> getResponseObjectClass() {
        return ResponseStatus.class;
    }
}
