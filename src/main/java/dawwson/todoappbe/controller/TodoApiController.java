package dawwson.todoappbe.controller;

import dawwson.todoappbe.controller.dto.*;
import dawwson.todoappbe.domain.Todo;
import dawwson.todoappbe.service.TodoService;
import dawwson.todoappbe.service.dto.UpdateTodoDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TodoApiController {

    private final TodoService todoService;

    @GetMapping("/api/v1/todos")
    public Response<List<GetTodosData>> getTodos() {
        List<Todo> todos = todoService.findTodos();
        List<GetTodosData> collect = todos
                .stream()
                .map(GetTodosData::of)
                .toList();

        return Response.<List<GetTodosData>>builder()
                .code(200)
                .message("Todo list is loaded successfully")
                .data(collect)
                .build();
    }

    @PostMapping("/api/v1/todos")
    @ResponseStatus(HttpStatus.CREATED)
    public Response<PostTodoData> postTodos(
            @RequestBody @Valid PostTodoRequest request
    ) {
        UUID newTodoId = todoService.createTodo(request.getContent());

        return Response.<PostTodoData>builder()
                .code(201)
                .message("Todo is created successfully")
                .data(PostTodoData.of(newTodoId))
                .build();
    }

    @PatchMapping("/api/v1/todos/{id}")
    public Response patchTodo(
            @PathVariable("id") UUID todoId,
            @RequestBody @Valid PatchTodoRequest request
    ) {
        todoService.updateTodo(todoId, new UpdateTodoDto(request.getIsDone(), request.getContent()));
        return Response.builder()
                .code(200)
                .message("Todo is updated successfully")
                .data(null)
                .build();
    }

    @DeleteMapping("/api/v1/todos/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTodo(
            @PathVariable("id") UUID todoId
    ) {
        todoService.deleteTodo(todoId);
    }
}
