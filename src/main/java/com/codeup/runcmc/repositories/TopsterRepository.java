package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.Topster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopsterRepository extends JpaRepository<Topster, Long> {
    Topster findByTitle(String title);
    Topster getById(long id);
	@Query(nativeQuery = true, value = "select * from topsters where user_id = :i")
    List<Topster> findAllByUser(@Param("i") long id);
}
