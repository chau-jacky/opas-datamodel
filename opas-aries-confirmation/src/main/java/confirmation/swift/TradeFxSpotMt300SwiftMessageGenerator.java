package confirmation.swift;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;

import org.apache.commons.lang.StringUtils;

import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field14C;
import com.prowidesoftware.swift.model.field.Field14S;
import com.prowidesoftware.swift.model.field.Field17F;
import com.prowidesoftware.swift.model.field.Field17I;
import com.prowidesoftware.swift.model.field.Field17O;
import com.prowidesoftware.swift.model.field.Field21A;
import com.prowidesoftware.swift.model.field.Field22C;
import com.prowidesoftware.swift.model.field.Field22L;
import com.prowidesoftware.swift.model.field.Field22M;
import com.prowidesoftware.swift.model.field.Field22N;
import com.prowidesoftware.swift.model.field.Field22P;
import com.prowidesoftware.swift.model.field.Field22R;
import com.prowidesoftware.swift.model.field.Field30U;
import com.prowidesoftware.swift.model.field.Field30V;
import com.prowidesoftware.swift.model.field.Field32B;
import com.prowidesoftware.swift.model.field.Field32E;
import com.prowidesoftware.swift.model.field.Field33B;
import com.prowidesoftware.swift.model.field.Field36;
import com.prowidesoftware.swift.model.field.Field53A;
import com.prowidesoftware.swift.model.field.Field53D;
import com.prowidesoftware.swift.model.field.Field56A;
import com.prowidesoftware.swift.model.field.Field56D;
import com.prowidesoftware.swift.model.field.Field57A;
import com.prowidesoftware.swift.model.field.Field57D;
import com.prowidesoftware.swift.model.field.Field72;
import com.prowidesoftware.swift.model.field.Field77D;

import confirmation.swift.SwiftMessageLiterals.Field17ICodes;
import model.cashflow.CashFlowSi;
import model.trade.Currency;
import model.trade.DoddFrankReportingInfo;
import model.trade.FtsCode;
import model.trade.FxSupplementaryInformation;
import model.trade.LegalAgreement;
import model.trade.PaymentIndicator;
import model.trade.Trade;
import model.trade.TradeFxSpot;

public class TradeFxSpotMt300SwiftMessageGenerator extends AbstractTradeMt300SwiftMessageGenerator {

	public TradeFxSpotMt300SwiftMessageGenerator() {
		super();
	}
	
	/**
	 * Format and return Sequence A Field 22C based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 22C
	 */
	@Override
	public Field22C getSequenceAField22C(Trade trade) {

		Field22C field22C = new Field22C();
		
		int field22CLength = 0;

		String ourSwiftBicCode = trade.getOrganization().getBicCode();
		String cptySwiftBicCode = trade.getCounterparty().getBicCode();
		BigDecimal contractRate = ((TradeFxSpot) trade).getContractRate();
		
		List<String> commonReference = SwiftMessageLiterals.formatCommonReference(
											ourSwiftBicCode, cptySwiftBicCode, contractRate);
		
		for (ListIterator<String> itr = commonReference.listIterator(); itr.hasNext();) {
			String component = itr.next();
			field22C.setComponent(itr.nextIndex(), component);
			field22CLength = field22CLength + component.length();
		}
		
		if (field22CLength == SwiftMessageLiterals.MT300_FIELD22C_LENGTH) {
			
			return field22C;
			
		} else {
			
			return new Field22C();
			
		}
		
	}
	
	/**
	 * Format and return Sequence A Field 17I based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 17I
	 */
	@Override
	public Field17I getSequenceAField17I(Trade trade) {
		
		Field17I field17I = new Field17I();
		
		boolean pvpIndicator = ((TradeFxSpot) trade).isPvpIndicator();
		
		if (pvpIndicator) {
			field17I.setIndicator(Field17ICodes.Y.name());
		} else {
			
			// Do nothing
			
		}
		
		return field17I;
		
	}
	
