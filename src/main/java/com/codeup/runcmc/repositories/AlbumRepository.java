package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.Album;
import com.codeup.runcmc.models.TopsterContent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumRepository extends JpaRepository <Album, Long> {

}