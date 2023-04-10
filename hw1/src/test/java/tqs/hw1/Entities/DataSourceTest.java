package tqs.hw1.Entities;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tqs.hw1.DataSource;

public class DataSourceTest {
    
    @Autowired
    private DataSource dataSource;

    @Test
    public void dataSourceAddEscapeCharacters(){

        String input = "{\"name\": \"John\",\"age\": 30,\"city\": \"New York\"}";

        String output = dataSource.addEscapeCharacters(input);

        assertEquals(output, "{\"name\": \"John\",\"age\": 30,\"city\": \"New York\"}");

    }

}
