package pjatk.tpo.tpo6_um_s31252.Services;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pjatk.tpo.tpo6_um_s31252.Exceptions.NotFoundException;
import pjatk.tpo.tpo6_um_s31252.Models.Gormit;
import pjatk.tpo.tpo6_um_s31252.Models.GormitRole;
import pjatk.tpo.tpo6_um_s31252.Models.TribeLeader;
import pjatk.tpo.tpo6_um_s31252.Models.TribeMember;
import pjatk.tpo.tpo6_um_s31252.Repositories.GormitRepository;
import pjatk.tpo.tpo6_um_s31252.Repositories.TribeLeaderRepository;
import pjatk.tpo.tpo6_um_s31252.Repositories.TribeMemberRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GormitService {
    private final GormitRepository repository;

    @Autowired
    private TribeLeaderRepository tribeLeaderRepository;

    @Autowired
    private TribeMemberRepository tribeMemberRepository;

    @Autowired
    public GormitService(GormitRepository repository) {
        this.repository = repository;
    }
    public List<Gormit> getAll() {
        return repository.findAll();
    }

    public Optional<Gormit> getById(int id) {
        return repository.findById(id);
    }

    public List<Gormit> getAllSortedBy(String field) {
        return switch (field.toLowerCase()) {
            case "name" -> repository.findAll(Sort.by("name"));
            case "title" -> repository.findAll(Sort.by("title"));
            case "tribe" -> repository.findAll(Sort.by("tribe.name"));
            case "role" -> repository.findAll(Sort.by("role"));
            default -> repository.findAll(Sort.by("id"));
        };
    }

    @Transactional
    public Gormit save(Gormit gormit) {
        Gormit saved = repository.save(gormit);

        if (gormit.getRole() == GormitRole.LEADER) {
            TribeLeader leader = new TribeLeader();
            leader.setGormit(saved);
            leader.setTribe(saved.getTribe());
            tribeLeaderRepository.save(leader);
        }
        else if (gormit.getRole() == GormitRole.MEMBER) {
            TribeMember member = new TribeMember();
            member.setGormit(saved);
            member.setTribe(saved.getTribe());
            tribeMemberRepository.save(member);
        }


        return saved;
    }


    @Transactional
    public void delete(int id) {
        Gormit gormit = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Gormit not found"));

        List<TribeLeader> leaders = tribeLeaderRepository.findByGormit(gormit);
        leaders.forEach(tribeLeaderRepository::delete);

        List<TribeMember> members = tribeMemberRepository.findByGormit(gormit);
        members.forEach(tribeMemberRepository::delete);

        repository.delete(gormit);
    }


}
