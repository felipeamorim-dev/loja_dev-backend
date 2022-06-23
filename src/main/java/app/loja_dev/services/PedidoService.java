package app.loja_dev.services;

import app.loja_dev.dto.PedidoDTO;
import app.loja_dev.entities.Pedido;

public interface PedidoService {

    Pedido createPedido(PedidoDTO pedidoDTO);
}
