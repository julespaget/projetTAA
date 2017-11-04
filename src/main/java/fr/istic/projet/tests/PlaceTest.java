package fr.istic.projet.tests;

import fr.istic.projet.collectors.PlacesCollector;
import fr.istic.projet.domain.Place;
import fr.istic.projet.service.PlaceService;
import fr.istic.projet.service.mapper.PlaceMapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@Configurable
public class PlaceTest {

    @Autowired
    PlaceService placeservice;

    public void placeCollectionTest(){
        Set<Place> placeSet = PlacesCollector.collectPlaces("cities.json");
        for(Place place : placeSet){
            placeservice.save(new PlaceMapperImpl().toDto(place));
        }
        System.out.println("Number of places in france = " +placeSet.size());
    }
}
