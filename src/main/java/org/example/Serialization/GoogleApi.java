package org.example.Serialization;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class GoogleApi {
    @Test
    public void Googleapi(){
        GoogleMpas p=new GoogleMpas();

        p.setAccuracy(50);
        p.setAddress("29, side layout, cohen 09");
        p.setName("Frontline house");
        p.setPhone_number("(+91) 983 893 3937");
        p.setWebsite("http://google.com");
        p.setLanguage("French-IN");
        List<String> myList =new ArrayList<String>();
        myList.add("shoe park");
        myList.add("shop");
        p.setTypes(myList);

        loc l=new loc();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);


         RestAssured.baseURI="https://rahulshettyacademy.com";
        Response res=given().log().all().queryParam("key"," qaclick123").body(p)
                .when().post("/maps/api/place/add/json")
                .then().statusCode(200).extract().response();

//        JsonPath j=new JsonPath(res);
//        System.out.println(j);
        String r=res.asString();
        System.out.println(r);

    }

}
