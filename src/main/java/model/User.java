package model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", schema = "postgres")
public class User {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;

    @ToString.Exclude
    @OneToMany(cascade = CascadeType.MERGE,
            mappedBy = "user",
            fetch = FetchType.LAZY)
    private List<Event> events;

}
