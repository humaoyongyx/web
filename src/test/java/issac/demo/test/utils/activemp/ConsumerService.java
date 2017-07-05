package issac.demo.test.utils.activemp;

import javax.jms.Destination;

public interface ConsumerService {  
    String receiveMessage(Destination destination, Destination replyDestination);  
}  