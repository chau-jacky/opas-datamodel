package model.cashflow;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import model.OpasOrganizationObject;
import model.party.Counterparty;
import model.trade.Currency;
import model.trade.Trade;

public class CashFlow extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4868837355079926425L;

	/* unique id of the cash flow */
	private long cashFlowId;
	
	/* unique cash flow reference numnber - 16 characters */
	private String cashFlowReference;
	
	/* used in the field 108 in header block 3 */
	private String messageUserReference;
	
	/* the trade that the cash flow is associated with */
	private Trade trade;
	
	/* currency information of the cash flow */
	private Currency currency;
	
	/* the amount of the cash flow */
	private BigDecimal cashFlowAmount;
	
	/* value date of the cash flow */
	private Date valueDate;

	public long getCashFlowId() {
		return cashFlowId;
	}

	public void setCashFlowId(long cashFlowId) {
		this.cashFlowId = cashFlowId;
	}

	public String getCashFlowReference() {
		return cashFlowReference;
	}

	public void setCashFlowReference(String cashFlowReference) {
		this.cashFlowReference = cashFlowReference;
	}

	public Trade getTrade() {
		return trade;
	}

	public void setTrade(Trade trade) {
		this.trade = trade;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(Currency currency) {
		this.currency = currency;
	}

	public BigDecimal getCashFlowAmount() {
		return cashFlowAmount;
	}

	public void setCashFlowAmount(BigDecimal cashFlowAmount) {
		this.cashFlowAmount = cashFlowAmount;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public String getMessageUserReference() {
		return messageUserReference;
	}

	public void setMessageUserReference(String messageUserReference) {
		this.messageUserReference = messageUserReference;
	}

}
