package dawwson.todoappbe.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@Sql(scripts = {"/sql/insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/sql/truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class TodoApiControllerTest {

    @BeforeAll
    static void setUp() {
        RestAssured.baseURI = "http://127.0.0.1:8080/api/v1";
    }

    @Test
    void 할_일_조회_성공하면_200_응답을_보낸다() {
        // given

        // when
        RequestSpecification request = RestAssured.given();
        Response response = request.get("/todos");

        // then
        response.then()
                .assertThat()
                .statusCode(200);
        response.then()
                .assertThat()
                .contentType(ContentType.JSON);
        response.then()
                .assertThat()
                .body("code", is(200))
                .body("message", isA(String.class))
                .body("data.size()", is(2));

        response.then()
                .assertThat()
                .body("data[0].id", isA(String.class))
                .body("data[0].isDone", isA(Boolean.class))
                .body("data[0].content", isA(String.class))
                .body("data[0].createdAt", isA(String.class))
                .body("data[1].id", isA(String.class))
                .body("data[1].isDone", isA(Boolean.class))
                .body("data[1].content", isA(String.class))
                .body("data[1].createdAt", isA(String.class));
    }

    @Test
    void 할_일_추가_성공하면_201_응답을_보낸다() throws JSONException {
        // given
        JSONObject requestBody = new JSONObject();
        requestBody.put("content", "이것은 테스트");

        // when
        RequestSpecification request = RestAssured.given();
        Response response = request
                .body(requestBody.toString())
                .contentType("application/json")
                .post("/todos");

        // then
        response.then()
                .assertThat()
                .statusCode(201);
        response.then()
                .contentType(ContentType.JSON);
        response.then()
                .body("code", is(201))
                .body("message", isA(String.class))
                .body("data.id", isA(String.class));
    }

    @Test
    void 할_일_수정_성공하면_200_응답을_보낸다() throws Exception {
        // given
        String testTodoId = "fec21270-8704-460a-aec4-0d324efeb990";

        JSONObject requestBody = new JSONObject();
        requestBody.put("isDone", true);
        requestBody.put("content", "This is test content");

        // when
        RequestSpecification request = RestAssured.given();
        Response response = request
                .body(requestBody.toString())
                .contentType("application/json")
                .patch("/todos/{todoId}", testTodoId);

        // then
        response.then()
                .statusCode(200);
        response.then()
                .contentType(ContentType.JSON);
        response.then()
                .body("code", is(200))
                .body("message", isA(String.class))
                .body("data", nullValue());
    }

    @Test
    void 할_일_삭제_성공하면_204_응답을_보낸다() {
        // given
        String testTodoId = "fec21270-8704-460a-aec4-0d324efeb990";

        // when
        RequestSpecification request = RestAssured.given();
        Response response = request
                .delete("/todos/{todoId}", testTodoId);

        // then
        response.then()
                .assertThat()
                .statusCode(204);
    }
}