package pjatk.tpo.tpo6_um_s31252.Controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pjatk.tpo.tpo6_um_s31252.Dtos.GormitDto;
import pjatk.tpo.tpo6_um_s31252.Dtos.InputGormitDto;
import pjatk.tpo.tpo6_um_s31252.Exceptions.NotFoundException;
import pjatk.tpo.tpo6_um_s31252.Models.*;
import pjatk.tpo.tpo6_um_s31252.Services.GormitService;
import pjatk.tpo.tpo6_um_s31252.Services.TribeLeaderService;
import pjatk.tpo.tpo6_um_s31252.Services.TribeMemberService;
import pjatk.tpo.tpo6_um_s31252.Services.TribeService;


import java.util.List;

@RestController
@RequestMapping("/data")
public class GormitController {

    private final GormitService gormitService;
    private final TribeService tribeService;
    private final TribeLeaderService tribeLeaderService;
    private final TribeMemberService tribeMemberService;

    @Autowired
    public GormitController(GormitService gormitService, TribeService tribeService,
                            TribeLeaderService tribeLeaderService, TribeMemberService tribeMemberService) {
        this.gormitService = gormitService;
        this.tribeService = tribeService;
        this.tribeLeaderService = tribeLeaderService;
        this.tribeMemberService = tribeMemberService;
    }

    @GetMapping
    public List<GormitDto> getAllGormits() {
        return gormitService.getAll().stream().map(this::convertToDto).toList();
    }

    @GetMapping("/{id}")
    public GormitDto getGormitById(@PathVariable int id) {
        Gormit gormit = gormitService.getById(id).orElseThrow(() -> new NotFoundException("Gormit with id " + id + " not found"));
        return convertToDto(gormit);
    }

    @GetMapping("/sorted")
    public List<GormitDto> getSorted(@RequestParam String sort) {
        return gormitService.getAllSortedBy(sort).stream()
                .map(this::convertToDto)
                .toList();
    }

    @PostMapping
    public GormitDto addGormit(@RequestBody @Valid InputGormitDto dto) {
        Tribe tribe = tribeService.getById(dto.getTribeId())
                .orElseThrow(() -> new NotFoundException("Tribe not found with id: " + dto.getTribeId()));

       Gormit gormit = dtoToEntity(dto,tribe,new Gormit());
       Gormit saved = gormitService.save(gormit);

        if (saved.getRole() == GormitRole.LEADER) {
            TribeLeader leader = new TribeLeader();
            leader.setGormit(saved);
            leader.setTribe(saved.getTribe());
            tribeLeaderService.save(leader);
        } else if (saved.getRole() == GormitRole.MEMBER) {
            TribeMember member = new TribeMember();
            member.setGormit(saved);
            member.setTribe(saved.getTribe());
            tribeMemberService.save(member);
        }

        return convertToDto(saved);
    }

    @PutMapping("/{id}")
    public GormitDto updateGormit(@PathVariable int id, @RequestBody @Valid InputGormitDto dto) {
        Tribe tribe = tribeService.getById(dto.getTribeId())
                .orElseThrow(() -> new NotFoundException("Tribe not found"));

        Gormit gormit = gormitService.getById(id)
                .orElseThrow(() -> new NotFoundException("Gormit not found"));

        Gormit updated = dtoToEntity(dto,tribe,gormit);
        return convertToDto(gormitService.save(updated));
    }

    @DeleteMapping("/{id}")
    public String deleteGormit(@PathVariable int id) {
        gormitService.delete(id);
        return("Deleted Gormit with id " + id);
    }

    private GormitDto convertToDto(Gormit gormit) {
        GormitDto dto = new GormitDto();
        dto.setId(gormit.getId());
        dto.setName(gormit.getName());
        dto.setTitle(gormit.getTitle());
        dto.setFirstAppearance(gormit.getFirstAppearance());
        dto.setDescription(gormit.getDescription());
        dto.setRole(gormit.getRole());
        dto.setImageName(gormit.getImageName());
        if (gormit.getTribe() != null) {
            dto.setTribeName(gormit.getTribe().getName());
        }
        return dto;
    }
    private Gormit dtoToEntity(InputGormitDto dto, Tribe tribe, Gormit gormit) {
        gormit.setName(dto.getName());
        gormit.setTitle(dto.getTitle());
        gormit.setFirstAppearance(dto.getFirstAppearance());
        gormit.setDescription(dto.getDescription());
        gormit.setTribe(tribe);
        gormit.setRole(dto.getRole());
        gormit.setImageName(dto.getImageName());
        return gormit;
    }

}
