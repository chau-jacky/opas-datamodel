package confirmation.swift;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field21;
import com.prowidesoftware.swift.model.field.Field22A;
import com.prowidesoftware.swift.model.field.Field29A;
import com.prowidesoftware.swift.model.field.Field30T;
import com.prowidesoftware.swift.model.field.Field77H;
import com.prowidesoftware.swift.model.field.Field82A;
import com.prowidesoftware.swift.model.field.Field82D;
import com.prowidesoftware.swift.model.field.Field83J;
import com.prowidesoftware.swift.model.field.Field87A;
import com.prowidesoftware.swift.model.field.Field87D;
import com.prowidesoftware.swift.model.field.Field87J;
import com.prowidesoftware.swift.model.field.Field94A;
import com.prowidesoftware.swift.model.field.Field98D;

import confirmation.swift.SwiftMessageLiterals.Field22ACodes;
import confirmation.swift.SwiftMessageLiterals.Field29ACodes;
import confirmation.swift.SwiftMessageLiterals.Field77HCodes;
import confirmation.swift.SwiftMessageLiterals.Field83aCodes;
import confirmation.swift.SwiftMessageLiterals.Field87aCodes;
import confirmation.swift.SwiftMessageLiterals.Field94ACodes;
import model.organization.BusinessClass;
import model.organization.Department;
import model.trade.ScopeOfOperation;
import model.trade.Trade;
import model.trade.TradeActivity;

/** 
 * This class provides the basic implementation of the MT300 message generator given
 * the general trade details.
 * 
 * @author simonchau
 *
 */
public abstract class AbstractTradeMt300SwiftMessageGenerator extends AbstractMt300SwiftMessageGenerator {

	SimpleDateFormat dateFormatter;

	public AbstractTradeMt300SwiftMessageGenerator() {
		super();
		dateFormatter = new SimpleDateFormat("yyyyMMdd");
	}

	/**
	 * Format and return Sequence A Field 20 based on the given trade.
	 *
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 20 
	 */
	@Override
	public Field20 getSequenceAField20(Trade trade) {
		
		Field20 field20 = new Field20();
		
		if (StringUtils.isNotBlank(trade.getTransactionReference())) {
			
			field20.setReference(trade.getTransactionReference());
			
		}
		
		return field20;
		
	}

	/**
	 * Format and return Sequence A Field 21 based on the given trade.
	 *
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 21
	 */
	@Override
	public Field21 getSequenceAField21(Trade trade) {
		
		Field21 field21 = new Field21();
		
		TradeActivity tradeActivity = trade.getTradeEventJournal().getTradeActivity();
		
		String originalTransactionReference = StringUtils.EMPTY;
		
		switch (tradeActivity) {
		
			case NEW:
				field21.setReference(SwiftMessageLiterals.NEW);
				break;
			case AMEND:
				originalTransactionReference = trade.getOriginalTrade().getTransactionReference();
				if (StringUtils.isNotBlank(originalTransactionReference)) {
					field21.setReference(originalTransactionReference);
				}
				break;
			case CANCEL:
				originalTransactionReference = trade.getOriginalTrade().getTransactionReference();
				if (StringUtils.isNotBlank(originalTransactionReference)) {
					field21.setReference(originalTransactionReference);
				}
				break;
			default:
				// Do nothing
			
		}
		
		return field21;
		
	}

	/**
	 * Format and return Sequence A Field 22A based on the given trade.
	 *
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 22A
	 */
	@Override
	public Field22A getSequenceAField22A(Trade trade) {

		Field22A field22A = new Field22A();

		TradeActivity tradeActivity = trade.getTradeEventJournal().getTradeActivity();
		
		switch (tradeActivity) {
		
			case NEW:
				field22A.setType(Field22ACodes.NEWT.name());
				break;
			case AMEND:
				field22A.setType(Field22ACodes.AMND.name());
				break;
			case CANCEL:
				field22A.setType(Field22ACodes.CANC.name());
				break;
			case DUMMY_SAVE:
				field22A.setType(Field22ACodes.DUPL.name());
				break;
			case OPTION_EXERCISE:
				field22A.setType(Field22ACodes.EXOP.name());
				break;
			default:
				// Do nothing
			
		}
		
		return field22A;
		
	}

