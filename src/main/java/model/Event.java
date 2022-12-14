package model;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "events", schema = "postgres")
public class Event {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(name = "created")
    private String created;

    @Column(name = "updated")
    private String updated;

    @ToString.Exclude
    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Exclude
    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.MERGE)
    @JoinColumn(name = "file_id")
    private File file;

}
