package app.loja_dev.repositories;

import app.loja_dev.entities.Pedido;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends DefaultRepository<Pedido, Long>{
}
