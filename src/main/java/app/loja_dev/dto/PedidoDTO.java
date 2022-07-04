package app.loja_dev.dto;


import app.loja_dev.entities.Item;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.Collections;
import java.util.List;

@Data
public class PedidoDTO {

    private Long id;
    @NotNull(message = "Id do usuário requerido")
    private Long usuarioId;
    @NotNull(message = "A data e hora do pedido deve ser registrada")
    private Instant momento;
    private List<Item> itens = Collections.emptyList();
}
