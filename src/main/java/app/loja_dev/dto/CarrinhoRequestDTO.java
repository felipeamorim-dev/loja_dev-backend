package app.loja_dev.dto;

import app.loja_dev.entities.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CarrinhoRequestDTO extends Default {

    @NotNull(message = "O id do usuário é requerido")
    private Long usuarioId;
    @NotNull(message = "O id do produto é requerido")
    private Long produtoId;
}
