package dawwson.todoappbe.domain;

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

    @ManyToOne(fetch = FetchType.LAZY)  // 지연로딩
    @JoinColumn(name = "USER_ID", nullable = false)  // 연관관계 주인
    private User user;

    @Column(nullable = false)
    @ColumnDefault("false")
    private Boolean isDone;

    @Column(nullable = false)
    private String content;
}
