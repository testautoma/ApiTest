package Tests;

import Entities.User;
import Network.Network;
import Network.Networks;
import Network.User.*;
import io.qameta.allure.*;
import org.junit.After;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class UserTest {

    Network network = Networks.petStore();
    ArrayList<String> userNamesToDelete = new ArrayList<>();

    @After
    public void finish() {
        for (String userName : userNamesToDelete) {
            network.perform(new DeleteUserRequest(userName));
        }
        userNamesToDelete.clear();
    }

    @Test
    @AllureId("1")
    @TmsLink("TGT-1")
    @Owner("AutoTest")
    @Severity(SeverityLevel.BLOCKER)
    @Feature(value = "POST /user")
    @DisplayName("POST /user - Создание нового пользователя")
    @Description("https://petstore.swagger.io/#/user/createUser " +
            "Создание нового пользователя")
    public void createUsers() {
        User newUser = User.builder().username("NewUser").build();
        network.perform(new CreateUserListRequest(Arrays.asList(newUser)))
                        .then()
                        .statusCode(200);
        userNamesToDelete.add(newUser.getUsername());
        User existingUser = network.object(new GetUserRequest(newUser.getUsername()));
        Assert.assertTrue(newUser.equalWithoutIDTo(existingUser));
    }

    @Test
    @AllureId("2")
    @TmsLink(value = "TGT-2")
    @Owner("AutoTest")
    @Severity(SeverityLevel.CRITICAL)
    @Feature("GET /user/{username}")
    @DisplayName("GET /user/{username} - Получение информации о пользователе по его имени")
    @Description("https://petstore.swagger.io/#/user/getUserByName " +
            "Получение информации о пользователе по его имени")
    public void getUserByName() {
        User newUser = User.builder().username("NewUser").build();
        network.perform(new CreateUserListRequest(Arrays.asList(newUser)));//создание юзера
        userNamesToDelete.add(newUser.getUsername());
        User existingUser = network.perform(new GetUserRequest(newUser.getUsername()))
                        .then()
                        .statusCode(200)
                        .extract().jsonPath().getObject("", User.class);
        Assert.assertTrue(newUser.equalWithoutIDTo(existingUser));
    }

    @Test
    @AllureId("3")
    @TmsLink("TGT-3")
    @Owner("AutoTest")
    @Severity(SeverityLevel.TRIVIAL)
    @Feature(value = "GET /user/{username} - 404 (негативный кейс)")
    @DisplayName("GET /user/{username} - 404 (негативный кейс) Получение информации о несуществующем пользователе по" +
            " его имени")
    @Description("https://petstore.swagger.io/#/user/getUserByName " +
            "Получение информации о несуществующем пользователе по его имени")
    public void getUserByNameNotFound() {
        network.perform(new GetUserRequest("NotExistedUser"))
                .then()
                .statusCode(404);
    }

    @Test
    @AllureId("4")
    @TmsLink("TGT-4")
    @Owner("AutoTest")
    @Severity(SeverityLevel.MINOR)
    @Feature(value = "PUT /user/{username}")
    @DisplayName("PUT /user/{username} - Изменение информации о пользователе")
    @Description("https://petstore.swagger.io/#/user/updateUser " +
            "Изменение информации о пользователе")
    public void updatedUser() {
        User newUser = User.builder().username("NewUser").build();
        network.perform(new CreateUserListRequest(Arrays.asList(newUser)));//создание юзера
        userNamesToDelete.add(newUser.getUsername());
        newUser.setFirstName("NewFirstName");
        network.perform(new UpdateUserRequest(newUser))
                        .then()
                        .statusCode(200);
        User existingUser = network.object(new GetUserRequest(newUser.getUsername()));
        System.out.println(newUser);
        System.out.println(existingUser);
        Assert.assertTrue(newUser.equalWithoutIDTo(existingUser));
    }

    @Test
    @AllureId("5")
    @TmsLink("TGT-5")
    @Owner("AutoTest")
    @Severity(SeverityLevel.CRITICAL)
    @Feature(value = "DELETE /user/{username}")
    @DisplayName("DELETE /user/{username} - Удаление информации о пользователе")
    @Description("https://petstore.swagger.io/#/user/deleteUser " +
            "Удаление информации о пользователе")
    public void deleteUser() {
        User newUser = User.builder().username("NewUser").build();
        network.perform(new CreateUserListRequest(Arrays.asList(newUser)));
        network.perform(new DeleteUserRequest(newUser.getUsername()))
                        .then()
                        .statusCode(200);
        network.perform(new GetUserRequest(newUser.getUsername()))
                .then()
                .statusCode(404);
    }
}