	/**
	 * Format and return Sequence A Field 94A based on the given trade.
	 *
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 94A
	 */
	@Override
	public Field94A getSequenceAField94A(Trade trade) {
		
		Field94A field94A = new Field94A();
		
		//ScopeOfOperation scopeOfOperation = trade.getScopeOfOperation();
		ScopeOfOperation scopeOfOperation = trade.getCounterparty().getScopeOfOperation();
		
		switch (scopeOfOperation) {
		
			case AGENT:
				field94A.setScope(Field94ACodes.AGNT.name());
				break;
			case BILATERAL:
				field94A.setScope(Field94ACodes.BILA.name());
				break;
			case BROKER:
				field94A.setScope(Field94ACodes.BROK.name());
				break;
			default:
				// Do nothing
			
		}
		
		return field94A;
		
	}

	/**
	 * Format and return Sequence A Field 82a based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 82a (Option A, D or J)
	 */
	@Override
	public Field getSequenceAField82a(Trade trade) {
		
		Field field82a;
		
		String ourSwiftBicCode = trade.getOrganization().getBicCode();
		
		if (StringUtils.isNotBlank(ourSwiftBicCode)) {
			
			/* Our Swift BIC Code present */
			
			Field82A field82A = new Field82A();
			
			field82A.setBIC(ourSwiftBicCode);
			field82a = field82A;
			
		} else {
			
			/* Our Swift BIC Code absent */
			
			Field82D field82D = new Field82D();
			
			String ourNameAndAddressLine1 = trade.getOrganization().getOrganizationNameAndAddressLine1();
			String ourNameAndAddressLine2 = trade.getOrganization().getOrganizationNameAndAddressLine2();
			String ourNameAndAddressLine3 = trade.getOrganization().getOrganizationNameAndAddressLine3();
			String ourNameAndAddressLine4 = trade.getOrganization().getOrganizationNameAndAddressLine4();
			
			if (StringUtils.isNotBlank(ourNameAndAddressLine1)) {
				field82D.setNameAndAddressLine1(ourNameAndAddressLine1);
			}
			
			if (StringUtils.isNotBlank(ourNameAndAddressLine2)) {
				field82D.setNameAndAddressLine2(ourNameAndAddressLine2);
			}
			
			if (StringUtils.isNotBlank(ourNameAndAddressLine3)) {
				field82D.setNameAndAddressLine3(ourNameAndAddressLine3);
			}
			
			if (StringUtils.isNotBlank(ourNameAndAddressLine4)) {
				field82D.setNameAndAddressLine4(ourNameAndAddressLine4);
			}
			
			field82a = field82D;
			
		}
		
		return field82a;
		
	}

