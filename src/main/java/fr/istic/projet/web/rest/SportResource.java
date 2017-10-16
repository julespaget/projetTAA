package fr.istic.projet.web.rest;

import com.codahale.metrics.annotation.Timed;
import fr.istic.projet.service.SportService;
import fr.istic.projet.web.rest.util.HeaderUtil;
import fr.istic.projet.service.dto.SportDTO;
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
 * REST controller for managing Sport.
 */
@RestController
@RequestMapping("/api")
public class SportResource {

    private final Logger log = LoggerFactory.getLogger(SportResource.class);

    private static final String ENTITY_NAME = "sport";

    private final SportService sportService;

    public SportResource(SportService sportService) {
        this.sportService = sportService;
    }

    /**
     * POST  /sports : Create a new sport.
     *
     * @param sportDTO the sportDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new sportDTO, or with status 400 (Bad Request) if the sport has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/sports")
    @Timed
    public ResponseEntity<SportDTO> createSport(@RequestBody SportDTO sportDTO) throws URISyntaxException {
        log.debug("REST request to save Sport : {}", sportDTO);
        if (sportDTO.getId() != null) {
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME, "idexists", "A new sport cannot already have an ID")).body(null);
        }
        SportDTO result = sportService.save(sportDTO);
        return ResponseEntity.created(new URI("/api/sports/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /sports : Updates an existing sport.
     *
     * @param sportDTO the sportDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated sportDTO,
     * or with status 400 (Bad Request) if the sportDTO is not valid,
     * or with status 500 (Internal Server Error) if the sportDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/sports")
    @Timed
    public ResponseEntity<SportDTO> updateSport(@RequestBody SportDTO sportDTO) throws URISyntaxException {
        log.debug("REST request to update Sport : {}", sportDTO);
        if (sportDTO.getId() == null) {
            return createSport(sportDTO);
        }
        SportDTO result = sportService.save(sportDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, sportDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /sports : get all the sports.
     *
     * @return the ResponseEntity with status 200 (OK) and the list of sports in body
     */
    @GetMapping("/sports")
    @Timed
    public List<SportDTO> getAllSports() {
        log.debug("REST request to get all Sports");
        return sportService.findAll();
        }

    /**
     * GET  /sports/:id : get the "id" sport.
     *
     * @param id the id of the sportDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the sportDTO, or with status 404 (Not Found)
     */
    @GetMapping("/sports/{id}")
    @Timed
    public ResponseEntity<SportDTO> getSport(@PathVariable Long id) {
        log.debug("REST request to get Sport : {}", id);
        SportDTO sportDTO = sportService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(sportDTO));
    }

    /**
     * DELETE  /sports/:id : delete the "id" sport.
     *
     * @param id the id of the sportDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/sports/{id}")
    @Timed
    public ResponseEntity<Void> deleteSport(@PathVariable Long id) {
        log.debug("REST request to delete Sport : {}", id);
        sportService.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
