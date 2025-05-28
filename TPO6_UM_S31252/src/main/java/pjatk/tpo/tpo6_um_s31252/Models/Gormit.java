package pjatk.tpo.tpo6_um_s31252.Models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Gormit {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;
  private String title;
  private String firstAppearance;
  @Column(length = 1000)
  private String description;

  @Enumerated(EnumType.STRING)
  private GormitRole role;

  @ManyToOne
  @JoinColumn(name = "tribe_id")
  private Tribe tribe;
  private String imageName;
}
