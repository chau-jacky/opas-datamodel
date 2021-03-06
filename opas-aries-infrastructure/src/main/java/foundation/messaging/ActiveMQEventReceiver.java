package foundation.messaging;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQEventReceiver implements EventReceiver, ExceptionListener {

	private ActiveMQConnectionFactory connectionFactory;

	public ActiveMQEventReceiver() {
		super();
		this.connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
	}

	public void receive(String queueName) throws OpasMessagingException {

		try {

			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			connection.setExceptionListener(this);

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue(queueName);

			// Create a MessageConsumer from the Session to the Topic or Queue
			MessageConsumer consumer = session.createConsumer(destination);

			// Wait for a message
			Message message = consumer.receive(1000);

			if (message instanceof TextMessage) {
				TextMessage textMessage = (TextMessage) message;
				String text = textMessage.getText();
				System.out.println("Received: " + text);
			} else {
				System.out.println("Received: " + message);
			}

			consumer.close();
			session.close();
			connection.close();
		} catch (JMSException je) {
			throw new OpasMessagingException(je.getMessage());
		}
	}

	@Override
	public void onException(JMSException arg0) {
		System.out.println(arg0.getMessage());
	}
}