	/**
	 * Format and return Sequence A Field 77D based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 77D
	 */
	@Override
	public Field77D getSequenceAField77D(Trade trade) {
		
		Field77D field77D = new Field77D();
		
		LegalAgreement legalAgreement = ((TradeFxSpot) trade).getLegalAgreement();
		String legalAgreementLine1 = legalAgreement.getLegalAgreementLine1();
		String legalAgreementLine2 = legalAgreement.getLegalAgreementLine2();
		String legalAgreementLine3 = legalAgreement.getLegalAgreementLine3();
		String legalAgreementLine4 = legalAgreement.getLegalAgreementLine4();
		String legalAgreementLine5 = legalAgreement.getLegalAgreementLine5();
		String legalAgreementLine6 = legalAgreement.getLegalAgreementLine6();
		
		if (StringUtils.isNotBlank(legalAgreementLine1)) {
			field77D.setNarrativeLine1(legalAgreementLine1);
		}
		if (StringUtils.isNotBlank(legalAgreementLine2)) {
			field77D.setNarrativeLine2(legalAgreementLine2);
		}
		if (StringUtils.isNotBlank(legalAgreementLine3)) {
			field77D.setNarrativeLine3(legalAgreementLine3);
		}
		if (StringUtils.isNotBlank(legalAgreementLine4)) {
			field77D.setNarrativeLine4(legalAgreementLine4);
		}
		if (StringUtils.isNotBlank(legalAgreementLine5)) {
			field77D.setNarrativeLine5(legalAgreementLine5);
		}
		if (StringUtils.isNotBlank(legalAgreementLine6)) {
			field77D.setNarrativeLine6(legalAgreementLine6);
		}
		
		return field77D;
	}
	
	/**
	 * Format and return Sequence A Field 14C based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 14C
	 */
	@Override
	public Field14C getSequenceAField14C(Trade trade) {
		
		Field14C field14C = new Field14C();
		
		LegalAgreement legalAgreement = ((TradeFxSpot) trade).getLegalAgreement();
		String yearOfVersion = legalAgreement.getYearOfVersion();
		
		if (StringUtils.isNotBlank(yearOfVersion)) {
			field14C.setYear(yearOfVersion);
		}
		
		return field14C;
	}
	
	/**
	 * Format and return Sequence A Field 17F based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 17F
	 */
	@Override
	public Field17F getSequenceAField17F(Trade trade) {
		
		Field17F field17F = new Field17F();
		
		boolean nonDeliverableIndicator = ((TradeFxSpot) trade).isNonDeliverableIndicator();
		
		if (nonDeliverableIndicator) {
			
			field17F.setIndicator(SwiftMessageLiterals.Field17FCodes.Y.name());
			
		} else {
			
			// Do nothing
			
		}
		
		return field17F;
		
	}

	/**
	 * Format and return Sequence A Field 17O based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 17O
	 */
	@Override
	public Field17O getSequenceAField17O(Trade trade) {

		Field17O field17O = new Field17O();
		
		boolean nonDeliverableIndicator = ((TradeFxSpot) trade).isNonDeliverableIndicator();
		
		if (nonDeliverableIndicator) {
			
			field17O.setIndicator(SwiftMessageLiterals.Field17OCodes.N.name());
			
		} else {
			
			// Do nothing
			
		}
		
		return field17O;
		
	}
	
	/**
	 * Format and return Sequence A Field 32E based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 32E
	 */
	@Override
	public Field32E getSequenceAField32E(Trade trade) {
	
		Field32E field32E = new Field32E();
		
		boolean nonDeliverableIndicator = ((TradeFxSpot) trade).isNonDeliverableIndicator();
		Currency boughtCurrency = ((TradeFxSpot) trade).getBoughtCurrency();
		Currency soldCurrency = ((TradeFxSpot) trade).getSoldCurrency();
		
		boolean boughtCurrencyNonDeliverableIndicator = boughtCurrency.isNonDeliverableCurrencyIndicator();
		boolean soldCurrencyNonDeliverableIndicator = soldCurrency.isNonDeliverableCurrencyIndicator();
		
		if (nonDeliverableIndicator) {
			
			if (boughtCurrencyNonDeliverableIndicator && !soldCurrencyNonDeliverableIndicator) {
			
				field32E.setCurrency(soldCurrency.getSettlementCurrencyCode());
				
			} else if (!boughtCurrencyNonDeliverableIndicator && soldCurrencyNonDeliverableIndicator) {
				
				field32E.setCurrency(boughtCurrency.getSettlementCurrencyCode());
				
			} else {
				
				// Do nothing
				
			}
			
		} else {
			
			// Do nothing
			
		}
		
		return field32E;
		
	}
	
