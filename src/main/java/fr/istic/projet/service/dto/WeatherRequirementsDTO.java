package fr.istic.projet.service.dto;


import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the WeatherRequirements entity.
 */
public class WeatherRequirementsDTO implements Serializable {

    private Long id;

    private Double temperatureMin;

    private Double temperatureMax;

    private Double windSpeedMin;

    private Double windSpeedMax;

    private Double windAngleMin;

    private Double windAngleMax;

    private Double waveHeightMin;

    private Double waveHeightMax;

    private Long precipitationMinId;

    private Long precipitationMaxId;

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

    public Double getWindAngleMin() {
        return windAngleMin;
    }

    public void setWindAngleMin(Double windAngleMin) {
        this.windAngleMin = windAngleMin;
    }

    public Double getWindAngleMax() {
        return windAngleMax;
    }

    public void setWindAngleMax(Double windAngleMax) {
        this.windAngleMax = windAngleMax;
    }

    public Double getWaveHeightMin() {
        return waveHeightMin;
    }

    public void setWaveHeightMin(Double waveHeightMin) {
        this.waveHeightMin = waveHeightMin;
    }

    public Double getWaveHeightMax() {
        return waveHeightMax;
    }

    public void setWaveHeightMax(Double waveHeightMax) {
        this.waveHeightMax = waveHeightMax;
    }

    public Long getPrecipitationMinId() {
        return precipitationMinId;
    }

    public void setPrecipitationMinId(Long precipitationId) {
        this.precipitationMinId = precipitationId;
    }

    public Long getPrecipitationMaxId() {
        return precipitationMaxId;
    }

    public void setPrecipitationMaxId(Long precipitationId) {
        this.precipitationMaxId = precipitationId;
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
            ", windAngleMin='" + getWindAngleMin() + "'" +
            ", windAngleMax='" + getWindAngleMax() + "'" +
            ", waveHeightMin='" + getWaveHeightMin() + "'" +
            ", waveHeightMax='" + getWaveHeightMax() + "'" +
            "}";
    }
}
