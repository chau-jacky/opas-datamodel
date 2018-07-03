package model.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TRADE_FX_SPOT")
@PrimaryKeyJoinColumn(name = "ID")
public class TradeFxSpot extends Trade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -265497230472768329L;

	@Column(name = "VALUE_DATE")
	private Date valueDate;

	private Currency boughtCurrency;

	@Column(name = "BOUGHT_CCY_AMOUNT")
	private BigDecimal boughtCurrencyAmount;

	private Currency soldCurrency;

	@Column(name = "SOLD_CCY_AMOUNT")
	private BigDecimal soldCurrencyAmount;

	@Column(name = "CONTRACT_RATE")
	private BigDecimal contractRate;
	
	@Column(name = "PVP_INDICATOR")
	private boolean pvpIndicator;
	
	private boolean nonDeliverableIndicator;
	
	private boolean fxSwapIndicator;
	
	private LegalAgreement legalAgreement;
	
	private FxSupplementaryInformation fxSupplementaryInfo;
	
	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public Currency getBoughtCurrency() {
		return boughtCurrency;
	}

	public void setBoughtCurrency(Currency boughtCurrency) {
		this.boughtCurrency = boughtCurrency;
	}

	public BigDecimal getBoughtCurrencyAmount() {
		return boughtCurrencyAmount;
	}

	public void setBoughtCurrencyAmount(BigDecimal boughtCurrencyAmount) {
		this.boughtCurrencyAmount = boughtCurrencyAmount;
	}

	public Currency getSoldCurrency() {
		return soldCurrency;
	}

	public void setSoldCurrency(Currency soldCurrency) {
		this.soldCurrency = soldCurrency;
	}

	public BigDecimal getSoldCurrencyAmount() {
		return soldCurrencyAmount;
	}

	public void setSoldCurrencyAmount(BigDecimal soldCurrencyAmount) {
		this.soldCurrencyAmount = soldCurrencyAmount;
	}

	public BigDecimal getContractRate() {
		return contractRate;
	}

	public void setContractRate(BigDecimal contractRate) {
		this.contractRate = contractRate;
	}

	public boolean isPvpIndicator() {
		return pvpIndicator;
	}

	public void setPvpIndicator(boolean pvpIndicator) {
		this.pvpIndicator = pvpIndicator;
	}

	public boolean isNonDeliverableIndicator() {
		return nonDeliverableIndicator;
	}

	public void setNonDeliverableIndicator(boolean nonDeliverableIndicator) {
		this.nonDeliverableIndicator = nonDeliverableIndicator;
	}

	public boolean isFxSwapIndicator() {
		return fxSwapIndicator;
	}

	public void setFxSwapIndicator(boolean fxSwapIndicator) {
		this.fxSwapIndicator = fxSwapIndicator;
	}

	public LegalAgreement getLegalAgreement() {
		return legalAgreement;
	}

	public void setLegalAgreement(LegalAgreement legalAgreement) {
		this.legalAgreement = legalAgreement;
	}

	public FxSupplementaryInformation getFxSupplementaryInfo() {
		return fxSupplementaryInfo;
	}

	public void setFxSupplementaryInfo(FxSupplementaryInformation fxSupplementaryInfo) {
		this.fxSupplementaryInfo = fxSupplementaryInfo;
	}

}
