package issac.demo.test.utils.rabbitmq;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RabbitmqTest {
    
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context=new ClassPathXmlApplicationContext("rabbitMQ.xml");
        Proceducer proceducer=(Proceducer) context.getBean("proceducer") ; 
        Person person=new Person("xxxx",22);
        System.out.println(person);
        proceducer.sendMessage(person);        
        
    }
}