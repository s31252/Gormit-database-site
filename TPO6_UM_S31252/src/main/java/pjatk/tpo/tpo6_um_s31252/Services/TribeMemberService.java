package pjatk.tpo.tpo6_um_s31252.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pjatk.tpo.tpo6_um_s31252.Models.TribeMember;
import pjatk.tpo.tpo6_um_s31252.Repositories.TribeMemberRepository;


@Service
public class TribeMemberService {
    private final TribeMemberRepository repository;

    @Autowired
    public TribeMemberService(TribeMemberRepository repository) {
        this.repository = repository;
    }
    public TribeMember save(TribeMember member) {
        return repository.save(member);
    }

}
