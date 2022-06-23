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
    @NotNull
    private Long usuarioId;
    @NotNull
    private Instant momento;
    private List<Item> itens = Collections.emptyList();
}
