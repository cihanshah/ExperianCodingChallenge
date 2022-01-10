package StepDefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.sl.In;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasSize;

public class MyStepdefs {

    @Given("the user creates a GET request returns expected user list and verifies the status code")
    public void the_user_creates_a_GET_request_and_verifies_the_response()throws Throwable {
        baseURI="https://reqres.in/";

        baseURI="https://reqres.in";
        Response response = given().
                when().get("/api/users");

        JsonPath jsonPath = response.jsonPath();

        response.prettyPrint();
        Integer [] arr={1,2,3,4};
        List<Integer> listOfIdsExpected=new ArrayList<>(Arrays.asList(arr));

        List<Integer> listofIdsActual= jsonPath.getList("data.id");

        //assert statuscode 200
        Assert.assertEquals(200, response.statusCode());

        //assert data size
        response.then().assertThat().body("data",hasSize(4));

        //assert id values
        Assert.assertEquals(listOfIdsExpected,listofIdsActual);
    }



    @Given("the user creates a GET request and verifies the welcome message")
    public void the_user_creates_a_POST_request_and_verifies_the_response() throws Throwable{

        baseURI="https://reqres.in/";

        baseURI="https://reqres.in";
        Response response = given().
                when().get("/api/users/2");

        JsonPath jsonPath = response.jsonPath();

        String welcomeMessage= jsonPath.getString("support.text");

        //assert statuscode 200
        Assert.assertEquals(200, response.statusCode());

        Assert.assertFalse(welcomeMessage.isEmpty())
    }

    @Given("the user creates a GET request and verifies the status code for non-existent user")
    public void the_user_creates_a_POST_request_and_verifies_the_response() throws Throwable{

        baseURI="https://reqres.in/";

        baseURI="https://reqres.in";
        Response response = given().
                when().get("/api/users/23");

        //assert statuscode 200
        Assert.assertEquals(404, response.statusCode());

    }

}