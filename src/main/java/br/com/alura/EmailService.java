package br.com.alura;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public class EmailService {

    public static void main(String[] args) {

        var emailService = new EmailService();
        try (var service = new KafkaService(EmailService.class.getSimpleName(), "ECOMMERCE_SEND_EMAIL",
                emailService::parsear)) {
            service.rodar();
        }
    }

    private void parsear(ConsumerRecord<String, String> record) {
        System.out.println("-------------------------------------------");
        System.out.println("Sending e-mail...");
        System.out.println("Chave da mensagem: " + record.key());
        System.out.println("Valor da mensagem: " + record.value());
        System.out.println("Partição: " + record.partition());
        System.out.println("Offset: " + record.offset());
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("E-mail sent!");
    }

}
