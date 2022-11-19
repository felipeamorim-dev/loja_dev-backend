package app.loja_dev.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoDTO {

    private Long id;

    @NotNull(message = "Nome do produto é requerido")
    private String nome;

    @NotNull(message = "A descrição do produto é requerida")
    private String descricao;

    @NotNull(message = "O preço do produto é requerido")
    private Double preco;

    private String urlImage;
}
