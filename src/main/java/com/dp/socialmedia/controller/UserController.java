package com.dp.socialmedia.controller;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.dp.socialmedia.entity.User;
import com.dp.socialmedia.filter.JwtUtils;
import com.dp.socialmedia.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtUtils utils;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @GetMapping("/{id}")
    public User retrieveUser(@PathVariable Long id) {
        return userService.findById(id);
    }

    @PostMapping("registration")
    public User createUser(@RequestBody User user) {
        log.info("Saving new user to the database");
        return userService.create(user);
    }

    @GetMapping("token")
    public void refreshToken(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
        String authorizationHeader =request.getHeader(AUTHORIZATION);
        String username = utils.getUsername(authorizationHeader);
        var user = userService.findByUsername(username);
        var userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());

        String refreshToken = utils.createToken(userDetails,  24 * 60 * 60 * 1000L);
        Map<String, String> tokens = new HashMap<>();
        tokens.put("refreshToken", refreshToken);
        response.setContentType(APPLICATION_JSON_VALUE);

        objectMapper.writeValue(response.getOutputStream(), tokens);
    }
}
