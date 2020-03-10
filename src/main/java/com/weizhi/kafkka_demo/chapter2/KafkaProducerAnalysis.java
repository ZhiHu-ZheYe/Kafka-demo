package com.weizhi.kafkka_demo.chapter2;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.objenesis.SpringObjenesis;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class KafkaProducerAnalysis {
    public static final String BrokerLIst="127.0.0.1:9092";
    public static final String topic="topic-demo";
    public static Properties initConfig(){
        Properties properties=new Properties();
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers",BrokerLIst);
        properties.put("client.id","producer.client.id.demo");
        return properties;
    }

    public static void main(String[] args){
        Properties properties=initConfig();
        //配制生产者客户端实例并创建KafkaProducer实例
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer(properties);
        //构建需要发送的消息
        ProducerRecord<String,String> producerRecord=new ProducerRecord(topic,"Hello,Kafka");
        //发送消息  同步
        try{
            Future<RecordMetadata> future=kafkaProducer.send(producerRecord);
            RecordMetadata metadata=future.get();
            System.out.println("topic:"+metadata.topic()+",partition:"+metadata.partition()
            +"offset:"+metadata.offset());
        }catch (ExecutionException | InterruptedException e){
            e.printStackTrace();
        }
        kafkaProducer.close();
    }
}
