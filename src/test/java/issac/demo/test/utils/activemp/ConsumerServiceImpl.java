package issac.demo.test.utils.activemp;

import javax.annotation.Resource;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

//@Service("consumerServiceImpl")  
public class ConsumerServiceImpl implements ConsumerService {  
      
    /** 
     * 注入JmsTemplate 
     */  
    @Resource(name="jmsTemplate")  
    private JmsTemplate jTemplate;  
      
    /** 
     * attention: 
     * Details：接收消息，同时回复消息 
     * @author chhliu 
     * 创建时间：2016-7-28 下午2:39:45 
     * @param destination 
     * @return 
     */  
    @Override  
    public String receiveMessage(Destination destination, Destination replyDestination) {  
        /** 
         * 接收消息队列中的消息 
         */  
        Message message = jTemplate.receive(destination);  
        try {  
            /** 
             * 此处为了更好的容错性，可以使用instanceof来判断下消息类型 
             */  
            if(message instanceof TextMessage){  
                String receiveMessage =  ((TextMessage) message).getText();  
                System.out.println("收到生产者的消息:"+receiveMessage);  
                /** 
                 * 收到消息之后，将回复报文放到回复队列里面去 
                 */  
                jTemplate.send(replyDestination, new MessageCreator() {  
                      
                    @Override  
                    public Message createMessage(Session session) throws JMSException {  
                        return session.createTextMessage("消费者已经收到生产者的消息了，这是一条确认报文!");  
                    }  
                });  
              return receiveMessage;  
            }  
        } catch (JMSException e) {  
            e.printStackTrace();  
        }  
        return "";  
    }  
}  