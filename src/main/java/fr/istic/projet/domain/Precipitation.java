package fr.istic.projet.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

import fr.istic.projet.domain.enumeration.PrecipitationType;

/**
 * A Precipitation.
 */
@Entity
@Table(name = "precipitation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Precipitation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "jhi_type")
    private PrecipitationType type;

    @Column(name = "jhi_value")
    private Double value;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PrecipitationType getType() {
        return type;
    }

    public Precipitation type(PrecipitationType type) {
        this.type = type;
        return this;
    }

    public void setType(PrecipitationType type) {
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public Precipitation value(Double value) {
        this.value = value;
        return this;
    }

    public void setValue(Double value) {
        this.value = value;
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
        Precipitation precipitation = (Precipitation) o;
        if (precipitation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), precipitation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Precipitation{" +
            "id=" + getId() +
            ", type='" + getType() + "'" +
            ", value='" + getValue() + "'" +
            "}";
    }
}
