package model.trade;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import model.OpasObject;


public class TradeEventJournal extends OpasObject {
 
	@Enumerated(EnumType.STRING)
	@Column(name = "TRADE_ACTIVITY", nullable = false)
	private TradeActivity tradeActivity;

	public TradeActivity getTradeActivity() {
		return tradeActivity;
	}

	public void setTradeActivity(TradeActivity tradeActivity) {
		this.tradeActivity = tradeActivity;
	}
	
}
