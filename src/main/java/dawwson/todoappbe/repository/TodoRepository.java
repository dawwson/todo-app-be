package dawwson.todoappbe.repository;

import dawwson.todoappbe.domain.Todo;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class TodoRepository {

    private final EntityManager em;

    public Todo findById(UUID id) {
        return em.find(Todo.class, id);
    }

    // TODO: userId로 조회
    public List<Todo> findAll() {
        String jpql = "select t from Todo t";

        return em
                .createQuery(jpql, Todo.class)
                .getResultList();
    }

    public void save(Todo todo) {
        em.persist(todo);
    }

    public void delete(Todo todo) {
        em.remove(todo);
    }
}
