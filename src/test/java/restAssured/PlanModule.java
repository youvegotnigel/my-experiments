package restAssured;

import base.BaseTests;
import com.listener.ListenerClass;
import com.log.Log;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneOffset;

import static io.restassured.RestAssured.given;

@Listeners({ListenerClass.class})
/** comment this out when running from testNG.xml **/
public class PlanModule extends BaseTests {

    @DataProvider(name = "factoryDataProvider")
    public static Object[][] getFactoryData() {
        return new Object[][]{
                {"ugl-ftml-unit1"},
                {"ant-aal-aepz"},
                {"bhg-knit"},
                {"sgb-clt1-ssl"},
                {"mfg-cls-ftry"},
                {"bgd-sgu-stl"},
                {"idn-demo-tcm"},
                {"ind-pemp-p122"},
                {"ind-clt-dgmt"},
                {"ind-sabs-c103"},
                {"ind-cls-gns"},
                {"ind-pemp-p78"},
                {"ind-rad-u186"},
                {"ind-rad-u413"},
                {"ind-para-b40"},
                {"ind-para-b35"},
                {"ind-para-c85"},
                {"ind-para-e3"},
                {"ind-para-e16"},
                {"ind-pemp-p13"},
                {"ind-well-b43"},
                {"idn-nir-alab"},
                {"ind-goki-uho"},
                {"ind-nee-tee"},
                {"bgd-dmn-dal"},
                {"ind-brij-dsn"},
                {"sri-bro-kid"},
                {"ind-afl-unt3"},
                {"ind-mat-clo"},
                {"ind-sabs-d247"},
                {"ind-sabs-f122"},
                {"ind-pemp-imt"},
                {"bgd-bxm-esp"},
                {"ind-kg1-dnim"},
                {"ind-bkf-s67"},
                {"ind-ncj-john"},
                {"idn-rtx-ptri"},
                {"bgd-kac-kfw"},
                {"ind-knit-snma"},
                {"bgd-hop-int"},
                {"ugl-ftml-uhm"},
                {"ind-tef-evf"},
                {"ugl-ftml-unt2"},
                {"idn-hari-solo"},
                {"idn-mod-ung"},
                {"ind-tex-tas"},
                {"bgd-luck-star"},
                {"idn-ptpb-teo"},
                {"idn-ptpb-teo4"},
                {"idn-ptpb-teo6"}
        };
    }

    @Test(dataProvider = "factoryDataProvider")
    public void plan_template_download(String subjectKey) {

        Log.startTestCase("Verify plan template download");

        given().header("Content-Type", "application/json")
                .pathParam("subjectKey", subjectKey)
                .get("https://oneapp.ncinga.com/core_services/api/v1/plan/template/bySubjectKey/{subjectKey}")
                .then()
                .statusCode(200);

        Log.endTestCase("Verify plan template download");
    }

    @Test(dataProvider = "factoryDataProvider")
    public void plan_list_v1(String subjectKey) {

        Log.startTestCase("Verify plan list v1");

        //LocalDateTime startOfDay = LocalDate.atTime(LocalTime.MAX);

        long startOfDay = LocalDate.now().atTime(LocalTime.MIN).toEpochSecond(ZoneOffset.of("+05:30"));
        long endOfDay = LocalDate.now().atTime(LocalTime.MAX).toEpochSecond(ZoneOffset.of("+05:30"));


//        System.out.println("start of day "+ startOfDay.toEpochSecond(ZoneOffset.of("+05:30")));
//        System.out.println("end of day "+ endOfDay.toEpochSecond(ZoneOffset.of("+05:30")));

//        System.out.println("start of day " + startOfDay);
//        System.out.println("end of day " + endOfDay);


        Response response = given()
                .pathParam("subjectKey",subjectKey)
                .pathParam("startTime", startOfDay)
                .pathParam("endTime",endOfDay)
                .get("https://oneapp.ncinga.com/core_services/api/v1/plan/listing?subjectKey={subjectKey}&t1={startTime}&t2={endTime}&planName=production_plan");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200); // verify status code is 200

        //System.out.println(response.prettyPrint());
        Log.endTestCase("Verify plan list v1");
    }

    @Test
    public void plan_upload(){

        File file = new File("C:\\Essentials\\NCingaFiles\\ONEAPP\\Bengal\\NE-1641\\old\\BHG_normal_plan.xlsx");

        Response response = given()
                .formParam("subjectKey","bhg-knit")
                .formParam("planName","production_plan")
                .formParam("user","test_user")
                .multiPart("file", file, "multipart/form-data")
                .post("https://oneapp.ncinga.com/core_services/api/v1/plan/upload")
                .thenReturn();

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200); // verify status code is 200

        //System.out.println(response.prettyPrint());
    }
}
