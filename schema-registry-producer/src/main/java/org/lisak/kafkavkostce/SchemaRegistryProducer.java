package org.lisak.kafkavkostce;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.lisak.kafkavkostce.LunarLandingTestDataFactory.LunarLandingTestData;
import org.lisak.avro.lunarlanding.LunarLanding;

import java.io.IOException;
import java.util.Properties;

public class SchemaRegistryProducer {
    private static final String KAFKA_TOPIC = "lunar-landings-schema";

    public static void main(String[] args)  throws IOException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092,localhost:29092,localhost:39092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        KafkaProducer<String, LunarLanding> producer = new KafkaProducer<>(props);

        Thread shutdownHook = new Thread(producer::close);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        while(true) {
            LunarLandingTestData lunarLandingTestData = LunarLandingTestDataFactory.randomLanding();

            System.out.print("Sending to Kafka on the " + KAFKA_TOPIC + " topic the information of the follwing landing: " + lunarLandingTestData.getProbeName());
            LunarLanding kafkaValue = serialize(lunarLandingTestData);
            ProducerRecord<String, LunarLanding> producerRecord =
                    new ProducerRecord<>(KAFKA_TOPIC, lunarLandingTestData.getProbeName(), kafkaValue);
            producer.send(producerRecord);
            System.out.println("...sent");

            Thread.sleep(1000);
        }
    }

    public static LunarLanding serialize(LunarLandingTestData testData) throws IOException {
        return LunarLanding.newBuilder()
                .setDate(testData.getDate())
                .setProbeName(testData.getProbeName())
                .setCountry(testData.getCountry())
                .build();
    }
}


