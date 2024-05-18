package ru.mirea.paintball.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mirea.paintball.entity.Map;

import java.util.Optional;

public interface MapRepository extends JpaRepository<Map, Long> {
    Optional<Map> findByMapname(String mapname);
}
