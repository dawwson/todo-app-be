package dawwson.todoappbe.service;

import dawwson.todoappbe.domain.Todo;
import dawwson.todoappbe.repository.TodoRepository;
import dawwson.todoappbe.service.dto.UpdateTodoDto;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
class TodoServiceTest {  // TODO: 각 테스트 돌릴 때마다 seed 하는 방법
    @Autowired TodoRepository todoRepository;  // TODO: mock repository로 교체
    @Autowired TodoService todoService;
    @Autowired EntityManager em;

    @Test
    void 할_일_생성() {
        // given
        // TODO: 유저 생성

        String testContent = "새로운 할 일";

        // when
        UUID savedTodoId = todoService.createTodo(testContent);

        // then
        Todo found = todoRepository.findById(savedTodoId);

        assertThat(found.getId()).isEqualTo(savedTodoId);
        assertThat(found.getContent()).isEqualTo(testContent);
        assertThat(found.getIsDone()).isFalse();
    }

    @Test
    void 모든_할_일_조회() {
        // given
        // TODO: 유저 생성

        Todo todoA = Todo.create("새로운 할 일1");
        Todo todoB = Todo.create("새로운 할 일2");

        todoRepository.save(todoA);
        todoRepository.save(todoB);

        // when
        List<Todo> todos = todoService.findTodos();

        // then
        assertThat(todos.size()).isEqualTo(2);
    }

    @Test
    void 특정_할_일_수정() {
        // given
        // TODO: 유저 생성
        Todo todoA = Todo.create("새로운 할 일1");
        todoRepository.save(todoA);

        // when
        todoService.updateTodo(todoA.getId(), new UpdateTodoDto(true));

        // then
        Todo found = todoRepository.findById(todoA.getId());
        assertThat(found.getIsDone()).isTrue();
    }

    @Test
    void 특정_할_일_삭제() {

        // given
        // TODO: 유저 생성
        Todo todoA = Todo.create("새로운 할 일1");
        todoRepository.save(todoA);

        // when
        todoService.deleteTodo(todoA.getId());

        // then
        Todo found = todoRepository.findById(todoA.getId());
        assertThat(found).isNull();
    }
}