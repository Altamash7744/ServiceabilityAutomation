package com.day1;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataDrivenExample {

    @DataProvider(name = "DataForPost")
    public Object[][] dataForPost() {

        Object[][] data = new Object[1][2];

        data[1][2] = "{\n" +
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
                "    \"postal_code\": 110030\n" +
                "}";

        return data;
    }


}
