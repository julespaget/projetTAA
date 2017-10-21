package fr.istic.projet.factories;

import fr.istic.projet.domain.Weather;


public class WeatherFactory {

    private static WeatherFactory _INSTANCE;

    private WeatherFactory(){
    }

    public WeatherFactory getFactory(){
        if(_INSTANCE == null){
            _INSTANCE = new WeatherFactory();
        }
        return _INSTANCE;
    }

    public Weather createWeather(String weatherString){
        return null;
    }
}
