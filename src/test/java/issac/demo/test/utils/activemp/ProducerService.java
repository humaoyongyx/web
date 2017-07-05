package issac.demo.test.utils.activemp;

import javax.jms.Destination;

public interface ProducerService {  
    void sendMessage(Destination destination, final String message);  
} 