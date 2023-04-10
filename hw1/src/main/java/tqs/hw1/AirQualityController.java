package tqs.hw1;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Optional;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;
import javax.swing.text.html.parser.Entity;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.JsonArray;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

@RestController
public class AirQualityController {

    @Autowired
    private AirQualityService airQualityService;
    

    Logger logger = LoggerFactory.getLogger(AirQualityController.class);


    @GetMapping(value= "/")
    public ModelAndView index(@RequestParam(value = "city", required = false) Optional<String> city) throws MalformedURLException, MalformedURLException, ProtocolException, IOException{

        ModelAndView mav = new ModelAndView("index");
        AirQuality response = airQualityService.getAirQuality(city.get());

        if(city.isPresent()) {
            mav.addObject("result", city.get());
            mav.addObject("aqi", response.getAqi());
            mav.addObject("ozone", response.getOzone());
            mav.addObject("pm10", response.getPM10());
            mav.addObject("pm25", response.getPM25());

            mav.addObject("o3forecast", response.getForecasts().get("o3"));
            mav.addObject("pm10forecast", response.getForecasts().get("pm25"));
            mav.addObject("pm25forecast", response.getForecasts().get("pm10"));
            
        }
        else {
            mav.addObject("result", city);            
        }

        String log = String.format("Search for air quality in %s", city.get());

        logger.info(log);



        return mav;
    }

    @GetMapping(value= "/airquality")
	public AirQuality getAirQuality(@RequestParam("city") String city) throws MalformedURLException, MalformedURLException, ProtocolException, IOException{

        AirQuality response = airQualityService.getAirQuality(city);
		return response;
	}
}
