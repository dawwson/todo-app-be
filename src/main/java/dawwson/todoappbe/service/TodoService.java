package dawwson.todoappbe.service;

import dawwson.todoappbe.domain.Todo;
import dawwson.todoappbe.repository.TodoRepository;
import dawwson.todoappbe.service.dto.UpdateTodoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TodoService {
    private final TodoRepository todoRepository;

    /**
     * Todo 생성
     * @param content Todo 내용
     * @return 생성된 todo의 id
     */
    @Transactional
    public UUID createTodo(String content) {
        Todo todo = Todo.create(content);
        todoRepository.save(todo);

        return todo.getId();  // 영속성 컨텍스트가 persist() 호출시 PK 획득
    }

    /**
     * Todo 조회
     * @return Todo 리스트
     */
    public List<Todo> findTodos() {
        return todoRepository.findAll();
    }

    /**
     * Todo 수정
     * @param id 수정할 Todo id
     * @param updateTodoDto 수정할 요소들
     */
    @Transactional
    public void updateTodo(UUID id, UpdateTodoDto updateTodoDto) {
        Todo todo = todoRepository.findById(id);
        todo.update(updateTodoDto);
    }

    /**
     * Todo 삭제
     * @param id 삭제할 Todo id
     */
    @Transactional
    public void deleteTodo(UUID id) {
        Todo todo = todoRepository.findById(id);
        todoRepository.delete(todo);
    }
}
