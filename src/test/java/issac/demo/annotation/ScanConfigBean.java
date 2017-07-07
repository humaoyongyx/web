package issac.demo.annotation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScanConfigBean {
	
	@Bean
	public ScanApi scanApi(){
		return ScanFactory.getScanApi(ScanApi.class);
	}
	
	@Bean
	public ScanApi scanApi222(){
		return ScanFactory.getScanApi(ScanApi.class);
	}

}
