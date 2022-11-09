package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "events", schema = "postgres")
public class Event {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;

    @Column(name = "created")
    private String created;

    @Column(name = "updated")
    private String updated;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    @JoinColumn(name = "file_id")
    private File file;

}
