package tqs.hw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.stereotype.Component;

import com.hazelcast.internal.server.tcp.ProtocolException;

@Component
public class DataSource {


    public AirQuality requestToAPI(String city) throws MalformedURLException, ProtocolException, IOException {

        URL url = new URL("https://api.waqi.info/feed/" + city + "/?token=3e04ff4a2219e9d107e678a587e6df1ce2b62492");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        String resolved = addEscapeCharacters(content.toString());
        JSONObject response = new JSONObject(resolved);

        AirQuality airQuality = new AirQuality();

        airQuality.setCity(response.getJSONObject("data").getJSONObject("city").getString("name"));
        airQuality.setAqi(response.getJSONObject("data").getInt("aqi"));
        airQuality.setOzone(response.getJSONObject("data").getJSONObject("iaqi").getJSONObject("o3").getInt("v"));
        airQuality.setPM10(response.getJSONObject("data").getJSONObject("iaqi").getJSONObject("pm10").getInt("v"));
        airQuality.setPM25(response.getJSONObject("data").getJSONObject("iaqi").getJSONObject("pm25").getInt("v"));

        Map<String, Object> forecasts = response.getJSONObject("data").getJSONObject("forecast").getJSONObject("daily").toMap();

        airQuality.setForecasts(forecasts);
        System.out.println(forecasts);

        return airQuality;
    }

    public static String addEscapeCharacters(String input) {
        String input2 = "{\"name\": \"John\",\"age\": 30,\"city\": \"New York\"}";
        
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            switch (c) {
                case '"':
                    builder.append("\"");
                    break;
                default:
                    builder.append(c);
                    break;
            }
        }
        return builder.toString();
    }


}
