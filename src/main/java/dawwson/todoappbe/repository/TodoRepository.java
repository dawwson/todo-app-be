package dawwson.todoappbe.repository;

import dawwson.todoappbe.domain.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoRepository {
    List<Todo> findByUserId(UUID userId);
    void save(Todo todo);
    int updateById(UUID todoId);
    int deleteById(UUID todoId);
}
