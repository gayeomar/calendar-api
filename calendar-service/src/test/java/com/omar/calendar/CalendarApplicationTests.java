package com.omar.calendar;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import com.omar.calendar.domain.to.CalendarTO;
import com.omar.calendar.domain.to.UserTO;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Profile("embedded")
public class CalendarApplicationTests {

    //@LocalServerPort private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testReadUser(){
        Map<String, String> uriVariables = new HashMap<>();
        //One user with email omar@gmail.com should have been loaded already
        ResponseEntity<UserTO> response = restTemplate.getForEntity("/v1/user/omar@gmail.com", UserTO.class, uriVariables);
        assertNotNull(response);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    public void testGetCalendar(){
        Map<String, String> uriVariables = new HashMap<>();
        ResponseEntity<CalendarTO> response = restTemplate.getForEntity("/v1/calendar/to/1", CalendarTO.class);
        //assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        CalendarTO responseTo = restTemplate.getForObject("/v1/calendar/to/1", CalendarTO.class);
        System.out.println("ResponseEntity: " + response);
        System.out.println("ResponseTo: " + responseTo);
    }

    //TODO: Against TDD, code first then test, bad, very B-A-D!!!
    /*
    @Test
    public void testCreateUser(){

    }

    @Test
    public void testCreateCalendar(){

    }

    @Test
    public void testReadCalendar(){

    }

    @Test
    public void testCreateEvent(){

    }

    @Test
    public void testReadEvent(){

    }
    */

}
