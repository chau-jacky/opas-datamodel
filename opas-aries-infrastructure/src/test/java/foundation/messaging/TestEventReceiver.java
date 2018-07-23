package foundation.messaging;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestEventReceiver {

	private EventReceiver eventReceiver;

	@Before
	public void setUp() throws Exception {
		eventReceiver = new ActiveMQEventReceiver();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws OpasMessagingException {

		eventReceiver.receive(TestMessagingConstants.TEST_TRADE_EVENT_QUEUE_NAME);

	}

}
