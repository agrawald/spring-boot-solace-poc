package au.com.mayi.consumer;

import com.solacesystems.jcsmp.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SolaceMsgConsumer implements XMLMessageListener, InitializingBean, DisposableBean {
	private final JCSMPSession jcsmpSession;
	private final Topic solaceTopic;

	private XMLMessageConsumer consumer;

	@Override
	public void onReceive(final BytesXMLMessage msg) {
		if (msg instanceof TextMessage) {
			log.info("============= TextMessage received: " + ((TextMessage) msg).getText());
		} else {
			log.info("============= Message received.");
		}
	}

	public void onException(JCSMPException e) {
		log.info("Consumer received exception:", e);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		consumer = jcsmpSession.getMessageConsumer(this);
		jcsmpSession.addSubscription(solaceTopic);
		consumer.start();
	}

	@Override
	public void destroy() throws Exception {
		consumer.close();
		log.info("Exiting.");
		jcsmpSession.closeSession();
	}
}
