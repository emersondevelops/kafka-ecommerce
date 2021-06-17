package br.com.alura;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class FraudDetectorService {

    public static void main(String[] args) {

        var fraudDetectorService = new FraudDetectorService();
        try (var service = new KafkaService(FraudDetectorService.class.getSimpleName(), "ECOMMERCE_NEW_ORDER",
                fraudDetectorService::parsear)) {
            service.rodar();
        }
    }

    private void parsear(ConsumerRecord<String, String> record) {
        System.out.println("-------------------------------------------");
        System.out.println("Processando new order, checking for fraud");
        System.out.println("Chave da mensagem: " + record.key());
        System.out.println("Valor da mensagem: " + record.value());
        System.out.println("Partição: " + record.partition());
        System.out.println("Offset: " + record.offset());
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Order processed!");
    }
}
