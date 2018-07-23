package settlement.swift;

import java.text.SimpleDateFormat;

import org.apache.commons.lang.StringUtils;

import com.prowidesoftware.swift.model.SwiftBlock1;
import com.prowidesoftware.swift.model.SwiftBlock2;
import com.prowidesoftware.swift.model.SwiftBlock2Input;
import com.prowidesoftware.swift.model.SwiftBlock3;
import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field108;
import com.prowidesoftware.swift.model.field.Field13C;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field21;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field52A;
import com.prowidesoftware.swift.model.field.Field53B;
import com.prowidesoftware.swift.model.field.Field54A;
import com.prowidesoftware.swift.model.field.Field56A;
import com.prowidesoftware.swift.model.field.Field56D;
import com.prowidesoftware.swift.model.field.Field57A;
import com.prowidesoftware.swift.model.field.Field57D;
import com.prowidesoftware.swift.model.field.Field58A;
import com.prowidesoftware.swift.model.field.Field58D;
import com.prowidesoftware.swift.model.field.Field72;

import model.cashflow.CashFlow;
import model.cashflow.CashFlowSi;
import model.party.Counterparty;
import model.trade.FtsCode;
import model.trade.FundTransferSystem;
import model.trade.NationalClearingSystemCode;
import model.trade.PaymentIndicator;
import settlement.swift.SwiftMessageLiterals.Mt202Field56ACodes;
import settlement.swift.SwiftMessageLiterals.Mt202Field56DCodes;
import settlement.swift.SwiftMessageLiterals.Mt202Field57ACodes;
import settlement.swift.SwiftMessageLiterals.Mt202Field57DCodes;
import settlement.swift.SwiftMessageLiterals.Mt202Field58ACodes;
import settlement.swift.SwiftMessageLiterals.Mt202Field58DCodes;

public class Mt202SwiftMessageGenerator extends AbstractMt202SwiftMessageGenerator {
	
	SimpleDateFormat dateFormatter;

	public Mt202SwiftMessageGenerator() {
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
		
		block2.setMessageType("202");
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
	public Field21 getField21(CashFlow cashFlow) {

		Field21 field21 = new Field21();
		
		field21.setReference(cashFlow.getCashFlowReference());
		
		return field21;
		
	}
	
	@Override
	public Field13C getField13C(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		/* TREATS does not generate this field currently */
		/* Used by CLS & Target2 payments */
		
		Field13C field13C = new Field13C();
		
		return field13C;
		
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
	public Field getField52a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		/* TREATS does not generate this field currently */
		
		Field52A field52A = new Field52A();
		
		return field52A;
		
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
				
				// Do nothing, our nostro account is missing in other bank's book
				
			}
			
		} else {
			
			// Do nothing, missing Pay settlement instruction
			
		}
		
