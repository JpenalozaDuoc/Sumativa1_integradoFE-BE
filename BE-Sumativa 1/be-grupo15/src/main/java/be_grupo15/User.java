package be_grupo15;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table
@Data
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    private String name;
    private String email;
    private String password;

    //getter and setter
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return name;
    }
    public void setUsername(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>(); // o devuelve roles si los tienes
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // lógica según tu necesidad
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // lógica según tu necesidad
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // lógica según tu necesidad
    }

    @Override
    public boolean isEnabled() {
        return true; // lógica según tu necesidad
    }


}
