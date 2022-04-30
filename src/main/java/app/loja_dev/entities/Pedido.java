package app.loja_dev.entities;

import app.loja_dev.enums.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pedido")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido extends Default {

    private Integer statusPedido;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
    @Column(name = "data_compra")
    private Instant momento;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @OneToMany(mappedBy = "pedido")
    private Set<ItemPedido> itemPedidos = new HashSet<>();

    public Pedido(Instant momento, StatusPedido statusPedido, Usuario usuario){
        this.momento = momento;
        setStatusPedido(statusPedido);
        this.usuario = usuario;
    }

    public void setStatusPedido(StatusPedido statusPedido) {
        if(statusPedido != null) this.statusPedido = statusPedido.getCode();
    }

    public StatusPedido getStatusPedido(){
        return StatusPedido.of(statusPedido);
    }
}
