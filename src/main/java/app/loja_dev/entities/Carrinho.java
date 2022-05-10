package app.loja_dev.entities;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "carrinho")
@Data
public class Carrinho extends Default{

    @OneToMany(mappedBy = "carrinho", fetch = FetchType.EAGER)
    private Set<Produto> produtos = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private Usuario usuario;

    public void setProdutos(Produto p){
        produtos.add(p);
    }

}