	/**
	 * Format and return Sequence A Field 30U based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 30U
	 */
	@Override
	public Field30U getSequenceAField30U(Trade trade) {
	
		/* This tag would not be required for FX Spot */
		
		Field30U field30U = new Field30U();
		
		return field30U;
		
	}

	/**
	 * Format and return Sequence A Field 14S based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 14S
	 */
	@Override
	public Field14S getSequenceAField14S(Trade trade) {
	
		/* This tag would not be required for FX Spot */
		
		Field14S field14S = new Field14S();
		
		return field14S;
		
	}

	/**
	 * Format and return Sequence A Field 21A based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 21A
	 */
	@Override
	public Field21A getSequenceAField21A(Trade trade) {
		
		Field21A field21A = new Field21A();
		
		boolean nonDeliverableIndicator = ((TradeFxSpot) trade).isNonDeliverableIndicator();
		
		if (nonDeliverableIndicator) {
			
			field21A.setReference(trade.getAssociatedTrade().getTransactionReference());
			
		} else {
			
			// Do nothing
			
		}
		
		return field21A;
		
	}
	
	/**
	 * Format and return Sequence B Field 30V based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 30T
	 */
	@Override
	public Field30V getSequenceBField30V(Trade trade) {
		
		Field30V field30V = new Field30V();
		
		field30V.setDate(dateFormatter.format(((TradeFxSpot)trade).getValueDate()));
		
		return field30V;
	}
	
	/**
	 * Format and return Sequence B Field 36 based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 36
	 */
	@Override
	public Field36 getSequenceBField36(Trade trade) {
		
		Field36 field36 = new Field36();
		
		BigDecimal contractRate = ((TradeFxSpot) trade).getContractRate();
		
		field36.setRate(contractRate);
		
		return field36;
	}

	/**
	 * Format and return Sequence B1 Field 32B based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 32B
	 */
	@Override
	public Field32B getSequenceB1Field32B(Trade trade) {
		
		Field32B field32B = new Field32B();

		TradeFxSpot tradeFxSpot = (TradeFxSpot) trade;

		field32B.setCurrency(tradeFxSpot.getBoughtCurrency().getCurrencyCode());
		field32B.setAmount(tradeFxSpot.getBoughtCurrencyAmount().abs());
		
		return field32B;
		
	}
	
	/**
	 * Format and return Sequence B2 Field 33B based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 33B
	 */
	@Override
	public Field33B getSequenceB2Field33B(Trade trade) {
		
		Field33B field33B = new Field33B();

		TradeFxSpot tradeFxSpot = (TradeFxSpot) trade;

		field33B.setCurrency(tradeFxSpot.getSoldCurrency().getCurrencyCode());
		field33B.setAmount(tradeFxSpot.getSoldCurrencyAmount().abs());
		
		return field33B;
		
	}
	
	/**
	 * Format and return Sequence B1 Field 53a based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 53a
	 */
	@Override
	public Field getSequenceB1Field53a(Trade trade) {
		
		Field field53a;
		
		Field53A field53A = new Field53A();
		
		field53a = field53A;
		
		CashFlowSi receiveCashFlowSi = ((TradeFxSpot)trade).getCashFlow(PaymentIndicator.RECEIVE).getCashFlowSi();
		
		String settlementBankSwiftBic = receiveCashFlowSi.getSettlementBankSwiftBicCode();
		
		if(FtsCode.CUA.equals(receiveCashFlowSi.getFts().getFtsCode()) && StringUtils.isNotBlank(settlementBankSwiftBic)) {
			
			String cptySettlementAccount = receiveCashFlowSi.getCounterpartyAccountOnSettlementBank();
			
			if (StringUtils.isNotBlank(cptySettlementAccount)) {
				
				field53A.setAccount(cptySettlementAccount);
				
			}
			
			if (StringUtils.isNotBlank(settlementBankSwiftBic)) { 
			
				field53A.setBIC(settlementBankSwiftBic);
				
			}
			
		}
		
		return field53a;
		
	}

