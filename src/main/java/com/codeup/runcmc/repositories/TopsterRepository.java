package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.Topster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface TopsterRepository extends JpaRepository<Topster, Long> {
    Topster findByTitle(String title);

    Topster getById(long id);

	@Query(nativeQuery = true, value = "select * from topsters where user_id = :i")
    List<Topster> findAllByUser(@Param("i") long id);

    List<Topster> findAllByTitle(String title);

    List<Topster> findByTitleIgnoreCaseContaining(String title);

    @Query(nativeQuery = true, value = "select * from topsters where title like lower(concat('%', concat(:title, '%')))")
    List<Topster> findByTitleLike(@Param("title")String title);

    List<Topster> findByTitleIgnoreCase(String title);

    @Query(nativeQuery = true, value = "select distinct title, topsters.id, topsters.body, topsters.publicity, topsters.user_id from topsters " +
            "join topster_contents tc on topsters.id = tc.topster_id " +
            "join albums on albums.id = tc.album_id " +
            "where albums.spotify_album_name like lower(concat('%', concat(:title, '%'))) and publicity = true or " +
            "title like lower(concat('%', concat(:title, '%'))) and publicity = true or " +
            "albums.spotify_artist like lower(concat('%', concat(:title, '%'))) and publicity = true")
    List<Topster> findAllBySearchTerm(@Param("title") String title);


    @Query(nativeQuery = true, value = "select title, topsters.id, topsters.body, topsters.publicity, topsters.user_id from topsters " +
            "join topster_contents tc on topsters.id = tc.topster_id " +
            "join albums on albums.id = tc.album_id " +
            "where albums.id = :albumID and publicity = true")
    List<Topster> findAllByAlbumID(@Param("albumID") long albumID);

    @Query(nativeQuery = true, value = "select * from topsters where publicity = :t")
		    List<Topster>findAllByPublicity(@Param("t") boolean publicity);

//    @Modifying
//    @Query ("delete from Favorites where topster_id = ?1")
    void deleteById(long id);
}