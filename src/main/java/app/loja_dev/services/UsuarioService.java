package app.loja_dev.services;

import app.loja_dev.entities.Usuario;

import java.util.Optional;

public interface UsuarioService extends DefaultService<Usuario, Long>{
    Usuario findByNomeUsuario(String nomeUsuario);
}
