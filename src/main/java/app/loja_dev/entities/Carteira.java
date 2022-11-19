package app.loja_dev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "carteira")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Carteira extends Default{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    @Column(name = "saldo")
    private Double saldo;

    public Carteira(Usuario usuario, Double saldo) {
        this.usuario = usuario;
        this.saldo = saldo;
    }
}
