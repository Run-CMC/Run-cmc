package com.codeup.runcmc.services;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;

@Component
public class RestTemplateTokenRequester {

    @Value("${spotify_client_id}")
    private String spotifyClientID;

    @Value("${spotify_client_secret}")
    private String spotifyClientSecret;

    public RestTemplateTokenRequester(@Value("${spotify_client_id}") String spotifyClientID, @Value("${spotify_client_secret}") String spotifyClientSecret) {
        this.spotifyClientID = spotifyClientID;
        this.spotifyClientSecret = spotifyClientSecret;
    }

    public String getSpotifyClientID() {
        return spotifyClientID;
    }

    public String getSpotifyClientSecret() {
        return spotifyClientSecret;
    }

    public TokenResponse requestAccessToken() {
        // Create a RestTemplate to describe the request
        RestTemplate restTemplate = new RestTemplate();
//        System.out.println(spotifyClientID);
//        System.out.println(spotifyClientSecret);
        // Specify the http headers that we want to attach to the request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", createAuthHeaderString(spotifyClientID, spotifyClientSecret));

        // Create a map of the key/value pairs that we want to supply in the body of the request
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("response_type","token");
//        map.add("client_id","ClientId");
//        map.add("username","user");
//        map.add("password","userpassword");
        map.add("scope","");
        map.add("grant_type","client_credentials");

        // Create an HttpEntity object, wrapping the body and headers of the request
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(map, headers);

        // Execute the request, as a POSt, and expecting a TokenResponse object in return
        ResponseEntity<TokenResponse> response =
                restTemplate.exchange("https://accounts.spotify.com/api/token",
                        HttpMethod.POST,
                        entity,
                        TokenResponse.class);

        return response.getBody();
    }

    // Just a helper method to create the basic auth header
    private static String createAuthHeaderString(String username, String password) {
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(StandardCharsets.US_ASCII));
        String authHeader = "Basic " + new String(encodedAuth);
        return authHeader;
    }
}