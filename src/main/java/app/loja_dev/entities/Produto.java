package app.loja_dev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;


@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto extends Default {

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "preco")
    private Double preco;

    @Column(name = "url_image", columnDefinition = "text")
    private String urlImage;

    @JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Item> itens = Collections.emptyList();

    @ManyToOne(fetch = FetchType.EAGER) @JsonIgnore
    @JoinColumn(name = "id_carrinho", referencedColumnName = "id")
    private Carrinho carrinho;
}
