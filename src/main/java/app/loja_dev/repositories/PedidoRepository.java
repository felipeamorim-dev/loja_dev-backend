package app.loja_dev.repositories;

import app.loja_dev.entities.Pedido;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface PedidoRepository extends DefaultRepository<Pedido, Long>{

    @Query(value = "SELECT p.* FROM pedido p WHERE p.data_compra >= :momento and p.usuario_id = :usuarioId", nativeQuery = true)
    Optional<Pedido> findByPedidoPerMomentAndUsuarioId(@Param("momento") Instant momento, @Param("usuarioId") Long usuarioId);

    @Query(value = "SELECT p.*, ip.item_id FROM pedido p INNER JOIN item_pedido ip ON ip.pedido_id = p.id WHERE p.id = :id", nativeQuery = true)
    Optional<Pedido> findPedidoById(@Param("id") Long id);
}
