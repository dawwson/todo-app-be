package dawwson.todoappbe.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class PostTodoRequest {
    // TODO: String이 아닌 값이어도 String으로 형변환 되는 이유는??
    @NotBlank
    private String content;
}
