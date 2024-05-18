package ru.mirea.paintball.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.mirea.paintball.entity.Map;
import ru.mirea.paintball.repository.MapRepository;

import java.util.ArrayList;

@Configuration
public class DataConfig {
    @Bean
    public CommandLineRunner loadData(MapRepository mapRepository) {
        return (args) -> {
            if (mapRepository.findAll().isEmpty()) {
                var maps  = new ArrayList<Map>();

                maps.add(Map.builder()
                        .mapname("Бункер")
                        .date("22.03.2025").build());

                maps.add(Map.builder()
                        .mapname("Кольцо")
                        .date("31.07.2024").build());

                maps.add(Map.builder()
                        .mapname("Реактор")
                        .date("06.07.2024").build());

                maps.add(Map.builder()
                        .mapname("Офис")
                        .date("21.07.2024").build());

                maps.add(Map.builder()
                        .mapname("Вертиго")
                        .date("22.05.2024").build());

                maps.add(Map.builder()
                        .mapname("Кеш")
                        .date("22.01.2025").build());



                mapRepository.saveAll(maps);
            }
        };
    }
}