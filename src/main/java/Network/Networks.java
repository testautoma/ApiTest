package Network;

public class Networks {
    static String BASE_URI = "https://petstore.swagger.io/v2";

    public static Network petStore() {
        return new Network(BASE_URI);
    }
}
