package app.loja_dev.dto;

import app.loja_dev.entities.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CarteiraDTO implements Serializable {

    private Long id;
    private Double saldo;
    private Usuario usuario;
}
