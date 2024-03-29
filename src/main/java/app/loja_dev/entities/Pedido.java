package app.loja_dev.entities;

import app.loja_dev.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.Instant;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "pedido")
@NamedEntityGraph(name = "pedido_graph", attributeNodes = {
        @NamedAttributeNode(value = "usuario", subgraph = "us"),
        @NamedAttributeNode(value = "itens", subgraph = "item"),
    }, subgraphs = {
        @NamedSubgraph(name = "us", attributeNodes = @NamedAttributeNode("carteira")),
        @NamedSubgraph(name = "item", attributeNodes = @NamedAttributeNode("produto")),
})
public class Pedido extends Default {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private StatusPedido statusPedido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(name = "data_compra")
    private Instant momento;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "total")
    private Double total;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_pedido",
            joinColumns = @JoinColumn(name = "pedido_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> itens = new ArrayList<>();

    public Pedido(Instant momento, StatusPedido statusPedido, Usuario usuario){
        this.momento = momento;
        setStatusPedido(statusPedido);
        this.usuario = usuario;
    }
}


