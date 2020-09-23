package astronauts;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Astronauts {
    @BeforeClass
    public static void setBaseUri() {
        RestAssured.baseURI = "http://api.open-notify.org/";
    } // end of BeforeClass method

    @Test
    public void getAstronauts() {
        Response response = given().when().get("/astros.json");
        response.prettyPrint();
        response.then().assertThat().statusCode(200).and()
                .body("number", is(3)).and()
                .body("people.craft", hasItems("ISS", "ISS", "ISS")).and()
                .body("people.name", hasItems("Chris Cassidy", "Anatoly Ivanishin", "Ivan Vagner")).and()
                .body("message", equalTo("success"));
    } // end of test

} // end of class Astronauts