	/**
	 * Format and return Sequence A Field 87a based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 87a (Option A, D or J)
	 */
	@Override
	public Field getSequenceAField87a(Trade trade) {
		
		Field field87a;
		
		String cmsIdentifier = trade.getCounterparty().getCmsIdentifier();
		
		if (StringUtils.isNotBlank(cmsIdentifier)) {
		
			/* CMS Identifier present, generate 87J */
			Field87J field87J = new Field87J();
			
			String field87JContent = SwiftMessageLiterals.SLASH;
			field87JContent = field87JContent.concat(Field87aCodes.ABIC.name());
			field87JContent = field87JContent.concat(SwiftMessageLiterals.SLASH);
			field87JContent = field87JContent.concat(Field87aCodes.UKWN.name());
			field87JContent = field87JContent.concat(SwiftMessageLiterals.SLASH);
			field87JContent = field87JContent.concat(Field87aCodes.NAME.name());
			field87JContent = field87JContent.concat(SwiftMessageLiterals.SLASH);
			field87JContent = field87JContent.concat(cmsIdentifier);
			
			field87J.setPartyIdentification(field87JContent);
			
			field87a = field87J;
			
		} else {
			
			/* CMS Identifier absent */
			
			String counterpartySwiftBicCode = trade.getCounterparty().getBicCode();
			
			if (StringUtils.isNotBlank(counterpartySwiftBicCode)) {
				
				/* Generate 87A if Counterparty Swift BIC Code exists */
				Field87A field87A = new Field87A();
				
				field87A.setBIC(counterpartySwiftBicCode);
				
				field87a = field87A;
				
			} else {
				
				/* Generate 87D if Counterparty Swift BIC Code exists */
				
				Field87D field87D = new Field87D();
				
				String counterpartyNameAndAddressLine1 = 
						trade.getCounterparty().getOrganization().getOrganizationNameAndAddressLine1();
				String counterpartyNameAndAddressLine2 = 
						trade.getCounterparty().getOrganization().getOrganizationNameAndAddressLine2();
				String counterpartyNameAndAddressLine3 = 
						trade.getCounterparty().getOrganization().getOrganizationNameAndAddressLine3();
				String counterpartyNameAndAddressLine4 = 
						trade.getCounterparty().getOrganization().getOrganizationNameAndAddressLine4();
				
				if (StringUtils.isNotBlank(counterpartyNameAndAddressLine1)) {
					field87D.setNameAndAddressLine1(counterpartyNameAndAddressLine1);
				}
				if (StringUtils.isNotBlank(counterpartyNameAndAddressLine2)) {
					field87D.setNameAndAddressLine2(counterpartyNameAndAddressLine2);
				}
				if (StringUtils.isNotBlank(counterpartyNameAndAddressLine3)) {
					field87D.setNameAndAddressLine3(counterpartyNameAndAddressLine3);
				}
				if (StringUtils.isNotBlank(counterpartyNameAndAddressLine4)) {
					field87D.setNameAndAddressLine4(counterpartyNameAndAddressLine4);
				}
				
				field87a = field87D;
				
			}
			
		}
		
		return field87a;
		
	}

	
	/**
	 * Format and return Sequence A Field 83a based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 83a (Option A, D or J)
	 */
	@Override
	public Field getSequenceAField83a(Trade trade) {
		
		Field field83a;
		
		Field83J field83J = new Field83J();
		
		field83a = field83J;
		
		String cmsIdentifier = trade.getCounterparty().getCmsIdentifier();
		
		if (StringUtils.isNotBlank(cmsIdentifier)) {
			
			String field83JContent = SwiftMessageLiterals.SLASH;
			field83JContent = field83JContent.concat(Field83aCodes.NAME.name());
			field83JContent = field83JContent.concat(SwiftMessageLiterals.SLASH);
			field83JContent = field83JContent.concat(trade.getCounterparty().getEntityOfFund());
			
			field83J.setPartyIdentification(field83JContent);
			
		} else {
			
			// Do nothing
			
		}
		
		return field83a;
		
	}
	
	/**
	 * Format and return Sequence A Field 77H based on the given trade.
	 *
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 77H
	 */
	@Override
	public Field77H getSequenceAField77H(Trade trade) {
		
		Field77H field77H = new Field77H();
		
		boolean offshoreDeliverableRmbFxTxn = SwiftMessageLiterals.checkOffshoreDeliverableRmbFxTxn(trade);
		
		if (offshoreDeliverableRmbFxTxn) {
			
			field77H.setTypeOfAgreement(Field77HCodes.ISDACN.name());
			
		}
		
		return field77H;
		
	}

	/**
	 * Format and return Sequence B Field 30T based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 30T
	 */
	@Override
	public Field30T getSequenceBField30T(Trade trade) {
		
		Field30T field30T = new Field30T();
		
		field30T.setDate(dateFormatter.format(trade.getCaptureDateTime()));
		
		return field30T;
		
	}

