package fr.istic.projet.factories;

import com.google.gson.*;
import fr.istic.projet.domain.Weather;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;


public class WeatherFactory {

    private static WeatherFactory _INSTANCE;

    private WeatherFactory(){
    }

    public static WeatherFactory getFactory(){
        if(_INSTANCE == null){
            _INSTANCE = new WeatherFactory();
        }
        return _INSTANCE;
    }

    public Weather createWeather(JsonObject weatherJson){
        //System.out.println(weatherJson.toString());
        Weather weather = new Weather();
        Date date = new Date(weatherJson.get("dt").getAsLong()*1000);
        weather.setDate(ZonedDateTime.ofInstant(date.toInstant(), ZoneId.of("GMT")));
        JsonObject weatherMain = weatherJson.get("main").getAsJsonObject();
        JsonObject weatherWind = weatherJson.get("wind").getAsJsonObject();
        weather.setTemperature(weatherMain.get("temp").getAsDouble());
        weather.setPressure(weatherMain.get("pressure").getAsDouble());
        weather.setHumidity(weatherMain.get("humidity").getAsDouble());
        weather.setWindSpeed(weatherWind.get("speed").getAsDouble());
        weather.setWindAngle(weatherWind.get("deg").getAsDouble());
        weather.setClouds(weatherJson.get("clouds").getAsJsonObject().get("all").getAsDouble());
        String main = weatherJson.get("weather").getAsJsonArray().get(0).getAsJsonObject().get("main").getAsString();
        switch(main){
            case "Rain":
                double value = weatherJson.get("rain").getAsJsonObject().get("3h").getAsDouble();
                weather.setPrecipitation(PrecipitationFactory.getFactory().createRain(value));
                break;
            case "Snow":
                weather.setPrecipitation(PrecipitationFactory.getFactory().createSnow());
        }
        System.out.println(weather.toString());
//        System.out.println(weather.getPrecipitation().toString());
        return weather;
    }
}
