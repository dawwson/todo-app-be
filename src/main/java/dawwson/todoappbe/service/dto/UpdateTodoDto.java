package dawwson.todoappbe.service.dto;

import lombok.Getter;

@Getter
public class UpdateTodoDto {
    private Boolean isDone;
    private String content;

    public UpdateTodoDto(Boolean isDone) {
        this.isDone = isDone;
    }

    public UpdateTodoDto(String content) {
        this.content = content;
    }

    public UpdateTodoDto(Boolean isDone, String content) {
        this.isDone = isDone;
        this.content = content;
    }
}
