package fr.istic.projet.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Weather.
 */
@Entity
@Table(name = "weather")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Weather implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "rain")
    private Boolean rain;

    @Column(name = "wave_height")
    private Double waveHeight;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperature() {
        return temperature;
    }

    public Weather temperature(Double temperature) {
        this.temperature = temperature;
        return this;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public Weather windSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
        return this;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Boolean isRain() {
        return rain;
    }

    public Weather rain(Boolean rain) {
        this.rain = rain;
        return this;
    }

    public void setRain(Boolean rain) {
        this.rain = rain;
    }

    public Double getWaveHeight() {
        return waveHeight;
    }

    public Weather waveHeight(Double waveHeight) {
        this.waveHeight = waveHeight;
        return this;
    }

    public void setWaveHeight(Double waveHeight) {
        this.waveHeight = waveHeight;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Weather weather = (Weather) o;
        if (weather.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), weather.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Weather{" +
            "id=" + getId() +
            ", temperature='" + getTemperature() + "'" +
            ", windSpeed='" + getWindSpeed() + "'" +
            ", rain='" + isRain() + "'" +
            ", waveHeight='" + getWaveHeight() + "'" +
            "}";
    }
}
