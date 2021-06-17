package br.com.alura;

import java.io.Closeable;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

public class DespachadorDeEmail implements Closeable {

    private final KafkaProducer<String, String> producer;

    public DespachadorDeEmail() {
        this.producer = new KafkaProducer<String, String>(properties());
    }

    private static Properties properties() {
        var properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        return properties;
    }

    public void enviar(String topico, String key, String value) throws InterruptedException, ExecutionException {
        var record = new ProducerRecord<String, String>(topico, key, value);
        Callback callback = (data, ex) -> {
            if (ex != null) {
                ex.printStackTrace();
                return;
            }
            System.out.println("Sucesso ao enviar: " + data.topic() + " / Partição: " + data.partition() + " / Offset: "
                    + data.offset() + " / Instante: " + data.timestamp());
        };
        producer.send(record, callback).get();
    }

    @Override
    public void close() {
        producer.close();
    }
}
