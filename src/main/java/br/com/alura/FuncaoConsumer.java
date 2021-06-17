package br.com.alura;

import org.apache.kafka.clients.consumer.ConsumerRecord;

public interface FuncaoConsumer {
    void consume(ConsumerRecord<String, String> record);
}
