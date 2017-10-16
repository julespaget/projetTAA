package fr.istic.projet.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Weather entity.
 */
public class WeatherDTO implements Serializable {

    private Long id;

    private Double temperature;

    private Double windSpeed;

    private Boolean rain;

    private Double waveHeight;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Boolean isRain() {
        return rain;
    }

    public void setRain(Boolean rain) {
        this.rain = rain;
    }

    public Double getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(Double waveHeight) {
        this.waveHeight = waveHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        WeatherDTO weatherDTO = (WeatherDTO) o;
        if(weatherDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), weatherDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WeatherDTO{" +
            "id=" + getId() +
            ", temperature='" + getTemperature() + "'" +
            ", windSpeed='" + getWindSpeed() + "'" +
            ", rain='" + isRain() + "'" +
            ", waveHeight='" + getWaveHeight() + "'" +
            "}";
    }
}
