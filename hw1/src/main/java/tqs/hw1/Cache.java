package tqs.hw1;

import java.util.HashMap;


public class Cache {
    private int requests = 0;
    private int hits = 0;
    private int misses = 0;

    private HashMap<String, AirQuality> entries = new HashMap<>();
    private HashMap<String, Long> entries_ttl = new HashMap<>();


    private long ttl;

    public int getRequests() {
        return this.requests;
    }

    public void setRequests(int requests) {
        this.requests = requests;
    }

    public int getHits() {
        return this.hits;
    }

    public void setHits(int hits) {
        this.hits = hits;
    }

    public int getMisses() {
        return this.misses;
    }

    public void setMisses(int misses) {
        this.misses = misses;
    }

    public HashMap<String,AirQuality> getEntries() {
        return this.entries;
    }

    public long getttl() {
        return this.ttl;
    }

    public void setttl(long ttl) {
        this.ttl = ttl;
    }


    public Cache(int ttl) {
        this.ttl = ttl;
    }

    public void addEntry(String key, AirQuality value) {
        this.entries.put(key, value);
        this.entries_ttl.put(key, System.currentTimeMillis() + this.ttl);
    }

    public void deleteEntry(String key) {
        this.entries.remove(key);
        this.entries_ttl.remove(key);
    }

    public AirQuality getAirQuality(String city) {
        
        this.requests++;
        if (this.entries.keySet().contains(city)) {
            this.hits++;
            return this.entries.get(city);
        }
        this.misses++;
        return null;
    }

    
}
