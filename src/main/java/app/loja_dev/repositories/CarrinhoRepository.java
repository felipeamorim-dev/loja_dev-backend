package app.loja_dev.repositories;

import app.loja_dev.entities.Carrinho;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrinhoRepository extends DefaultRepository<Carrinho, Long> {

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, attributePaths = {"usuario", "usuario.perfis", "usuario.carteira"})
    @Query("SELECT c FROM Carrinho c JOIN c.usuario u WHERE u.id = :usuarioId")
    Optional<Carrinho> findByUsuario(@Param("usuarioId") Long usuarioId);
}
