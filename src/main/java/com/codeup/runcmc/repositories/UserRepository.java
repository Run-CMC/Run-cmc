package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

    @Query(nativeQuery = true, value = "delete from favorites where topster_id = :tid")
    void deleteFavoritesByTopsterId(@Param("tid") long topsterId);
}
