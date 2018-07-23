package settlement.swift;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.commons.lang.StringUtils;

import com.prowidesoftware.swift.model.SwiftBlock1;
import com.prowidesoftware.swift.model.SwiftBlock2;
import com.prowidesoftware.swift.model.SwiftBlock2Input;
import com.prowidesoftware.swift.model.SwiftBlock3;
import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field108;
import com.prowidesoftware.swift.model.field.Field13C;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field23B;
import com.prowidesoftware.swift.model.field.Field23E;
import com.prowidesoftware.swift.model.field.Field26T;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field33B;
import com.prowidesoftware.swift.model.field.Field36;
import com.prowidesoftware.swift.model.field.Field50A;
import com.prowidesoftware.swift.model.field.Field50F;
import com.prowidesoftware.swift.model.field.Field50K;
import com.prowidesoftware.swift.model.field.Field51A;
import com.prowidesoftware.swift.model.field.Field52A;
import com.prowidesoftware.swift.model.field.Field53B;
import com.prowidesoftware.swift.model.field.Field54A;
import com.prowidesoftware.swift.model.field.Field55A;
import com.prowidesoftware.swift.model.field.Field56A;
import com.prowidesoftware.swift.model.field.Field56D;
import com.prowidesoftware.swift.model.field.Field57A;
import com.prowidesoftware.swift.model.field.Field57D;
import com.prowidesoftware.swift.model.field.Field59;
import com.prowidesoftware.swift.model.field.Field59F;
import com.prowidesoftware.swift.model.field.Field70;
import com.prowidesoftware.swift.model.field.Field71A;
import com.prowidesoftware.swift.model.field.Field71F;
import com.prowidesoftware.swift.model.field.Field71G;
import com.prowidesoftware.swift.model.field.Field72;
import com.prowidesoftware.swift.model.field.Field77B;
import com.prowidesoftware.swift.model.field.Field77T;

import model.cashflow.CashFlow;
import model.cashflow.CashFlowSi;
import model.cashflow.ChargesForIndicator;
import model.cashflow.PayWireType;
import model.party.Counterparty;
import model.party.ERDSLegalEntity;
import model.trade.Currency;
import model.trade.FtsCode;
import model.trade.FundTransferSystem;
import model.trade.NationalClearingSystemCode;
import model.trade.PaymentIndicator;
import model.trade.Trade;
import model.trade.TradeFxForward;
import model.trade.TradeFxSpot;
import settlement.swift.SwiftMessageLiterals.Mt103Field23BCodes;
import settlement.swift.SwiftMessageLiterals.Mt103Field23ECodes;
import settlement.swift.SwiftMessageLiterals.Mt103Field56ACodes;
import settlement.swift.SwiftMessageLiterals.Mt103Field59Codes;
import settlement.swift.SwiftMessageLiterals.Mt103Field59FCodes;
import settlement.swift.SwiftMessageLiterals.Mt103Field71ACodes;
import settlement.swift.SwiftMessageLiterals.Mt103SenderAndReceiverBicCountryCodes;
import settlement.swift.SwiftMessageLiterals.Mt202Field56ACodes;
import settlement.swift.SwiftMessageLiterals.Mt202Field57ACodes;
import settlement.swift.SwiftMessageLiterals.Mt202Field57DCodes;

public class Mt103SwiftMessageGenerator extends AbstractMt103SwiftMessageGenerator {

	SimpleDateFormat dateFormatter;

	public Mt103SwiftMessageGenerator() {
		super();
		dateFormatter = new SimpleDateFormat("yyMMdd");
	}
	
	@Override
	public SwiftBlock1 getSwiftBlock1(CashFlow cashFlow) {

		SwiftBlock1 block1 = new SwiftBlock1();
		
		block1.setApplicationId("F");
		block1.setServiceId("01");
		block1.setSender(cashFlow.getOrganization().getBicCode());
		block1.setSessionNumber("0001");
		block1.setSequenceNumber("000001");
		
		return block1;
		
	}

	@Override
	public SwiftBlock2 getSwiftBlock2(CashFlowSi cashFlowSi) {

		SwiftBlock2Input block2 = new SwiftBlock2Input();
		
		block2.setMessageType("103");
		block2.setReceiver(cashFlowSi.getOurSettlementSwiftBicCode());
		
		return block2;
		
	}

	@Override
	public SwiftBlock3 getSwiftBlock3(CashFlow cashFlow) {

		SwiftBlock3 block3 = new SwiftBlock3();
		
		/* Indicates the Message User Reference (MUR) value, which can be up to 16 characters, 
		 * and will be returned in the ACK */
		Field108 field108 = new Field108(cashFlow.getMessageUserReference());
		
		block3.append(field108);
		
		return block3;
		
	}

	@Override
	public Field20 getField20(CashFlow cashFlow) {
		
		Field20 field20 = new Field20();
		
		field20.setReference(cashFlow.getCashFlowReference());
		
		return field20;
		
	}

	@Override
	public Field13C getField13C(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		/* TREATS does not generate this field currently */
		/* Used by CLS & Target2 payments */
		
		Field13C field13C = new Field13C();
		
		return field13C;
		
	}

	@Override
	public Field23B getField23B(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		//TREATS use only CRED in the current design
		
		Field23B field23B = new Field23B();
		
		field23B.setType(Mt103Field23BCodes.CRED.name());
		
		return field23B;
		
	}

	@Override
	public Field23E getField23E(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		//TREATS use only CORT in the current design
		
		Field23E field23E = new Field23E();
		
		field23E.setCode(Mt103Field23ECodes.CORT.name());
		
		return field23E;
		
	}

