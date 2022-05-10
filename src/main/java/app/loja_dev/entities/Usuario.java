package app.loja_dev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.util.Lazy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Usuario extends Default{

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "username_github", length = 100, nullable = false)
    private String nomeUsuario;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(unique = true, name = "senha", nullable = false)
    private String senha;

    @Column(name = "urlImagePerfil")
    private String urlImagePerfil;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pedido> pedidos = new ArrayList<>();

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Carteira carteira;

    @OneToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL) @JsonIgnore
    @JoinColumn(name = "id_carrinho", referencedColumnName = "id")
    private Carrinho carrinho;

}
