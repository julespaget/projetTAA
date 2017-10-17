package fr.istic.projet.service.dto;


import java.time.ZonedDateTime;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A DTO for the Weather entity.
 */
public class WeatherDTO implements Serializable {

    private Long id;

    private ZonedDateTime date;

    private Double temperature;

    private Double windSpeed;

    private Double windAngle;

    private Double waveHeight;

    private Double clouds;

    private Double pressure;

    private Double humidity;

    private Long precipitationId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
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

    public Double getWindAngle() {
        return windAngle;
    }

    public void setWindAngle(Double windAngle) {
        this.windAngle = windAngle;
    }

    public Double getWaveHeight() {
        return waveHeight;
    }

    public void setWaveHeight(Double waveHeight) {
        this.waveHeight = waveHeight;
    }

    public Double getClouds() {
        return clouds;
    }

    public void setClouds(Double clouds) {
        this.clouds = clouds;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Long getPrecipitationId() {
        return precipitationId;
    }

    public void setPrecipitationId(Long precipitationId) {
        this.precipitationId = precipitationId;
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
            ", date='" + getDate() + "'" +
            ", temperature='" + getTemperature() + "'" +
            ", windSpeed='" + getWindSpeed() + "'" +
            ", windAngle='" + getWindAngle() + "'" +
            ", waveHeight='" + getWaveHeight() + "'" +
            ", clouds='" + getClouds() + "'" +
            ", pressure='" + getPressure() + "'" +
            ", humidity='" + getHumidity() + "'" +
            "}";
    }
}
