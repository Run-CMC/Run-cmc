package com.codeup.runcmc.services;

import com.codeup.runcmc.models.AlbumResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;

public class AlbumJsonDeserializer extends JsonDeserializer<AlbumResponse> {

    public AlbumResponse deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException
    {
        JsonNode rootNode = p.getCodec().readTree(p);

        String albumArt = rootNode.findPath("url").asText();
        String albumName = rootNode.get("name").asText();
        String artistName = rootNode.findPath("name").asText();
        String releaseDate = rootNode.get("release_date").asText();
//        String artist = node.get("name").asText();
//        String artist = rootNode.get("artists").get("name").asText();
//        System.out.println(artistsNode);
//        System.out.println(artistsNode.elements());
//        JsonNode artistName = artistsNode.get("name");
//        System.out.println(artistName);
//        Iterator<JsonNode> artistsIterator = artistsNode.elements();
//        while (artistsIterator.hasNext()) {
//            System.out.println(artistsIterator.next() + " ");
//        }
//        JsonNode artistNameNode = artistsNode.get(3);
//        System.out.println(artistNameNode);
//        String artistName = artistNameNode.asText();
//        System.out.println(artistName);
//        String[] artistsArray = new String[0];
//        List<String> artistsList = new ArrayList<>();
//        for (JsonNode node : artistsNode) {
//            artistsList.add(node.asText());
//        }

        return new AlbumResponse(albumArt, albumName, artistName, releaseDate);
    }

}
