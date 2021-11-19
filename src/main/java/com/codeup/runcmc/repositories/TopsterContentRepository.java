package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.TopsterContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopsterContentRepository extends JpaRepository<TopsterContent, Long> {
    TopsterContent findBySpotifyAlbumName(String name);

    TopsterContent findBySpotifyAlbumID(Long id); //this might need to change
}
