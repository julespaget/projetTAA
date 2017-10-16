package fr.istic.projet.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.projet.service.WeatherService;
import fr.istic.projet.web.rest.util.HeaderUtil;
import fr.istic.projet.service.dto.WeatherDTO;
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
 * REST controller for managing Weather.
 */
@RestController
@RequestMapping("/api")
public class WeatherResource {

    private final Logger log = LoggerFactory.getLogger(WeatherResource.class);

    private static final String ENTITY_NAME = "weather";

    private final WeatherService weatherService;

    public WeatherResource(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * POST  /weathers : Create a new weather.
     *
     * @param weatherDTO the weatherDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new weatherDTO, or with status 400 (Bad Request) if the weather has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/weathers")
    @Timed
    public ResponseEntity<WeatherDTO> createWeather(@RequestBody WeatherDTO weatherDTO) throws URISyntaxException {
        log.debug("REST request to save Weather : {}", weatherDTO);
        if (weatherDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new weather cannot already have an ID")).body(null);
        }
        WeatherDTO result = weatherService.save(weatherDTO);
        return ResponseEntity.created(new URI("/api/weathers/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /weathers : Updates an existing weather.
     *
     * @param weatherDTO the weatherDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated weatherDTO,
     * or with status 400 (Bad Request) if the weatherDTO is not valid,
     * or with status 500 (Internal Server Error) if the weatherDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/weathers")
    @Timed
    public ResponseEntity<WeatherDTO> updateWeather(@RequestBody WeatherDTO weatherDTO) throws URISyntaxException {
        log.debug("REST request to update Weather : {}", weatherDTO);
        if (weatherDTO.getId() == null) {
            return createWeather(weatherDTO);
        }
        WeatherDTO result = weatherService.save(weatherDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, weatherDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /weathers : get all the weathers.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of weathers in body
     */
    @GetMapping("/weathers")
    @Timed
    public List<WeatherDTO> getAllWeathers() {
        log.debug("REST request to get all Weathers");
        return weatherService.findAll();
        }

    /**
     * GET  /weathers/:id : get the "id" weather.
     *
     * @param id the id of the weatherDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the weatherDTO, or with status 404 (Not Found)
     */
    @GetMapping("/weathers/{id}")
    @Timed
    public ResponseEntity<WeatherDTO> getWeather(@PathVariable Long id) {
        log.debug("REST request to get Weather : {}", id);
        WeatherDTO weatherDTO = weatherService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(weatherDTO));
    }

    /**
     * DELETE  /weathers/:id : delete the "id" weather.
     *
     * @param id the id of the weatherDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/weathers/{id}")
    @Timed
    public ResponseEntity<Void> deleteWeather(@PathVariable Long id) {
        log.debug("REST request to delete Weather : {}", id);
        weatherService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
