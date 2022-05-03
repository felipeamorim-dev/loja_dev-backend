package app.loja_dev.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

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

}
