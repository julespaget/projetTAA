package fr.istic.projet.service.util;

import fr.istic.projet.domain.Weather;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Set;

public class WeatherCollector {

    private static String api_key = "e52b9bb6d58f2dde6f191b8e1e0cd638";
    private static String api_url = "https://api.openweathermap.org/data/2.5/forecast?units=metric&appid=" + api_key;

    public static Set<Weather> collectWeather(double latitude, double longitude){
        try {
            URLConnection connection = new URL(api_url + "&lat=" + latitude + "&lon=" + longitude).openConnection();
            connection.setRequestProperty("Accept-Charset", "UTF-8");
            InputStream response = connection.getInputStream();
            try (Scanner scanner = new Scanner(response)) {
                String responseBody = scanner.useDelimiter("\\A").next();
                System.out.println(responseBody);
            }
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
