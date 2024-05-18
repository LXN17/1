package ru.mirea.paintball.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.paintball.entity.Map;
import ru.mirea.paintball.entity.MapDTO;
import ru.mirea.paintball.services.MapService;

import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {

    @Autowired
    private MapService mapService;

    @GetMapping("/read")
    public ResponseEntity<List<Map>> readAll() {
        return new ResponseEntity<>(mapService.readAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Map> create(@RequestBody MapDTO dto) {
        return new ResponseEntity<>(mapService.create(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        mapService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
