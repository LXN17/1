package ru.mirea.paintball.config;


import org.json.JSONException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;
import ru.mirea.paintball.services.MyUserDetails;
import java.util.Base64;

@Component
public class JwtCore {
    @Value("${secret}")
    private String secret;

    public String generateToken(Authentication authentication){
        MyUserDetails myUserDetails = (MyUserDetails) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject(myUserDetails.getUsername())
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
    }

    public String getNameFromJwt(String token) throws JSONException {
        String[] chunks = token.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        org.json.JSONObject jsonObject = new org.json.JSONObject(payload);
        return jsonObject.getString("sub");
    }
}
