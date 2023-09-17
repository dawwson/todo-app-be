package dawwson.todoappbe.controller.dto;

import lombok.Getter;

import java.util.UUID;

@Getter
public class PostTodoData {
    private UUID id;

    public static PostTodoData of(UUID id) {
        PostTodoData postTodoData = new PostTodoData();
        postTodoData.id = id;
        return postTodoData;
    }
}
