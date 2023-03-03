package com.day1;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class ServiceabilityTest {

    @Test
    public void serviceability() {

        RestAssured.baseURI = "https://smapi-v2-uat.shiprocket.in";

        RequestSpecification request = RestAssured.given();

        request.header("Content-Type", "application/json");

        String addServiceabilityRequestBody = "{\n" +
                "    \"skus\": [\n" +
                "        {\n" +
                "            \"sku\": \"SM-G975FCKG\",\n" +
                "            \"quantity\": 1,\n" +
                "            \"is_instant_exchange_order\": false,\n" +
                "            \"is_deferred_exchange_order\": false,\n" +
                "            \"warehouse_ids\": [\n" +
                "                \"NERP_SWH_BANGALORE_BGT01_IM\"\n" +
                "            ]\n" +
                "        }\n" +
                "    ],\n" +
                "    \"postal_code\": 800001\n" +
                "}";

        Response addBooksResponse = request.body(addServiceabilityRequestBody).post("/v1/external/samsung/serviceability");

        Assertions.assertEquals(200, addBooksResponse.getStatusCode());

        addBooksResponse.prettyPrint();
    }
}




