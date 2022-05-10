package app.loja_dev.dto;

import app.loja_dev.entities.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CarrinhoRequestDTO extends Default {

    @NotNull
    private Long usuarioId;
    @NotNull
    private Long produtoId;
}
