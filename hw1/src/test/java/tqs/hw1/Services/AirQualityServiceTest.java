package tqs.hw1.Services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;  

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.hazelcast.internal.server.tcp.ProtocolException;

import tqs.hw1.AirQuality;
import tqs.hw1.AirQualityService;
import tqs.hw1.DataSource;

@ExtendWith(MockitoExtension.class)
public class AirQualityServiceTest {

    @Mock
    private DataSource dataSource;
    
    @InjectMocks
    private AirQualityService airQualityService;


    @BeforeEach
    public void setUp() throws ProtocolException, MalformedURLException, IOException {
    
        MockitoAnnotations.initMocks(this);
    
        Map<String, Object> map = new HashMap<>();
        ArrayList<Map<String, Object>> value = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();
        
        map2.put("avg", 33);
        map2.put("min", 25);
        map2.put("max", 44);
        map2.put("day", "2023-04-02");
        
        map.put("pm10", map2);
        
        AirQuality airQuality = new AirQuality("London", 15, 10, 20, 14, map);
        
        assertNotNull(dataSource);
        
        when(dataSource.requestToAPI("London")).thenReturn(airQuality);

    }



    @Test
    public void getAirQualityTest() throws MalformedURLException, java.net.ProtocolException, IOException {

        String city = "London";
        AirQuality airQualityReturned = airQualityService.getAirQuality(city);

        assertEquals(city, airQualityReturned.getCity());
        assertEquals(15, airQualityReturned.getAqi());
        assertEquals(10, airQualityReturned.getOzone());
        assertEquals(20, airQualityReturned.getPM10());
        assertEquals(14, airQualityReturned.getPM25());

        Map<String, Object> map = new HashMap<>();
        ArrayList<Map<String, Object>> value = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();

        map2.put("avg", 33);
        map2.put("min", 25);
        map2.put("max", 44);
        map2.put("day", "2023-04-02");

        map.put("pm10", map2);

        assertEquals(map, airQualityReturned.getForecasts());


    }
}
