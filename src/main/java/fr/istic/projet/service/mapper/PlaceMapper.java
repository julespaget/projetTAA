package fr.istic.projet.service.mapper;

import fr.istic.projet.domain.*;
import fr.istic.projet.service.dto.PlaceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Place and its DTO PlaceDTO.
 */
@Mapper(componentModel = "spring", uses = {LocationMapper.class, WeatherMapper.class, })
public interface PlaceMapper extends EntityMapper <PlaceDTO, Place> {

    @Mapping(source = "location.id", target = "locationId")

    @Mapping(source = "weather.id", target = "weatherId")
    PlaceDTO toDto(Place place); 

    @Mapping(source = "locationId", target = "location")

    @Mapping(source = "weatherId", target = "weather")
    @Mapping(target = "sportLists", ignore = true)
    Place toEntity(PlaceDTO placeDTO); 
    default Place fromId(Long id) {
        if (id == null) {
            return null;
        }
        Place place = new Place();
        place.setId(id);
        return place;
    }
}
