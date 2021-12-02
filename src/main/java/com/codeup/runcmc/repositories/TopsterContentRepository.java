package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.Album;
import com.codeup.runcmc.models.TopsterContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TopsterContentRepository extends JpaRepository<TopsterContent, Long> {

@Query(nativeQuery = true, value =  "select * from topster_contents join topsters t on t.id = topster_contents.topster_id where topster_id = :i")
	List<TopsterContent> selectTopsterContentWithTopsterID(@Param("i") long id);

}
