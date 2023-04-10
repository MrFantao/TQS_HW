package tqs.hw1.Controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.CoreMatchers.is;

import tqs.hw1.AirQuality;
import tqs.hw1.AirQualityController;
import tqs.hw1.AirQualityService;

import static org.mockito.BDDMockito.given;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;



@WebMvcTest(AirQualityController.class)
public class AirQualityControllerTest {


    @Autowired
    private MockMvc mvc;

    @MockBean
    private AirQualityService airQualityService;

    @BeforeEach
    void setUp(){
    }

    @Test
    public void getAirQualityTest() throws Exception {
        Map<String, Object> map = new HashMap<>();
        ArrayList<Map<String, Object>> value = new ArrayList<>();
        Map<String, Object> map2 = new HashMap<>();

        map2.put("avg", 33);
        map2.put("min", 25);
        map2.put("max", 44);
        map2.put("day", "2023-04-02");

        map.put("pm10", map2);

        AirQuality airQuality = new AirQuality("London", 15, 10, 20, 14, map);

        given(airQualityService.getAirQuality("London")).willReturn(airQuality);
        
        mvc.perform(get("/airquality?city={param}", "London").contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.city", is("London")))
        .andExpect(jsonPath("$.aqi", is(15)))
        .andExpect(jsonPath("$.ozone", is(10)))
        .andExpect(jsonPath("$.pm10", is(20)))
        .andExpect(jsonPath("$.pm25", is(14)));

        
    }
    
}
