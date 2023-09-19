package dawwson.todoappbe.controller;

import dawwson.todoappbe.domain.Todo;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.*;

@SpringBootTest
@Sql(scripts = {"/sql/insert.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = {"/sql/truncate.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class TodoApiControllerTest {
    @Autowired EntityManager em;

    @Test
    void 할_일_추가하기_성공() throws JSONException {
        // TODO: User 생성
        JSONObject requestBody = new JSONObject();
        requestBody.put("content", "이것은 테스트");

        RestAssured
                // given
                .given().log().all()
                .body(requestBody.toString())
                .contentType(ContentType.JSON)
                // when
                .when()
                .post("/api/v1/todos")
                // then
                .then().log().all()
                    .assertThat()
                        .statusCode(201)
                        .contentType(ContentType.JSON)
                        .body("code", is(201))
                        .body("message", isA(String.class))
                        .body("data.id", isA(String.class));
    }
}