package app.loja_dev.repositories;

import app.loja_dev.entities.Carrinho;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends DefaultRepository<Carrinho, Long> {

    @Query("select c from Carrinho c where c.usuario.id = :usuarioId")
    Optional<Carrinho> findByUsuario(@Param("usuarioId") Long usuarioId);
}
