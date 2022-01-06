package Network;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public interface NetworkRequest<ResponseObject> {
    public Response response(RequestSpecification specification);
    public Class<ResponseObject> getResponseObjectClass();
}
