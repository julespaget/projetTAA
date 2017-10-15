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
 * A Sport.
 */
@Entity
@Table(name = "sport")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Sport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @ManyToOne
    private WeatherRequirements weatherRequired;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "sport_place_list",
               joinColumns = @JoinColumn(name="sports_id", referencedColumnName="id"),
               inverseJoinColumns = @JoinColumn(name="place_lists_id", referencedColumnName="id"))
    private Set<Place> placeLists = new HashSet<>();

    @ManyToMany(mappedBy = "sportLists")
    @JsonIgnore
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Person> personLists = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Sport title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WeatherRequirements getWeatherRequired() {
        return weatherRequired;
    }

    public Sport weatherRequired(WeatherRequirements weatherRequirements) {
        this.weatherRequired = weatherRequirements;
        return this;
    }

    public void setWeatherRequired(WeatherRequirements weatherRequirements) {
        this.weatherRequired = weatherRequirements;
    }

    public Set<Place> getPlaceLists() {
        return placeLists;
    }

    public Sport placeLists(Set<Place> places) {
        this.placeLists = places;
        return this;
    }

    public Sport addPlaceList(Place place) {
        this.placeLists.add(place);
        place.getSportLists().add(this);
        return this;
    }

    public Sport removePlaceList(Place place) {
        this.placeLists.remove(place);
        place.getSportLists().remove(this);
        return this;
    }

    public void setPlaceLists(Set<Place> places) {
        this.placeLists = places;
    }

    public Set<Person> getPersonLists() {
        return personLists;
    }

    public Sport personLists(Set<Person> people) {
        this.personLists = people;
        return this;
    }

    public Sport addPersonList(Person person) {
        this.personLists.add(person);
        person.getSportLists().add(this);
        return this;
    }

    public Sport removePersonList(Person person) {
        this.personLists.remove(person);
        person.getSportLists().remove(this);
        return this;
    }

    public void setPersonLists(Set<Person> people) {
        this.personLists = people;
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
        Sport sport = (Sport) o;
        if (sport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), sport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Sport{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            "}";
    }
}
