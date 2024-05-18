package ru.mirea.paintball.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.mirea.paintball.config.JwtCore;
import ru.mirea.paintball.entity.User;
import ru.mirea.paintball.entity.SignInRequest;
import ru.mirea.paintball.entity.SignUpRequest;
import ru.mirea.paintball.repository.UserRepository;

@RestController
@RequestMapping("/auth")
public class SecurityController {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtCore jwtCore;

    @PostMapping("/sign-in")
    public ResponseEntity<?> signin(@RequestBody SignInRequest signInRequest){
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Неправильное имя пользователя или пароль");
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtCore.generateToken(authentication);
        return ResponseEntity.ok(jwt);
    }

    @PostMapping("/sign-up")
    public ResponseEntity<?> signup(@RequestBody SignUpRequest signUpRequest){
        if(userRepository.existsMyUserByUsername(signUpRequest.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Логин уже существует!");
        }
        User user = new User();
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setFio(signUpRequest.getFio());
        userRepository.save(user);
        return ResponseEntity.ok("Успешная регистрация");
    }
}
