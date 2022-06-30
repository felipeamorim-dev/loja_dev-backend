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
    @NotNull
    private Instant momento;
    private StatusPedido statusPedido;
    @NotNull
    private Long usuarioId;
    private String nomeUsuario;
    private List<Item> itens = Collections.emptyList();
    private Double total;
}
