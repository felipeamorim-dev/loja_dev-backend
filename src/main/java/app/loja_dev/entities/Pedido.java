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
public class Pedido extends Default {

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "status")
    private StatusPedido statusPedido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(name = "data_compra")
    private Instant momento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_pedido",
            joinColumns = @JoinColumn(name = "pedido_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> itens = Collections.emptyList();

    public Pedido(Instant momento, StatusPedido statusPedido, Usuario usuario){
        this.momento = momento;
        setStatusPedido(statusPedido);
        this.usuario = usuario;
    }
}


