package ru.mirea.paintball.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    private String fio;
    @OneToMany
    @JoinColumn(name = "maps_id")
    private List<Map> writtenMaps=new ArrayList<>();

    public void addMap(Map map) {
        this.writtenMaps.add(map);
    }

    public void removeMap(Map map) {
        this.writtenMaps.remove(map);
    }

}
