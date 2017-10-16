package fr.istic.projet.service;

import fr.istic.projet.service.dto.SportDTO;
import java.util.List;

/**
 * Service Interface for managing Sport.
 */
public interface SportService {

    /**
     * Save a sport.
     *
     * @param sportDTO the entity to save
     * @return the persisted entity
     */
    SportDTO save(SportDTO sportDTO);

    /**
     *  Get all the sports.
     *
     *  @return the list of entities
     */
    List<SportDTO> findAll();

    /**
     *  Get the "id" sport.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    SportDTO findOne(Long id);

    /**
     *  Delete the "id" sport.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
