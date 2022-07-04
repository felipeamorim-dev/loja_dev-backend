package app.loja_dev.dto.response;

import app.loja_dev.entities.Item;
import app.loja_dev.enums.StatusPedido;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Data
public class PedidoResponse {

    private Long id;
    @NotNull(message = "A data da criação do pedido é requerida")
    private Instant momento;
    @NotNull(message = "O status do pedido é requerido")
    private StatusPedido statusPedido;
    @NotNull(message = "O id do usuário é requerido")
    private Long usuarioId;
    private String nomeUsuario;
    private List<Item> itens = Collections.emptyList();
    private Double total;
}
