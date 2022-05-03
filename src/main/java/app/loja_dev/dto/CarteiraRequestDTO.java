package app.loja_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarteiraRequestDTO {

    @NotNull
    private Long usuarioId;
    @NotNull
    private Double valor;
}
