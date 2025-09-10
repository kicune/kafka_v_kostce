package org.lisak.kafkavkostce;

import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.lisak.avro.Probe;
import org.lisak.avro.Probe;

import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Properties;
import java.util.UUID;

public class SchemaRegistryConsumer {
    private static final String KAFKA_TOPIC = "lunar-landings";

    public static void main(String[] args) throws IOException {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092,localhost:29092,localhost:39092");
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "lunar-landings-schema-consumer");
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);

        // To start processing messages from the beginning of the topic
        //props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // Randomize Consumer Group ID to receive the initial records on each run
        //props.put(ConsumerConfig.GROUP_ID_CONFIG, "lunar-landings-schema-consumer-" + (int)(Math.random()*(1000)));

        KafkaConsumer<String, Probe> consumer = new KafkaConsumer<>(props);

        Thread shutdownHook = new Thread(consumer::close);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        consumer.subscribe(Collections.singletonList(KAFKA_TOPIC));

        while(true) {
            ConsumerRecords<String, Probe> records = consumer.poll(Duration.ofMillis(100));

            for(ConsumerRecord<String, Probe> record : records) {
                Probe lunarLanding = record.value();

                System.out.println("Consumed message: \n" + record.key() + " : " + lunarLanding.toString());
            }
        }
    }

}
