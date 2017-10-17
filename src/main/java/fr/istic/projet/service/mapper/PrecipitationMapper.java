package fr.istic.projet.service.mapper;

import fr.istic.projet.domain.*;
import fr.istic.projet.service.dto.PrecipitationDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity Precipitation and its DTO PrecipitationDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PrecipitationMapper extends EntityMapper <PrecipitationDTO, Precipitation> {
    
    
    default Precipitation fromId(Long id) {
        if (id == null) {
            return null;
        }
        Precipitation precipitation = new Precipitation();
        precipitation.setId(id);
        return precipitation;
    }
}
