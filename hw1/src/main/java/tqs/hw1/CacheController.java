package tqs.hw1;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CacheController {

    @Autowired
    private AirQualityService airQualityService;
    
    @GetMapping("/cache/requests")
    public ResponseEntity<HashMap<String, Integer>> getCacheRequests() {
        
        int requests = airQualityService.getCacheRequests();
        HashMap<String, Integer> map = new HashMap<>();

        map.put("requests", requests);

        return new ResponseEntity<HashMap<String, Integer>>(map, HttpStatus.OK);
    }

    @GetMapping("/cache/hits")
    public HashMap<String, Integer> getCacheHits() {
        
        int hits = airQualityService.getCacheHits();
        HashMap<String, Integer> map = new HashMap<>();

        map.put("hits", hits);

        return map;

    }

    @GetMapping("/cache/misses")
    public HashMap<String, Integer> getCacheMisses() {
        
        int misses = airQualityService.getCacheMisses();
        HashMap<String, Integer> map = new HashMap<>();

        map.put("misses", misses);

        return map;

    }
    
}
