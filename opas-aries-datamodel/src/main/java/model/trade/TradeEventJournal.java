package model.trade;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import model.OpasOrganizationObject;

public class TradeEventJournal extends OpasOrganizationObject {
 
	/* internal use, used for uniquely identifying the event journal record */
	private Long tradeEventJournalId;

	@Enumerated(EnumType.STRING)
	@Column(name = "TRADE_ACTIVITY", nullable = false)
	private TradeActivity tradeActivity;
	
	public Long getTradeEventJournalId() {
		return tradeEventJournalId;
	}

	public void setTradeEventJournalId(Long tradeEventJournalId) {
		this.tradeEventJournalId = tradeEventJournalId;
	}

	public TradeActivity getTradeActivity() {
		return tradeActivity;
	}

	public void setTradeActivity(TradeActivity tradeActivity) {
		this.tradeActivity = tradeActivity;
	}
	
}
