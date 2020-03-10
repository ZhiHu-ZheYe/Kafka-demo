package com.weizhi.kafkka_demo.charpter1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

public class ConsumerFastStart {
    public static final String brokerList="127.0.0.1:9092";
    public static final String topic="topic-demo";
    public static final String groupId="group.demo";
    public static void main(String[] main){
        Properties properties=new Properties();
        properties.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("bootstrap.servers",brokerList);
        //设置消费组名称，具体名称可见第三章
        properties.put("group.id",groupId);
        //创建一个消费者客户端实例
        KafkaConsumer<String,String> consumer=new KafkaConsumer(properties);
        //订阅主题
        consumer.subscribe(Collections.singletonList(topic));
        //循环消费消息
        while (true) {
            ConsumerRecords<String,String> records=consumer.poll(Duration.ofMillis(10000));
            for(ConsumerRecord<String,String> consumerRecord:records){
                if (!consumerRecord.value().isEmpty())
                    System.out.println(consumerRecord.value());
            }

        }
    }

}
