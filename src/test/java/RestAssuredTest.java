import com.fasterxml.jackson.databind.util.JSONPObject;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.delete;
import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class RestAssuredTest {


    String url = "https://jsonplaceholder.typicode.com/";

    @Test
    public void getPageOne() {

        given().
                param("page", "1").
                when().
                get(url).
                then().
                statusCode(200).
                body("page", equalTo(1));
    }
    @Test
    public void getUser() {
        get(url + "/2").then().body("data.id", equalTo(2));

    }
    @Test
    public void getUser404() {
        get(url + "users/11").then().statusCode(404);
    }
    @Test
    public void getUser200 () {
        get(url + "users/2").then().statusCode(200);

    }

    @Test
    public void postUser201 () {
        Map<String, Object> jsonAsMap = new HashMap<String, Object>();
        jsonAsMap.put("name", "morpheus");
        given()
                .contentType("application/json")
                .body(jsonAsMap)
                .when()
                .post(url + "/users")
                .then()
                .statusCode(404);
    }

    @Test
    public void deleteUser200 () {
        delete(url + "users/10").then().statusCode(200);
    }

    @Test
    public void deleteUser404 () {
        delete(url + "users/11").then().statusCode(404);
    }

}

