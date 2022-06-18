package app.loja_dev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "items")
@Data
@NoArgsConstructor
public class Item extends Default{

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private Double preco;

    @JsonIgnore
    @ManyToMany(mappedBy = "itens", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    @JsonIgnore
    @ManyToMany(mappedBy = "itens", fetch = FetchType.LAZY)
    private List<Carrinho> carrinhos;
}
