package app.loja_dev.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

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

    @JsonIgnore
    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private Carteira carteira;

}
