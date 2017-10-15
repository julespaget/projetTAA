package fr.istic.projet.repository;

import fr.istic.projet.domain.Sport;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

/**
 * Spring Data JPA repository for the Sport entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SportRepository extends JpaRepository<Sport, Long> {
    @Query("select distinct sport from Sport sport left join fetch sport.placeLists")
    List<Sport> findAllWithEagerRelationships();

    @Query("select sport from Sport sport left join fetch sport.placeLists where sport.id =:id")
    Sport findOneWithEagerRelationships(@Param("id") Long id);

}
