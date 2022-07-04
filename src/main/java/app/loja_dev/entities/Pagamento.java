package app.loja_dev.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pagamento")
@AllArgsConstructor
@NoArgsConstructor
public class Pagamento extends Default{

    @Column(name = "autorizacao")
    private boolean autorizacao;

    @Column(name = "mensagem", length = 100)
    private String mensagem;

    @OneToOne
    @JoinColumn(name = "pedido_id", referencedColumnName = "id")
    private Pedido pedido;
}
