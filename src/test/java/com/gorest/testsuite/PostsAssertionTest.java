package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;

public class PostsAssertionTest {
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

    //1. Verify the if the total record is 25
    @Test
    public void test001() {
        response.body("total.size()", is(25));
    }
//  2. Verify the if the title of id = 2730 is equal to ”Ad ipsa coruscus ipsam eos demitto centum.”
    @Test
    public void test002(){
        response.body("findAll{id = 39277}", hasItem(hasEntry("title", "Deleniti supplanto spectaculum consequatur aro bis.")));
    }
//  3. Check the single user_id in the Array list (5522)
    @Test
    public void test003(){
        response.body("user_id", hasItem(2272652));
    }
//  4. Check the multiple ids in the ArrayList (2693, 2684,2681)
    @Test
    public void test004(){
        response.body("id", hasItems(39282,39281, 39280));
    }
//5. Verify the body of userid = 2678 is equal “Surgo templum cribro. Turbo villa cunae. Vitiosus aegrotatio adsum. Voluptate terebro torrens. Tertius decimus termes. Adhuc texo vel. Antiquus pariatur admoveo. Admiratio tutamen molestiae. Conqueror acceptus tersus. Facere non ducimus.
    @Test
    public void test005(){
        response.body("findAll{user_id = 2272652}", hasItem(hasEntry("body", "Surgo templum cribro. Turbo villa cunae. Vitiosus aegrotatio adsum. Voluptate terebro torrens. Tertius decimus termes. Adhuc texo vel. Antiquus pariatur admoveo. Admiratio tutamen molestiae. Conqueror acceptus tersus. Facere non ducimus.")));
    }


}
