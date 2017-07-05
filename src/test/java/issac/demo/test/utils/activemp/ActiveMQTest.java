package issac.demo.test.utils.activemp;  
  
import javax.annotation.Resource;
import javax.jms.Destination;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;  
import org.springframework.kafka.core.KafkaTemplate;  
import org.springframework.test.context.ContextConfiguration;

import issac.demo.test.JUnit4ClassRunner;  
  
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/activemq.xml" })
public class ActiveMQTest {  
    
    @Resource(name="producerServiceImpl")  
    private ProducerService pService;  
      
    @Resource(name="queueDestination")  
    private Destination receiveQueue;  
      
    @Test  
    public void producerTest(){  
    	for (int i = 0; i <	 3; i++) {
    		  pService.sendMessage(receiveQueue, "hello world!"+i);  
		}
      
    }  
}  