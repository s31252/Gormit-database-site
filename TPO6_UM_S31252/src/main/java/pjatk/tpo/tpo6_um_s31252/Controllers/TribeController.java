package pjatk.tpo.tpo6_um_s31252.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pjatk.tpo.tpo6_um_s31252.Exceptions.NotFoundException;
import pjatk.tpo.tpo6_um_s31252.Models.Tribe;
import pjatk.tpo.tpo6_um_s31252.Services.TribeService;

import java.util.List;

@RestController
@RequestMapping("data/tribes")
public class TribeController {

    private final TribeService tribeService;

    @Autowired
    public TribeController(TribeService tribeService) {
        this.tribeService = tribeService;
    }
    @GetMapping
    public List<Tribe> getAllTribes() {
        return tribeService.getAll();
    }
    @GetMapping("/{id}")
    public Tribe getTribeById(@PathVariable int id) {
        return tribeService.getById(id).orElseThrow(()->new NotFoundException("Tribe not found"));
    }

}
