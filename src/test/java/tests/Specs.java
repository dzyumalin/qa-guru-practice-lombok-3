package tests;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;

public class Specs {

    public static RequestSpecification request = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .log().all()
            .contentType(JSON);

    public static RequestSpecification requestToCreate = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .log().all()
            .contentType(JSON)
            .body("{\"name\": \"morpheus\"," +
                    "\"job\": \"leader\"}");

    public static RequestSpecification requestToRegister = with()
            .baseUri("https://reqres.in")
            .basePath("/api")
            .log().all()
            .contentType(JSON)
            .body("{\"email\": \"eve.holt@reqres.in\"," +
                    "\"password\": \"pistol\"}");

    public static ResponseSpecification responseToRegister = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectBody("id", is(4))
            .expectBody("token", is("QpwL5tke4Pnpja7X4"))
            .build();

    public static ResponseSpecification responseToCreate= new ResponseSpecBuilder()
            .expectStatusCode(201)
            .expectBody("name", is("morpheus"))
            .expectBody("job", is("leader"))
            .build();

    public static ResponseSpecification responseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

}
