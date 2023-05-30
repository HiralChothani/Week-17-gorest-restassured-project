package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in";

        response = given()
                .when()
                .get("/public/v2/users?page=1&per_page=20")
                .then().statusCode(200);
        // response.log().all(); //this line is to print data into console
    }
//1. Extract the All Ids
    @Test
    public void test001(){
        List<Integer> listOfIds = response.extract().path("id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of Ids are : " + listOfIds);
        System.out.println("------------------End of Test---------------------------");

    }
//2. Extract the all Names
    @Test
    public void test002(){
        List<Integer> listOfNames = response.extract().path("name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("List of names are : " + listOfNames);
        System.out.println("------------------End of Test---------------------------");

    }
//3. Extract the name of 5th object
    @Test
    public void test003(){
        String nameOfObjectFive = response.extract().path("[4].name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("the name of 5th object : " + nameOfObjectFive);
        System.out.println("------------------End of Test---------------------------");

    }
//4. Extract the names of all object whose status = inactive
    @Test
    public void test004(){
        List<Object> name = response.extract().path("findAll{it.status == 'inactive'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("names of all object whose status = inactive : " + name);
        System.out.println("------------------End of Test---------------------------");
    }
//5. Extract the gender of all the object whose status = active
    @Test
    public void test005(){
        List<String> gender = response.extract().path("findAll{it.status == 'active'}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("gender of all the object whose status = active : " + gender);
        System.out.println("------------------End of Test-----------");
    }
//6. Print the names of the object whose gender = female
    @Test
    public void test006(){
        List<String> name = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("names of the object whose gender = female : " + name);
        System.out.println("------------------End of Test-----------");
    }
//7. Get all the emails of the object where status = inactive
    @Test
    public void test007(){
        List<String> email = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the emails of the object where status = inactive : " + email);
        System.out.println("------------------End of Test-----------");
    }
//8. Get the ids of the object where gender = male
    @Test
    public void test008(){
        List<Object> ids = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("ids of the object where gender = male : " + ids);
        System.out.println("------------------End of Test-----------");
    }
//9. Get all the status
    @Test
    public void test009(){
        List<String> status = response.extract().path("status");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("all the status : " + status);
        System.out.println("------------------End of Test---------------------------");
    }

//10. Get email of the object where name = Aishani Kocchar PhD
    @Test
    public void test010(){
        List<String> email = response.extract().path("findAll{it.name == 'Aishani Kocchar PhD'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("email of the object where name = Aishani Kocchar PhD : " + email);
        System.out.println("------------------End of Test-----------");
    }
//11. Get gender of id = 5471
    @Test
    public void test011(){
        List<String> gender = response.extract().path("findAll{it.id == 2272547}.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("gender of id = 5471 : " + gender);
        System.out.println("------------------End of Test-----------");
    }

}
