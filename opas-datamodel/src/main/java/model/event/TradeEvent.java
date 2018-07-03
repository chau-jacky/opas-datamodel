package model.event;

import model.workflow.TradeAction;

public class TradeEvent extends Event {

	TradeAction action;
	Long tradeId;

	public TradeAction getAction() {
		return action;
	}

	public void setAction(TradeAction action) {
		this.action = action;
	}

	public Long getTradeId() {
		return tradeId;
	}

	public void setTradeId(Long tradeId) {
		this.tradeId = tradeId;
	}

}
