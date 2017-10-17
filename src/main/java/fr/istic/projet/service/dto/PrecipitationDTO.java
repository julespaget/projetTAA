package fr.istic.projet.service.dto;


import java.io.Serializable;
import java.util.Objects;
import fr.istic.projet.domain.enumeration.PrecipitationType;

/**
 * A DTO for the Precipitation entity.
 */
public class PrecipitationDTO implements Serializable {

    private Long id;

    private PrecipitationType type;

    private Double value;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrecipitationType getType() {
        return type;
    }

    public void setType(PrecipitationType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PrecipitationDTO precipitationDTO = (PrecipitationDTO) o;
        if(precipitationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), precipitationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrecipitationDTO{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
