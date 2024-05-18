package ru.mirea.paintball.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.mirea.paintball.entity.Map;
import ru.mirea.paintball.services.MyUserDetailsService;

import java.security.Principal;
import java.util.List;


@RestController
@RequestMapping("/secured")
public class MainController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @GetMapping("/user/name")
    public String userAccess(Principal principal){
        if(principal == null){
            return null;
        }
        return principal.getName();
    }
    @GetMapping("/user/fio")
    public String fioAccess(Principal principal){
        if(principal == null){
            return null;
        }
        return myUserDetailsService.getFio(principal.getName());
    }

    @PostMapping("/map/add/{mapId}")
    public ResponseEntity<?> addMap(Principal principal, @PathVariable Long mapId) {
        myUserDetailsService.addMap(principal.getName(), mapId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/map/remove/{mapId}")
    public ResponseEntity<?> removeMap(Principal principal, @PathVariable Long mapId) {
        myUserDetailsService.removeMap(principal.getName(), mapId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/maps")
    public ResponseEntity<List<Map>> getMap(Principal principal) {
        List<Map> doctors = myUserDetailsService.getMaps(principal.getName());
        return ResponseEntity.status(HttpStatus.OK).body(doctors);
    }

}
