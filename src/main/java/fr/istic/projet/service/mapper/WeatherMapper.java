package fr.istic.projet.service.mapper;

import fr.istic.projet.domain.*;
import fr.istic.projet.service.dto.WeatherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Weather and its DTO WeatherDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WeatherMapper extends EntityMapper <WeatherDTO, Weather> {
    
    
    default Weather fromId(Long id) {
        if (id == null) {
            return null;
        }
        Weather weather = new Weather();
        weather.setId(id);
        return weather;
    }
}
