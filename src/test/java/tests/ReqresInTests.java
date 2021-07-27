package tests;

import models.Register;
import models.UserData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.Specs.*;

public class ReqresInTests {

    @Test
    public void registerSuccessfullLombok() {
        Register register = given()
                .spec(requestToRegister)
                .when()
                .post("/register")
                .then()
                .spec(responseToRegister)
                .log().body()
                .extract().as(Register.class);

        assertEquals("QpwL5tke4Pnpja7X4", register.getToken());
    }

    @Test
    public void registerSuccessfull() {
        given()
                .spec(requestToRegister)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("token", is("QpwL5tke4Pnpja7X4"));
    }

    @Test
    public void singleUserLombok() {
        UserData data =
                given()
                        .spec(request)
                        .when()
                        .get("/users/2")
                        .then()
                        .log().body()
                        .extract().as(UserData.class);

        assertEquals("janet.weaver@reqres.in", data.getUser().getEmail());
    }

    @Test
    public void createUserTest() {
        given()
                .spec(requestToCreate)
                .when()
                .post("/users")
                .then()
                .spec(responseToCreate);
    }

    @Test
    public void listUserTestGroovy() {
        given()
                .spec(request)
                .when()
                .get("/users?page=2")
                .then()
                .log().body()
                .body("data.findAll{it.id < 8}.first_name.flatten()",
                        hasItem("Michael"));
    }
}
