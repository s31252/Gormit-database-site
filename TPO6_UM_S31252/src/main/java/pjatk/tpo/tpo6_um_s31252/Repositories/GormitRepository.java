package pjatk.tpo.tpo6_um_s31252.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.tpo.tpo6_um_s31252.Models.Gormit;

@Repository
public interface GormitRepository extends JpaRepository<Gormit, Integer> {
}
