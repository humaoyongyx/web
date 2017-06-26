package issac.demo.test.utils;  
  
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.kafka.core.KafkaTemplate;  
import org.springframework.test.context.ContextConfiguration;

import issac.demo.test.JUnit4ClassRunner;  
  
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/kafka-producer.xml" })
public class KafkaTest {  
      
    @Autowired  
    private KafkaTemplate<Integer, String> kafkaTemplate;  
      
    @Test  
    public void testTemplateSend(){  
        kafkaTemplate.sendDefault("hello world from java!xx");  
    }  
  
}  