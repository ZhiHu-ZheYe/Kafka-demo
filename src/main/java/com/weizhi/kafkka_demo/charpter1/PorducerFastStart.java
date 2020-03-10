package com.weizhi.kafkka_demo.charpter1;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class PorducerFastStart {
    public static final String BrokerLIst="127.0.0.1:9092";
    public static final String topic="topic-demo";
    public static void main(String[] args){
        Properties properties=new Properties();
        properties.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");
        properties.put("bootstrap.servers",BrokerLIst);
        //配制生产者客户端实例并创建KafkaProducer实例
        KafkaProducer<String,String> kafkaProducer=new KafkaProducer(properties);
        //构建需要发送的消息
        ProducerRecord<String,String> producerRecord=new ProducerRecord(topic,"Hello,Kafka");
        //发送消息
        try{
            kafkaProducer.send(producerRecord);
        }catch (Exception e){
            e.printStackTrace();
        }
        kafkaProducer.close();
    }
}
