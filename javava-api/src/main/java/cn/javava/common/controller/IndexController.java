package cn.javava.common.controller;

import com.google.common.collect.ImmutableMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class IndexController {

    @GetMapping(path = "/")
    public Map<String, String> doGet() {
        ImmutableMap.Builder<String, String> builder = ImmutableMap.builder();
        builder.put("rooms_url", "http://api.javava.com/rooms");
        builder.put("streams_url", "http://api.javava.com/streams");
        builder.put("users_url", "http://api.javava.com/users");
        builder.put("shelves_url", "http://api.javava.com/shelves");
        builder.put("games_url", "http://api.javava.com/games");
        return builder.build();
    }
}
