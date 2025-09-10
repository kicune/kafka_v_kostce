package org.lisak.kafkavkostce;

import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.serializers.KafkaAvroSerializerConfig;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.lisak.avro.Probe;
import org.lisak.avro.MissionDetails;

import java.io.IOException;
import java.util.Properties;

public class SchemaRegistryProducerNo2 {
    private static final String KAFKA_TOPIC = "lunar-landings";

    public static void main(String[] args)  throws IOException, InterruptedException {
        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:19092,localhost:29092,localhost:39092");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put(KafkaAvroSerializerConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

        KafkaProducer<String, Probe> producer = new KafkaProducer<>(props);

        Thread shutdownHook = new Thread(producer::close);
        Runtime.getRuntime().addShutdownHook(shutdownHook);

        while(true) {
            LunarLandingTestDataFactory.LunarLandingTestData lunarLandingTestData = LunarLandingTestDataFactory.randomLanding();

            System.out.print("Sending to Kafka on the " + KAFKA_TOPIC + " topic the information of the follwing landing: " + lunarLandingTestData.getProbeName());
            Probe kafkaValue = serialize(lunarLandingTestData);
            ProducerRecord<String, Probe> producerRecord =
                    new ProducerRecord<>(KAFKA_TOPIC, lunarLandingTestData.getProbeName(), kafkaValue);
            producer.send(producerRecord);
            System.out.println("...sent");

            Thread.sleep(1000);
        }
    }

    public static Probe serialize(LunarLandingTestDataFactory.LunarLandingTestData testData) throws IOException {
        MissionDetails missionDetails = MissionDetails.newBuilder()
                .setGoal(testData.getMissionGoal())
                .setLaunchVehicle(testData.getLaunchVehicle())
                .setMass(testData.getLandingMassKg())
                .build();
        return Probe.newBuilder()
                .setDate(testData.getDate())
                .setProbeName(testData.getProbeName())
                .setCountry(testData.getCountry())
                //.setMissionDetails(missionDetails)
                .build();
    }
}


