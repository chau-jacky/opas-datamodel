package model.party;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.OpasOrganizationObject;

public class CounterpartySsi extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5365468668617308397L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "PARTY_COUNTERPARTY_SSI_ID", nullable = false)
	private long counterpartySsiId;

	@Column(name = "CCY_CODE", nullable = false)
	private String currencCode;

	@Column(name = "TRADE_CLASS_NAME", nullable = false)
	private String tradeClassName;

	@Column(name = "DEFAULT_SSI", nullable = false)
	private Integer defaultSsi;

	@Column(name = "SEQ", nullable = false)
	private Integer sequence;

	public long getCounterpartySsiId() {
		return counterpartySsiId;
	}

	public void setCounterpartySsiId(long counterpartySsiId) {
		this.counterpartySsiId = counterpartySsiId;
	}

	public String getCurrencCode() {
		return currencCode;
	}

	public void setCurrencCode(String currencCode) {
		this.currencCode = currencCode;
	}

	public String getTradeClassName() {
		return tradeClassName;
	}

	public void setTradeClassName(String tradeClassName) {
		this.tradeClassName = tradeClassName;
	}

}
