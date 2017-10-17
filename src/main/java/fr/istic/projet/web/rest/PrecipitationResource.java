package fr.istic.projet.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.projet.service.PrecipitationService;
import fr.istic.projet.web.rest.util.HeaderUtil;
import fr.istic.projet.service.dto.PrecipitationDTO;
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
 * REST controller for managing Precipitation.
 */
@RestController
@RequestMapping("/api")
public class PrecipitationResource {

    private final Logger log = LoggerFactory.getLogger(PrecipitationResource.class);

    private static final String ENTITY_NAME = "precipitation";

    private final PrecipitationService precipitationService;

    public PrecipitationResource(PrecipitationService precipitationService) {
        this.precipitationService = precipitationService;
    }

    /**
     * POST  /precipitations : Create a new precipitation.
     *
     * @param precipitationDTO the precipitationDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new precipitationDTO, or with status 400 (Bad Request) if the precipitation has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/precipitations")
    @Timed
    public ResponseEntity<PrecipitationDTO> createPrecipitation(@RequestBody PrecipitationDTO precipitationDTO) throws URISyntaxException {
        log.debug("REST request to save Precipitation : {}", precipitationDTO);
        if (precipitationDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new precipitation cannot already have an ID")).body(null);
        }
        PrecipitationDTO result = precipitationService.save(precipitationDTO);
        return ResponseEntity.created(new URI("/api/precipitations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /precipitations : Updates an existing precipitation.
     *
     * @param precipitationDTO the precipitationDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated precipitationDTO,
     * or with status 400 (Bad Request) if the precipitationDTO is not valid,
     * or with status 500 (Internal Server Error) if the precipitationDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/precipitations")
    @Timed
    public ResponseEntity<PrecipitationDTO> updatePrecipitation(@RequestBody PrecipitationDTO precipitationDTO) throws URISyntaxException {
        log.debug("REST request to update Precipitation : {}", precipitationDTO);
        if (precipitationDTO.getId() == null) {
            return createPrecipitation(precipitationDTO);
        }
        PrecipitationDTO result = precipitationService.save(precipitationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, precipitationDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /precipitations : get all the precipitations.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of precipitations in body
     */
    @GetMapping("/precipitations")
    @Timed
    public List<PrecipitationDTO> getAllPrecipitations() {
        log.debug("REST request to get all Precipitations");
        return precipitationService.findAll();
        }

    /**
     * GET  /precipitations/:id : get the "id" precipitation.
     *
     * @param id the id of the precipitationDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the precipitationDTO, or with status 404 (Not Found)
     */
    @GetMapping("/precipitations/{id}")
    @Timed
    public ResponseEntity<PrecipitationDTO> getPrecipitation(@PathVariable Long id) {
        log.debug("REST request to get Precipitation : {}", id);
        PrecipitationDTO precipitationDTO = precipitationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(precipitationDTO));
    }

    /**
     * DELETE  /precipitations/:id : delete the "id" precipitation.
     *
     * @param id the id of the precipitationDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/precipitations/{id}")
    @Timed
    public ResponseEntity<Void> deletePrecipitation(@PathVariable Long id) {
        log.debug("REST request to delete Precipitation : {}", id);
        precipitationService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
