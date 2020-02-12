package au.com.mayi.publisher;

import com.solacesystems.jcsmp.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SolaceMsgPublisher implements JCSMPStreamingPublishEventHandler, InitializingBean {
	private final JCSMPSession jcsmpSession;
	private final Topic solaceTopic;

	@Override
	public void responseReceived(String msgId) {
		log.info("Producer received response for msg: " + msgId);
	}

	public void handleError(String messageID, JCSMPException e, long timestamp) {
		log	.info("Producer received error for msg: %s@%s - %s%n", messageID, timestamp, e);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		final XMLMessageProducer producer = jcsmpSession.getMessageProducer(this);
		for(int i=0; i<10; i++) {
			final String msg = "Hello World: " + i;
			final TextMessage jcsmpMsg = JCSMPFactory.onlyInstance().createMessage(TextMessage.class);
			jcsmpMsg.setText(msg);
			jcsmpMsg.setDeliveryMode(DeliveryMode.PERSISTENT);
			log.info("============= Sending " + msg);
			producer.send(jcsmpMsg, solaceTopic);
			Thread.sleep(1000);
		}

	}
}
