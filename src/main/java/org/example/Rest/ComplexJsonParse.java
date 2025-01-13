package org.example.Rest;

import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath j=new JsonPath(DummyJsonResponse.Response());

        //1. Print No of courses returned by API
        int NoOfCourses=j.getInt("courses.size");
        System.out.println(NoOfCourses);

        //2.Print Purchase Amount
       int PurchaseAmount= j.getInt("dashboard.purchaseAmount");
       System.out.println(PurchaseAmount);

       //3. Print Title of the first course
       String FirstCourseTitle=j.getString("courses[0].title");
       System.out.println(FirstCourseTitle);

       // 4. Print All course titles and their respective Prices
        for(int i=0;i<NoOfCourses;i++){
            String Title=j.getString("courses["+i+"].title");
            System.out.println(Title);
            //toString at last will convert any input into string
            System.out.println(j.get("courses["+i+"].price").toString());
        }

        //5. Print no of copies sold by RPA Course
        System.out.println("Print no of copies sold by RPA Course");
        for(int i=0;i<NoOfCourses;i++){
            String courses=j.getString("courses["+i+"].title");
            if(courses.equalsIgnoreCase("RPA")){
                int copies=j.get("courses["+i+"].copies");
                System.out.println(copies);
            }

        }
    }
}
