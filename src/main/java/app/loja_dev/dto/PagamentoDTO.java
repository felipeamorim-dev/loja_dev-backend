package app.loja_dev.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PagamentoDTO {

    private Long id;

    @NotNull
    private Long pedidoId;

}
