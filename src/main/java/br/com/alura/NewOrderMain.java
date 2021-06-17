package br.com.alura;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

public class NewOrderMain {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        try (var despachador = new DespachadorDeEmail()) {
            for (int i = 0; i < 10; i++) {

                var key = UUID.randomUUID().toString();

                var value = key + ",2142421,21324154363";
                despachador.enviar("ECOMMERCE_NEW_ORDER", key, value);

                var email = "Thank you for your order! We are processing it";
                despachador.enviar("ECOMMERCE_SEND_EMAIL", key, email);
            }
        }
    }
}
