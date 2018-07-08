package model.cashflow;

import java.io.Serializable;

import javax.persistence.Column;

import model.OpasOrganizationObject;
import model.trade.FundTransferSystem;
import model.trade.PaymentIndicator;

public class CashFlowSi extends OpasOrganizationObject implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8365658912406895341L;

	private Long cashFlowSiId;
	
	private CashFlow cashFlow;
	
	/* *****************
	 * Basic Information 
	 * ***************** */
	private FundTransferSystem fts;
	private String currencyCode;
	private PaymentIndicator paymentIndicator;
	@Column(name = "PAYMENT_DETAILS_LINE_1", length = 35)
	private String paymentDetailsLine1;
	@Column(name = "PAYMENT_DETAILS_LINE_2", length = 35)
	private String paymentDetailsLine2;
	@Column(name = "PAYMENT_DETAILS_LINE_3", length = 35)
	private String paymentDetailsLine3;
	@Column(name = "PAYMENT_DETAILS_LINE_4", length = 35)
	private String paymentDetailsLine4;
	
	private String senderToReceiverNarrativeLine1;
	private String senderToReceiverNarrativeLine2;
	private String senderToReceiverNarrativeLine3;
	private String senderToReceiverNarrativeLine4;
	private String senderToReceiverNarrativeLine5;
	private String senderToReceiverNarrativeLine6;
	
	/* ******************************
	 * Our Pay or Receive Information 
	 * ****************************** */
	private String ourSettlementBankAcronym;
	
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
	
	private String cptySettlementBankAcronym;
	
	private String ibanNumber;
	
	/* Intermediary Bank Information */
	private String intermediaryBankNameLine1; /*GGTRM1*/
	private String intermediaryBankNameLine2; /*GGTRM2*/
	private String intermediaryBankClearingBankCode; /*GGTRIK*/
	private String intermediaryBankClearingSortCode; /*GGTRIF*/
	private String intermediaryBankSwiftBicCode; /*GGTRIS*/
	
	/* Beneficiary or Remitting Bank Information */
	private String settlementBankNameLine1; /*GGTRN1*/ /*GGTPN1*/
	private String settlementBankNameLine2; /*GGTRN2*/ /*GGTPN2*/
	private String settlementBankClearingBankCode; /*GGTRSK*/ /*GGTPSK*/
	private String settlementBankClearingSortCode; /*GGTRIR*/ /*GGTPSR*/
	private String settlementBankAccountOnIntermediary; /*GGTRIA*/
	private String settlementBankSwiftBicCode; /*GGTRSA*/ /*GGTPSA*/
	
	private String counterpartyAccountOnSettlementBank; /*GGTRCA*/ /*GGTPCA*/
	private String counterpartyClearingSortCode; /*GGTRSR*/
	
	/* Third Party Information */
	private String thirdPartyInstructionLine1; /*GGTRT1*/
	private String thirdPartyInstructionLine2; /*GGTRT2*/
	private String thirdPartyInstructionLine3; /*GGTRT3*/
	private String thirdPartyAccountNumber; /*GGTRAN*/
	private String thirdPartyClearingSortCode; /*GGTRSC*/
	private boolean thirdPartyBankIndicator; /*GGTRTB*/
	
	public Long getCashFlowSiId() {
		return cashFlowSiId;
	}
	public void setCashFlowSiId(Long cashFlowSiId) {
		this.cashFlowSiId = cashFlowSiId;
	}
	public CashFlow getCashFlow() {
		return cashFlow;
	}
	public void setCashFlow(CashFlow cashFlow) {
		this.cashFlow = cashFlow;
	}
	public FundTransferSystem getFts() {
		return fts;
	}
	public void setFts(FundTransferSystem fts) {
		this.fts = fts;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
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
	public String getOurSettlementBankAcronym() {
		return ourSettlementBankAcronym;
	}
	public void setOurSettlementBankAcronym(String ourSettlementBankAcronym) {
		this.ourSettlementBankAcronym = ourSettlementBankAcronym;
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
	public String getOurSettlementCountry() {
		return ourSettlementCountry;
	}
	public void setOurSettlementCountry(String ourSettlementCountry) {
		this.ourSettlementCountry = ourSettlementCountry;
	}
	public String getCptySettlementBankAcronym() {
		return cptySettlementBankAcronym;
	}
	public void setCptySettlementBankAcronym(String cptySettlementBankAcronym) {
		this.cptySettlementBankAcronym = cptySettlementBankAcronym;
	}
	public String getIbanNumber() {
		return ibanNumber;
	}
	public void setIbanNumber(String ibanNumber) {
		this.ibanNumber = ibanNumber;
	}
	public String getSenderToReceiverNarrativeLine1() {
		return senderToReceiverNarrativeLine1;
	}
	public void setSenderToReceiverNarrativeLine1(String senderToReceiverNarrativeLine1) {
		this.senderToReceiverNarrativeLine1 = senderToReceiverNarrativeLine1;
	}
	public String getSenderToReceiverNarrativeLine2() {
		return senderToReceiverNarrativeLine2;
	}
	public void setSenderToReceiverNarrativeLine2(String senderToReceiverNarrativeLine2) {
		this.senderToReceiverNarrativeLine2 = senderToReceiverNarrativeLine2;
	}
	public String getSenderToReceiverNarrativeLine3() {
		return senderToReceiverNarrativeLine3;
	}
	public void setSenderToReceiverNarrativeLine3(String senderToReceiverNarrativeLine3) {
		this.senderToReceiverNarrativeLine3 = senderToReceiverNarrativeLine3;
	}
	public String getSenderToReceiverNarrativeLine4() {
		return senderToReceiverNarrativeLine4;
	}
	public void setSenderToReceiverNarrativeLine4(String senderToReceiverNarrativeLine4) {
		this.senderToReceiverNarrativeLine4 = senderToReceiverNarrativeLine4;
	}
	public String getSenderToReceiverNarrativeLine5() {
		return senderToReceiverNarrativeLine5;
	}
	public void setSenderToReceiverNarrativeLine5(String senderToReceiverNarrativeLine5) {
		this.senderToReceiverNarrativeLine5 = senderToReceiverNarrativeLine5;
	}
	public String getSenderToReceiverNarrativeLine6() {
		return senderToReceiverNarrativeLine6;
	}
	public void setSenderToReceiverNarrativeLine6(String senderToReceiverNarrativeLine6) {
		this.senderToReceiverNarrativeLine6 = senderToReceiverNarrativeLine6;
	}
	public String getIntermediaryBankNameLine1() {
		return intermediaryBankNameLine1;
	}
	public void setIntermediaryBankNameLine1(String intermediaryBankNameLine1) {
		this.intermediaryBankNameLine1 = intermediaryBankNameLine1;
	}
	public String getIntermediaryBankNameLine2() {
		return intermediaryBankNameLine2;
	}
	public void setIntermediaryBankNameLine2(String intermediaryBankNameLine2) {
		this.intermediaryBankNameLine2 = intermediaryBankNameLine2;
	}
	public String getIntermediaryBankClearingBankCode() {
		return intermediaryBankClearingBankCode;
	}
	public void setIntermediaryBankClearingBankCode(String intermediaryBankClearingBankCode) {
		this.intermediaryBankClearingBankCode = intermediaryBankClearingBankCode;
	}
	public String getIntermediaryBankClearingSortCode() {
		return intermediaryBankClearingSortCode;
	}
	public void setIntermediaryBankClearingSortCode(String intermediaryBankClearingSortCode) {
		this.intermediaryBankClearingSortCode = intermediaryBankClearingSortCode;
	}
	public String getIntermediaryBankSwiftBicCode() {
		return intermediaryBankSwiftBicCode;
	}
	public void setIntermediaryBankSwiftBicCode(String intermediaryBankSwiftBicCode) {
		this.intermediaryBankSwiftBicCode = intermediaryBankSwiftBicCode;
	}
	public String getSettlementBankNameLine1() {
		return settlementBankNameLine1;
	}
	public void setSettlementBankNameLine1(String settlementBankNameLine1) {
		this.settlementBankNameLine1 = settlementBankNameLine1;
	}
	public String getSettlementBankNameLine2() {
		return settlementBankNameLine2;
	}
	public void setSettlementBankNameLine2(String settlementBankNameLine2) {
		this.settlementBankNameLine2 = settlementBankNameLine2;
	}
	public String getSettlementBankClearingBankCode() {
		return settlementBankClearingBankCode;
	}
	public void setSettlementBankClearingBankCode(String settlementBankClearingBankCode) {
		this.settlementBankClearingBankCode = settlementBankClearingBankCode;
	}
	public String getSettlementBankClearingSortCode() {
		return settlementBankClearingSortCode;
	}
	public void setSettlementBankClearingSortCode(String settlementBankClearingSortCode) {
		this.settlementBankClearingSortCode = settlementBankClearingSortCode;
	}
	public String getSettlementBankAccountOnIntermediary() {
		return settlementBankAccountOnIntermediary;
	}
	public void setSettlementBankAccountOnIntermediary(String settlementBankAccountOnIntermediary) {
		this.settlementBankAccountOnIntermediary = settlementBankAccountOnIntermediary;
	}
	public String getSettlementBankSwiftBicCode() {
		return settlementBankSwiftBicCode;
	}
	public void setSettlementBankSwiftBicCode(String settlementBankSwiftBicCode) {
		this.settlementBankSwiftBicCode = settlementBankSwiftBicCode;
	}
	public String getCounterpartyAccountOnSettlementBank() {
		return counterpartyAccountOnSettlementBank;
	}
	public void setCounterpartyAccountOnSettlementBank(String counterpartyAccountOnSettlementBank) {
		this.counterpartyAccountOnSettlementBank = counterpartyAccountOnSettlementBank;
	}
	public String getCounterpartyClearingSortCode() {
		return counterpartyClearingSortCode;
	}
	public void setCounterpartyClearingSortCode(String counterpartyClearingSortCode) {
		this.counterpartyClearingSortCode = counterpartyClearingSortCode;
	}
	public String getThirdPartyInstructionLine1() {
		return thirdPartyInstructionLine1;
	}
	public void setThirdPartyInstructionLine1(String thirdPartyInstructionLine1) {
		this.thirdPartyInstructionLine1 = thirdPartyInstructionLine1;
	}
	public String getThirdPartyInstructionLine2() {
		return thirdPartyInstructionLine2;
	}
	public void setThirdPartyInstructionLine2(String thirdPartyInstructionLine2) {
		this.thirdPartyInstructionLine2 = thirdPartyInstructionLine2;
	}
	public String getThirdPartyInstructionLine3() {
		return thirdPartyInstructionLine3;
	}
	public void setThirdPartyInstructionLine3(String thirdPartyInstructionLine3) {
		this.thirdPartyInstructionLine3 = thirdPartyInstructionLine3;
	}
	public String getThirdPartyAccountNumber() {
		return thirdPartyAccountNumber;
	}
	public void setThirdPartyAccountNumber(String thirdPartyAccountNumber) {
		this.thirdPartyAccountNumber = thirdPartyAccountNumber;
	}
	public String getThirdPartyClearingSortCode() {
		return thirdPartyClearingSortCode;
	}
	public void setThirdPartyClearingSortCode(String thirdPartyClearingSortCode) {
		this.thirdPartyClearingSortCode = thirdPartyClearingSortCode;
	}
	public boolean isThirdPartyBankIndicator() {
		return thirdPartyBankIndicator;
	}
	public void setThirdPartyBankIndicator(boolean thirdPartyBankIndicator) {
		this.thirdPartyBankIndicator = thirdPartyBankIndicator;
	}
	
}
