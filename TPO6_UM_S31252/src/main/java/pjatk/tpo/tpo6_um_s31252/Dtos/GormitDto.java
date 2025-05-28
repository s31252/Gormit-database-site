package pjatk.tpo.tpo6_um_s31252.Dtos;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import pjatk.tpo.tpo6_um_s31252.Models.GormitRole;

@Getter
@Setter
public class GormitDto {
    private int id;
    private String name;
    private String title;
    private String firstAppearance;
    private String tribeName;
    @JsonIgnore
    private int tribeId;
    private GormitRole role;
    private String description;
    @JsonIgnore
    private String imageName;
}
