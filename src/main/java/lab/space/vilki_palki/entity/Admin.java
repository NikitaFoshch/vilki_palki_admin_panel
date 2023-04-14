package lab.space.vilki_palki.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lab.space.vilki_palki.entity.common.MappedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "admin")
@Data
@EqualsAndHashCode(callSuper = true)
public class Admin extends MappedEntity implements UserDetails {
    @Column(length = 50)
    private String firstname;
    @Column(length = 50)
    private String lastname;
    @Column(length = 100)
    private String password;
    @Column(length = 50)
    private String email;
    private int securityLevel;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
