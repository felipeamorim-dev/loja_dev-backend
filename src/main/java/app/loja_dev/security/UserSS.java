package app.loja_dev.security;

import app.loja_dev.enums.Perfil;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

public class UserSS implements UserDetails {

    @Getter
    private Long id;
    private String nomeUsuario;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(Long id, String nomeUsuario, String senha, Set<Perfil> perfis) {
        this.id = id;
        this.nomeUsuario = nomeUsuario;
        this.senha = senha;
        this.authorities = perfis.stream()
                .map(perfil -> new SimpleGrantedAuthority(perfil.name()))
                .collect(Collectors.toSet());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.senha;
    }

    @Override
    public String getUsername() {
        return this.nomeUsuario;
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
