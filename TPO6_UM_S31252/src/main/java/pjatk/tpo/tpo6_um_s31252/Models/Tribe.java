package pjatk.tpo.tpo6_um_s31252.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
@Entity
public class Tribe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "tribe", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Gormit> gormits;

}
