package persistence;

import model.workflow.TradeAction;
import model.workflow.TradeStatus;

public class TestConstants {

	public static final Long ORGANIZATION_ID = new Long(12345678);

	public static final Long USER_ID = new Long(987654321);

	public static final Long COUNTERPARTY_ID = new Long(456);

	public static final Long BOOK_ID = new Long(88965431);

	public static final String BOOK_NAME = "12345";

	public static final TradeStatus TRADE_STATUS = TradeStatus.NONE;

	public static final TradeAction TRADE_ACTION = TradeAction.NEW;

}
