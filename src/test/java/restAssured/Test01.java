package restAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;


public class Test01 {


    @Test
    public void test01(){

        Response response = get("https://reqres.in/api/users?page=2");
        response.getStatusCode();
        System.out.println(response.getStatusCode());
    }

    @Test
    public void test02(){
        given().get("https://reqres.in/api/users?page=2").then().statusCode(200);
        given().get("https://reqres.in/api/users?page=2").then().body("data.id[0]", equalTo(7));
    }

    @Test
    public void test03(){
        given().get("https://reqres.in/api/users?page=2").then().statusCode(200);
        given().get("https://reqres.in/api/users?page=2").then().body("data.id[0]", equalTo(7));
        given().get("https://reqres.in/api/users?page=2").then()
                .body("data.id[0]", equalTo(7))
                .body("total",equalTo(12))
                .body("per_page",equalTo(6))
                .body("data.first_name", hasItems("Michael", "Lindsay", "Rachel"))
                .body("data.first_name[0]",equalTo("Michael"));
    }

    @Test
    public void test_04(){

        JSONObject request = new JSONObject();
        baseURI = "https://nfactorylive.ncinga.com";

        request.put("subject_key","mfg-cls-ftry");
        request.put("date","2020-09-19");

        given().
                body(request.toJSONString()).
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                when().
                post("/app_shift_services/v2/getschedulesforsubjectkey").
                then().
                statusCode(200)
                .body("schedule.duration[0]",hasItems("60"))
                        .log().all();
    }


    @Test
    public void sclTokenTest(){

        JSONObject request = new JSONObject();
        baseURI = "https://oneapp.ncinga.com";

        request.put("username","Zilingo");
        request.put("password","upb6k7");
        request.put("type","auth");


//        given().header("Content-Type","application/json").
//                body(request.toJSONString()).when().post("https://oneapp.ncinga.com/rest_services/post_service").then().statusCode(200)
//                .body("userId", equalTo("Zilingo"))
//                .log().all();


                given().header("Content-Type","application/json")
                        .body(request.toJSONString())
                        .when().post("https://oneapp.ncinga.com/rest_services/post_service").then().statusCode(200)
                        .body("userId", equalTo("Zilingo"));

    }
}
