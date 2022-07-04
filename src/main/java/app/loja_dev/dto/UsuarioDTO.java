package app.loja_dev.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {


    private Long id;
    @NotNull(message = "O nome do usuário é requerido")
    private String nome;
    private String nomeUsuario;
    private String urlImagePerfil;
}
