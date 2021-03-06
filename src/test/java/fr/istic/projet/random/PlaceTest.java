package fr.istic.projet.random;

import fr.istic.projet.collectors.PlacesCollector;
import fr.istic.projet.domain.Place;
import fr.istic.projet.service.PlaceService;
import fr.istic.projet.service.dto.PlaceDTO;
import fr.istic.projet.service.mapper.PlaceMapperImpl;
import io.advantageous.boon.core.Sys;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Set;

@Service
@Configurable
public class PlaceTest {

    @Autowired
    PlaceService placeservice;

    @Test
    public void placeCollectionTest(){
        Set<Place> placeSet = PlacesCollector.collectPlaces("cities.json");
        for(Place place : placeSet){
            placeservice.save(new PlaceMapperImpl().toDto(place));
        }
        //System.out.println("Number of places in france = " +placeSet.size());
    }
}
