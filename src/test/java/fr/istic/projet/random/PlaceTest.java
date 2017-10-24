package fr.istic.projet.random;

import fr.istic.projet.collectors.PlacesCollector;
import fr.istic.projet.domain.Place;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

public class PlaceTest {

    @Test
    public void placeCollectionTest(){
//        EntityManagerFactory factory =
//            Persistence.createEntityManagerFactory("mysql");
        System.out.println(Persistence.getPersistenceUtil());
/*        EntityManager manager = factory.createEntityManager();
        Set<Place> placeSet = PlacesCollector.collectPlaces("cities.json");
        System.out.println("Number of places = " +placeSet.size());*/
    }
}
