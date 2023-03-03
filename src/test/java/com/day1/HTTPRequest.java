package com.day1;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.matcher.RestAssuredMatchers;
import org.hamcrest.Matchers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class HTTPRequest {

//     given()
//         content type, set cookies, add auth, add param, set headers info etc....
//     when()
//         get, post, put, delete
//     then()
//        validate status code, extract response, extract headers cookies & response body....
    @Test
    void getUsers() {

        given()

                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .body("page",equalTo(2))
                    .log().all();
    }


}
