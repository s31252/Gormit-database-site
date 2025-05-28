package pjatk.tpo.tpo6_um_s31252.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.tpo.tpo6_um_s31252.Models.TribeLeader;
import pjatk.tpo.tpo6_um_s31252.Repositories.TribeLeaderRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TribeLeaderService {
    private final TribeLeaderRepository repository;

    @Autowired
    public TribeLeaderService(TribeLeaderRepository repository) {
        this.repository = repository;
    }
    public TribeLeader save(TribeLeader leader) {
        return repository.save(leader);
    }
}
