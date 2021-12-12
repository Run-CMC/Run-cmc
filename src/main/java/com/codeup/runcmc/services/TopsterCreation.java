package com.codeup.runcmc.services;

import com.codeup.runcmc.models.Album;
import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.models.TopsterContent;
import com.codeup.runcmc.repositories.AlbumRepository;
import com.codeup.runcmc.repositories.TopsterContentRepository;
import com.codeup.runcmc.repositories.TopsterRepository;
import com.codeup.runcmc.repositories.UserRepository;
import org.springframework.validation.Errors;

import java.util.ArrayList;
import java.util.List;

public class TopsterCreation {

//    private static RestTemplateTokenRequester restTemplateTokenRequester;
//
//    private static TopsterRepository topsterRepository;
//
//    private static AlbumRepository albumRepository;
//
//    private static UserRepository userRepository;
//
//    private static TopsterContentRepository topsterContentRepository;

    //returns true if none of the relevant album fields are blank
    public static boolean topsterValidator(String topsterType, String[] srcs, String[] titles){
        if(topsterType.equals("3x3")){
            for(int i = 0; i < 9; i++){
                if(srcs[i].equals("")||titles[i].equals("")){
                    return false;
                }
            }
            return true;
        }else if(topsterType.equals("4x4")){
            for(int i = 9; i < 25; i++){
                if(srcs[i].equals("")||titles[i].equals("")){
                    return false;
                }
            }
            return true;
        }else if(topsterType.equals("5x5")){
            for(int i = 25; i < 50; i++){
                if(srcs[i].equals("")||titles[i].equals("")){
                    return false;
                }
            }
            return true;
        } else {
            System.out.println("This should never happen if you coded things right.");
            return false;
        }
    }
    public static List<TopsterContent> createTopsters(
            Topster topster,
            String topsterType,
            String[] srcs,
            String[] titles,
            String[] artists,
            String[] releaseDates,
            int[] positions,
            String[] spotifyIDs,
            AlbumRepository albumRepository,
            TopsterContentRepository topsterContentRepository,
            Errors validation){
        List<TopsterContent> topsterContents = new ArrayList<TopsterContent>();
        if(topsterType.equals("3x3")){
            System.out.println("going down the 3x3 path");
//            parsing the first 9 values (which will be from the 3x3) into albums
            for(int i = 0; i < 9; i++){
                Album album;
                if(!albumRepository.existsAlbumBySpotifyAlbumID(spotifyIDs[i])){
                    album = new Album();
                    album.setSpotifyAlbumArt(srcs[i]);
                    album.setReleaseDate(releaseDates[i]);
                    album.setSpotifyAlbumName(titles[i]);
                    album.setSpotifyArtist(artists[i]);
                    album.setSpotifyAlbumID(spotifyIDs[i]);
                } else {
//                    get the album INCLUDING topsterContent List from db if it already exists
                    album = new Album(albumRepository.findBySpotifyAlbumID(spotifyIDs[i]));
                }
//                set values on TopsterContent object
                TopsterContent topsterContent = new TopsterContent();
                topsterContent.setTopster(topster);
                topsterContent.setPosition(positions[i]);
                topsterContent.setAlbum(album);

                topsterContents.add(topsterContent);
//                maybe this shouldn't happen here
                albumRepository.save(album);
                topsterContentRepository.save(topsterContent);
            }
            return topsterContents;
        } else if(topsterType.equals("4x4")){
            System.out.println("going down the 4x4 path");
            for(int i = 9; i < 25; i++){
                Album album;
                if(albumRepository.findBySpotifyAlbumID(spotifyIDs[i]) == null){
                    album = new Album();
                    album.setSpotifyAlbumArt(srcs[i]);
                    album.setReleaseDate(releaseDates[i]);
                    album.setSpotifyAlbumName(titles[i]);
                    album.setSpotifyArtist(artists[i]);
                    album.setSpotifyAlbumID(spotifyIDs[i]);
                } else {
//                    get the album INCLUDING topsterContent List from db if it already exists
                    album = new Album(albumRepository.findBySpotifyAlbumID(spotifyIDs[i]));
                }
//                set values on TopsterContent object
                TopsterContent topsterContent = new TopsterContent();
                topsterContent.setTopster(topster);
                topsterContent.setPosition(positions[i]);
                topsterContent.setAlbum(album);

                topsterContents.add(topsterContent);
//                maybe this shouldn't happen here
                albumRepository.save(album);
                topsterContentRepository.save(topsterContent);
            }
            return topsterContents;
        }else if(topsterType.equals("5x5")){
            System.out.println("going down the 5x5 path");
            for(int i = 25; i < 50; i++){
                Album album;
                if(albumRepository.findBySpotifyAlbumID(spotifyIDs[i]) == null){
                    album = new Album();
                    album.setSpotifyAlbumArt(srcs[i]);
                    album.setReleaseDate(releaseDates[i]);
                    album.setSpotifyAlbumName(titles[i]);
                    album.setSpotifyArtist(artists[i]);
                    album.setSpotifyAlbumID(spotifyIDs[i]);
                } else {
//                    get the album INCLUDING topsterContent List from db if it already exists
                    album = new Album(albumRepository.findBySpotifyAlbumID(spotifyIDs[i]));
                }
//                set values on TopsterContent object
                TopsterContent topsterContent = new TopsterContent();
                topsterContent.setTopster(topster);
                topsterContent.setPosition(positions[i]);
                topsterContent.setAlbum(album);

                topsterContents.add(topsterContent);
//                maybe this shouldn't happen here
                albumRepository.save(album);
                topsterContentRepository.save(topsterContent);
            }
            return topsterContents;
        } else{
            System.out.println("Uhhhh.... this shouldn't be happening");
            return topsterContents;
        }
    }
}
