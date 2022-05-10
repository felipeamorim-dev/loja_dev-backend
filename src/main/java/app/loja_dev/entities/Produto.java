package app.loja_dev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "url_image")
    private String urlImage;

    @JsonIgnore
    @OneToMany(mappedBy = "produto", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ItemPedido> itemPedidos = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER) @JsonIgnore
    @JoinColumn(name = "id_carrinho", referencedColumnName = "id")
    private Carrinho carrinho;
}
