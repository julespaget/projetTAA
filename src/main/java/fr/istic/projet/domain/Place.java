package fr.istic.projet.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Place.
 */
@Entity
@Table(name = "place")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @OneToOne
    @JoinColumn(unique = true)
    private Location emplacement;

    @ManyToOne
    private Location location;

    @ManyToOne
    private Weather weather;

    @ManyToMany(mappedBy = "placeLists")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Sport> sportLists = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Place nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Location getEmplacement() {
        return emplacement;
    }

    public Place emplacement(Location location) {
        this.emplacement = location;
        return this;
    }

    public void setEmplacement(Location location) {
        this.emplacement = location;
    }

    public Location getLocation() {
        return location;
    }

    public Place location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Weather getWeather() {
        return weather;
    }

    public Place weather(Weather weather) {
        this.weather = weather;
        return this;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Set<Sport> getSportLists() {
        return sportLists;
    }

    public Place sportLists(Set<Sport> sports) {
        this.sportLists = sports;
        return this;
    }

    public Place addSportList(Sport sport) {
        this.sportLists.add(sport);
        sport.getPlaceLists().add(this);
        return this;
    }

    public Place removeSportList(Sport sport) {
        this.sportLists.remove(sport);
        sport.getPlaceLists().remove(this);
        return this;
    }

    public void setSportLists(Set<Sport> sports) {
        this.sportLists = sports;
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
        Place place = (Place) o;
        if (place.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), place.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Place{" +
            "id=" + getId() +
            ", nom='" + getNom() + "'" +
            "}";
    }
}
