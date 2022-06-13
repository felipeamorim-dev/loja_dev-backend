package app.loja_dev.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Table(name = "carrinho")
@Data
public class Carrinho extends Default{

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "item_carrinho",
            joinColumns = @JoinColumn(name = "carrinho_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id")
    )
    private List<Item> itens = Collections.emptyList();

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;


}
