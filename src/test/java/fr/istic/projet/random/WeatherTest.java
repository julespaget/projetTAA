package fr.istic.projet.random;

import fr.istic.projet.collectors.WeatherCollector;
import org.junit.Test;

public class WeatherTest {

    @Test
    public void SimpleWeatherTest(){
        WeatherCollector.collectWeather(48.1119800, -1.6742900); //Rennes
        WeatherCollector.collectWeather(55.864237, -4.251806); //Glasgow
        //WeatherCollector.collectWeather(55.864237, -4.251806);
    }
}
