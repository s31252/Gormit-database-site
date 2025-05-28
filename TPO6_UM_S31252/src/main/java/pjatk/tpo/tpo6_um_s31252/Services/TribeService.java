package pjatk.tpo.tpo6_um_s31252.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.tpo.tpo6_um_s31252.Models.Tribe;
import pjatk.tpo.tpo6_um_s31252.Models.TribeLeader;
import pjatk.tpo.tpo6_um_s31252.Models.TribeMember;
import pjatk.tpo.tpo6_um_s31252.Repositories.TribeLeaderRepository;
import pjatk.tpo.tpo6_um_s31252.Repositories.TribeMemberRepository;
import pjatk.tpo.tpo6_um_s31252.Repositories.TribeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TribeService {
    private final TribeRepository repository;

    @Autowired
    public TribeService(TribeRepository repository) {
        this.repository = repository;
    }
    public List<Tribe> getAll() {
        return repository.findAll();
    }

    public Optional<Tribe> getById(int id) {
        return repository.findById(id);
    }
}
