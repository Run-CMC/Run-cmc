package com.codeup.runcmc.repositories;

import com.codeup.runcmc.models.Topster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopsterRepository extends JpaRepository<Topster, Long> {
    Topster findByTitle(String title);
}
