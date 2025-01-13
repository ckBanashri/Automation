
package org.example.Rest;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import static io.restassured.RestAssured.*;



public class FirstRest {
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response=given().log().all().queryParam("key","qaclick123")
                .header("Content-Type","application/json").body("{\n" +
                        "    \"status\": \"OK\",\n" +
                        "    \"place_id\": \"9a309d5184d03ae6904ff61724394623\",\n" +
                        "    \"scope\": \"APP\",\n" +
                        "    \"reference\": \"3e05188908aa863b3c320cab50e3f1143e05188908aa863b3c320cab50e3f114\",\n" +
                        "    \"id\": \"3e05188908aa863b3c320cab50e3f114\"\n" +
                        "}")
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).extract().response().asString();


        System.out.println(response);
        //JsonPath will take the input as String and convert to JSON
        JsonPath js=new JsonPath(response);
        String Placeid = js.getString("place_id");
        System.out.println(Placeid);
        //put
        /*String newAddress="70 Summer walk, USA";
        given().log().all().queryParam("key", "qaclick123")
                .header("Content-Type", "application/json\r\n"
                        + "").body("{\r\n"
                        + "\"place_id\":\""+Placeid+"\",\r\n"
                        + "\"address\":\""+newAddress+"\",\r\n"
                        + "\"key\":\"qaclick123\"\r\n"
                        + "}").when().put("maps/api/place/update/json").then().log().all()
                .assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));


        String getResponce=given().log().all().queryParam("key","qaclick123")
                .queryParam("place_id",Placeid)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        System.out.println(getResponce);

        JsonPath js1=new JsonPath(getResponce);
        String actualAdd=js1.get("address");
        System.out.println(actualAdd);

        Assert.assertEquals(actualAdd, newAddress);
*/




    }

}



