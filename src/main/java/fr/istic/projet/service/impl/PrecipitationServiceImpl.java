package fr.istic.projet.service.impl;

import fr.istic.projet.service.PrecipitationService;
import fr.istic.projet.domain.Precipitation;
import fr.istic.projet.repository.PrecipitationRepository;
import fr.istic.projet.service.dto.PrecipitationDTO;
import fr.istic.projet.service.mapper.PrecipitationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Precipitation.
 */
@Service
@Transactional
public class PrecipitationServiceImpl implements PrecipitationService{

    private final Logger log = LoggerFactory.getLogger(PrecipitationServiceImpl.class);

    private final PrecipitationRepository precipitationRepository;

    private final PrecipitationMapper precipitationMapper;

    public PrecipitationServiceImpl(PrecipitationRepository precipitationRepository, PrecipitationMapper precipitationMapper) {
        this.precipitationRepository = precipitationRepository;
        this.precipitationMapper = precipitationMapper;
    }

    /**
     * Save a precipitation.
     *
     * @param precipitationDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public PrecipitationDTO save(PrecipitationDTO precipitationDTO) {
        log.debug("Request to save Precipitation : {}", precipitationDTO);
        Precipitation precipitation = precipitationMapper.toEntity(precipitationDTO);
        precipitation = precipitationRepository.save(precipitation);
        return precipitationMapper.toDto(precipitation);
    }

    /**
     *  Get all the precipitations.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<PrecipitationDTO> findAll() {
        log.debug("Request to get all Precipitations");
        return precipitationRepository.findAll().stream()
            .map(precipitationMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one precipitation by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public PrecipitationDTO findOne(Long id) {
        log.debug("Request to get Precipitation : {}", id);
        Precipitation precipitation = precipitationRepository.findOne(id);
        return precipitationMapper.toDto(precipitation);
    }

    /**
     *  Delete the  precipitation by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Precipitation : {}", id);
        precipitationRepository.delete(id);
    }
}