	@Override
	public Field26T getField26T(CashFlow cashFlow, CashFlowSi cashFlowSi) {

		//TREATS does not generate this field currently
		
		Field26T field26T = new Field26T();
		
		return field26T;
		
	}

	@Override
	public Field32A getField32A(CashFlow cashFlow) {
	
		Field32A field32A = new Field32A();
		
		field32A.setDate(dateFormatter.format(cashFlow.getValueDate()));
		field32A.setCurrency(cashFlow.getCurrency().getSettlementCurrencyCode());
		field32A.setAmount(cashFlow.getCashFlowAmount().doubleValue());
		
		return field32A;
		
	}

	@Override
	public Field33B getField33B(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field33B field33B = new Field33B();
		
		String originatingSwiftBic = cashFlow.getOrganization().getBicCode();
		String destinationSwiftBic = cashFlowSi.getOurSettlementSwiftBicCode();
		
		String originatingSwiftBicCountryCode = SwiftMessageLiterals.retrieveCountryCodeFromSwiftBic(originatingSwiftBic);
		String destinationSwiftBicCountryCode = SwiftMessageLiterals.retrieveCountryCodeFromSwiftBic(destinationSwiftBic);
		
		boolean originatingSwiftBicCountryCodeOnList;
		boolean destintionSwiftBicCountryCodeOnList;
		
		if(StringUtils.isNotBlank(originatingSwiftBicCountryCode) &&
				Mt103SenderAndReceiverBicCountryCodes.getMt103SenderAndReceiverBicCountryCodes(originatingSwiftBicCountryCode) != null) {
		
			originatingSwiftBicCountryCodeOnList = true;
			
		} else {
			
			originatingSwiftBicCountryCodeOnList = false;
			
		}
		
		if(StringUtils.isNotBlank(destinationSwiftBicCountryCode) &&
				Mt103SenderAndReceiverBicCountryCodes.getMt103SenderAndReceiverBicCountryCodes(destinationSwiftBicCountryCode) != null) {
		
			destintionSwiftBicCountryCodeOnList = true;
			
		} else {
			
			destintionSwiftBicCountryCodeOnList = false;
			
		}
		
		if(originatingSwiftBicCountryCodeOnList && destintionSwiftBicCountryCodeOnList) {
			
			//Field 33B is Mandatory
			field33B.setCurrency(cashFlow.getCurrency().getSettlementCurrencyCode());
			field33B.setAmount(cashFlow.getCashFlowAmount());
			
		} else {
			
			//Field 33B is Optional
			
			if(StringUtils.equals(getField71A(cashFlowSi).getValue(), 
					SwiftMessageLiterals.Mt103Field71ACodes.BEN.getCode())) {
				
				//All transaction charges are to be borne by the beneficiary customer
				
				field33B.setCurrency(cashFlow.getCurrency().getSettlementCurrencyCode());
				field33B.setAmount(cashFlow.getCashFlowAmount());
				
			} else {
				
				//Do nothing
				
			}
			
		}
		
		return field33B;
		
	}

	@Override
	public Field36 getField36(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		//TREATS does not generate this field currently
		
		Field36 field36 = new Field36();
		
		return field36;
		
	}

	@Override
	public Field getField50a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field50A field50A = new Field50A();
		Field50F field50F = new Field50F();
		Field50K field50K = new Field50K();
		
		Field field50a;
		
		/* trade info */
		Trade trade = cashFlow.getTrade();
		
		/* counterparty related info */
		Counterparty counterparty = trade.getCounterparty();
		String counterpartyAcronym = counterparty.getAcronym();
		String cptySwiftBicCode = counterparty.getOrganization().getBicCode();
		String cptyNameAndAddressLine1 = counterparty.getNameAndAddressLine1();
		String cptyNameAndAddressLine2 = counterparty.getNameAndAddressLine2();
		String cptyNameAndAddressLine3 = counterparty.getNameAndAddressLine3();
		String cptyNameAndAddressLine4 = counterparty.getNameAndAddressLine4();
		ERDSLegalEntity legalEntity = counterparty.getErdsLegalEntity();
		String countryDescription = SwiftMessageLiterals.retrieveCountryDescription(
										counterparty.getCountryOfResidence());
		
		/* our settlement info */
		String ourSwiftBicCode = cashFlow.getOrganization().getBicCode();
		String ourAccountWithThemInTheirBook = cashFlowSi.getOurAccountWithThemInTheirBook();
		
		/* group member + branch menumonic */
		String groupMemberBranchMneumonic = cashFlow.getOrganization().getGroupMemberAbbreviation().concat(
												cashFlow.getOrganization().getBranchMneumonic());
		
		boolean EURegulationFlag = cashFlow.getOrganization().isEURegulationFlag();
		boolean globalStandardCompliantFlag = cashFlowSi.isGlobalStandardCompliantFlag();
		boolean ourBankAsOrderingCustomer = false;
		
		if(!PayWireType.TRUE_THIRD_PARTY.equals(cashFlowSi.getPayWireType()) &&
				trade.getProduct().isOurBankAsOrderingCustomer()) {
			
			ourBankAsOrderingCustomer = true;
			
		} else {
			
			ourBankAsOrderingCustomer = false;
					
		}
		
		//System.out.println(ourBankAsOrderingCustomer);
		
		String formattedCounterpartyAcronym;
		
