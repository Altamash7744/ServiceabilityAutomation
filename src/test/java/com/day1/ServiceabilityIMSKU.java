package com.day1;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;


import static io.restassured.RestAssured.given;

public class ServiceabilityIMSKU {

    public static void main(String[] args) {

        String url = "https://smapi-v2-uat.shiprocket.in/v1/external/samsung/serviceability";

        JSONObject requestBody = new JSONObject();
        requestBody.put("postal_code", 110030);

        JSONObject sku = new JSONObject();
        sku.put("sku", "SM-G975FCKG");
        sku.put("quantity", 1);
        sku.put("is_instant_exchange_order", false);
        sku.put("is_deferred_exchange_order", false);

        String[] warehouseIds = {"NERP_SWH_BANGALORE_BGT01_IM"};
        sku.put("warehouse_ids", warehouseIds);

        JSONArray skus = new JSONArray();
        skus.put(sku);
        requestBody.put("skus", skus);

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody.toString())
                .when()
                .post(url);

//        String responseBody = response.getBody().asString();
//        System.out.println(responseBody);}

        Assertions.assertEquals(200, response.getStatusCode());
        response.prettyPrint();
    }

}





