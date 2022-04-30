package app.loja_dev.enums;

import lombok.Getter;

import java.util.Arrays;

public enum StatusPedido {

    AGUARDANDO_PAGAMENTO(1),
    PAGO(2),
    ENVIADO(3),
    ENTREGUE(4),
    CANCELADO(5);

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
