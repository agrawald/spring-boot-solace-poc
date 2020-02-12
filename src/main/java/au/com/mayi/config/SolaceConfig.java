package au.com.mayi.config;

import au.com.mayi.consumer.SolaceMsgConsumer;
import com.solacesystems.jcsmp.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SolaceConfig {
	@Bean
	Topic solaceTopic() {
		return JCSMPFactory.onlyInstance().createTopic("tutorial/topic");
	}

	@Bean
	JCSMPSession jcsmpSession(final SpringJCSMPFactory solaceFactory) throws InvalidPropertiesException {
		return solaceFactory.createSession();
	}

	@PostConstruct
	public void postConstruct() {

	}
}
