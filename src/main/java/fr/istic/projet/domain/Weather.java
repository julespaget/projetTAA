package fr.istic.projet.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.time.ZonedDateTime;
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

    @Column(name = "jhi_date")
    private ZonedDateTime date;

    @Column(name = "temperature")
    private Double temperature;

    @Column(name = "wind_speed")
    private Double windSpeed;

    @Column(name = "wind_angle")
    private Double windAngle;

    @Column(name = "wave_height")
    private Double waveHeight;

    @Column(name = "clouds")
    private Double clouds;

    @Column(name = "pressure")
    private Double pressure;

    @Column(name = "humidity")
    private Double humidity;

    @OneToOne
    @JoinColumn(unique = true)
    private Precipitation precipitation;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZonedDateTime getDate() {
        return date;
    }

    public Weather date(ZonedDateTime date) {
        this.date = date;
        return this;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
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

    public Double getWindAngle() {
        return windAngle;
    }

    public Weather windAngle(Double windAngle) {
        this.windAngle = windAngle;
        return this;
    }

    public void setWindAngle(Double windAngle) {
        this.windAngle = windAngle;
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

    public Double getClouds() {
        return clouds;
    }

    public Weather clouds(Double clouds) {
        this.clouds = clouds;
        return this;
    }

    public void setClouds(Double clouds) {
        this.clouds = clouds;
    }

    public Double getPressure() {
        return pressure;
    }

    public Weather pressure(Double pressure) {
        this.pressure = pressure;
        return this;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    public Double getHumidity() {
        return humidity;
    }

    public Weather humidity(Double humidity) {
        this.humidity = humidity;
        return this;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public Weather precipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
        return this;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
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