	/**
	 * Format and return Sequence B1 Field 57a based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 57a
	 */
	@Override
	public Field getSequenceB1Field57a(Trade trade) {

		Field field57a;

		Field57A field57A = new Field57A();
		
		field57a = field57A;
		
		CashFlowSi receiveCashFlowSi = ((TradeFxSpot)trade).getCashFlow(PaymentIndicator.RECEIVE).getCashFlowSi();
		
		String ourSettlementSwiftBicCode = receiveCashFlowSi.getOurSettlementSwiftBicCode();
		
		if (StringUtils.isNotBlank(ourSettlementSwiftBicCode)) {
			
			field57A.setBIC(ourSettlementSwiftBicCode);
			
		} else {
			
			Field57D field57D = new Field57D();
			
			String ourSettlementNameAndAddressLine1 = receiveCashFlowSi.getOurSettlementNameAndAddressLine1();
			String ourSettlementNameAndAddressLine2 = receiveCashFlowSi.getOurSettlementNameAndAddressLine2();
			
			if (StringUtils.isNotBlank(ourSettlementNameAndAddressLine1)) {
				
				field57D.setNameAndAddressLine1(ourSettlementNameAndAddressLine1);
				
			} else {
				
				//Do nothing
				
			}
			
			if (StringUtils.isNotBlank(ourSettlementNameAndAddressLine2)) {
				
				field57D.setNameAndAddressLine2(ourSettlementNameAndAddressLine2);
				
			} else {
				
				//Do nothing
				
			}
			
			field57a = field57D;
			
		}
		
		return field57a;
		
	}

	/**
	 * Format and return Sequence B2 Field 53a based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 53a
	 */
	@Override
	public Field getSequenceB2Field53a(Trade trade) {
		
		Field field53a;
		
		Field53A field53A = new Field53A();
		
		field53a = field53A;
		
		CashFlowSi payCashFlowSi = ((TradeFxSpot)trade).getCashFlow(PaymentIndicator.PAY).getCashFlowSi();
		
		String ourSettlementSwiftBic = payCashFlowSi.getOurSettlementSwiftBicCode();
		String ourAccountWithThemInTheirBook = payCashFlowSi.getOurAccountWithThemInTheirBook();
		
		if (StringUtils.isNotBlank(ourSettlementSwiftBic)) {
			
			/* Our Settlement Swift BIC present, generate 53A */
			
			String field53AContent = StringUtils.EMPTY;
			
			if(FtsCode.CHIPS.equals(payCashFlowSi.getFts().getFtsCode()) && 
					StringUtils.isNotBlank(payCashFlowSi.getOurSettlementClearingSortCode())) { 
				
				field53AContent = SwiftMessageLiterals.CHIPS_ACCOUNT_PREFIX;
				field53AContent = field53AContent.concat(
										StringUtils.substring(payCashFlowSi.getOurSettlementClearingSortCode(), 0, 6));
				field53A.setAccount(field53AContent);
				field53A.setBIC(ourSettlementSwiftBic);
				
			} else if (StringUtils.isNotBlank(ourAccountWithThemInTheirBook)) {
				
				field53A.setAccount(ourAccountWithThemInTheirBook);
				field53A.setBIC(ourSettlementSwiftBic);
				
			} else {
				
				field53A.setBIC(ourSettlementSwiftBic);
				
			}
			
		} else {
			
			/* Our Settlement Swift BIC absent, generate 53D */
			
			Field53D field53D = new Field53D();
			
			String ourSettlementNameAndAddressLine1 = payCashFlowSi.getOurSettlementNameAndAddressLine1();
			String ourSettlementNameAndAddressLine2 = payCashFlowSi.getOurSettlementNameAndAddressLine2();
			
			if (StringUtils.isNotBlank(ourSettlementNameAndAddressLine1)) {
				
				field53D.setNameAndAddressLine1(ourSettlementNameAndAddressLine1);
				
			}
			if (StringUtils.isNotBlank(ourSettlementNameAndAddressLine2)) {
				
				field53D.setNameAndAddressLine2(ourSettlementNameAndAddressLine2);
				
			}
			
			field53a = field53D;
			
		}
	
		return field53a;
		
	}

