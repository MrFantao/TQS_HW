package tqs.hw1;

import java.util.Map;

public class AirQuality {
    private String city;
    private int aqi;
    private int ozone;
    private int so2;
    private int pm10;
    private int pm25;
    private Map<String, Object> forecasts;




    AirQuality() {

    }

    public AirQuality(String city, int aqi, int ozone, int pm10, int pm25, Map<String, Object> forecasts) {
        this.city = city;
        this.aqi = aqi;
        this.ozone = ozone;
        this.pm10 = pm10;
        this.pm25 = pm25;
        this.forecasts = forecasts;


    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {

        this.city = city;       
    }

    public int getAqi() {

        return this.aqi;
    
    }

    public void setAqi(int aqi) {

        this.aqi = aqi;

    }

    public int getOzone() {
        return this.ozone;
    }

    public void setOzone(int ozone) {

        this.ozone = ozone;
    
    }

    public int getPM10() {
        return this.pm10;
    }

    public void setPM10(int pm10) {

        this.pm10 = pm10;
    
    }

    public int getPM25() {
        return this.pm25;
    }

    public void setPM25(int pm25) {

        this.pm25 = pm25;
    
    }

    public Map<String, Object> getForecasts() {
        return this.forecasts;
    }

    public void setForecasts(Map<String, Object> forecasts) {
        this.forecasts = forecasts;
    }







}
