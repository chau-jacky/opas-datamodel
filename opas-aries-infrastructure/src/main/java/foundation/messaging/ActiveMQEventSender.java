package foundation.messaging;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.google.gson.Gson;

import model.event.Event;

public class ActiveMQEventSender implements EventSender {

	private ActiveMQConnectionFactory connectionFactory;

	public ActiveMQEventSender() {
		super();
		this.connectionFactory = new ActiveMQConnectionFactory("vm://localhost");
	}

	public void send(Event event, String queueName) throws OpasMessagingException {

		try {
			// Create a Connection
			Connection connection = connectionFactory.createConnection();
			connection.start();

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue(queueName);

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.PERSISTENT);

			// Create a messages
			Gson gson = new Gson();
			String text = gson.toJson(event);
			TextMessage message = session.createTextMessage(text);

			// Tell the producer to send the message
			System.out.println("Sent message: " + text);
			producer.send(message);

			// Clean up
			session.close();
			connection.close();
		} catch (JMSException je) {
			throw new OpasMessagingException(je.getMessage());

		}
	}

}
