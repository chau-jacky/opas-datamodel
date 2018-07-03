package model.trade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;

import model.OpasOrganizationObject;

public class TradeSsi extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5222138628549656286L;

	private long tradeId;

	private PaymentType paymentType;
	private Date paymentDate;
	private double currencyAmount;
	
	/* *****************
	 * Basic Information 
	 * ***************** */
	private String settlementBankAcronym;
	private FtsCode ftsCode;
	private String currencyCode;
	private PaymentIndicator paymentIndicator;
	@Column(name = "PAYMENT_DETAILS_LINE_1", length = 35)
	private String paymentDetailsLine1;
	@Column(name = "PAYMENT_DETAILS_LINE_2", length = 35)
	private String paymentDetailsLine2;
	
	/* ******************************
	 * Our Pay or Receive Information 
	 * ****************************** */
	@Column(name = "OUR_ACC_WITH_THEM_IN_OUR_BOOK", length = 34)
	private String ourAccountWithThemInOurBook;
	@Column(name = "OUR_ACC_WITH_THEM_IN_THEIR_BOOK", length = 34)
	private String ourAccountWithThemInTheirBook;
	@Column(name = "THEIR_ACC_WITH_US_IN_OUR_BOOK", length = 34)
	private String theirAccountWithUsInOurBook;
	
	private String ourSettlementSwiftBicCode;
	private String ourSettlementClearingSortCode;
	private String ourSettlementClearingBankCode;
	
	@Column(name = "OUR_NAME_AND_ADDRESS_LINE1", length = 35)
	private String ourSettlementNameAndAddressLine1;
	@Column(name = "OUR_NAME_AND_ADDRESS_LINE2", length = 35)
	private String ourSettlementNameAndAddressLine2;

	private String ourSettlementCountry;
	
	/* ***************************************
	 * Counterparty Pay or Receive Information 
	 * *************************************** */ 
	
	/* Intermediary Bank Information */
	private String cptyIntermediaryNameLine1;
	private String cptyIntermediaryNameLine2;
	private String cptyIntermediaryBankCode;
	private String cptyIntermediarySortCode;
	private String cptyIntermediarySwiftBicCode;
	
	/* Third Party Information */
	private String cptyThirdPartyInstructionLine1;
	private String cptyThirdPartyInstructionLine2;
	private String cptyThirdPartyInstructionLine3;
	private String cptyThirdPartyAccountNumber;
	private String cptyThirdPartyClearingSortCode;
	private boolean cptyThirdPartyBankIndicator;
	
	/* Beneficiary or Remitting Bank Information */
	private String cptySettlementNameLine1;
	private String cptySettlementNameLine2;
	private String cptySettlementAccountOnIntermediary;
	private String cptySettlementAccountOnIntermediarySortCode;
	private String cptySettlementAccount;
	private String cptySettlementAccountSortCode;
	private String cptySettlementSwiftBicCode;
	
	public long getTradeId() {
		return tradeId;
	}

	public void setTradeId(long tradeId) {
		this.tradeId = tradeId;
	}

	public PaymentIndicator getPaymentIndicator() {
		return paymentIndicator;
	}

	public void setPaymentIndicator(PaymentIndicator paymentIndicator) {
		this.paymentIndicator = paymentIndicator;
	}
	
	public String getPaymentDetailsLine1() {
		return paymentDetailsLine1;
	}

	public void setPaymentDetailsLine1(String paymentDetailsLine1) {
		this.paymentDetailsLine1 = paymentDetailsLine1;
	}

	public String getPaymentDetailsLine2() {
		return paymentDetailsLine2;
	}

	public void setPaymentDetailsLine2(String paymentDetailsLine2) {
		this.paymentDetailsLine2 = paymentDetailsLine2;
	}

	public String getOurAccountWithThemInOurBook() {
		return ourAccountWithThemInOurBook;
	}

	public void setOurAccountWithThemInOurBook(String ourAccountWithThemInOurBook) {
		this.ourAccountWithThemInOurBook = ourAccountWithThemInOurBook;
	}

	public String getOurAccountWithThemInTheirBook() {
		return ourAccountWithThemInTheirBook;
	}

	public void setOurAccountWithThemInTheirBook(String ourAccountWithThemInTheirBook) {
		this.ourAccountWithThemInTheirBook = ourAccountWithThemInTheirBook;
	}

	public String getTheirAccountWithUsInOurBook() {
		return theirAccountWithUsInOurBook;
	}

	public void setTheirAccountWithUsInOurBook(String theirAccountWithUsInOurBook) {
		this.theirAccountWithUsInOurBook = theirAccountWithUsInOurBook;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public double getCurrencyAmount() {
		return currencyAmount;
	}

	public void setCurrencyAmount(double currencyAmount) {
		this.currencyAmount = currencyAmount;
	}

	public String getSettlementBankAcronym() {
		return settlementBankAcronym;
	}

	public void setSettlementBankAcronym(String settlementBankAcronym) {
		this.settlementBankAcronym = settlementBankAcronym;
	}

	public FtsCode getFtsCode() {
		return ftsCode;
	}

	public void setFtsCode(FtsCode ftsCode) {
		this.ftsCode = ftsCode;
	}

	public String getOurSettlementSwiftBicCode() {
		return ourSettlementSwiftBicCode;
	}

	public void setOurSettlementSwiftBicCode(String ourSettlementSwiftBicCode) {
		this.ourSettlementSwiftBicCode = ourSettlementSwiftBicCode;
	}

	public String getOurSettlementClearingSortCode() {
		return ourSettlementClearingSortCode;
	}

	public void setOurSettlementClearingSortCode(String ourSettlementClearingSortCode) {
		this.ourSettlementClearingSortCode = ourSettlementClearingSortCode;
	}

	public String getOurSettlementClearingBankCode() {
		return ourSettlementClearingBankCode;
	}

	public void setOurSettlementClearingBankCode(String ourSettlementClearingBankCode) {
		this.ourSettlementClearingBankCode = ourSettlementClearingBankCode;
	}

	public String getCptyIntermediaryNameLine1() {
		return cptyIntermediaryNameLine1;
	}

	public void setCptyIntermediaryNameLine1(String cptyIntermediaryNameLine1) {
		this.cptyIntermediaryNameLine1 = cptyIntermediaryNameLine1;
	}

	public String getCptyIntermediaryNameLine2() {
		return cptyIntermediaryNameLine2;
	}

	public void setCptyIntermediaryNameLine2(String cptyIntermediaryNameLine2) {
		this.cptyIntermediaryNameLine2 = cptyIntermediaryNameLine2;
	}
	
	public String getOurSettlementCountry() {
		return ourSettlementCountry;
	}

	public void setOurSettlementCountry(String ourSettlementCountry) {
		this.ourSettlementCountry = ourSettlementCountry;
	}

	public String getCptyIntermediaryBankCode() {
		return cptyIntermediaryBankCode;
	}

	public void setCptyIntermediaryBankCode(String cptyIntermediaryBankCode) {
		this.cptyIntermediaryBankCode = cptyIntermediaryBankCode;
	}

	public String getCptyIntermediarySortCode() {
		return cptyIntermediarySortCode;
	}

	public void setCptyIntermediarySortCode(String cptyIntermediarySortCode) {
		this.cptyIntermediarySortCode = cptyIntermediarySortCode;
	}

	public String getCptyIntermediarySwiftBicCode() {
		return cptyIntermediarySwiftBicCode;
	}

	public void setCptyIntermediarySwiftBicCode(String cptyIntermediarySwiftBicCode) {
		this.cptyIntermediarySwiftBicCode = cptyIntermediarySwiftBicCode;
	}

	public String getCptyThirdPartyInstructionLine1() {
		return cptyThirdPartyInstructionLine1;
	}

	public void setCptyThirdPartyInstructionLine1(String cptyThirdPartyInstructionLine1) {
		this.cptyThirdPartyInstructionLine1 = cptyThirdPartyInstructionLine1;
	}

	public String getCptyThirdPartyInstructionLine2() {
		return cptyThirdPartyInstructionLine2;
	}

	public void setCptyThirdPartyInstructionLine2(String cptyThirdPartyInstructionLine2) {
		this.cptyThirdPartyInstructionLine2 = cptyThirdPartyInstructionLine2;
	}

	public String getCptyThirdPartyInstructionLine3() {
		return cptyThirdPartyInstructionLine3;
	}

	public void setCptyThirdPartyInstructionLine3(String cptyThirdPartyInstructionLine3) {
		this.cptyThirdPartyInstructionLine3 = cptyThirdPartyInstructionLine3;
	}

	public String getCptyThirdPartyAccountNumber() {
		return cptyThirdPartyAccountNumber;
	}

	public void setCptyThirdPartyAccountNumber(String cptyThirdPartyAccountNumber) {
		this.cptyThirdPartyAccountNumber = cptyThirdPartyAccountNumber;
	}

	public String getCptyThirdPartyClearingSortCode() {
		return cptyThirdPartyClearingSortCode;
	}

	public void setCptyThirdPartyClearingSortCode(String cptyThirdPartyClearingSortCode) {
		this.cptyThirdPartyClearingSortCode = cptyThirdPartyClearingSortCode;
	}

	public boolean isCptyThirdPartyBankIndicator() {
		return cptyThirdPartyBankIndicator;
	}

	public void setCptyThirdPartyBankIndicator(boolean cptyThirdPartyBankIndicator) {
		this.cptyThirdPartyBankIndicator = cptyThirdPartyBankIndicator;
	}

	public String getCptySettlementNameLine1() {
		return cptySettlementNameLine1;
	}

	public void setCptySettlementNameLine1(String cptySettlementNameLine1) {
		this.cptySettlementNameLine1 = cptySettlementNameLine1;
	}

	public String getCptySettlementNameLine2() {
		return cptySettlementNameLine2;
	}

	public void setCptySettlementNameLine2(String cptySettlementNameLine2) {
		this.cptySettlementNameLine2 = cptySettlementNameLine2;
	}

	public String getCptySettlementAccountOnIntermediary() {
		return cptySettlementAccountOnIntermediary;
	}

	public void setCptySettlementAccountOnIntermediary(String cptySettlementAccountOnIntermediary) {
		this.cptySettlementAccountOnIntermediary = cptySettlementAccountOnIntermediary;
	}

	public String getCptySettlementAccountOnIntermediarySortCode() {
		return cptySettlementAccountOnIntermediarySortCode;
	}

	public void setCptySettlementAccountOnIntermediarySortCode(String cptySettlementAccountOnIntermediarySortCode) {
		this.cptySettlementAccountOnIntermediarySortCode = cptySettlementAccountOnIntermediarySortCode;
	}

	public String getCptySettlementAccount() {
		return cptySettlementAccount;
	}

	public void setCptySettlementAccount(String cptySettlementAccount) {
		this.cptySettlementAccount = cptySettlementAccount;
	}

	public String getCptySettlementAccountSortCode() {
		return cptySettlementAccountSortCode;
	}

	public void setCptySettlementAccountSortCode(String cptySettlementAccountSortCode) {
		this.cptySettlementAccountSortCode = cptySettlementAccountSortCode;
	}

	public String getOurSettlementNameAndAddressLine1() {
		return ourSettlementNameAndAddressLine1;
	}

	public void setOurSettlementNameAndAddressLine1(String ourSettlementNameAndAddressLine1) {
		this.ourSettlementNameAndAddressLine1 = ourSettlementNameAndAddressLine1;
	}

	public String getOurSettlementNameAndAddressLine2() {
		return ourSettlementNameAndAddressLine2;
	}

	public void setOurSettlementNameAndAddressLine2(String ourSettlementNameAndAddressLine2) {
		this.ourSettlementNameAndAddressLine2 = ourSettlementNameAndAddressLine2;
	}

	public String getCptySettlementSwiftBicCode() {
		return cptySettlementSwiftBicCode;
	}

	public void setCptySettlementSwiftBicCode(String cptySettlementSwiftBicCode) {
		this.cptySettlementSwiftBicCode = cptySettlementSwiftBicCode;
	}

}
