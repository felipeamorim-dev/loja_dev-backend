package app.loja_dev.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class Item extends Default{

    @ManyToOne
    @JoinColumn(name = "produto_id", referencedColumnName = "id")
    private Produto produto;

    @Column(name = "quantidade")
    private Integer quantidade;

    @Column(name = "preco_unitario")
    private Double preco;

    @ManyToMany(mappedBy = "itens", fetch = FetchType.LAZY)
    private List<Pedido> pedidos;

    @ManyToMany(mappedBy = "itens", fetch = FetchType.LAZY)
    private List<Carrinho> carrinhos;
}
