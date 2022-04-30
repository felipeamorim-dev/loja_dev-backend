package app.loja_dev.services;

import app.loja_dev.entities.Usuario;
import app.loja_dev.repositories.DefaultRepository;
import app.loja_dev.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl extends DefaultServiceImpl<Usuario, Long> implements UsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(DefaultRepository<Usuario, Long> defaultRepository) {
        super(defaultRepository);
    }
}
