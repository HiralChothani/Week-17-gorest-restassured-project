package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.Matchers.hasEntry;

public class UserAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";
        // RestAssured.port = 3030;

        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
        // response.log().all(); //this line is to print data into console
    }

    //    1. Verify the if the total record is 20
    @Test
    public void test001() {

        response.body("total.size()", is(20));
        // response.body("id", hasSize(20));
    }

    //            2. Verify the if the name of id = 5487 is equal to ”Hamsini Trivedi”

    @Test
    public void test002() {
        response.body("findAll{id = 2272527}", hasItem(hasEntry("name", "Mahesh Kocchar")));
    }

//   3. Check the single ‘Name’ in the Array list (Saraswati Patel)
    @Test
    public void test003(){
        response.body("name", hasItem("Saraswati Patel"));
    }
//4. Check the multiple ‘Names’ in the ArrayList (Pushti Abbott", "Mahesh Kocchar", "Vrund Iyer)
@Test
    public void test004(){
        response.body("name", hasItems("Pushti Abbott", "Mahesh Kocchar", "Vrund Iyer"));
}
//5. Verify the emai of userid = 5471 is equal “adiga_aanjaneya_rep@jast.org”
    @Test
    public void test005(){
        response.body("findAll{id = 2272525}", hasItem(hasEntry("email", "rana_ii_chaanakya@mraz.example")));
    }

//            6. Verify the status is “Active” of user name is “Mahesh Kocchar”
    @Test
    public void test006(){
        response.body("findAll{name = 'Mahesh Kocchar'}", hasItem(hasEntry("status", "active")));
    }
//            7. Verify the Gender = male of user name is “Mahesh Kocchar"
    @Test
    public void test007(){
        response.body("findAll{name = 'Mahesh Kocchar'}", hasItem(hasEntry("gender", "male")));
    }
}
