package app.loja_dev.dto;

import app.loja_dev.entities.Default;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CarrinhoRequestDTO extends Default {

    private Long id;
    @NotNull(message = "O id do usuário é requerido")
    private Long usuarioId;
    @NotNull(message = "O id do produto é requerido")
    private Long produtoId;
}
