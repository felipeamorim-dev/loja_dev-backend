package app.loja_dev.dto;

import app.loja_dev.enums.Perfil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {


    private Long id;
    @NotNull(message = "O nome do usuário é requerido")
    private String nome;
    private String nomeUsuario;
    private String urlImagePerfil;
    private Set<Perfil> perfils = new HashSet<>();
}
