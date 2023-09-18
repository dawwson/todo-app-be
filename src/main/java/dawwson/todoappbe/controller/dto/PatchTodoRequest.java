package dawwson.todoappbe.controller.dto;

import lombok.Getter;

@Getter
public class PatchTodoRequest {
    private Boolean isDone;
    private String content;
}
