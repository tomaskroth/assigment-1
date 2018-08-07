package com.waes.differ.api.controller;

import com.waes.differ.api.DifferAppAPI;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.apache.http.HttpStatus.SC_CREATED;
import static org.apache.http.HttpStatus.SC_OK;
import static org.apache.http.HttpStatus.SC_UNPROCESSABLE_ENTITY;
import static org.hamcrest.Matchers.*;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {DifferAppAPI.class}
)
@RunWith(SpringJUnit4ClassRunner.class)
public class DifferControllerTest {

    @LocalServerPort
    private int randomServerPort;

    @Test
    public void calculateWithMissingLeftContentShouldRespondUnprocessableEntity() {
        String id = UUID.randomUUID().toString();
        String side = "right";
        String body = UUID.randomUUID().toString();

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .pathParam("side", side)
                .body(body)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_CREATED)
                .when()
                .post("/diff/{id}/{side}");

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_UNPROCESSABLE_ENTITY)
                .when()
                .get("/diff/{id}");
    }

    @Test
    public void calculateWithMissingRightContentShouldRespondUnprocessableEntity() {
        String id = UUID.randomUUID().toString();
        String side = "left";
        String body = UUID.randomUUID().toString();

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .pathParam("side", side)
                .body(body)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_CREATED)
                .when()
                .post("/diff/{id}/{side}");

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_UNPROCESSABLE_ENTITY)
                .when()
                .get("/diff/{id}");
    }

    @Test
    public void calculateWithValidDiffableEqualSidesShouldRespondOKWithProperMessage() {
        String id = UUID.randomUUID().toString();
        String side = "left";
        String body = UUID.randomUUID().toString();

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .pathParam("side", side)
                .body(body)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_CREATED)
                .when()
                .post("/diff/{id}/{side}");

        side = "right";
        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .pathParam("side", side)
                .body(body)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_CREATED)
                .when()
                .post("/diff/{id}/{side}");

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_OK)
                .when()
                .get("/diff/{id}")
                .then()
                .body("message", is("Comparison sides are equal to each other!"))
                .body("diffable.id", is(id));
    }

    @Test
    public void calculateWithValidDiffableDifferentSidesSizesShouldRespondOKWithProperMessage() {
        String id = UUID.randomUUID().toString();
        String side = "left";
        String body = UUID.randomUUID().toString();

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .pathParam("side", side)
                .body(body)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_CREATED)
                .when()
                .post("/diff/{id}/{side}");

        side = "right";
        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .pathParam("side", side)
                .body(body.substring(2))
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_CREATED)
                .when()
                .post("/diff/{id}/{side}");

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_OK)
                .when()
                .get("/diff/{id}")
                .then()
                .body("message", is("Comparison sides have different sizes!"))
                .body("diffable.id", is(id));
    }

    @Test
    public void calculateWithDiffableShouldRespondOKWithDifferences() {
        String id = UUID.randomUUID().toString();
        String side = "left";
        String originalBody = "this is the original";
        String changedBody =  "this is the changed ";

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .pathParam("side", side)
                .body(originalBody)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_CREATED)
                .when()
                .post("/diff/{id}/{side}");

        side = "right";
        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .pathParam("side", side)
                .body(changedBody)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_CREATED)
                .when()
                .post("/diff/{id}/{side}");

        given()
                .baseUri(this.baseUrl())
                .pathParam("id", id)
                .contentType(ContentType.JSON)
                .expect()
                .statusCode(SC_OK)
                .when()
                .get("/diff/{id}")
                .then()
                .body("diffable.id", is(id))
                .body("message", isEmptyOrNullString())
                .body("differences", iterableWithSize(2))
        ;
    }

    private String baseUrl() {
        return String.format("http://localhost:%d/v1", this.randomServerPort);
    }

}
