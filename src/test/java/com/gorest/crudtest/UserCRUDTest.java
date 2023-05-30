package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {
    static String user_name = "ABC" + TestUtils.getRandomValue();
    static String user_email = TestUtils.getRandomValue() + "xyz@gmail.com";
    static int userId;
    //    static String gender;
//    static String status;
    static int id;

    @Test
    public void test001() {
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName(user_name);
        postsPojo.setEmail(user_email);
        postsPojo.setGender("Female");
        postsPojo.setStatus("Active");

        Response response = given()
                .contentType(ContentType.JSON) // this line instead of below line
//                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 4d2d823f67b81559dca6d9d4644eb8f303a396dd2c049a308bb85196407038ef")
                .when()
                .body(postsPojo)
                .post();
        response.prettyPrint();
        response.then().log().all().statusCode(201);
        userId = response.jsonPath().get("id");
        System.out.println("Id is : userId");

    }

    @Test
    public void test002() {

        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 4d2d823f67b81559dca6d9d4644eb8f303a396dd2c049a308bb85196407038ef")
                .when()
                // .get("/2329369");
                .get("/" + userId);

        response.then().statusCode(200);
        response.prettyPrint();

//        String s1 = "findAll{it.name == '";
//        String s2 = "'}.get(0)";
//        HashMap<String , Object> userMap = given()
//                .header("Content-Type", "application/json")
//                .header("Authorization", "Bearer 4d2d823f67b81559dca6d9d4644eb8f303a396dd2c049a308bb85196407038ef")
//                .when()
//                .get("/public/v2/users")
//                .then().statusCode(200)
//                .extract()
//                .path(s1+user_name+s2);
//        Assert.assertThat(userMap, hasValue(user_name));
//        id = (int) userMap.get("id");
//        System.out.println(id);

    }

    @Test
    public void test003() {
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName("Radhika");
        postsPojo.setEmail(user_email);
        postsPojo.setGender("Female");
        postsPojo.setStatus("Active");
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 4d2d823f67b81559dca6d9d4644eb8f303a396dd2c049a308bb85196407038ef")
                .when()
                .body(postsPojo)
                .patch("/" + userId);
        response.prettyPrint();
        response.then().log().all().statusCode(200);
    }

    @Test
    public void test004() {
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 4d2d823f67b81559dca6d9d4644eb8f303a396dd2c049a308bb85196407038ef")
                .when()
                .delete("/" + userId);
        response.prettyPrint();
        response.then().log().all().statusCode(204);


    }


}
