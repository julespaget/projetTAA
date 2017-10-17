package fr.istic.projet.repository;

import fr.istic.projet.domain.Precipitation;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the Precipitation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PrecipitationRepository extends JpaRepository<Precipitation, Long> {

}
