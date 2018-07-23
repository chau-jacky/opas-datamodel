package foundation.messaging;

public interface EventReceiver {

	public void receive(String queueName) throws OpasMessagingException;

}
