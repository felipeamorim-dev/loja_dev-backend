package app.loja_dev.entities;

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

    @OneToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "saldo")
    private Double saldo;
}
