package pjatk.tpo.tpo6_um_s31252.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pjatk.tpo.tpo6_um_s31252.Models.Tribe;

@Repository
public interface TribeRepository extends JpaRepository<Tribe,Integer> {
}
