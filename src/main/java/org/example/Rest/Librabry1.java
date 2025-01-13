package org.example.Rest;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class Librabry1 {
  @Test
    public void add(){
      RestAssured.baseURI="http://216.10.245.166";

      String getResponse=given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json").body("{\n" +
              "\n" +
              "\"name\":\"2Learn Appium Automation with Java1\",\n" +
              "\"isbn\":\"1bcd2\",\n" +
              "\"aisle\":\"1222\",\n" +
              "\"author\":\"1John foe2\"\n" +
              "}").when().post("Library/Addbook.php")
              .then().log().all().assertThat().statusCode(200)
              //body("Msg",equalTo("successfully added"));
              .extract().response().asString();
      System.out.println(getResponse);

      JsonPath js=new JsonPath(getResponse);
      String msg=js.getString("Msg");
      System.out.println(msg);
  }
}

