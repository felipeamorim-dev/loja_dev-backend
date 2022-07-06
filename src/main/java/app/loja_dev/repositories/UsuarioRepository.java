package app.loja_dev.repositories;

import app.loja_dev.dto.UsuarioDTO;
import app.loja_dev.entities.Usuario;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends DefaultRepository<Usuario, Long>{

    @Query(nativeQuery = true, value = "SELECT u.id, u.nome, u.username_github, u.email, u.senha, u.url_image_perfil, u.id_carrinho\r\n " +
            "FROM usuario u INNER JOIN carteira c ON u.id = c.id_usuario INNER JOIN carrinho car ON u.id_carrinho = car.id WHERE u.id = :id")
    Optional<Usuario> findById(@Param("id") Long id);

    Optional<Usuario> findByNomeUsuario(String nomeUsuario);

}
