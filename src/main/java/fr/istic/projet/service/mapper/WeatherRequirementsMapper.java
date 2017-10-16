package fr.istic.projet.service.mapper;

import fr.istic.projet.domain.*;
import fr.istic.projet.service.dto.WeatherRequirementsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WeatherRequirements and its DTO WeatherRequirementsDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface WeatherRequirementsMapper extends EntityMapper <WeatherRequirementsDTO, WeatherRequirements> {
    
    
    default WeatherRequirements fromId(Long id) {
        if (id == null) {
            return null;
        }
        WeatherRequirements weatherRequirements = new WeatherRequirements();
        weatherRequirements.setId(id);
        return weatherRequirements;
    }
}
