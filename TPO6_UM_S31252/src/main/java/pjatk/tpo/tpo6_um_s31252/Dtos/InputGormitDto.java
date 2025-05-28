package pjatk.tpo.tpo6_um_s31252.Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import pjatk.tpo.tpo6_um_s31252.Models.GormitRole;

@Getter
@Setter
public class InputGormitDto {
    private int id;
    @NotBlank(message = "Name is required")
    private String name;
    private String title;
    private String firstAppearance;
    @NotNull(message = "Tribe Id is required")
    private Integer tribeId;
    @NotNull(message = "Role is required")
    private GormitRole role;
    private String description;
    private String imageName;
}