		return field53a;
		
	}

	@Override
	public Field getField54a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field54A field54A = new Field54A();
		
		Field field54a = field54A;
		
		return field54a;
		
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
			field56D.setAccount(SwiftMessageLiterals.SLASH.concat(SwiftMessageLiterals.CHIPS_PARTICIPANT).concat(intermediaryBankClearingBankCode));
			
		} else if(StringUtils.isNotBlank(intermediaryBankClearingSortCode)) {
			
			NationalClearingSystemCode ftsSystemCode = fts.getNationalClearingSystemCode();
			
			Mt202Field56ACodes field56ACode = Mt202Field56ACodes.getMt202Field56ACodes(ftsSystemCode.name());
			Mt202Field56DCodes field56DCode = Mt202Field56DCodes.getMt202Field56DCodes(ftsSystemCode.name());
			
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
			
			if(field56DCode != null) {
				
				if(field56DCode.equals(Mt202Field56DCodes.RT)) {
					
					field56D.setAccount(SwiftMessageLiterals.SLASH.concat(field56DCode.getCode()));
					
				} else {
				
					field56D.setAccount(SwiftMessageLiterals.SLASH.concat(field56DCode.getCode()).
											concat(intermediaryBankClearingSortCode));
				
				}
				
			} else {
				
				field56D.setAccount(intermediaryBankClearingSortCode);
				
			}
			
		} else {
			
			//Do nothing
			
		}
		
		/* Format 56A Party Identifier */
		
		if(StringUtils.isNotBlank(intermediaryBankSwiftBic)) {
			
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
	public Field getField58a(CashFlow cashFlow, CashFlowSi cashFlowSi) {
		
		Field58A field58A = new Field58A();
		Field58D field58D = new Field58D();
		
		Field field58a;
		
		Counterparty counterparty = cashFlow.getTrade().getCounterparty();
		String cptyNameAndAddressLine1 = counterparty.getNameAndAddressLine1();
		String cptyNameAndAddressLine2 = counterparty.getNameAndAddressLine2();
		String cptyNameAndAddressLine3 = counterparty.getNameAndAddressLine3();
		String cptyNameAndAddressLine4 = counterparty.getNameAndAddressLine4();
		String countryDescription = SwiftMessageLiterals.retrieveCountryDescription(
											cashFlow.getOrganization().getCountryCode());
		
		String cptyClearingSortCode = cashFlowSi.getCounterpartyClearingSortCode();
		String cptySettlementAccount = cashFlowSi.getCounterpartyAccountOnSettlementBank();
		String ibanNumber = cashFlowSi.getIbanNumber();
		FundTransferSystem fts = cashFlowSi.getFts();

		String cptySwiftBicCode = counterparty.getOrganization().getBicCode();
		
		/* Format Party Account Information */
		
		if(StringUtils.isNotBlank(ibanNumber)) {
			
			field58A.setAccount(ibanNumber);
			field58D.setAccount(ibanNumber);
			
		} else if(StringUtils.isNotBlank(cptyClearingSortCode)) {
			
			NationalClearingSystemCode ftsSystemCode = fts.getNationalClearingSystemCode();
			
			Mt202Field58ACodes field58ACode = Mt202Field58ACodes.getMt202Field58ACodes(ftsSystemCode.name());
			Mt202Field58DCodes field58DCode = Mt202Field58DCodes.getMt202Field58DCodes(ftsSystemCode.name());
			
			if(field58ACode != null) {
				
				//Real Time Gross Settlement
				if(field58ACode.equals(Mt202Field58ACodes.RT)) {
					
					field58A.setAccount(SwiftMessageLiterals.SLASH.concat(field58ACode.getCode()));
					
				} else {
				
					field58A.setAccount(SwiftMessageLiterals.SLASH.concat(field58ACode.getCode()).
											concat(cptyClearingSortCode));
				
				}
				
			} else {
				
				if(FtsCode.CHIPS.equals(fts.getFtsCode())) { 
				
					//TODO To confirm with users if CHIPS UID should be included in Option A as it should be used with Option D 
					//according to Swift Standard
					
					field58A.setAccount(SwiftMessageLiterals.SLASH.concat(SwiftMessageLiterals.CHIPS_UID).concat(cptyClearingSortCode));
					
				} else {
					
					field58A.setAccount(cptyClearingSortCode);
					
				}
				
			}
			
			if(field58DCode != null) {
				
				if(field58DCode.equals(Mt202Field58DCodes.RT)) {
					
					field58D.setAccount(SwiftMessageLiterals.SLASH.concat(field58DCode.getCode()));
					
				} else {
				
					field58D.setAccount(SwiftMessageLiterals.SLASH.concat(field58DCode.getCode()).
											concat(cptyClearingSortCode));
				
				}
				
			} else {
				
				field58D.setAccount(cptyClearingSortCode);
				
			}
				
		} else if(StringUtils.isNotBlank(cptySettlementAccount)) { 
			
			field58A.setAccount(cptySettlementAccount);
			field58D.setAccount(cptySettlementAccount);
			
		} else {
			
			//Do Nothing
			
		}
		
		/* Format 58A Party Identifier */
		
		if(StringUtils.isNotBlank(cptySwiftBicCode)) {
			
			field58A.setBIC(cptySwiftBicCode);
			
			field58a = field58A;
			
		} else {
			
			/* Format 58D Name and Address */
			
			int componentSize = field58D.componentsSize();
			int componentNumber = 3;
			
			if(StringUtils.isNotBlank(cptyNameAndAddressLine1)) {
				field58D.setComponent(componentNumber, cptyNameAndAddressLine1);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(cptyNameAndAddressLine2)) {
				field58D.setComponent(componentNumber, cptyNameAndAddressLine2);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(cptyNameAndAddressLine3)) {
				field58D.setComponent(componentNumber, cptyNameAndAddressLine3);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(cptyNameAndAddressLine4) && componentNumber < componentSize) {
				field58D.setComponent(componentNumber, cptyNameAndAddressLine4);
				componentNumber++;
			}
			
			if(StringUtils.isNotBlank(countryDescription)) {
				field58D.setComponent(componentNumber, countryDescription);
			}
			
			field58a = field58D;

		}
		
		return field58a;
		
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

}
