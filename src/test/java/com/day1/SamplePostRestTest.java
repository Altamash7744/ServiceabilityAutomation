package com.day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class SamplePostRestTest {

    @Test
    public void createUser_whenSuccess() {

        RestAssured.baseURI = "https://smapi-v2-uat.shiprocket.in";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        JSONArray jsonArray = new JSONArray();
        jsonArray.put("Skus");

        JSONObject jo = new JSONObject();
        jo.put("sku", "SM-G975FCKG");
        jo.put("quantity", "1");
        jo.put("is_instant_exchange_order", "false");
        jo.put("is_deferred_exchange_order","false");

        jsonArray.put(jo);

        given().log().all().header("authorization", "Bearer 0431655cfe7ba40a791e0ce32d83ad33363348919c11627f409a3228f205e15f23")
                .accept(ContentType.JSON)
                .contentType("application/json")
                .and()
                .body(jsonArray.toString())
                .post("/v1/external/samsung/serviceability")   //hit the post end point
                .thenReturn().asString();

    }

}
