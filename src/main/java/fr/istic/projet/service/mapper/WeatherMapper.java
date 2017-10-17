package fr.istic.projet.service.mapper;

import fr.istic.projet.domain.*;
import fr.istic.projet.service.dto.WeatherDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Weather and its DTO WeatherDTO.
 */
@Mapper(componentModel = "spring", uses = {PrecipitationMapper.class, })
public interface WeatherMapper extends EntityMapper <WeatherDTO, Weather> {

    @Mapping(source = "precipitation.id", target = "precipitationId")
    WeatherDTO toDto(Weather weather); 

    @Mapping(source = "precipitationId", target = "precipitation")
    Weather toEntity(WeatherDTO weatherDTO); 
    default Weather fromId(Long id) {
        if (id == null) {
            return null;
        }
        Weather weather = new Weather();
        weather.setId(id);
        return weather;
    }
}
