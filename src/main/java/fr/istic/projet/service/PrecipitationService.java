package fr.istic.projet.service;

import fr.istic.projet.service.dto.PrecipitationDTO;
import java.util.List;

/**
 * Service Interface for managing Precipitation.
 */
public interface PrecipitationService {

    /**
     * Save a precipitation.
     *
     * @param precipitationDTO the entity to save
     * @return the persisted entity
     */
    PrecipitationDTO save(PrecipitationDTO precipitationDTO);

    /**
     *  Get all the precipitations.
     *
     *  @return the list of entities
     */
    List<PrecipitationDTO> findAll();

    /**
     *  Get the "id" precipitation.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    PrecipitationDTO findOne(Long id);

    /**
     *  Delete the "id" precipitation.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
