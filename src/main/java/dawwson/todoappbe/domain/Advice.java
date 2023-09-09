package dawwson.todoappbe.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Entity
@Getter
public class Advice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ADVICE_ID")
    private UUID id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String author;

}
