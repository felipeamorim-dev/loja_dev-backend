package app.loja_dev.repositories;

import app.loja_dev.entities.ItemPedido;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemPedidoRepository extends DefaultRepository<ItemPedido, Long> {

    @Query("SELECT ip FROM ItemPedido ip WHERE ip.pedidoId = :pedidoId")
    List<ItemPedido> findAllPerPedidoId(@Param("pedidoId") Long pedidoId);
}
