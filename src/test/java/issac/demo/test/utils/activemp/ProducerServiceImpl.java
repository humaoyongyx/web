package issac.demo.test.utils.activemp;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

@Service("producerServiceImpl")  
public class ProducerServiceImpl implements ProducerService {  
      
    /** 
     * 注入JmsTemplate 
     */  
    @Resource(name="jmsTemplate")  
    private JmsTemplate jTemplate;  
  
    /** 
     * attention: 
     * Details：发送消息 
     * @author chhliu 
     * 创建时间：2016-7-28 下午2:33:14 
     * @param destination 
     * @param message 
     */  
    @Override  
    public void sendMessage(Destination receivedestination, final String message) {  
          
        System.out.println("================生产者创建了一条消息==============");  
        jTemplate.send(receivedestination, new MessageCreator() {  
              
            @Override  
            public Message createMessage(Session session) throws JMSException {  
                return session.createTextMessage("hello acticeMQ:"+message);  
            }  
        });  
    }


}  