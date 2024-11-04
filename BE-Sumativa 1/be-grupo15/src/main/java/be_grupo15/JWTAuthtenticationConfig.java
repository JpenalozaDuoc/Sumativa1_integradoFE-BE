package be_grupo15;

import static be_grupo15.Constants.SUPER_SECRET_KEY;
import static be_grupo15.Constants.getSigningKey;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import io.jsonwebtoken.Jwts;


@Configuration
public class JWTAuthtenticationConfig {

    public String getJWTToken(String name) {
        List<GrantedAuthority> grantedAUthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
    

    Map<String, Object> claims = new HashMap<>();
    claims.put("authorities",grantedAUthorities.stream().
            map(GrantedAuthority::getAuthority).
            collect(Collectors.toList()));

    String token = Jwts.builder()
        .claims()
        .add(claims)
        .subject(name)
        .issuedAt(new Date(System.currentTimeMillis()))
        .expiration(new Date(System.currentTimeMillis()+1000 * 60 * 1440 ))
        .and()
        .signWith(getSigningKey(SUPER_SECRET_KEY))
        .compact();

    return"Bearer "+token;
    }
}