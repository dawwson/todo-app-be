package dawwson.todoappbe.controller;

import dawwson.todoappbe.controller.dto.GetTodosData;
import dawwson.todoappbe.controller.dto.Response;
import dawwson.todoappbe.domain.Todo;
import dawwson.todoappbe.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
}
