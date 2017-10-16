package fr.istic.projet.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.projet.service.WeatherRequirementsService;
import fr.istic.projet.web.rest.util.HeaderUtil;
import fr.istic.projet.service.dto.WeatherRequirementsDTO;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing WeatherRequirements.
 */
@RestController
@RequestMapping("/api")
public class WeatherRequirementsResource {

    private final Logger log = LoggerFactory.getLogger(WeatherRequirementsResource.class);

    private static final String ENTITY_NAME = "weatherRequirements";

    private final WeatherRequirementsService weatherRequirementsService;

    public WeatherRequirementsResource(WeatherRequirementsService weatherRequirementsService) {
        this.weatherRequirementsService = weatherRequirementsService;
    }

    /**
     * POST  /weather-requirements : Create a new weatherRequirements.
     *
     * @param weatherRequirementsDTO the weatherRequirementsDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new weatherRequirementsDTO, or with status 400 (Bad Request) if the weatherRequirements has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/weather-requirements")
    @Timed
    public ResponseEntity<WeatherRequirementsDTO> createWeatherRequirements(@RequestBody WeatherRequirementsDTO weatherRequirementsDTO) throws URISyntaxException {
        log.debug("REST request to save WeatherRequirements : {}", weatherRequirementsDTO);
        if (weatherRequirementsDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new weatherRequirements cannot already have an ID")).body(null);
        }
        WeatherRequirementsDTO result = weatherRequirementsService.save(weatherRequirementsDTO);
        return ResponseEntity.created(new URI("/api/weather-requirements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /weather-requirements : Updates an existing weatherRequirements.
     *
     * @param weatherRequirementsDTO the weatherRequirementsDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated weatherRequirementsDTO,
     * or with status 400 (Bad Request) if the weatherRequirementsDTO is not valid,
     * or with status 500 (Internal Server Error) if the weatherRequirementsDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/weather-requirements")
    @Timed
    public ResponseEntity<WeatherRequirementsDTO> updateWeatherRequirements(@RequestBody WeatherRequirementsDTO weatherRequirementsDTO) throws URISyntaxException {
        log.debug("REST request to update WeatherRequirements : {}", weatherRequirementsDTO);
        if (weatherRequirementsDTO.getId() == null) {
            return createWeatherRequirements(weatherRequirementsDTO);
        }
        WeatherRequirementsDTO result = weatherRequirementsService.save(weatherRequirementsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, weatherRequirementsDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /weather-requirements : get all the weatherRequirements.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of weatherRequirements in body
     */
    @GetMapping("/weather-requirements")
    @Timed
    public List<WeatherRequirementsDTO> getAllWeatherRequirements() {
        log.debug("REST request to get all WeatherRequirements");
        return weatherRequirementsService.findAll();
        }

    /**
     * GET  /weather-requirements/:id : get the "id" weatherRequirements.
     *
     * @param id the id of the weatherRequirementsDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the weatherRequirementsDTO, or with status 404 (Not Found)
     */
    @GetMapping("/weather-requirements/{id}")
    @Timed
    public ResponseEntity<WeatherRequirementsDTO> getWeatherRequirements(@PathVariable Long id) {
        log.debug("REST request to get WeatherRequirements : {}", id);
        WeatherRequirementsDTO weatherRequirementsDTO = weatherRequirementsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(weatherRequirementsDTO));
    }

    /**
     * DELETE  /weather-requirements/:id : delete the "id" weatherRequirements.
     *
     * @param id the id of the weatherRequirementsDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/weather-requirements/{id}")
    @Timed
    public ResponseEntity<Void> deleteWeatherRequirements(@PathVariable Long id) {
        log.debug("REST request to delete WeatherRequirements : {}", id);
        weatherRequirementsService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
