package fr.istic.projet.service.impl;

import fr.istic.projet.service.WeatherService;
import fr.istic.projet.domain.Weather;
import fr.istic.projet.repository.WeatherRepository;
import fr.istic.projet.service.dto.WeatherDTO;
import fr.istic.projet.service.mapper.WeatherMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing Weather.
 */
@Service
@Transactional
public class WeatherServiceImpl implements WeatherService{

    private final Logger log = LoggerFactory.getLogger(WeatherServiceImpl.class);

    private final WeatherRepository weatherRepository;

    private final WeatherMapper weatherMapper;

    public WeatherServiceImpl(WeatherRepository weatherRepository, WeatherMapper weatherMapper) {
        this.weatherRepository = weatherRepository;
        this.weatherMapper = weatherMapper;
    }

    /**
     * Save a weather.
     *
     * @param weatherDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public WeatherDTO save(WeatherDTO weatherDTO) {
        log.debug("Request to save Weather : {}", weatherDTO);
        Weather weather = weatherMapper.toEntity(weatherDTO);
        weather = weatherRepository.save(weather);
        return weatherMapper.toDto(weather);
    }

    /**
     *  Get all the weathers.
     *
     *  @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public List<WeatherDTO> findAll() {
        log.debug("Request to get all Weathers");
        return weatherRepository.findAll().stream()
            .map(weatherMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     *  Get one weather by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public WeatherDTO findOne(Long id) {
        log.debug("Request to get Weather : {}", id);
        Weather weather = weatherRepository.findOne(id);
        return weatherMapper.toDto(weather);
    }

    /**
     *  Delete the  weather by id.
     *
     *  @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Weather : {}", id);
        weatherRepository.delete(id);
    }
}
