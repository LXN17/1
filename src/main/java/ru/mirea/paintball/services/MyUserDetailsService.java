package ru.mirea.paintball.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.mirea.paintball.entity.Map;
import ru.mirea.paintball.entity.User;
import ru.mirea.paintball.repository.MapRepository;
import ru.mirea.paintball.repository.UserRepository;

import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private MapRepository doctorRepository;

    public void addMap(String userName, Long mapId) {
        User user = repository.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + userName));
        Map map = doctorRepository.findById(mapId)
                .orElseThrow(() -> new IllegalArgumentException("карта не найдена: " + mapId));
        user.addMap(map);
        repository.save(user);
    }

    public void removeMap(String userName, Long mapId) {
        User user = repository.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + userName));
        Map map = doctorRepository.findById(mapId)
                .orElseThrow(() -> new IllegalArgumentException("карта не найдена: " + mapId));
        user.removeMap(map);
        repository.save(user);
    }

    public List<Map> getMaps(String userName) {
        User user = repository.findByUsername(userName)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден: " + userName));
        return user.getWrittenMaps();
    }


    public String getFio(String username){
        User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return user.getFio();
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username + " not found"));
        return MyUserDetails.build(user);
    }



}
