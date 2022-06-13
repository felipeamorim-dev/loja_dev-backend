package app.loja_dev.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "item_pedido")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class ItemPedido extends Default{

    @Column(name = "pedido_id")
    private Long pedidoId;

    @Column(name = "item_id")
    private Long itemId;

}
