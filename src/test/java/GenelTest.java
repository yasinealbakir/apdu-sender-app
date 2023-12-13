import Pojo.PostClass;
import io.restassured.http.ContentType;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GenelTest {

    public JSONObject readJsonFile(String file) throws FileNotFoundException {
        File jsonFile = new File(file);
        FileReader fileReader = new FileReader(jsonFile);
        JSONTokener jsonTokener = new JSONTokener(fileReader);
        JSONObject jsonData = new JSONObject(jsonTokener);
        return jsonData;
    }

    @Test(priority = 1, testName = "TCKK Başvuru Id'ye Göre Kart Tipi Kontrolü")
    public void checkTckkCardTypeTest() {
        // Hashmap using
        HashMap data = new HashMap();
        data.put("basvuruId", "200000427");
        given()
                .contentType(ContentType.JSON)
                .body(data)
                .when()
                .post("http://localhost:7001/api/TckkNBSMachine/GetApplyKeyValue")

                .then()
                .body("cardType", equalTo("TCKK"))
                .body("isContactCoding", equalTo(true))
                .body("isContactLessCoding", equalTo(true))
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 1, testName = "Mavi Kart Başvuru Id'ye Göre Kart Tipi Kontrolü")
    public void checkBlueCardTypeTest() {
        // org.json library using
        JSONObject jsonData = new JSONObject();
        jsonData.put("basvuruId", "200000000226");

        given()
                .contentType(ContentType.JSON)
                .body(jsonData.toString()) //data stringe çevrilmezse hata alınıyor.
                .when()
                .post("http://localhost:7001/api/TckkNBSMachine/GetApplyKeyValue")

                .then()
                .body("cardType", equalTo("TCKK"))
                .body("isContactCoding", equalTo(false))
                .body("isContactLessCoding", equalTo(true))
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 1, testName = "Meclis Kartı Başvuru Id'ye Göre Kart Tipi Kontrolü")
    public void checkMvdCardTypeTest() {
        // pojo class using
        PostClass pojoData = new PostClass();
        pojoData.setBasvuruId("200000000222");

        given()
                .contentType(ContentType.JSON)
                .body(pojoData)
                .when()
                .post("http://localhost:7001/api/TckkNBSMachine/GetApplyKeyValue")

                .then()
                .body("cardType", equalTo("TCKK"))
                .body("isContactCoding", equalTo(true))
                .body("isContactLessCoding", equalTo(false))
                .statusCode(200)
                .log().all();
    }

    @Test(priority = 1, testName = "Özel Güvenlik Kartı Başvuru Id'ye Göre Kart Tipi Kontrolü")
    public void checkSecurityCardTypeTest() throws FileNotFoundException {
        // external json file using
        given()
                .contentType(ContentType.JSON)
                .body(readJsonFile("src/test/resources/JsonFiles/body.json").toString())
                .when()
                .post("http://localhost:7001/api/TckkNBSMachine/GetApplyKeyValue")

                .then()
                .body("cardType", equalTo("TCKK"))
                .body("isContactCoding", equalTo(false))
                .body("isContactLessCoding", equalTo(false))
                .statusCode(200)
                .log().all();
    }

    @Test
    public void queryAndPathParamTest() {
        //https://reqres.in/api/users?page=2&id=5
        given()
                .pathParam("mypath", "users")
                .queryParam("page", 2)
                .queryParam("id", 5)
                .when()
                .get("https://reqres.in/api/{mypath}?/page={page}&id={id}")
                .then()
                .statusCode(200);

    }
}