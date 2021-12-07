package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.Album;
import com.codeup.runcmc.models.Topster;
import com.codeup.runcmc.models.TopsterContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AlbumRepository extends JpaRepository <Album, Long> {
	Album getById(long id);

	Album findBySpotifyAlbumID(String spotifyAlbumID);

	boolean existsAlbumBySpotifyAlbumID(String spotifyAlbumID);

	@Query(nativeQuery = true, value = "select * from albums join topster_contents tc on albums.id = tc.album_id join albums a on a.id = tc.album_id join topsters t on t.id = tc.topster_id where topster_id = :i")
	List<Album> selectWithTopsterFK(@Param("i") long id);

}