package org.example.Jira;
import static io.restassured.RestAssured.*;

import com.beust.ah.A;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.example.Pojo.Api;
import org.example.Pojo.GetCourse;
import org.example.Pojo.WebAutomation;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.management.remote.JMXServiceURL;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AuthorizationService {
    @Test
    public static void Auth(){

        RestAssured.baseURI="https://rahulshettyacademy.com";

        String[] expectedcourse={"Selenium Webdriver Java","Cypress","Protractor"};

        String token=given().log().all().formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type","client_credentials")
                .formParam("scope","trust")
                .post("oauthapi/oauth2/resourceOwner/token")
                .then().log().all().extract().response()
                .asString();

        JsonPath js=new JsonPath(token);
       String Access_token= js.getString("access_token");
       System.out.println(Access_token);

//       //Get
        GetCourse Access_token1=given().log().all().queryParam("access_token",Access_token)
                .when().get("/oauthapi/getCourseDetails")
                        .then().log().all().extract().response()
                                 .as(GetCourse.class);
        System.out.println(Access_token1);
       System.out.println(Access_token1.getInstructor());
       System.out.println(Access_token1.getLinkedIn());
       System.out.println(Access_token1.getCourses().getApi().get(1).getCourseTitle());

       System.out.println(Access_token1.getCourses().getApi().get(1).getCourseTitle());

     //get the price of soap course
        List<Api> api=Access_token1.getCourses().getApi();
      for(int i=0;i< api.size();i++){
           if(api.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
           {
               System.out.println(api.get(i).getPrice());

           }
           System.out.println(Access_token1.getCourses().getMobile().get(0).getCourseTitle());
      }

      //get the course 0f webautomation

        ArrayList<String> actualCourses=new ArrayList<String>();
        List<WebAutomation> webAuto=Access_token1.getCourses().getWebAutomation();
           for(int j=0;j<webAuto.size();j++){
               actualCourses.add(webAuto.get(j).getCourseTitle());

           }
       List<String> expectedList= Arrays.asList(expectedcourse);
           Assert.assertTrue(actualCourses.equals(expectedList));
       }
 }

