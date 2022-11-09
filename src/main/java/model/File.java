package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "files", schema = "postgres")
public class File {

    @Id
    @GeneratedValue(generator = "increment")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "url")
    private String url;

    @OneToOne(
            mappedBy = "file",
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL)
    private Event event;

}
