package app.loja_dev.services;

import app.loja_dev.dto.PedidoDTO;
import app.loja_dev.entities.Pedido;

import java.util.List;

public interface PedidoService {

    Pedido findPedidoById(Long id);

    Pedido createPedido(PedidoDTO pedidoDTO);

    List<Pedido> findAllPedidoPerStatusAndUsuarioId(Integer status, Long usuarioId);

    void updateStatusPedido(Integer status, Long pedidoId);
}
