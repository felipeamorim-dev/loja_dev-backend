package app.loja_dev.entities;

import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.LazyCollection;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Item> itens = new ArrayList<>();

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

}
