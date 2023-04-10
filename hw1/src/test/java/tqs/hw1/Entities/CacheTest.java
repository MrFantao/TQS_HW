package tqs.hw1.Entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import tqs.hw1.AirQuality;
import tqs.hw1.Cache;

public class CacheTest {
    
    @Test
    public void CacheStartWithZeroRequestsTest() {

        Cache cache = new Cache(5000);

        assertEquals(cache.getRequests(), 0);

    }

    @Test
    public void CacheAddRequestsTest() {
        Cache Cache = new Cache(5000);
        

        Cache.getAirQuality("London");

        int requests = Cache.getRequests();

        assertEquals(requests, 1);     

    }

    @Test
    public void CacheAddMissesTest() {
        Cache Cache = new Cache(5000);

        Cache.getAirQuality("London");

        int misses = Cache.getMisses();

        assertEquals(misses, 1);     

    }

    @Test
    public void CacheAddHitsTest() {
        Cache Cache = new Cache(5000);

        Map<String, Object> map = new HashMap<>();
        ArrayList<Map<String, Object>> value = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();

        map2.put("avg", 33);
        map2.put("min", 25);
        map2.put("max", 44);
        map2.put("day", "2023-04-02");

        map.put("pm10", map2);

        AirQuality airQuality = new AirQuality("London", 15, 10, 20, 14, map);


        Cache.addEntry("London", airQuality);

        Cache.getAirQuality("London");

        int hits = Cache.getHits();

        assertEquals(hits, 1);     

    }
}
