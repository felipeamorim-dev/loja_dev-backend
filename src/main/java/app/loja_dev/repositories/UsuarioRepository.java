package app.loja_dev.repositories;

import app.loja_dev.entities.Usuario;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends DefaultRepository<Usuario, Long>{
}
