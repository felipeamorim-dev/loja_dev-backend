package app.loja_dev.enums;

import lombok.Getter;

import java.util.Arrays;

public enum StatusPedido {

    AGUARDANDO_PAGAMENTO(0),
    PAGO(1),
    ENVIADO(2),
    ENTREGUE(3),
    CANCELADO(4);

    @Getter
    private int code;

    private StatusPedido(int code){
        this.code = code;
    }

    public static StatusPedido of(int code) {

        for(StatusPedido sp : StatusPedido.values()) {
            if (sp.getCode() == code) return sp;
        }
        throw new IllegalArgumentException("Código de status do pedido inválido.");
    }
}
