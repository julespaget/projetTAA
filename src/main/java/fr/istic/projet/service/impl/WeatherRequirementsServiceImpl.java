package fr.istic.projet.service.impl;

import fr.istic.projet.service.WeatherRequirementsService;
import fr.istic.projet.domain.WeatherRequirements;
import fr.istic.projet.repository.WeatherRequirementsRepository;
import fr.istic.projet.service.dto.WeatherRequirementsDTO;
import fr.istic.projet.service.mapper.WeatherRequirementsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing WeatherRequirements.
 */
@Service
@Transactional
public class WeatherRequirementsServiceImpl implements WeatherRequirementsService{

    private final Logger log = LoggerFactory.getLogger(WeatherRequirementsServiceImpl.class);

    private final WeatherRequirementsRepository weatherRequirementsRepository;

    private final WeatherRequirementsMapper weatherRequirementsMapper;

    public WeatherRequirementsServiceImpl(WeatherRequirementsRepository weatherRequirementsRepository, WeatherRequirementsMapper weatherRequirementsMapper) {
        this.weatherRequirementsRepository = weatherRequirementsRepository;
        this.weatherRequirementsMapper = weatherRequirementsMapper;
    }

    /**
     * Save a weatherRequirements.
     *
     * @param weatherRequirementsDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WeatherRequirementsDTO save(WeatherRequirementsDTO weatherRequirementsDTO) {
        log.debug("Request to save WeatherRequirements : {}", weatherRequirementsDTO);
        WeatherRequirements weatherRequirements = weatherRequirementsMapper.toEntity(weatherRequirementsDTO);
        weatherRequirements = weatherRequirementsRepository.save(weatherRequirements);
        return weatherRequirementsMapper.toDto(weatherRequirements);
    }

    /**
     *  Get all the weatherRequirements.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WeatherRequirementsDTO> findAll() {
        log.debug("Request to get all WeatherRequirements");
        return weatherRequirementsRepository.findAll().stream()
            .map(weatherRequirementsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one weatherRequirements by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WeatherRequirementsDTO findOne(Long id) {
        log.debug("Request to get WeatherRequirements : {}", id);
        WeatherRequirements weatherRequirements = weatherRequirementsRepository.findOne(id);
        return weatherRequirementsMapper.toDto(weatherRequirements);
    }

    /**
     *  Delete the  weatherRequirements by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete WeatherRequirements : {}", id);
        weatherRequirementsRepository.delete(id);
    }
}
