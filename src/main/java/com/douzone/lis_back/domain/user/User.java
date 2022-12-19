package com.douzone.lis_back.domain.user;

import lombok.Builder;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


@Data
@Builder
public class User implements UserDetails {

    private Long userId;

    private String departmentCode;

    private String username;

    private String password;

    private String name;

    private String userPhoneNumber;

    private String userAddress;

    private String authority;

    private String userEmail;

    private LocalDate joinDt;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> Author = new ArrayList<GrantedAuthority>();
        Author.add(new SimpleGrantedAuthority(authority));
        return Author;
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
