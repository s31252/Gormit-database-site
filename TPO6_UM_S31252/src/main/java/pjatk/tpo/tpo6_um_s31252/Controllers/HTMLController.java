package pjatk.tpo.tpo6_um_s31252.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pjatk.tpo.tpo6_um_s31252.Dtos.GormitDto;
import pjatk.tpo.tpo6_um_s31252.Dtos.InputGormitDto;
import pjatk.tpo.tpo6_um_s31252.Exceptions.NotFoundException;
import pjatk.tpo.tpo6_um_s31252.Models.*;
import pjatk.tpo.tpo6_um_s31252.Services.GormitService;
import pjatk.tpo.tpo6_um_s31252.Services.TribeService;

@Controller
public class HTMLController {

    @Autowired
    private GormitService gormitService;

    @Autowired
    private TribeService tribeService;

    @GetMapping("/gormits/add")
    public String showAddForm(Model model) {
        model.addAttribute("gormit", new InputGormitDto());
        model.addAttribute("tribes", tribeService.getAll());
        model.addAttribute("roles", GormitRole.values());
        return "add-gormit";
    }

    @PostMapping("/gormits/add")
    public String addGormit(@ModelAttribute InputGormitDto dto) {
        Tribe tribe = tribeService.getById(dto.getTribeId())
                .orElseThrow(() -> new NotFoundException("Tribe not found"));

        Gormit gormit = new Gormit();
        gormit.setName(dto.getName());
        gormit.setTitle(dto.getTitle());
        gormit.setFirstAppearance(dto.getFirstAppearance());
        gormit.setDescription(dto.getDescription());
        gormit.setTribe(tribe);
        gormit.setRole(dto.getRole());
        gormit.setImageName(dto.getImageName());

        gormitService.save(gormit);

        return "redirect:/gormits";
    }

    @GetMapping("/gormits")
    public String showAllSorted(Model model, @RequestParam(defaultValue = "id") String sort) {
        var gormici = gormitService.getAllSortedBy(sort).stream()
                .map(gormit -> {
                    GormitDto dto = new GormitDto();
                    dto.setId(gormit.getId());
                    dto.setName(gormit.getName());
                    dto.setTitle(gormit.getTitle());
                    dto.setFirstAppearance(gormit.getFirstAppearance());
                    dto.setDescription(gormit.getDescription());
                    dto.setRole(gormit.getRole());
                    if (gormit.getTribe() != null) {
                        dto.setTribeName(gormit.getTribe().getName());
                        dto.setTribeId(gormit.getTribe().getId());
                    }
                    return dto;
                }).toList();

        model.addAttribute("gormici", gormici);
        model.addAttribute("sort", sort);
        return "gormits";
    }

    @GetMapping("/gormits/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Gormit gormit = gormitService.getById(id).orElseThrow(() -> new NotFoundException("Gormit not found"));

        InputGormitDto dto = new InputGormitDto();
        dto.setName(gormit.getName());
        dto.setTitle(gormit.getTitle());
        dto.setDescription(gormit.getDescription());
        dto.setFirstAppearance(gormit.getFirstAppearance());
        dto.setRole(gormit.getRole());
        dto.setTribeId(gormit.getTribe().getId());
        dto.setId(gormit.getId());
        dto.setImageName(gormit.getImageName());
        model.addAttribute("gormit", dto);
        model.addAttribute("tribes", tribeService.getAll());
        model.addAttribute("roles", GormitRole.values());
        return "edit-gormit";
    }

    @GetMapping("/gormits/view/{id}")
    public String viewGormit(@PathVariable int id, Model model) {
        Gormit gormit = gormitService.getById(id)
                .orElseThrow(() -> new NotFoundException("Gormit not found"));
        model.addAttribute("gormit", gormit);
        return "view-gormit";
    }

    @PostMapping("/gormits/edit/{id}")
    public String updateGormit(@PathVariable int id, @ModelAttribute InputGormitDto dto) {
        Tribe tribe = tribeService.getById(dto.getTribeId())
                .orElseThrow(() -> new NotFoundException("Tribe not found"));

        Gormit gormit = gormitService.getById(id).orElseThrow(() -> new NotFoundException("Gormit not found"));
        gormit.setName(dto.getName());
        gormit.setTitle(dto.getTitle());
        gormit.setDescription(dto.getDescription());
        gormit.setFirstAppearance(dto.getFirstAppearance());
        gormit.setRole(dto.getRole());
        gormit.setTribe(tribe);
        gormit.setImageName(dto.getImageName());

        gormitService.save(gormit);
        return "redirect:/gormits";
    }


    @PostMapping("/gormits/delete/{id}")
    public String deleteGormit(@PathVariable int id) {
        gormitService.delete(id);
        return "redirect:/gormits";
    }


    @GetMapping("/tribes/view/{id}")
    public String viewTribe(@PathVariable int id, Model model) {
        Tribe tribe = tribeService.getById(id)
                .orElseThrow(() -> new NotFoundException("Tribe not found"));
        model.addAttribute("tribe", tribe);
        model.addAttribute("gormits",tribe.getGormits());
        return "view-tribe";
    }

}
