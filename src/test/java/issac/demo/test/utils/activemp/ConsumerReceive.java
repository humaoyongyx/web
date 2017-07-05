package issac.demo.test.utils.activemp;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class ConsumerReceive implements MessageListener {  
  
    @Override  
    public void onMessage(Message message) {  
        try {  
            if(message instanceof TextMessage){  
                String receiveMessage = ((TextMessage) message).getText();  
                System.out.println("消费者收到的消息为:"+receiveMessage);  
            }  
        } catch (JMSException e) {  
            e.printStackTrace();  
        }  
    }  
}  