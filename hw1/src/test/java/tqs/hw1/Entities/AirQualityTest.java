package tqs.hw1.Entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tqs.hw1.AirQuality;

public class AirQualityTest {   

    @Test
    public void airQualityGetsTests() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<Map<String, Object>> value = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();

        map2.put("avg", 33);
        map2.put("min", 25);
        map2.put("max", 44);
        map2.put("day", "2023-04-02");

        map.put("pm10", map2);

        AirQuality airQuality = new AirQuality("London", 15, 10, 20, 14, map);

        String city = airQuality.getCity();
        assertEquals("London", city);
        assertThat(city, is("London"));

        int aqi = airQuality.getAqi();
        assertEquals(15, aqi);
        assertThat(aqi, is(15));

        int ozone = airQuality.getOzone();
        assertEquals(10, ozone);
        assertThat(ozone, is(10));

        int pm10 = airQuality.getPM10();
        assertEquals(20, pm10);
        assertThat(pm10, is(20));

        int pm25 = airQuality.getPM25();
        assertEquals(14, pm25);
        assertThat(pm25, is(14));

        Map<String, Object> forecasts = airQuality.getForecasts();
        assertEquals(map, forecasts);
        assertThat(forecasts, is(map));

    }
    
}
