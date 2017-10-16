package fr.istic.projet.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Sport entity.
 */
public class SportDTO implements Serializable {

    private Long id;

    private String title;

    private Long weatherRequiredId;

    private Set<PlaceDTO> placeLists = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getWeatherRequiredId() {
        return weatherRequiredId;
    }

    public void setWeatherRequiredId(Long weatherRequirementsId) {
        this.weatherRequiredId = weatherRequirementsId;
    }

    public Set<PlaceDTO> getPlaceLists() {
        return placeLists;
    }

    public void setPlaceLists(Set<PlaceDTO> places) {
        this.placeLists = places;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SportDTO sportDTO = (SportDTO) o;
        if(sportDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sportDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SportDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
