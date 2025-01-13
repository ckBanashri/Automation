
package org.example.OMS;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class CreateOrder { // Class name corrected to follow Java conventions

    @Test
    public void createOrderTest1() {
        // Set base URI
        RestAssured.baseURI = "http://localhost:7001";

        // Send POST request
        Response res = given()
                .log().all()
                .queryParam("YFSEnvironment.progId", "SterlingHttpTester")
                .queryParam("InteropApiName", "createOrder")
                .queryParam("IsFlow", "N")
                .queryParam("ApiName", "createOrder")
                .queryParam("YFSEnvironment.userId", "admin")
                .queryParam("YFSEnvironment.password", "password")
                .queryParam("InteropApiData", "<Order DocumentType=\"0001\" OrderNo=\"\" EnterpriseCode=\"DEFAULT\">" +
                        "<OrderLines>" +
                        "    <OrderLine OrderedQty=\"1\">" +
                        "        <Item ItemID=\"100099\" UnitCost=\"10.0\" UnitOfMeasure=\"EACH\"/>" +
                        "    </OrderLine>" +
                        "</OrderLines>" +
                        "<PersonInfoShipTo AddressLine1=\"234 Copley Place\" City=\"Boston\" Country=\"US\" DayPhone=\"\" EMailID=\"\" " +
                        "FirstName=\"Stefan\" LastName=\"Salvatore\" MobilePhone=\"\" State=\"MA\" ZipCode=\"02116\"/>" +
                        "<PersonInfoBillTo AddressLine1=\"234 Copley Place\" City=\"Boston\" Country=\"US\" DayPhone=\"\" EMailID=\"\" " +
                        "FirstName=\"Lakshmi\" LastName=\"A\" MobilePhone=\"\" State=\"MA\" ZipCode=\"02116\"/>" +
                        "</Order>")
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
        String orderNumber = xmlPath.getString("Order.@OrderNo");

        // Print the extracted Order Number
        System.out.println("Order Number: " + orderNumber);
    }
}
