package org.example.Rest;

import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumAllCoursePrice {
    //6. Verify if Sum of all Course prices matches with Purchase Amount
    @Test
    public void SumPrice(){
        int sum=0;
        JsonPath j=new JsonPath(DummyJsonResponse.Response());
        int Size=j.getInt("courses.size()");
        System.out.println(Size);

        for(int i=0;i<Size;i++){
           int price= j.getInt("courses["+i+"].price");
           int copies=j.getInt("courses["+i+"].copies");
           int multipleOfPriceCopies =price * copies;
           System.out.println(multipleOfPriceCopies);
            sum=sum+multipleOfPriceCopies;
        }
        System.out.println(sum);
        int PurchasePrice=j.getInt("dashboard.purchaseAmount");
        Assert.assertEquals(sum,PurchasePrice);
    }
}
