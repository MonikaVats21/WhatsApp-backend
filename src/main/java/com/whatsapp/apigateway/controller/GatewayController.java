package com.whatsapp.apigateway.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class GatewayController {

    private final RestTemplate restTemplate = new RestTemplate();

    private static final String AUTH_SERVICE = "http://localhost:8081";
    private static final String USER_SERVICE = "http://localhost:8082";
    private static final String CHAT_SERVICE = "http://localhost:8083";

    @RequestMapping("/api/auth/**")
    public ResponseEntity<String> authService(
            @RequestBody(required = false) String body,
            HttpMethod method,
            HttpServletRequest request) {
        return forward(request, body, method, AUTH_SERVICE);
    }

    @RequestMapping("/api/users/**")
    public ResponseEntity<String> userService(
            @RequestBody(required = false) String body,
            HttpMethod method,
            HttpServletRequest request) {
        return forward(request, body, method, USER_SERVICE);
    }

    @RequestMapping("/api/chat/**")
    public ResponseEntity<String> chatService(
            @RequestBody(required = false) String body,
            HttpMethod method,
            HttpServletRequest request) {
        return forward(request, body, method, CHAT_SERVICE);
    }

    private ResponseEntity<String> forward(
            HttpServletRequest request,
            String body,
            HttpMethod method,
            String serviceUrl) {

        String uri = serviceUrl + request.getRequestURI();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(body, headers);

        return restTemplate.exchange(uri, method, entity, String.class);
    }
}