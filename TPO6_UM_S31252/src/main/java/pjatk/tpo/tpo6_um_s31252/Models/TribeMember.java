package pjatk.tpo.tpo6_um_s31252.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TribeMember {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Gormit gormit;

    @ManyToOne
    private Tribe tribe;
}
