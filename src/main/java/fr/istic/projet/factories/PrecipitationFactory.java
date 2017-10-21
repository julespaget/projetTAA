package fr.istic.projet.factories;

import com.google.gson.JsonObject;
import fr.istic.projet.domain.Precipitation;
import fr.istic.projet.domain.enumeration.PrecipitationType;

public class PrecipitationFactory {

    private static PrecipitationFactory _INSTANCE;

    private PrecipitationFactory(){
    }

    public static PrecipitationFactory getFactory(){
        if(_INSTANCE == null){
            _INSTANCE = new PrecipitationFactory();
        }
        return _INSTANCE;
    }

    public Precipitation createRain(double value){
        Precipitation precipitation = new Precipitation();
        precipitation.setType(PrecipitationType.RAIN);
        precipitation.setValue(value);
        return precipitation;
    }

    public Precipitation createSnow(){
        Precipitation precipitation = new Precipitation();
        precipitation.setType(PrecipitationType.SNOW);
        precipitation.setValue(0.);
        return precipitation;
    }
}
