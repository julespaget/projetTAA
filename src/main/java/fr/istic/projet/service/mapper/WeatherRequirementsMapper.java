package fr.istic.projet.service.mapper;

import fr.istic.projet.domain.*;
import fr.istic.projet.service.dto.WeatherRequirementsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity WeatherRequirements and its DTO WeatherRequirementsDTO.
 */
@Mapper(componentModel = "spring", uses = {PrecipitationMapper.class, })
public interface WeatherRequirementsMapper extends EntityMapper <WeatherRequirementsDTO, WeatherRequirements> {

    @Mapping(source = "precipitationMin.id", target = "precipitationMinId")

    @Mapping(source = "precipitationMax.id", target = "precipitationMaxId")
    WeatherRequirementsDTO toDto(WeatherRequirements weatherRequirements); 

    @Mapping(source = "precipitationMinId", target = "precipitationMin")

    @Mapping(source = "precipitationMaxId", target = "precipitationMax")
    WeatherRequirements toEntity(WeatherRequirementsDTO weatherRequirementsDTO); 
    default WeatherRequirements fromId(Long id) {
        if (id == null) {
            return null;
        }
        WeatherRequirements weatherRequirements = new WeatherRequirements();
        weatherRequirements.setId(id);
        return weatherRequirements;
    }
}
