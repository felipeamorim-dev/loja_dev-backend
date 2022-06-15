package app.loja_dev.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ItemDTO {

    private Long id;
    @NotNull
    private Integer quantidade;
    @NotNull
    private Double preco;
    @NotNull
    private Long produtoId;
}
