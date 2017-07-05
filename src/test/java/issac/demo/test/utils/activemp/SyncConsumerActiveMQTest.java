package issac.demo.test.utils.activemp;
import javax.annotation.Resource;  
import javax.jms.Destination;  
  
import org.junit.Test;  
import org.junit.runner.RunWith;  
import org.springframework.test.context.ContextConfiguration;  
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;  
  
@RunWith(SpringJUnit4ClassRunner.class)  
@ContextConfiguration(locations = { "/activemq.xml" })  
public class SyncConsumerActiveMQTest {  
      
    @Resource(name="consumerServiceImpl")  
    private ConsumerService cService;  
      
    @Resource(name="queueDestination")  
    private Destination receiveQueue;  
      
    @Resource(name="responseQueue")  
    private Destination replyQueue;  
      
    @Test  
    public void receiveTest(){  
        String result = cService.receiveMessage(receiveQueue, replyQueue);  
        System.out.println(result);  
    }  
}  