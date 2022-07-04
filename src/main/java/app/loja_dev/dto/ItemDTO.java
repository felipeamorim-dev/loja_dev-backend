package app.loja_dev.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemDTO {

    private Long id;
    @NotNull(message = "A quantidade de do produto no item é requerido")
    private Integer quantidade;
    @NotNull(message = "O preço do produto é requerido")
    private Double preco;
    @NotNull(message = "O id do produto é requerido")
    private Long produtoId;
}
