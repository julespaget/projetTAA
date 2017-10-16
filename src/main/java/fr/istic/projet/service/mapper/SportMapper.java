package fr.istic.projet.service.mapper;

import fr.istic.projet.domain.*;
import fr.istic.projet.service.dto.SportDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Sport and its DTO SportDTO.
 */
@Mapper(componentModel = "spring", uses = {WeatherRequirementsMapper.class, PlaceMapper.class, })
public interface SportMapper extends EntityMapper <SportDTO, Sport> {

    @Mapping(source = "weatherRequired.id", target = "weatherRequiredId")
    SportDTO toDto(Sport sport); 

    @Mapping(source = "weatherRequiredId", target = "weatherRequired")
    @Mapping(target = "personLists", ignore = true)
    Sport toEntity(SportDTO sportDTO); 
    default Sport fromId(Long id) {
        if (id == null) {
            return null;
        }
        Sport sport = new Sport();
        sport.setId(id);
        return sport;
    }
}
