package fr.istic.projet.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import fr.istic.projet.domain.enumeration.Ternary;

/**
 * A WeatherRequirements.
 */
@Entity
@Table(name = "weather_requirements")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class WeatherRequirements implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature_min")
    private Double temperatureMin;

    @Column(name = "temperature_max")
    private Double temperatureMax;

    @Column(name = "wind_speed_min")
    private Double windSpeedMin;

    @Column(name = "wind_speed_max")
    private Double windSpeedMax;

    @Enumerated(EnumType.STRING)
    @Column(name = "rain")
    private Ternary rain;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTemperatureMin() {
        return temperatureMin;
    }

    public WeatherRequirements temperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
        return this;
    }

    public void setTemperatureMin(Double temperatureMin) {
        this.temperatureMin = temperatureMin;
    }

    public Double getTemperatureMax() {
        return temperatureMax;
    }

    public WeatherRequirements temperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
        return this;
    }

    public void setTemperatureMax(Double temperatureMax) {
        this.temperatureMax = temperatureMax;
    }

    public Double getWindSpeedMin() {
        return windSpeedMin;
    }

    public WeatherRequirements windSpeedMin(Double windSpeedMin) {
        this.windSpeedMin = windSpeedMin;
        return this;
    }

    public void setWindSpeedMin(Double windSpeedMin) {
        this.windSpeedMin = windSpeedMin;
    }

    public Double getWindSpeedMax() {
        return windSpeedMax;
    }

    public WeatherRequirements windSpeedMax(Double windSpeedMax) {
        this.windSpeedMax = windSpeedMax;
        return this;
    }

    public void setWindSpeedMax(Double windSpeedMax) {
        this.windSpeedMax = windSpeedMax;
    }

    public Ternary getRain() {
        return rain;
    }

    public WeatherRequirements rain(Ternary rain) {
        this.rain = rain;
        return this;
    }

    public void setRain(Ternary rain) {
        this.rain = rain;
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
        WeatherRequirements weatherRequirements = (WeatherRequirements) o;
        if (weatherRequirements.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), weatherRequirements.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "WeatherRequirements{" +
            "id=" + getId() +
            ", temperatureMin='" + getTemperatureMin() + "'" +
            ", temperatureMax='" + getTemperatureMax() + "'" +
            ", windSpeedMin='" + getWindSpeedMin() + "'" +
            ", windSpeedMax='" + getWindSpeedMax() + "'" +
            ", rain='" + getRain() + "'" +
            "}";
    }
}
