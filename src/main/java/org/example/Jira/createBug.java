package org.example.Jira;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.File;

import static io.restassured.RestAssured.*;
public class createBug {
    public static void main(String[] args){
        RestAssured.baseURI="https://banashrick7.atlassian.net";

        //createBug
       String createBugResponse=given().log().all().header("Content-Type","application/json")
               .header("Authorization","Basic YmFuYXNocmljazdAZ21haWwuY29tOkFUQVRUM3hGZkdGMHZRUl9IeWZhN2pKX3NDYWJvcUlEcGM0ajJMNGR5VlIyYTdncFZMOWZQd0hPVzFWbFp0cjRLRzNJcmhyNGRjRVhBY2xYMTd2V1RiLVFQX2pUUVlnMGJaaG5TWEZzQmdvMmJ0SnRjTlQzazdiVlpSZExyLV9GYW5tcGFxcVNkTzJ5Q0hqU2ZBRVAzOE5VckpKZE1hSzM4NnVVbkc5QXAwcVhPZ2VhdDFQZlN2dz1DQjkzOTkyNg==")
               .body("{\n" +
                       "    \"fields\": {\n" +
                       "       \"project\":\n" +
                       "       {\n" +
                       "          \"key\": \"SCRUM\"\n" +
                       "       },\n" +
                       "       \"summary\": \"List1 are not working.\",\n" +
                       "       \n" +
                       "       \"issuetype\": {\n" +
                       "          \"name\": \"Bug\"\n" +
                       "       }\n" +
                       "   }\n" +
                       "}").when().post("/rest/api/2/issue")
               .then().log().all()
               .assertThat().statusCode(201)
               .extract().response().asString();
        JsonPath js=new JsonPath(createBugResponse);
        String idvalue=js.getString("id");
        System.out.println(idvalue);

        given().log().all().pathParams("Key",idvalue)
                .header("X-Atlassian-Token","no-check")
                .header("Authorization","Basic YmFuYXNocmljazdAZ21haWwuY29tOkFUQVRUM3hGZkdGMHZRUl9IeWZhN2pKX3NDYWJvcUlEcGM0ajJMNGR5VlIyYTdncFZMOWZQd0hPVzFWbFp0cjRLRzNJcmhyNGRjRVhBY2xYMTd2V1RiLVFQX2pUUVlnMGJaaG5TWEZzQmdvMmJ0SnRjTlQzazdiVlpSZExyLV9GYW5tcGFxcVNkTzJ5Q0hqU2ZBRVAzOE5VckpKZE1hSzM4NnVVbkc5QXAwcVhPZ2VhdDFQZlN2dz1DQjkzOTkyNg==")
                .multiPart("file",new File("D:/1.jpeg")).log().all()
                .post("rest/api/3/issue/{Key}/attachments")
                        .then().log().all().assertThat().statusCode(200);


    }
}
