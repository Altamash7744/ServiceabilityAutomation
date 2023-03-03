package com.day1;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.Assertions;
import org.testng.annotations.Test;


public class AuthenticationForwardOrder {

    @Test
    public void bearerTokenAutheticationTest() {

        RestAssured.baseURI = "https://smapi-v2-uat.shiprocket.in";

        RequestSpecification request = RestAssured.given();

        String payload = "{\n" +
                "    \"email\": \"api.samsung@yopmail.com\",\n" +
                "    \"password\": \"Samsung@2021\"\n" +
                "}";

        request.header("Content-Type", "application/json");

        Response responseFromGenerateToken = request.body(payload).post("/v1/external/auth/login");

        responseFromGenerateToken.prettyPrint();

        String jsonString = responseFromGenerateToken.getBody().asString();

        String tokenGenerated = JsonPath.from(jsonString).get("token");

        request.header("Authorization", "Bearer " + tokenGenerated)
                .header("Content-Type", "application/json");

        String addBookDetails = "{\n" +
                "    \"order_id\": \"Testorder00121202\",\n" +
                "    \"order_date\": \"2023-02-09 09:25\",\n" +
                "    \"channel_id\": \"2944005\",\n" +
                "    \"billing_customer_name\": \"Altamash\",\n" +
                "    \"billing_last_name\": \"QA\",\n" +
                "    \"billing_address\": \"plot 96 watare sir wuba masjid nipani\",\n" +
                "    \"billing_city\": \"Pune\",\n" +
                "    \"billing_pincode\": \"110030\",\n" +
                "    \"billing_state\": \"Maharashtra\",\n" +
                "    \"billing_country\": \"India\",\n" +
                "    \"billing_email\": \"jax@counterstike.com\",\n" +
                "    \"billing_phone\": \"7744918893\",\n" +
                "    \"shipping_is_billing\": true,\n" +
                "    \"order_items\": [\n" +
                "        {\n" +
                "            \"name\": \"Galaxy S10+\",\n" +
                "            \"sku\": \"SM-G975FCKG\",\n" +
                "            \"units\": 1,\n" +
                "            \"selling_price\": \"100\"\n" +
                "          }\n" +
                "    ],\n" +
                "    \"payment_method\": \"prepaid\",\n" +
                "    \"sub_total\": 100, \n" +
                "    \"length\": 10,\n" +
                "    \"breadth\": 10,\n" +
                "    \"height\": 10,\n" +
                "    \"weight\": 21,\n" +
                "    \"pickup_location\": \"NERP_SWH_NOIDA_NOT01\",\n" +
                "    \"custom_order_id\": \"11539876109\",\n" +
                "    \"shipping_methods//1234\": {\n" +
                "        \"priority_delivery_code\": \"sdd\",\n" +
                "        \"estimated_delivery_date\": \"2023-01-04\"\n" +
                "    },\n" +
                "    \"print_label\": false,\n" +
                "    \"generate_manifest\": 0,\n" +
                "    \"request_pickup\" : true\n" +
                "}\n";

        Response addBooksResponse = request.body(addBookDetails).post("/v1/external/shipments/create/forward-shipment");

        Assertions.assertEquals(200, addBooksResponse.getStatusCode());

        addBooksResponse.prettyPrint();
    }
}
