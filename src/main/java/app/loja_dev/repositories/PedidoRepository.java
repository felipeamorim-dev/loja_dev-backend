package app.loja_dev.repositories;

import app.loja_dev.entities.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface PedidoRepository extends DefaultRepository<Pedido, Long>{

    @Query(value = "SELECT p.* FROM pedido p WHERE p.data_compra >= :momento and p.usuario_id = :usuarioId", nativeQuery = true)
    Optional<Pedido> findByPedidoPerMomentAndUsuarioId(@Param("momento") Instant momento, @Param("usuarioId") Long usuarioId);

    @EntityGraph(type = EntityGraph.EntityGraphType.FETCH, value = "pedido_graph")
    @Query("SELECT p FROM Pedido p JOIN p.itens ip WHERE p.id = :id")
    Optional<Pedido> findPedidoById(@Param("id") Long id);

}
