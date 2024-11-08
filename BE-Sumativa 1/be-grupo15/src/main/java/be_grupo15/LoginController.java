package be_grupo15;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class LoginController {

    @Autowired
    JWTAuthtenticationConfig jwtAuthtenticationConfig;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @PostMapping("login")
    public String login(
            @RequestParam("user") String username,
            @RequestParam("encryptedPass") String encryptedPass) {
        
                final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if (!userDetails.getPassword().equals(encryptedPass)) {
                    throw new RuntimeException("Invalid login");
                    
                }

                String token = jwtAuthtenticationConfig.getJWTToken(username);
        
        return token;
    }
    
}
