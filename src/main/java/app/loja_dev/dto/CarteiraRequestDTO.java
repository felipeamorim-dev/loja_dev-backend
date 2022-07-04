package app.loja_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarteiraRequestDTO {

    @NotNull(message = "O id do usuário é requerido")
    private Long usuarioId;
    @NotNull(message = "O saldo da conta do usuário é requerido")
    private Double valor;
}
