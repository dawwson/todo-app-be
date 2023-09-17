package dawwson.todoappbe.domain;

import dawwson.todoappbe.service.dto.UpdateTodoDto;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.ColumnDefault;

import java.util.UUID;

@Entity
@Getter
public class Todo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "TODO_ID")
    private UUID id;

    // 임시 주석 처리 -> user 도메인 로직 만들고 해제
    //@ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
    //@JoinColumn(name = "USER_ID", nullable = false)  // 연관관계 주인
    //private User user;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDone;

    @Column(nullable = false)
    private String content;

    /* 생성 메서드 */
    public static Todo create(String content) {
        Todo todo = new Todo();
        //todo.user = user;
        todo.isDone = false;
        todo.content = content;

        return todo;
    }

    /* 수정 메서드 */
    public void update(UpdateTodoDto updateTodoDto) {
        if (updateTodoDto.getIsDone() != null) {
            this.isDone = updateTodoDto.getIsDone();
        }
        if (updateTodoDto.getContent() != null) {
            this.content = updateTodoDto.getContent();
        }
    }
}
