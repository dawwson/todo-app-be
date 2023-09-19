package dawwson.todoappbe.controller;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.*;

// TODO: 데이터 초기화 안 되는 문제 해결
@SpringBootTest
class TodoApiControllerTest {
    @Autowired EntityManager em;

    @Test
    void 할_일_추가하기_성공() {
        // TODO: User 생성
        Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("content", "테스트임");

        RestAssured
                // given
                .given().log().all()
                .body(bodyMap)
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