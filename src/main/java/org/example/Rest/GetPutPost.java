package org.example.Rest;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.*;


public class GetPutPost {
    public static void main(String[] args) {
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String respose=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
                .body("{\r\n"
                        + "  \"location\": {\r\n"
                        + "    \"lat\": -38.383494,\r\n"
                        + "    \"lng\": 33.427362\r\n"
                        + "  },\r\n"
                        + "  \"accuracy\": 50,\r\n"
                        + "  \"name\": \"Frontline house1\",\r\n"
                        + "  \"phone_number\": \"(+91) 983 893 3937\",\r\n"
                        + "  \"address\": \"29, side layout, cohen 09\",\r\n"
                        + "  \"types\": [\r\n"
                        + "    \"shoe park\",\r\n"
                        + "    \"shop\"\r\n"
                        + "  ],\r\n"
                        + "  \"website\": \"https://rahulshettyacademy.com\",\r\n"
                        + "  \"language\": \"French-IN\"\r\n"
                        + "}").when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
                .header("server","Apache/2.4.52 (Ubuntu)").extract().response().asString();


        System.out.println(respose);

        JsonPath js=new JsonPath(respose);
        String placeID=js.getString("place_id");
        String NewAddress = "70 Summer walk, USA";
        given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").
                body("{\r\n"
                        + "\"place_id\":\""+placeID+"\",\r\n"
                        + "\"address\":\""+NewAddress+"\",\r\n"
                        + "\"key\":\"qaclick123\"\r\n"
                        + "}").when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));


        String respose1=given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeID).header("Content-Type","application/json")
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js1=new JsonPath(respose1);
        System.out.println(js1);
        String Auaddress=js1.getString("address");
       assertEquals(Auaddress,NewAddress);
        System.out.print("he");
    }
}