		if(StringUtils.equals(StringUtils.substring(counterpartyAcronym, 0, 1), SwiftMessageLiterals.HASH)) { 
		
			//Remove if the the counterparty acronym starts with # sign
			
			formattedCounterpartyAcronym = StringUtils.replaceOnce(counterpartyAcronym, 
												SwiftMessageLiterals.HASH, StringUtils.EMPTY);
			
		} else {
			
			formattedCounterpartyAcronym = counterpartyAcronym;
			
		}
		
		//System.out.println(formattedCounterpartyAcronym);
		
		/* format the account attribute */
		if(ourBankAsOrderingCustomer) { 
			
			//our bank as ordering customer
			
			if(EURegulationFlag) { 
				
				//EU Regulation 2015/847 is effective
				
				if(StringUtils.isNotBlank(ourAccountWithThemInTheirBook)) {
				
					field50A.setAccount(ourAccountWithThemInTheirBook);
					field50K.setAccount(ourAccountWithThemInTheirBook);
					
				} else {
					
					//Prompt error
					
				}
				
			} else {
				
				//EU Regulation 2015/847 is not effective
				
				field50A.setAccount(groupMemberBranchMneumonic.concat(SwiftMessageLiterals.SPACE).
										concat(cashFlow.getCashFlowReference()));
				field50K.setAccount(groupMemberBranchMneumonic.concat(SwiftMessageLiterals.SPACE).
										concat(cashFlow.getCashFlowReference()));
				
			}
			
		} else {
			
			//trading counterparty as ordering customer
			field50A.setAccount(formattedCounterpartyAcronym.concat(SwiftMessageLiterals.SPACE).
										concat(cashFlow.getCashFlowReference()));
			field50K.setAccount(formattedCounterpartyAcronym.concat(SwiftMessageLiterals.SPACE).
										concat(cashFlow.getCashFlowReference()));
			
		}
		
		
		if(globalStandardCompliantFlag) {
			
			if(ourBankAsOrderingCustomer) {
				
				//Generate 50A
				field50A.setBIC(ourSwiftBicCode);
				field50a = field50A;
				
			} else {
				
				if(legalEntity != null && legalEntity.isSwiftCompliantFlag()) {
					
					//Generate 50F
					
					String legalEntityNameLine1 = legalEntity.getLegalEntityNameLine1();
					String legalEntityNameLine2 = legalEntity.getLegalEntityNameLine2();
					String legalEntityNameLine3 = legalEntity.getLegalEntityNameLine3();
					
					String legalEntityAddressLine1 = legalEntity.getLegalEntityAddressLine1();
					String legalEntityAddressLine2 = legalEntity.getLegalEntityAddressLine2();
					
					String countryStateCity = StringUtils.replaceOnce(legalEntity.getCountryStateCity(), "3/", StringUtils.EMPTY);
					
					field50F.setPartyIdentifier(SwiftMessageLiterals.SLASH.concat(legalEntity.getGridId().toString()));
					
					int componentSize = field50F.componentsSize();
					int componentNumber = 2;
					
					if(StringUtils.isNotBlank(legalEntityNameLine1)) {
						field50F.setComponent(componentNumber, "1");
						componentNumber++;
						field50F.setComponent(componentNumber, legalEntityNameLine1);
						componentNumber++;
					}
					
					if(StringUtils.isNotBlank(legalEntityNameLine2)) {
						field50F.setComponent(componentNumber, "1");
						componentNumber++;
						field50F.setComponent(componentNumber, legalEntityNameLine2);
						componentNumber++;
					}
					
					if(StringUtils.isNotBlank(legalEntityNameLine3)) {
						field50F.setComponent(componentNumber, "1");
						componentNumber++;
						field50F.setComponent(componentNumber, legalEntityNameLine3);
						componentNumber++;
					}
					
					if(StringUtils.isNotBlank(legalEntityAddressLine1) && componentNumber < componentSize-2) {
						field50F.setComponent(componentNumber, "2");
						componentNumber++;
						field50F.setComponent(componentNumber, legalEntityAddressLine1);
						componentNumber++;
					}
					
					if(StringUtils.isNotBlank(legalEntityAddressLine2) && componentNumber < componentSize-2) {
						field50F.setComponent(componentNumber, "2");
						componentNumber++;
						field50F.setComponent(componentNumber, legalEntityAddressLine2);
						componentNumber++;
					}
					
					if(StringUtils.isNotBlank(countryStateCity)) {
						field50F.setComponent(componentNumber, "3");
						componentNumber++;
						field50F.setComponent(componentNumber, countryStateCity);
						componentNumber++;
					}
					
					field50a = field50F;
					
				} else if(StringUtils.isNotBlank(cptySwiftBicCode)) {
					
					//Generate 50A
					field50A.setBIC(cptySwiftBicCode);
					field50a = field50A;
					
				} else {
					
					//Generate 50K
					int componentSize = field50K.componentsSize();
					int componentNumber = 2;
					
					if(StringUtils.isNotBlank(cptyNameAndAddressLine1)) {
						field50K.setComponent(componentNumber, cptyNameAndAddressLine1);
						componentNumber++;
					}
					
					if(StringUtils.isNotBlank(cptyNameAndAddressLine2)) {
						field50K.setComponent(componentNumber, cptyNameAndAddressLine2);
						componentNumber++;
					}
					
					if(StringUtils.isNotBlank(cptyNameAndAddressLine3)) {
						field50K.setComponent(componentNumber, cptyNameAndAddressLine3);
						componentNumber++;
					}
					
					if(StringUtils.isNotBlank(cptyNameAndAddressLine4) && componentNumber < componentSize) {
						field50K.setComponent(componentNumber, cptyNameAndAddressLine4);
						componentNumber++;
					}
					
					if(StringUtils.isNotBlank(countryDescription)) {
						field50K.setComponent(componentNumber, countryDescription);
					}
					
					field50a = field50K;
					
				}
				
			}
			
		} else {
			
			//ordering customer is trading counterparty if not GS compliant
			
			if(StringUtils.isNotBlank(cptySwiftBicCode)) {
				
				//format 50A
				field50A.setBIC(cptySwiftBicCode);
				field50a = field50A;
				
			} else {
				
				//format 50K
				int componentSize = field50K.componentsSize();
				int componentNumber = 2;
				
				if(StringUtils.isNotBlank(cptyNameAndAddressLine1)) {
					field50K.setComponent(componentNumber, cptyNameAndAddressLine1);
					componentNumber++;
				}
				
				if(StringUtils.isNotBlank(cptyNameAndAddressLine2)) {
					field50K.setComponent(componentNumber, cptyNameAndAddressLine2);
					componentNumber++;
				}
				
				if(StringUtils.isNotBlank(cptyNameAndAddressLine3)) {
					field50K.setComponent(componentNumber, cptyNameAndAddressLine3);
					componentNumber++;
				}
				
				if(StringUtils.isNotBlank(cptyNameAndAddressLine4) && componentNumber < componentSize) {
					field50K.setComponent(componentNumber, cptyNameAndAddressLine4);
					componentNumber++;
				}
				
				if(StringUtils.isNotBlank(countryDescription)) {
					field50K.setComponent(componentNumber, countryDescription);
				}
				
				field50a = field50K;
				
			}
			
		}
		
