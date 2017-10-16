package fr.istic.projet.service;

import fr.istic.projet.service.dto.WeatherRequirementsDTO;
import java.util.List;

/**
 * Service Interface for managing WeatherRequirements.
 */
public interface WeatherRequirementsService {

    /**
     * Save a weatherRequirements.
     *
     * @param weatherRequirementsDTO the entity to save
     * @return the persisted entity
     */
    WeatherRequirementsDTO save(WeatherRequirementsDTO weatherRequirementsDTO);

    /**
     *  Get all the weatherRequirements.
     *
     *  @return the list of entities
     */
    List<WeatherRequirementsDTO> findAll();

    /**
     *  Get the "id" weatherRequirements.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WeatherRequirementsDTO findOne(Long id);

    /**
     *  Delete the "id" weatherRequirements.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
