package ru.mirea.paintball.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.mirea.paintball.entity.Map;
import ru.mirea.paintball.entity.MapDTO;
import ru.mirea.paintball.repository.MapRepository;

import java.util.List;

@Service
@AllArgsConstructor

public class MapService {
    @Autowired
    private MapRepository mapRepository;

    public Map create(MapDTO dto) {
        Map map = Map.builder()
                .mapname(dto.getMapname())
                .date(dto.getDate())
                .build();
        return mapRepository.save(map);
    }

    public List<Map> readAll(){
        return mapRepository.findAll();
    }

    public void delete(Long id){
        mapRepository.deleteById(id);
    }

}
