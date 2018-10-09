package com.omar.calendar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import lombok.Getter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CalendarApplication {

    public static void main(String[] args) {
        SpringApplication.run(CalendarApplication.class, args);

        List<Integer> list = Arrays.asList(new Integer[]{1, 8, 3});

        System.out.println(list.stream().max(Comparator.naturalOrder()));

        System.out.println(list.stream().reduce(Integer::max));

        int max = list.stream().mapToInt(Integer::intValue).max().getAsInt();

        LongStream longStream = list.stream().mapToLong(Integer::longValue);

        max = list.stream().collect(Collectors.summarizingInt(Integer::intValue)).getMax();
    }

        boolean friendWatch(List<Friend> myFriends){
            boolean result = false;

            for(Friend myFriend : myFriends) {
                //myFriend.getId() ==
            }

            return result;
        }

       /* List<Integer> getFriendIds(List<Friend> myFriends){

            for(Friend myFriend : myFriends) {
                myFriend.getFriends();
            }

        }*/

       @Getter
       class Friend {
            final int id;
            final String name;
            final List<Friend> friends;

            public Friend(int id, String name){
                this.id = id;
                this.name = name;
                this.friends = new ArrayList<>();
            }
       }

}
