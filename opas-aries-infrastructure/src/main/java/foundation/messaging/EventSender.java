package foundation.messaging;

import model.event.Event;

public interface EventSender {

	public void send(Event event, String topics) throws OpasMessagingException;

}
