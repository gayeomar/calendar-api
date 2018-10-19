package com.omar.calendar.client;

import static com.omar.calendar.util.CalendarConstant.BASE_URL_CALENDAR;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.omar.calendar.domain.to.UserTO;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

//@Component
@RestController
@RequestMapping(path = "/cb")
@Slf4j
public class UserClient {

    private RestTemplate restTemplate;

    private final String url = "http://localhost:8080/v1/user/{email}";

    public UserClient (RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    @HystrixCommand(fallbackMethod = "getUserFallBack",
            groupKey = "calendar_getUser_groupKey",
            commandKey = "calendar_getUser_commandKey",
            commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="5000")})
    public UserTO getUser(String email){

        log.error("############### getUser called!");
        UserTO  response = restTemplate.getForObject(url, UserTO.class, email);


        Map<String, String> uriVariable = new HashMap<>();
        uriVariable.put("email", email);

        if(RandomUtils.nextBoolean()){
            throw new RuntimeException("Boom!!!");
        }

        return response;
    }

    public UserTO getUserFallBack(String email){

        log.error("************* getUserFallBack called!");

        return null;
    }

    @HystrixCommand(fallbackMethod = "circuitBreakerFallBack",
            groupKey = "cb_groupKey",
            commandKey = "cb_commandKey",
            commandProperties = {@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="2000")})
    @GetMapping
    int circuitBreaker(){
        if(RandomUtils.nextBoolean()){
            throw new RuntimeException("Boom!!!");
        }
        return 1;
    }

    int circuitBreakerFallBack(){
        return 2;
    }


}
