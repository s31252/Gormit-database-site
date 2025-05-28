package pjatk.tpo.tpo6_um_s31252.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.tpo.tpo6_um_s31252.Models.Gormit;
import pjatk.tpo.tpo6_um_s31252.Models.TribeLeader;

import java.util.List;

@Repository
public interface TribeLeaderRepository extends JpaRepository<TribeLeader, Integer> {
    List<TribeLeader> findByGormit(Gormit gormit);

}
