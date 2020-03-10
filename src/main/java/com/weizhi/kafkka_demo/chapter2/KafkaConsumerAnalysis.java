package com.weizhi.kafkka_demo.chapter2;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

public class KafkaConsumerAnalysis {
    public static final String brokerList="127.0.0.1:9092";
    public static final String topic="topic-demo";
    public static final String groupId="group.demo";
    public static final AtomicBoolean isRunning=new AtomicBoolean(true);


    public static Properties initConfig(){
        Properties properties=new Properties();
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers",brokerList);
        properties.put("group.id",groupId);
        properties.put("client.id", "consumer.client.id.demo");
        return properties;
    }
    public static void main(String[] args){
        //1、配置消费者客户端配置信息并创建相应消费者客户端
        Properties properties=initConfig();
        KafkaConsumer<String,String> kafkaConsumer=new KafkaConsumer<>(properties);
        //2、订阅主题
        kafkaConsumer.subscribe(Arrays.asList(topic));
        try{
            while(isRunning.get()){
                //3、拉取并消费消息
                ConsumerRecords<String, String> records=kafkaConsumer.poll(Duration.ofMillis(4000));
                for(ConsumerRecord<String,String> record :records){
                    System.out.println("topic="+record.topic()+",partition="+record
                    .partition()+",offset="+record.offset());
                    System.out.println("key="+record.key()+",value="+record.value());
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            //5、关闭消费者实例
            kafkaConsumer.close();
        }
    }
}
