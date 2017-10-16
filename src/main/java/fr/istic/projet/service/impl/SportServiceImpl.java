package fr.istic.projet.service.impl;

import fr.istic.projet.service.SportService;
import fr.istic.projet.domain.Sport;
import fr.istic.projet.repository.SportRepository;
import fr.istic.projet.service.dto.SportDTO;
import fr.istic.projet.service.mapper.SportMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Sport.
 */
@Service
@Transactional
public class SportServiceImpl implements SportService{

    private final Logger log = LoggerFactory.getLogger(SportServiceImpl.class);

    private final SportRepository sportRepository;

    private final SportMapper sportMapper;

    public SportServiceImpl(SportRepository sportRepository, SportMapper sportMapper) {
        this.sportRepository = sportRepository;
        this.sportMapper = sportMapper;
    }

    /**
     * Save a sport.
     *
     * @param sportDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public SportDTO save(SportDTO sportDTO) {
        log.debug("Request to save Sport : {}", sportDTO);
        Sport sport = sportMapper.toEntity(sportDTO);
        sport = sportRepository.save(sport);
        return sportMapper.toDto(sport);
    }

    /**
     *  Get all the sports.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<SportDTO> findAll() {
        log.debug("Request to get all Sports");
        return sportRepository.findAllWithEagerRelationships().stream()
            .map(sportMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one sport by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public SportDTO findOne(Long id) {
        log.debug("Request to get Sport : {}", id);
        Sport sport = sportRepository.findOneWithEagerRelationships(id);
        return sportMapper.toDto(sport);
    }

    /**
     *  Delete the  sport by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Sport : {}", id);
        sportRepository.delete(id);
    }
}
