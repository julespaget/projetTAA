package fr.istic.projet.service;

import fr.istic.projet.service.dto.WeatherDTO;
import java.util.List;

/**
 * Service Interface for managing Weather.
 */
public interface WeatherService {

    /**
     * Save a weather.
     *
     * @param weatherDTO the entity to save
     * @return the persisted entity
     */
    WeatherDTO save(WeatherDTO weatherDTO);

    /**
     *  Get all the weathers.
     *
     *  @return the list of entities
     */
    List<WeatherDTO> findAll();

    /**
     *  Get the "id" weather.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    WeatherDTO findOne(Long id);

    /**
     *  Delete the "id" weather.
     *
     *  @param id the id of the entity
     */
    void delete(Long id);
}