	/**
	 * Format and return Sequence B2 Field 56a based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 56a
	 */
	@Override
	public Field getSequenceB2Field56a(Trade trade) {
		
		Field field56a;
		
		Field56A field56A = new Field56A();
		
		field56a = field56A;
		
		CashFlowSi payCashFlowSi = ((TradeFxSpot)trade).getCashFlow(PaymentIndicator.PAY).getCashFlowSi();
		
		String cptyIntermediarySwiftBicCode = payCashFlowSi.getIntermediaryBankSwiftBicCode();
		
		if (StringUtils.isNotBlank(cptyIntermediarySwiftBicCode)) {
			
			field56A.setBIC(cptyIntermediarySwiftBicCode);
			
		} else {
			
			Field56D field56D = new Field56D();
			
			String field56DContent = StringUtils.EMPTY;
			
			String cptyIntermediaryBankCode = payCashFlowSi.getIntermediaryBankClearingBankCode();
			String cptyIntermediaryNameAndAddressLine1 = payCashFlowSi.getIntermediaryBankNameLine1();
			String cptyIntermediaryNameAndAddressLine2 = payCashFlowSi.getIntermediaryBankNameLine2();
			
			if (StringUtils.isNotBlank(cptyIntermediaryBankCode)) {
				
				field56DContent = SwiftMessageLiterals.COUNTERPARTY_ACCOUNT_PREFIX;
				field56DContent = field56DContent.concat(cptyIntermediaryBankCode);
				field56D.setAccount(field56DContent);
				
			}
			
			if (StringUtils.isNotBlank(cptyIntermediaryNameAndAddressLine1)) {
				
				field56D.setNameAndAddressLine1(cptyIntermediaryNameAndAddressLine1);
				
			}
			
			if (StringUtils.isNotBlank(cptyIntermediaryNameAndAddressLine2)) {
				
				field56D.setNameAndAddressLine2(cptyIntermediaryNameAndAddressLine2);
				
			}
			
			field56a = field56D;
			
		}
	
		return field56a;
		
	}
	
	/**
	 * Format and return Sequence B2 Field 57a based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 57a
	 */
	@Override
	public Field getSequenceB2Field57a(Trade trade) {
		
		Field field57a;
		
		Field57A field57A = new Field57A();

		field57a = field57A;
		
		CashFlowSi cashFlowSi = ((TradeFxSpot)trade).getCashFlow(PaymentIndicator.PAY).getCashFlowSi();
		
		String settlementBankSwiftBic = cashFlowSi.getSettlementBankSwiftBicCode();
		
		if (StringUtils.isNotBlank(settlementBankSwiftBic)) {
			
			field57A.setBIC(settlementBankSwiftBic);
			
		} else {
			
			Field57D field57D = new Field57D();
			
			String settlementBankNameLine1 = cashFlowSi.getSettlementBankNameLine1();
			String settlementBankNameLine2 = cashFlowSi.getSettlementBankNameLine2();
			
			if (StringUtils.isNotBlank(settlementBankNameLine1)) {
				
				field57D.setNameAndAddressLine1(settlementBankNameLine1);
				
			}
			if (StringUtils.isNotBlank(settlementBankNameLine2)) {
				
				field57D.setNameAndAddressLine2(settlementBankNameLine2);
				
			}
			
			field57a = field57D;
			
		}
		
		return field57a;
		
	}

	/**
	 * Format and return Sequence C Field 72 based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 72
	 */
	@Override
	public Field72 getSequenceCField72(Trade trade) {
		
		Field72 field72 = new Field72();
		
		FxSupplementaryInformation fxSupplementaryInfo = ((TradeFxSpot) trade).getFxSupplementaryInfo();
		String senderToReceiverInfoLine1 = fxSupplementaryInfo.getSenderToReceiverInfoLine1();
		String senderToReceiverInfoLine2 = fxSupplementaryInfo.getSenderToReceiverInfoLine2();
		String senderToReceiverInfoLine3 = fxSupplementaryInfo.getSenderToReceiverInfoLine3();
		
		if (StringUtils.isNotBlank(senderToReceiverInfoLine1)) {
			field72.setNarrativeLine1(senderToReceiverInfoLine1);
		}
	
		if (StringUtils.isNotBlank(senderToReceiverInfoLine2)) {
			field72.setNarrativeLine2(senderToReceiverInfoLine2);
		}
		
		if (StringUtils.isNotBlank(senderToReceiverInfoLine3)) {
			field72.setNarrativeLine3(senderToReceiverInfoLine3);
		}
		
		return field72;
		
	}
	
