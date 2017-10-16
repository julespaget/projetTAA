package fr.istic.projet.service.dto;


import java.io.Serializable;
import java.util.Objects;
import fr.istic.projet.domain.enumeration.Ternary;

/**
 * A DTO for the WeatherRequirements entity.
 */
public class WeatherRequirementsDTO implements Serializable {

    private Long id;

    private Double temperatureMin;

    private Double temperatureMax;

    private Double windSpeedMin;

    private Double windSpeedMax;

    private Ternary rain;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getWindSpeedMin() {
        return windSpeedMin;
    }

    public void setWindSpeedMin(Double windSpeedMin) {
        this.windSpeedMin = windSpeedMin;
    }

    public Double getWindSpeedMax() {
        return windSpeedMax;
    }

    public void setWindSpeedMax(Double windSpeedMax) {
        this.windSpeedMax = windSpeedMax;
    }

    public Ternary getRain() {
        return rain;
    }

    public void setRain(Ternary rain) {
        this.rain = rain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WeatherRequirementsDTO weatherRequirementsDTO = (WeatherRequirementsDTO) o;
        if(weatherRequirementsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), weatherRequirementsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WeatherRequirementsDTO{" +
            "id=" + getId() +
            ", temperatureMin='" + getTemperatureMin() + "'" +
            ", temperatureMax='" + getTemperatureMax() + "'" +
            ", windSpeedMin='" + getWindSpeedMin() + "'" +
            ", windSpeedMax='" + getWindSpeedMax() + "'" +
            ", rain='" + getRain() + "'" +
            "}";
    }
}
