package tqs.hw1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


@Service
public class AirQualityService {

    @Autowired
    private DataSource dataSource;

    private Cache cache = new Cache(5000);

    public AirQuality getAirQuality(String city) throws MalformedURLException, ProtocolException, IOException{

        cache.setRequests(cache.getRequests() + 1);
        if(cache.getEntries().containsKey(city)) {
            cache.setHits(cache.getHits() + 1);
            return cache.getEntries().get(city);
        }
        cache.setMisses(cache.getMisses() + 1);
        AirQuality airQuality = dataSource.requestToAPI(city);
        cache.addEntry(city, airQuality);

        return airQuality;

    }

    public int getCacheHits() {
        return cache.getHits();
    }

    public int getCacheMisses() {
        return cache.getMisses();
    }

    public int getCacheRequests() {
        return cache.getRequests();
    }
    
}
