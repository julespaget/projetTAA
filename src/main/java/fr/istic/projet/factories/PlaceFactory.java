package fr.istic.projet.factories;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fr.istic.projet.domain.Location;
import fr.istic.projet.domain.Place;

public class PlaceFactory {
    public static Place createPlace(JsonObject placeJson) {
        Place place;
        Location location;
        String name = placeJson.get("name").getAsString();
        String country = placeJson.get("country").getAsString();
        double latitude = placeJson.get("lat").getAsDouble();
        double longitude = placeJson.get("lng").getAsDouble();

        if(country.equals("FR")){
//            System.out.println("latitude =" + latitude + " longitude =" + longitude);
            place = new Place();
            place.setNom(name);
            location = new Location();
            location.setLatitude(latitude);
            location.setLongitude(longitude);
            place.setLocation(location);
            return place;
        }

        return null;
    }
}
