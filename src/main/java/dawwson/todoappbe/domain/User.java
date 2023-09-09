package dawwson.todoappbe.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
@Table(name = "users")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "USER_ID")
    private UUID id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String nickname;

    // TODO: 필요해질 때 양방향 매핑 추가
    //@OneToMany(mappedBy = "user")
    //private List<Todo> todos = new ArrayList<>();
}