		return field50a;
		
	}

	@Override
	public Field51A getField51A(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		//TREATS does not generate this field currently
		
		Field51A field51A = new Field51A();
		
		return field51A;
		
	}

	@Override
	public Field getField52a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field52A field52A = new Field52A();
		
		Field field52a = field52A;
		
		/* trade info */
		Trade trade = cashFlow.getTrade();
		
		String ourSwiftBicCode = cashFlow.getOrganization().getBicCode();
		
		boolean globalStandardCompliantFlag = cashFlowSi.isGlobalStandardCompliantFlag();
		boolean ourBankAsOrderingCustomer = false;
		
		if(!PayWireType.TRUE_THIRD_PARTY.equals(cashFlowSi.getPayWireType()) &&
				trade.getProduct().isOurBankAsOrderingCustomer()) {
			
			ourBankAsOrderingCustomer = true;
			
		} else {
			
			ourBankAsOrderingCustomer = false;
					
		}
		
		if(globalStandardCompliantFlag && !ourBankAsOrderingCustomer) {
			
			field52A.setBIC(ourSwiftBicCode);
			
		} else {
			
			//Do nothing, not to generate Field 52a
			
		}
		
		return field52a;
		
	}

	@Override
	public Field getField53a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field53B field53B = new Field53B();
		
		Field field53a = field53B;
		
		if(cashFlowSi.getPaymentIndicator().equals(PaymentIndicator.PAY)) {
			
			String ourAccountWithThemInTheirBook = cashFlowSi.getOurAccountWithThemInTheirBook();
			
			if(StringUtils.isNotBlank(ourAccountWithThemInTheirBook)) {
				
				field53B.setAccount(ourAccountWithThemInTheirBook);
				
			} else {
				
				//Do nothing, our nostro account is missing in other bank's book
				
			}
			
		} else {
			
			//Do nothing, missing Pay settlement instruction
			
		}
		
		return field53a;
		
	}

	@Override
	public Field getField54a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		//TREATS does not generate this field currently
		
		Field54A field54A = new Field54A();
		
		return field54A;
		
	}

	@Override
	public Field getField55a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
	
		//TREATS does not generate this field currently
		
		Field55A field55A = new Field55A();
		
		return field55A;
		
	}

	@Override
	public Field getField56a(CashFlow cashFlow, CashFlowSi cashFlowSi) {

		Field56A field56A = new Field56A();
		Field56D field56D = new Field56D();
		
		Field field56a;
		
		String intermediaryBankSwiftBic = cashFlowSi.getIntermediaryBankSwiftBicCode();
		String intermediaryBankNameLine1 = cashFlowSi.getIntermediaryBankNameLine1();
		String intermediaryBankNameLine2 = cashFlowSi.getIntermediaryBankNameLine2();
		
		String intermediaryBankClearingBankCode = cashFlowSi.getIntermediaryBankClearingBankCode();
		String intermediaryBankClearingSortCode = cashFlowSi.getIntermediaryBankClearingSortCode();
		
		FundTransferSystem fts = cashFlowSi.getFts();
		
		if(FtsCode.CHIPS.equals(fts.getFtsCode()) && StringUtils.isNotBlank(intermediaryBankClearingBankCode)) {
			
			//TODO To confirm with users if CHIPS Participant Number should be included in Option A as it is redundant according to Swift Standard
			
			field56A.setAccount(SwiftMessageLiterals.SLASH.concat(SwiftMessageLiterals.CHIPS_PARTICIPANT).concat(intermediaryBankClearingBankCode));
			
		} else if(StringUtils.isNotBlank(intermediaryBankClearingSortCode)) {
			
			NationalClearingSystemCode ftsSystemCode = fts.getNationalClearingSystemCode();
			
			Mt103Field56ACodes field56ACode = Mt103Field56ACodes.getMt103Field56ACodes(ftsSystemCode.name());
			
			if(field56ACode != null) {
				
				//Real Time Gross Settlement
				if(field56ACode.equals(Mt202Field56ACodes.RT)) {
					
					field56A.setAccount(SwiftMessageLiterals.SLASH.concat(field56ACode.getCode()));
					
				} else {
				
					field56A.setAccount(SwiftMessageLiterals.SLASH.concat(field56ACode.getCode()).
											concat(intermediaryBankClearingSortCode));
				
				}
				
			} else {
				
				
				if(FtsCode.CHIPS.equals(fts.getFtsCode())) { 
					
					//TODO To confirm with users if CHIPS UID should be included in Option A as it should be used with Option D 
					//according to Swift Standard
					
					field56A.setAccount(SwiftMessageLiterals.SLASH.concat(SwiftMessageLiterals.CHIPS_UID).concat(intermediaryBankClearingSortCode));
					
				} else {
					
					field56A.setAccount(intermediaryBankClearingSortCode);
					
				}
				
			}
			
		} else {
			
			//Do nothing
			
		}
		
		if(StringUtils.isNotBlank(intermediaryBankSwiftBic)) {
			
			/* Format 56A Party Identifier */
			
			field56A.setBIC(intermediaryBankSwiftBic);
			
			field56a = field56A;
			
		} else {
			
			/* Format 56D Name and Address */
			
			if(StringUtils.isNotBlank(intermediaryBankNameLine1)) {
				
				field56D.setNameAndAddressLine1(intermediaryBankNameLine1);
				
			}
			
			if(StringUtils.isNotBlank(intermediaryBankNameLine2)) {
				
				field56D.setNameAndAddressLine2(intermediaryBankNameLine2);
				
			}

			field56a = field56D;

		}
		
		return field56a;
		
	}

	@Override
	public Field getField57a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field57A field57A = new Field57A();
		Field57D field57D = new Field57D();
		
		Field field57a;
		
		String beneficiaryBankSettlementSwiftBic = cashFlowSi.getSettlementBankSwiftBicCode();
		String beneficiaryBankNameLine1 = cashFlowSi.getSettlementBankNameLine1();
		String beneficiaryBankNameLine2 = cashFlowSi.getSettlementBankNameLine2();
		
		String beneficiaryBankClearingBankCode = cashFlowSi.getSettlementBankClearingBankCode();
		String beneficiaryBankClearingSortCode = cashFlowSi.getSettlementBankClearingSortCode();
		String beneficiaryBankAccountOnIntermediary = cashFlowSi.getSettlementBankAccountOnIntermediary();
		String intermediaryBankClearingBankCode = cashFlowSi.getIntermediaryBankClearingBankCode();
		FundTransferSystem fts = cashFlowSi.getFts();
		
		/* Format Party Account Information */
		
		if(FtsCode.CHIPS.equals(fts.getFtsCode()) && StringUtils.isNotBlank(beneficiaryBankClearingBankCode) &&
				StringUtils.isBlank(intermediaryBankClearingBankCode)) {
			
			//TODO To confirm with users if CHIPS Participant Number should be included in Option A as it is redundant according to Swift Standard
			
			field57A.setAccount(SwiftMessageLiterals.SLASH.concat(SwiftMessageLiterals.CHIPS_PARTICIPANT).concat(beneficiaryBankClearingBankCode));
			field57D.setAccount(SwiftMessageLiterals.SLASH.concat(SwiftMessageLiterals.CHIPS_PARTICIPANT).concat(beneficiaryBankClearingBankCode));
			
		} else if(StringUtils.isNotBlank(beneficiaryBankClearingSortCode)) {
			
			NationalClearingSystemCode ftsSystemCode = fts.getNationalClearingSystemCode();
			
			Mt202Field57ACodes field57ACode = Mt202Field57ACodes.getMt202Field57ACodes(ftsSystemCode.name());
			Mt202Field57DCodes field57DCode = Mt202Field57DCodes.getMt202Field57DCodes(ftsSystemCode.name());
			
			if(field57ACode != null) {
				
				//Real Time Gross Settlement
				if(field57ACode.equals(Mt202Field57ACodes.RT)) {
					
					field57A.setAccount(SwiftMessageLiterals.SLASH.concat(field57ACode.getCode()));
					
				} else {
				
					field57A.setAccount(SwiftMessageLiterals.SLASH.concat(field57ACode.getCode()).
											concat(beneficiaryBankClearingSortCode));
				
				}
				
			} else {
				
				if(FtsCode.CHIPS.equals(fts.getFtsCode())) { 
					
					//TODO To confirm with users if CHIPS UID should be included in Option A as it should be used with Option D 
					//according to Swift Standard
					
					field57A.setAccount(SwiftMessageLiterals.SLASH.concat(SwiftMessageLiterals.CHIPS_UID).concat(beneficiaryBankClearingSortCode));
					
				} else {
					
					field57A.setAccount(beneficiaryBankClearingSortCode);
					
				}
				
			}
			
			if(field57DCode != null) {
				
				if(field57DCode.equals(Mt202Field57DCodes.RT)) {
					
					field57D.setAccount(SwiftMessageLiterals.SLASH.concat(field57DCode.getCode()));
					
				} else {
				
					field57D.setAccount(SwiftMessageLiterals.SLASH.concat(field57DCode.getCode()).
											concat(beneficiaryBankClearingSortCode));
				
				}
				
			} else {
					
					field57D.setAccount(beneficiaryBankClearingSortCode);
				
			}
			
		} else if(StringUtils.isNotBlank(beneficiaryBankAccountOnIntermediary)) {
			
			field57A.setAccount(beneficiaryBankAccountOnIntermediary);
			field57D.setAccount(beneficiaryBankAccountOnIntermediary);
			
		} else {
			
			//Do nothing
			
		}
		
		/* Format 57A Party Identifier */
		
		if(StringUtils.isNotBlank(beneficiaryBankSettlementSwiftBic)) {
			
			field57A.setBIC(beneficiaryBankSettlementSwiftBic);
			
			field57a = field57A;
			
		} else {
			
			/* Format 57D Name and Address */
			
			if(StringUtils.isNotBlank(beneficiaryBankNameLine1)) {
				field57D.setNameAndAddressLine1(beneficiaryBankNameLine1);
			}
			
			if(StringUtils.isNotBlank(beneficiaryBankNameLine2)) {
				field57D.setNameAndAddressLine2(beneficiaryBankNameLine2);
			}

			field57a = field57D;

		}
		
		return field57a;
		
	}

	@Override
	public Field getField59a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field59 field59 = new Field59();
		Field59F field59F = new Field59F();
		
		Field field59a;
		
		//boolean EURegulationFlag = cashFlow.getOrganization().isEURegulationFlag();
		boolean globalStandardCompliantFlag = cashFlowSi.isGlobalStandardCompliantFlag();
		
		/* trade info */
		Trade trade = cashFlow.getTrade();
		
		/* counterparty info */
		Counterparty counterparty = trade.getCounterparty();
		ERDSLegalEntity legalEntity = counterparty.getErdsLegalEntity();
		
		/* cash flow si */
		String cptyClearingSortCode = cashFlowSi.getCounterpartyClearingSortCode();
		String cptySettlementAccount = cashFlowSi.getCounterpartyAccountOnSettlementBank();
		String ibanNumber = cashFlowSi.getIbanNumber();
		FundTransferSystem fts = cashFlowSi.getFts();
		
		String cptyNameAndAddressLine1 = counterparty.getNameAndAddressLine1();
		String cptyNameAndAddressLine2 = counterparty.getNameAndAddressLine2();
		String cptyNameAndAddressLine3 = counterparty.getNameAndAddressLine3();
		String cptyNameAndAddressLine4 = counterparty.getNameAndAddressLine4();
		String countryDescription = SwiftMessageLiterals.retrieveCountryDescription(
											counterparty.getCountryOfResidence());
		
		/* Format Party Account Information */
		
		if(StringUtils.isNotBlank(cptyClearingSortCode)) {
			
			NationalClearingSystemCode ftsSystemCode = fts.getNationalClearingSystemCode();
			
			Mt103Field59Codes field59Code = Mt103Field59Codes.getMt103Field59Codes(ftsSystemCode.name());
			Mt103Field59FCodes field59FCode = Mt103Field59FCodes.getMt103Field59FCodes(ftsSystemCode.name());
			
			if(field59Code != null) {
				
				field59.setAccount(SwiftMessageLiterals.SLASH.concat(field59Code.getCode()).
											concat(cptyClearingSortCode));
				
			} else {
				
				ArrayList<FtsCode> excludedFtsCodeList = new ArrayList<FtsCode>();
				
				excludedFtsCodeList.add(FtsCode.CHAPS);
				excludedFtsCodeList.add(FtsCode.RTGS);
				excludedFtsCodeList.add(FtsCode.RTGSP);
				excludedFtsCodeList.add(FtsCode.BSB);
				excludedFtsCodeList.add(FtsCode.FED);
				excludedFtsCodeList.add(FtsCode.BOJ);
				excludedFtsCodeList.add(FtsCode.CASH);
				excludedFtsCodeList.add(FtsCode.DDA);
				excludedFtsCodeList.add(FtsCode.EURO);
				excludedFtsCodeList.add(FtsCode.INT);
				excludedFtsCodeList.add(FtsCode.NET);
				excludedFtsCodeList.add(FtsCode.RT);
				excludedFtsCodeList.add(FtsCode.SUC);
				
				if(excludedFtsCodeList.contains(fts.getFtsCode())) { 
				
					//TODO To confirm with users if the national clearing code of these fts should be included 
					//as these should not be used according to Swift Standard
					
					field59.setAccount(SwiftMessageLiterals.SLASH.concat(ftsSystemCode.name()).concat(cptyClearingSortCode));
					
				} else {
					
					field59.setAccount(cptyClearingSortCode);
					
				}
				
			}
			
			if(field59FCode != null) {
				
				field59F.setAccount(SwiftMessageLiterals.SLASH.concat(field59FCode.getCode()).
						concat(cptyClearingSortCode));
				
			} else {
				
				ArrayList<FtsCode> excludedFtsCodeList = new ArrayList<FtsCode>();
				
				excludedFtsCodeList.add(FtsCode.CHAPS);
				excludedFtsCodeList.add(FtsCode.RTGS);
				excludedFtsCodeList.add(FtsCode.RTGSP);
				excludedFtsCodeList.add(FtsCode.BSB);
				excludedFtsCodeList.add(FtsCode.FED);
				excludedFtsCodeList.add(FtsCode.BOJ);
				excludedFtsCodeList.add(FtsCode.CASH);
				excludedFtsCodeList.add(FtsCode.DDA);
				excludedFtsCodeList.add(FtsCode.EURO);
				excludedFtsCodeList.add(FtsCode.INT);
				excludedFtsCodeList.add(FtsCode.NET);
				excludedFtsCodeList.add(FtsCode.RT);
				excludedFtsCodeList.add(FtsCode.SUC);
				
				if(excludedFtsCodeList.contains(fts.getFtsCode())) { 
				
					//TODO To confirm with users if the national clearing code of these fts should be included 
					//as these should not be used according to Swift Standard
					
					field59F.setAccount(SwiftMessageLiterals.SLASH.concat(ftsSystemCode.name()).concat(cptyClearingSortCode));
					
				} else {
					
					field59F.setAccount(cptyClearingSortCode);
					
				}
				
			}
				
		} else if(StringUtils.isNotBlank(ibanNumber)) { 
			
			field59.setAccount(ibanNumber);
			field59F.setAccount(ibanNumber);
			
		} else if(StringUtils.isNotBlank(cptySettlementAccount)) {
			
			field59.setAccount(cptySettlementAccount);
			field59F.setAccount(cptySettlementAccount);
			
		} else {
			
			//Do Nothing
			
		}
		
			
		if(globalStandardCompliantFlag && legalEntity != null && legalEntity.isSwiftCompliantFlag()) {
			
			//generate Field 59F
			
			String legalEntityNameLine1 = legalEntity.getLegalEntityNameLine1();
			String legalEntityNameLine2 = legalEntity.getLegalEntityNameLine2();
			String legalEntityNameLine3 = legalEntity.getLegalEntityNameLine3();
			
			String legalEntityAddressLine1 = legalEntity.getLegalEntityAddressLine1();
			String legalEntityAddressLine2 = legalEntity.getLegalEntityAddressLine2();
			
			String countryStateCity = StringUtils.replaceOnce(legalEntity.getCountryStateCity(), "3/", StringUtils.EMPTY);
			
			//field59F.setAccount(SwiftMessageLiterals.SLASH.concat(legalEntity.getGridId().toString()));
			
			int componentSize = field59F.componentsSize();
			int componentNumber = 2;
			
			if(StringUtils.isNotBlank(legalEntityNameLine1)) {
				field59F.setComponent(componentNumber, "1");
				componentNumber++;
				field59F.setComponent(componentNumber, legalEntityNameLine1);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(legalEntityNameLine2)) {
				field59F.setComponent(componentNumber, "1");
				componentNumber++;
				field59F.setComponent(componentNumber, legalEntityNameLine2);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(legalEntityNameLine3)) {
				field59F.setComponent(componentNumber, "1");
				componentNumber++;
				field59F.setComponent(componentNumber, legalEntityNameLine3);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(legalEntityAddressLine1) && componentNumber < componentSize-2) {
				field59F.setComponent(componentNumber, "2");
				componentNumber++;
				field59F.setComponent(componentNumber, legalEntityAddressLine1);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(legalEntityAddressLine2) && componentNumber < componentSize-2) {
				field59F.setComponent(componentNumber, "2");
				componentNumber++;
				field59F.setComponent(componentNumber, legalEntityAddressLine2);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(countryStateCity)) {
				field59F.setComponent(componentNumber, "3");
				componentNumber++;
				field59F.setComponent(componentNumber, countryStateCity);
				componentNumber++;
			}
			
			field59a = field59F;
			
		} else {
			
			//generate Field 59
			
			int componentSize = field59.componentsSize();
			int componentNumber = 2;
			
			if(StringUtils.isNotBlank(cptyNameAndAddressLine1)) {
				field59.setComponent(componentNumber, cptyNameAndAddressLine1);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(cptyNameAndAddressLine2)) {
				field59.setComponent(componentNumber, cptyNameAndAddressLine2);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(cptyNameAndAddressLine3)) {
				field59.setComponent(componentNumber, cptyNameAndAddressLine3);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(cptyNameAndAddressLine4) && componentNumber < componentSize) {
				field59.setComponent(componentNumber, cptyNameAndAddressLine4);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(countryDescription)) {
				field59.setComponent(componentNumber, countryDescription);
			}
			
			field59a = field59;
			
		}
		
		return field59a;
		
	}

	@Override
	public Field70 getField70(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field70 field70 = new Field70();
		
		Trade trade = cashFlow.getTrade();
		
		String paymentDetailsLine1 = cashFlowSi.getPaymentDetailsLine1();
		String paymentDetailsLine2 = cashFlowSi.getPaymentDetailsLine2();
		boolean fxSwapIndicator;
		String BOTPurposeCode;
		boolean nettedSettlementIndicator = cashFlow.isNettedSettlementIndicator();
		Currency cashFlowCurrency = cashFlow.getCurrency();
		
		String field70Content;
		
		if(trade instanceof TradeFxSpot) {
			
			fxSwapIndicator = ((TradeFxSpot)trade).isFxSwapIndicator();
			
		} else if(trade instanceof TradeFxForward) {
			
			fxSwapIndicator = ((TradeFxForward)trade).isFxSwapIndicator();
			
		} else {
			
			fxSwapIndicator = false;
			
		}
		
		/* format the 1st line */
		
		if(StringUtils.isNotBlank(paymentDetailsLine1)) {
			
			field70.setNarrativeLine1(paymentDetailsLine1);
			
		} else {
			
			//Do nothing
			
		}
		
		/* format the 2nd line */
		
		if(StringUtils.isNotBlank(paymentDetailsLine2)) {
			
			field70.setNarrativeLine2(paymentDetailsLine2);
			
		} else {
			
			//Do nothing
			
		}
		
		/* format the 3rd & 4th line */
		
		if(StringUtils.equals(cashFlowCurrency.getCurrencyCode(), "THB")) {
	
			if(fxSwapIndicator) {
				
				BOTPurposeCode = SwiftMessageLiterals.BOT_FXSWAP_PURPOSE_CODE;
				
				field70Content = SwiftMessageLiterals.BOT_PURPOSE_CODE_PREFIX.concat(SwiftMessageLiterals.SPACE).
										concat(BOTPurposeCode);
				
				field70.setNarrativeLine3(field70Content);
	
			} else if(nettedSettlementIndicator) {
				
				BOTPurposeCode = SwiftMessageLiterals.BOT_OTHER_PURPOSE_CODE;
				
				field70Content = SwiftMessageLiterals.BOT_PURPOSE_CODE_PREFIX.concat(SwiftMessageLiterals.SPACE).
										concat(BOTPurposeCode);
				
				field70.setNarrativeLine3(field70Content);
				
				field70.setNarrativeLine4("NET PAYMENT OF FX OR MM DEALS");
			
			} else {
				
				String productDescription = trade.getProduct().getProductDescription();
				
				BOTPurposeCode = SwiftMessageLiterals.BOT_PURPOSE_CODE_MAP.get(trade.getProduct().getProductCategory());
				
				if(BOTPurposeCode != null) {
					
					field70Content = SwiftMessageLiterals.BOT_PURPOSE_CODE_PREFIX.concat(SwiftMessageLiterals.SPACE).
											concat(BOTPurposeCode);
					
					field70.setNarrativeLine3(field70Content);
					
				} else {
				
					BOTPurposeCode = SwiftMessageLiterals.BOT_OTHER_PURPOSE_CODE;
					
					field70Content = SwiftMessageLiterals.BOT_PURPOSE_CODE_PREFIX.concat(SwiftMessageLiterals.SPACE).
											concat(BOTPurposeCode);
				
					field70.setNarrativeLine3(field70Content);
				
				}
				
				field70.setNarrativeLine4(productDescription);
				
			}
		
		}
		
		return field70;
		
	}

	@Override
	public Field71A getField71A(CashFlowSi cashFlowSi) {
		
		Field71A field71A = new Field71A();
		
		ChargesForIndicator chargesForIndicator = cashFlowSi.getChargesForIndicator();
		Mt103Field71ACodes field71ACode = Mt103Field71ACodes.getMt103Field71ACodes(chargesForIndicator.name());
	
		if(field71ACode != null) {
			
			field71A.setCode(field71ACode.getCode());
			
		} else {
			
			//Do nothing
			
		}
		
		return field71A;
		
	}

	@Override
	public Field71F getField71F(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field71F field71F = new Field71F();
	
		Number senderCharge = new BigDecimal(0);
		
		if(StringUtils.equals(getField71A(cashFlowSi).getValue(), 
				SwiftMessageLiterals.Mt103Field71ACodes.BEN.getCode())) {
			
			//All transaction charges are to be borne by the beneficiary customer
			
			field71F.setCurrency(cashFlow.getCurrency().getSettlementCurrencyCode());
			field71F.setAmount(senderCharge);
			
		} else {
			
			//Do nothing
			
		}
		
		return field71F;
	}

	@Override
	public Field71G getField71G(CashFlow cashFlow, CashFlowSi cashFlowSi) {
	
		//TREATS does not generate this field currently
		
		Field71G field71G = new Field71G();
		
		return field71G;
		
	}

	@Override
	public Field72 getField72(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field72 field72 = new Field72();
		
		String senderToReceiverNarrativeLine1 = cashFlowSi.getSenderToReceiverNarrativeLine1();
		String senderToReceiverNarrativeLine2 = cashFlowSi.getSenderToReceiverNarrativeLine2();
		String senderToReceiverNarrativeLine3 = cashFlowSi.getSenderToReceiverNarrativeLine3();
		String senderToReceiverNarrativeLine4 = cashFlowSi.getSenderToReceiverNarrativeLine4();
		String senderToReceiverNarrativeLine5 = cashFlowSi.getSenderToReceiverNarrativeLine5();
		String senderToReceiverNarrativeLine6 = cashFlowSi.getSenderToReceiverNarrativeLine6();
		
		if(StringUtils.isNotBlank(senderToReceiverNarrativeLine1)) {
			
			field72.setNarrativeLine1(senderToReceiverNarrativeLine1);
			
		}
		
		if(StringUtils.isNotBlank(senderToReceiverNarrativeLine2)) {
			
			field72.setNarrativeLine2(senderToReceiverNarrativeLine2);
			
		}
		
		if(StringUtils.isNotBlank(senderToReceiverNarrativeLine3)) {
			
			field72.setNarrativeLine3(senderToReceiverNarrativeLine3);
			
		}
		
		if(StringUtils.isNotBlank(senderToReceiverNarrativeLine4)) {
			
			field72.setNarrativeLine4(senderToReceiverNarrativeLine4);
			
		}
		
		
		if(StringUtils.isNotBlank(senderToReceiverNarrativeLine5)) {
			
			field72.setNarrativeLine5(senderToReceiverNarrativeLine5);
			
		}
		
		if(StringUtils.isNotBlank(senderToReceiverNarrativeLine6)) {
			
			field72.setNarrativeLine6(senderToReceiverNarrativeLine6);
			
		}
		
		return field72;
		
	}

	@Override
	public Field77B getField77B(CashFlow cashFlow, CashFlowSi cashFlowSi) {

		//TREATS does not generate this field currently
		
		Field77B field77B = new Field77B();
		
		return field77B;
		
	}

	@Override
	public Field77T getField77T(CashFlow cashFlow, CashFlowSi cashFlowSi) {

		//TREATS does not generate this field currently
		
		Field77T field77T = new Field77T();
		
		return field77T;
		
	}

}
