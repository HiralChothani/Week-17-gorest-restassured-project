package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .when()
                .get("/public/v2/posts?page=1&per_page=25")
                .then().statusCode(200);
//         response.log().all(); this line is to print data into console
    }

    //1. Extract the title
    @Test
    public void test001() {
        List<String> listOfTitles = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of names are : " + listOfTitles);
        System.out.println("------------------End of Test---------------------------");
    }

    //2. Extract the total number of record
    @Test
    public void test002() {
        int totalRecords = response.extract().path("size()");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("total number of record : " + totalRecords);
        System.out.println("------------------End of Test---------------------------");
    }

    //3. Extract the body of 15th record
    @Test
    public void test003() {
        String body = response.extract().path("[14].body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the body of 15th record : " + body);
        System.out.println("------------------End of Test---------------------------");
    }

    //4. Extract the user_id of all the records
    @Test
    public void test004() {
        List<Integer> listOfUserIds = response.extract().path("user_id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the user_id of all the records : " + listOfUserIds);
        System.out.println("------------------End of Test---------------------------");
    }

    //5. Extract the title of all the records
    @Test
    public void test005() {
        List<String> titleOfAllRecords = response.extract().path("title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the user_id of all the records : " + titleOfAllRecords);
        System.out.println("------------------End of Test---------------------------");
    }

    //6. Extract the title of all records whose user_id = 5456
    @Test
    public void test006() {
        List<String> title = response.extract().path("findAll{user_id = 2272652}.title");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("title of all records whose user_id = 2272652 : " + title);
        System.out.println("------------------End of Test---------------------------");
    }

    //7. Extract the body of all records whose id = 39282
    @Test
    public void test007() {
        List<String> body = response.extract().path("findAll{id = 39282}.body");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the body of all records whose id = 39282 : " + body);
        System.out.println("------------------End of Test---------------------------");
    }
}
