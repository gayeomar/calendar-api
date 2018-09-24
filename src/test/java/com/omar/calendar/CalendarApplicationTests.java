package com.omar.calendar;

import com.omar.calendar.domain.to.UserTO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CalendarApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testReadUser(){
        ResponseEntity<UserTO> response = restTemplate.getForEntity("/v1/user/omar@gmail.com", request, UserTO.class)

    }

}
