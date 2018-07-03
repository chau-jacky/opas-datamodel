package model.trade;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TRADE_FX_FORWARD")
@PrimaryKeyJoinColumn(name = "ID")
public class TradeFxForward extends Trade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8897705067424295658L;
	@Column(name = "SWAP_POINTS")
	private BigDecimal swapPoints;

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
	
	@Column(name = "NDF_SETTLEMENT_RATE_SOURCE", length = 5)
	private String ndfSettlementRateSource;
	@Column(name = "NDF_SETTLEMENT_TIME", length = 4)
	private String ndfSettlementTime;
	@Column(name = "NDF_SETTLEMENT_LOCATION", length = 4)
	private String ndfSettlementLocation;
	
	private boolean fxSwapIndicator;
	
	private LegalAgreement legalAgreement;

	private FxSupplementaryInformation fxSupplementaryInfo;
	
	@Column(name = "FIXING_DATE")
	private Date fixingDate;

	public BigDecimal getSwapPoints() {
		return swapPoints;
	}

	public void setSwapPoints(BigDecimal swapPoints) {
		this.swapPoints = swapPoints;
	}

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

	public Date getFixingDate() {
		return fixingDate;
	}

	public void setFixingDate(Date fixingDate) {
		this.fixingDate = fixingDate;
	}

	public String getNdfSettlementRateSource() {
		return ndfSettlementRateSource;
	}

	public void setNdfSettlementRateSource(String ndfSettlementRateSource) {
		this.ndfSettlementRateSource = ndfSettlementRateSource;
	}

	public String getNdfSettlementTime() {
		return ndfSettlementTime;
	}

	public void setNdfSettlementTime(String ndfSettlementTime) {
		this.ndfSettlementTime = ndfSettlementTime;
	}

	public String getNdfSettlementLocation() {
		return ndfSettlementLocation;
	}

	public void setNdfSettlementLocation(String ndfSettlementLocation) {
		this.ndfSettlementLocation = ndfSettlementLocation;
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
