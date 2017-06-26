package issac.demo.test.utils;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.kafka.listener.MessageListener;

public class KafkaConsumer implements MessageListener<Integer, String>{  
  
    @Override  
    public void onMessage(ConsumerRecord<Integer, String> record) {  
        //System.out.println(record); 
        System.out.println(record.value());
    }  
   /**
    * 
 虚拟机配置host和本机host一样的配置，否则要更改kafaka的server.properties
bin/kafka-server-start.sh config/server.properties
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic Hello-Kafka
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic Hello-Kafka --from-beginning
    * @param args
    */
   public static void main(String[] args) {
	 ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("kafka-consumer.xml");
	 classPathXmlApplicationContext.start();
}
   
  
}  