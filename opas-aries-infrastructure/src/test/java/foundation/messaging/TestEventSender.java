package foundation.messaging;

import java.sql.Timestamp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import model.event.EventStatus;
import model.event.TradeEvent;
import model.workflow.TradeAction;

public class TestEventSender {

	private EventSender eventSender;

	@Before
	public void setUp() throws Exception {
		eventSender = new ActiveMQEventSender();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws OpasMessagingException {

		TradeEvent tradeEvent = new TradeEvent();

		tradeEvent.setAction(TradeAction.NEW);
		tradeEvent.setCreateDatetime(new Timestamp(System.currentTimeMillis()));
		tradeEvent.setStatus(EventStatus.PENDING);
		tradeEvent.setTradeId(new Long(System.currentTimeMillis()));

		eventSender.send(tradeEvent, TestMessagingConstants.TEST_TRADE_EVENT_QUEUE_NAME);
	}

}
