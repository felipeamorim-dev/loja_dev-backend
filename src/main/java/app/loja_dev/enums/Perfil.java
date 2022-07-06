package app.loja_dev.enums;

import lombok.Getter;

public enum Perfil {

    ADMIN(0),
    CLIENTE(1);

    @Getter
    private int code;

    private Perfil(int code){
        this.code = code;
    }

    public static Perfil of(int code) {

        for(Perfil perfil : Perfil.values()) {
            if (perfil.getCode() == code) return perfil;
        }
        throw new IllegalArgumentException("Código de status do pedido inválido.");
    }
}
