package tests;

import models.Register;
import models.RegisterData;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static tests.Specs.*;

public class RegisterTests {

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
    public void registerSuccessfullGroovy() {
        given()
                .spec(requestToRegister)
                .when()
                .post("/register")
                .then()
                .spec(responseSpec)
                .log().body()
                .body("register.findAll{it.token}.token.flatten()",
                        hasItem("QpwL5tke4Pnpja7X4"));
    }

}