	/**
	 * Format and return Sequence C Field 29A based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 29A
	 */
	@Override
	public Field29A getSequenceCField29A(Trade trade) {
		
		Field29A field29A = new Field29A();

		List<Department> departmentList = trade.getOrganization().getDepartmentList();
		
		for (Iterator<Department> iterator = departmentList.iterator(); iterator.hasNext();) {
			
			Department department = iterator.next();
			
			if (StringUtils.equals(department.getBusinessClass().name(), BusinessClass.FXMM_METALS.name())) {
				
				int lineCounter = 1;
				String field29AContent = StringUtils.EMPTY;
				
				if (StringUtils.isNotBlank(department.getDepartmentName())) {
					field29AContent = SwiftMessageLiterals.SLASH;
					field29AContent = field29AContent.concat(Field29ACodes.DEPT.name());
					field29AContent = field29AContent.concat(SwiftMessageLiterals.SLASH);
					field29AContent = field29AContent.concat(department.getDepartmentName());
					field29A.setComponent(lineCounter, field29AContent);
					++lineCounter;
				}
				
				if (StringUtils.isNotBlank(department.getContactPerson())) {
					field29AContent = SwiftMessageLiterals.SLASH;
					field29AContent = field29AContent.concat(Field29ACodes.NAME.name());
					field29AContent = field29AContent.concat(SwiftMessageLiterals.SLASH);
					field29AContent = field29AContent.concat(department.getContactPerson());
					field29A.setComponent(lineCounter, field29AContent);
					++lineCounter;
				}
				
				if (StringUtils.isNotBlank(department.getPhoneNumber())) {
					field29AContent = SwiftMessageLiterals.SLASH;
					field29AContent = field29AContent.concat(Field29ACodes.PHON.name());
					field29AContent = field29AContent.concat(SwiftMessageLiterals.SLASH);
					field29AContent = field29AContent.concat(department.getPhoneNumber());
					field29A.setComponent(lineCounter, field29AContent);
					++lineCounter;
				}
				
				if (StringUtils.isNotBlank(department.getFaxNumber())) {
					field29AContent = SwiftMessageLiterals.SLASH;
					field29AContent = field29AContent.concat(Field29ACodes.FAXT.name());
					field29AContent = field29AContent.concat(SwiftMessageLiterals.SLASH);
					field29AContent = field29AContent.concat(department.getFaxNumber());
					field29A.setComponent(lineCounter, field29AContent);
					++lineCounter;
				}
				
				if (StringUtils.isNotBlank(department.getTelexNumber()) && lineCounter <=4) {
					field29AContent = SwiftMessageLiterals.SLASH;
					field29AContent = field29AContent.concat(Field29ACodes.TELX.name());
					field29AContent = field29AContent.concat(SwiftMessageLiterals.SLASH);
					field29AContent = field29AContent.concat(department.getTelexNumber());
					field29A.setComponent(lineCounter, field29AContent);
					++lineCounter;
				}
				
			}
		}
		
		return field29A;
		
	}

	/**
	 * Format and return Sequence E Field 98D based on the given trade.
	 * 
	 * @param trade FX trade that requires generation of MT300
	 * @return Formatted Field 98D
	 */
	@Override
	public Field98D getSequenceEField98D(Trade trade) {
		
		Field98D field98D = new Field98D();
		
		Calendar executionDateTime = trade.getDoddFrankReportingInfo().getExecutionDateTime();
		
		field98D.setDate(executionDateTime);
		field98D.setTime(executionDateTime);
		
		ZonedDateTime zonedExecutionDateTime = ZonedDateTime.ofInstant(
				executionDateTime.toInstant(), executionDateTime.getTimeZone().toZoneId());
		ZoneOffset offset = zonedExecutionDateTime.getOffset();
		
		String offsetSign = StringUtils.substring(offset.getId(), 0, 1);
		String offsetTime = StringUtils.substring(offset.getId(), 1, 6);
		
		switch (offsetSign) {
			case "Z":
				field98D.setUTCIndicator(SwiftMessageLiterals.ZERO_TIMEZONE_OFFSET);
				break;
			case "+":
				field98D.setUTCIndicator(offsetTime.replace(SwiftMessageLiterals.COLON_STRING, StringUtils.EMPTY));
				break;
			case "-":
				field98D.setSign(SwiftMessageLiterals.TIMEZONE_OFFSET_MINUS_SIGN);
				field98D.setUTCIndicator(offsetTime.replace(SwiftMessageLiterals.COLON_STRING, StringUtils.EMPTY));
				break;
			default:
				// Do nothing
		}
		
		return field98D;
		
	}

}
