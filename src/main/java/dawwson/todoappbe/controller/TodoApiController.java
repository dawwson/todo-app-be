package dawwson.todoappbe.controller;

import dawwson.todoappbe.controller.dto.GetTodosData;
import dawwson.todoappbe.controller.dto.PostTodoData;
import dawwson.todoappbe.controller.dto.PostTodoRequest;
import dawwson.todoappbe.controller.dto.Response;
import dawwson.todoappbe.domain.Todo;
import dawwson.todoappbe.service.TodoService;
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
}
