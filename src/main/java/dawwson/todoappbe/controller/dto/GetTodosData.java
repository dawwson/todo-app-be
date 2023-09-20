package dawwson.todoappbe.controller.dto;

import dawwson.todoappbe.domain.Todo;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
public class GetTodosData {
    private UUID id;
    private Boolean isDone;
    private String content;
    private LocalDateTime createdAt;

    public static GetTodosData of(Todo todo) {
        GetTodosData getTodosData = new GetTodosData();
        getTodosData.id = todo.getId();
        getTodosData.isDone = todo.getIsDone();
        getTodosData.content = todo.getContent();
        getTodosData.createdAt = todo.getCreatedAt();

        return getTodosData;
    }
}
