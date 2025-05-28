package pjatk.tpo.tpo6_um_s31252.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pjatk.tpo.tpo6_um_s31252.Models.Gormit;
import pjatk.tpo.tpo6_um_s31252.Models.TribeMember;

import java.util.List;


public interface TribeMemberRepository extends JpaRepository<TribeMember, Integer> {
    List<TribeMember> findByGormit(Gormit gormit);

}