	/**
	 * Format and return Sequence E Field 22L based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 22L
	 */
	@Override
	public Field22L getSequenceEField22L(Trade trade) {
	
		Field22L field22L = new Field22L();
		
		boolean doddFrankSiteIndicator = trade.getOrganization().isDoddFrankReportingFlag();
		boolean fxSwapIndicator = ((TradeFxSpot) trade).isFxSwapIndicator();
		
		if (doddFrankSiteIndicator && fxSwapIndicator) {
			
			field22L.setType(SwiftMessageLiterals.AS_REQUIRED);
			
		} else {
			
			// Do nothing 
			
		}
		
		return field22L;
		
	}

	/**
	 * Format and return Sequence E Field 22M based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 22M
	 */
	@Override
	public Field22M getSequenceEField22M(Trade trade) {
		
		Field22M field22M = new Field22M();
		
		boolean doddFrankSiteIndicator = trade.getOrganization().isDoddFrankReportingFlag();
		boolean fxSwapIndicator = ((TradeFxSpot) trade).isFxSwapIndicator();
		DoddFrankReportingInfo doddFrankReportingInfo = trade.getDoddFrankReportingInfo();
		
		if (doddFrankSiteIndicator && fxSwapIndicator) {
			
			field22M.setType(StringUtils.substring(doddFrankReportingInfo.getDealUsi(), 0, 10));
			
		} else {
			
			// Do nothing 
			
		}
		
		return field22M;
		
	}

	/**
	 * Format and return Sequence E Field 22N based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 22N
	 */
	@Override
	public Field22N getSequenceEField22N(Trade trade) {

		Field22N field22N = new Field22N();
		
		boolean doddFrankSiteIndicator = trade.getOrganization().isDoddFrankReportingFlag();
		boolean fxSwapIndicator = ((TradeFxSpot) trade).isFxSwapIndicator();
		DoddFrankReportingInfo doddFrankReportingInfo = trade.getDoddFrankReportingInfo();
		
		if (doddFrankSiteIndicator && fxSwapIndicator) {
			
			field22N.setTransactionIdentifier(StringUtils.substring(doddFrankReportingInfo.getDealUsi(), 10));
			
		} else {
			
			// Do nothing 
			
		}
		
		return field22N;
		
	}

	/**
	 * Format and return Sequence E Field 22P based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 22P
	 */
	@Override
	public Field22P getSequenceEField22P(Trade trade) {
		
		Field22P field22P = new Field22P();
		
		boolean doddFrankSiteIndicator = trade.getOrganization().isDoddFrankReportingFlag();
		boolean fxSwapIndicator = ((TradeFxSpot) trade).isFxSwapIndicator();
		DoddFrankReportingInfo doddFrankReportingInfo = trade.getDoddFrankReportingInfo();
		
		if (doddFrankSiteIndicator && fxSwapIndicator) {
			
			field22P.setType(StringUtils.substring(doddFrankReportingInfo.getDealPriorUsi(), 0, 10));
			
		} else {
			
			// Do nothing 
			
		}
		
		return field22P;
	}

	/**
	 * Format and return Sequence E Field 22R based on the given Spot trade.
	 *
	 * @param trade FX Spot trade that requires generation of MT300
	 * @return Formatted Field 22R
	 */
	@Override
	public Field22R getSequenceEField22R(Trade trade) {
		
		Field22R field22R = new Field22R();
		
		boolean doddFrankSiteIndicator = trade.getOrganization().isDoddFrankReportingFlag();
		boolean fxSwapIndicator = ((TradeFxSpot) trade).isFxSwapIndicator();
		DoddFrankReportingInfo doddFrankReportingInfo = trade.getDoddFrankReportingInfo();
		
		if (doddFrankSiteIndicator && fxSwapIndicator) {
			
			field22R.setPriorTransactionIdentifier(StringUtils.substring(doddFrankReportingInfo.getDealPriorUsi(), 10));
			
		} else {
			
			// Do nothing 
			
		}
		
		return field22R;
		
	}

}
