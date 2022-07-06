package app.loja_dev.services;

import app.loja_dev.entities.Usuario;
import app.loja_dev.repositories.UsuarioRepository;
import app.loja_dev.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> user = usuarioRepository.findByNomeUsuario(username);
        if(user.isPresent())
            return new UserSS(user.get().getId(), user.get().getNomeUsuario(), user.get().getSenha(), user.get().getPerfis());

        throw new UsernameNotFoundException("username not found");
    }
}
