package fr.istic.projet.collectors;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import fr.istic.projet.domain.Place;
import fr.istic.projet.factories.PlaceFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class PlacesCollector {

    public static Set<Place> collectPlaces(String filename){
        try {
            String cities = new String(Files.readAllBytes(Paths.get(filename)));
            JsonArray placesArray = new JsonParser().parse(cities).getAsJsonArray();
            Set<Place> placesSet = new HashSet<>();
            Place place;
            for(JsonElement placeJson : placesArray){
                place = PlaceFactory.createPlace(placeJson.getAsJsonObject());
                if(place != null) {
                    placesSet.add(place);
                }
            }
            return placesSet;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
