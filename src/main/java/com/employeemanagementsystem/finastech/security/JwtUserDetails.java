package com.employeemanagementsystem.finastech.security;

import com.employeemanagementsystem.finastech.entity.Role;
import com.employeemanagementsystem.finastech.entity.User;
import com.employeemanagementsystem.finastech.repository.RoleRepository;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Getter
@Setter
public class JwtUserDetails implements UserDetails {

    public Long id;
    private String userName;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    private JwtUserDetails(Long id, String userName, String password, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.authorities = authorities;
    }

    public static JwtUserDetails create(User user) {
        List<Role> roles = user.getRoles();

        List<GrantedAuthority> authoritiesList = new ArrayList<>();
        //authoritiesList.add(new SimpleGrantedAuthority("user"));

        for(Role role : roles) {
            authoritiesList.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return new JwtUserDetails(user.getId(), user.getUserName(), user.getPassword(),authoritiesList);
    }

    @Override
    public String getUsername() {
        return null;
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
