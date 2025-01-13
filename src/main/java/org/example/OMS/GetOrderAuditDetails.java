package org.example.OMS;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class GetOrderAuditDetails {
    // Class name corrected to follow Java conventions

    @Test
    public void GetOrderAuditDetails() {
        // Set base URI
        RestAssured.baseURI = "http://localhost:7001";

        // Send POST request
        Response res = given()
                .log().all()
                .queryParam("YFSEnvironment.progId", "SterlingHttpTester")
                .queryParam("InteropApiName", "getOrderAuditDetails")
                .queryParam("IsFlow", "N")
                .queryParam("ApiName", "getOrderAuditDetails")
                .queryParam("YFSEnvironment.userId", "admin")
                .queryParam("YFSEnvironment.password", "password")
                .queryParam("InteropApiData", "<OrderAudit OrderAuditKey=\"2024102913335820391\"/>")
                .header("Accept", "*/*")
                .header("Content-Type", "application/x-www-form-urlencoded")
                .when()
                .log().all()
                .post("/smcfs/servlets/IBMApiTesterServlet")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response();

        // Parse the response XML
        XmlPath xmlPath = new XmlPath(res.asString());
        System.out.println("response="+xmlPath);
        String orderNumber = xmlPath.getString("Order.@OrderNo");

        // Print the extracted Order Number
        System.out.println("Order Number: " + orderNumber);
    }
}
