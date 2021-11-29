package com.codeup.runcmc.services;

import com.codeup.runcmc.models.AlbumResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

// Source: https://stackoverflow.com/questions/62382614/get-access-Album-from-oauth2-rest-api-using-java

@Component
public class RestTemplateAlbumRequester {

    public static AlbumResponse requestAccessAlbum(String accessToken, String spotifyID) {
        // Create a RestTemplate to describe the request
        RestTemplate restTemplate = new RestTemplate();

        // Specify the http headers that we want to attach to the request
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.add("Authorization", "Bearer " + accessToken);

        // Create an HttpEntity object, wrapping the body and headers of the request
        HttpEntity<Object> entity = new HttpEntity<>(headers);

        // Execute the request, as a GET, and expecting a AlbumResponse object in return
        ResponseEntity<AlbumResponse> response = restTemplate.exchange("https://api.spotify.com/v1/albums/" + spotifyID,
                HttpMethod.GET,
                entity,
                AlbumResponse.class);

        return response.getBody();
    }

}