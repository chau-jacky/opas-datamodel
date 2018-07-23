package confirmation;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field21;
import com.prowidesoftware.swift.model.field.Field22A;
import com.prowidesoftware.swift.model.field.Field29A;
import com.prowidesoftware.swift.model.field.Field30T;
import com.prowidesoftware.swift.model.field.Field30V;
import com.prowidesoftware.swift.model.field.Field53A;
import com.prowidesoftware.swift.model.field.Field53D;
import com.prowidesoftware.swift.model.field.Field56A;
import com.prowidesoftware.swift.model.field.Field56D;
import com.prowidesoftware.swift.model.field.Field56J;
import com.prowidesoftware.swift.model.field.Field57A;
import com.prowidesoftware.swift.model.field.Field57D;
import com.prowidesoftware.swift.model.field.Field57J;
import com.prowidesoftware.swift.model.field.Field77H;
import com.prowidesoftware.swift.model.field.Field82A;
import com.prowidesoftware.swift.model.field.Field82D;
import com.prowidesoftware.swift.model.field.Field83J;
import com.prowidesoftware.swift.model.field.Field87A;
import com.prowidesoftware.swift.model.field.Field87D;
import com.prowidesoftware.swift.model.field.Field87J;
import com.prowidesoftware.swift.model.field.Field94A;

import confirmation.swift.TradeFxSpotMt300SwiftMessageGenerator;

import model.book.Book;
import model.cashflow.CashFlow;
import model.cashflow.CashFlowSi;
import model.organization.BusinessClass;
import model.organization.Department;
import model.organization.Organization;
import model.party.Counterparty;
import model.trade.Currency;
import model.trade.FundTransferSystem;
import model.trade.PaymentIndicator;
import model.trade.ScopeOfOperation;
import model.trade.TradeActivity;
import model.trade.TradeEventJournal;
import model.trade.TradeFxSpot;

public class TestTradeMt300SwiftMessageGenerator {
	
	/* FX Spot Trade */
	private static TradeFxSpot tradeFxSpot;
	
	/* Original Trade */
	private static TradeFxSpot originalTradeFxSpot;
	
	/* Trade Event Journal */
	private static TradeEventJournal tradeEventJournal;
	
	/* Organization */
	private static Organization organization;
	
	/* Counterparty */
	private static Counterparty counterparty;
	
	/* Counterparty Organization */
	private static Organization cptyOrganization;
	
	/* FX Spot MT300 message generator */
	public static TradeFxSpotMt300SwiftMessageGenerator spotMt300Generator;
	
	/* expected result */
	private String expectedFieldContent;
	
	/* constants */
	private static final String EMPTY_STRING = "";
	private static final String STRING_ONE_SPACE = " ";
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
		// Do nothing
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		
		// Do nothing 
		
	}

	@Before
	public void setUp() throws Exception {
		
		tradeFxSpot = new TradeFxSpot();
		spotMt300Generator = new TradeFxSpotMt300SwiftMessageGenerator();
		tradeEventJournal = new TradeEventJournal();
		originalTradeFxSpot = new TradeFxSpot();
		organization = new Organization();
		counterparty = new Counterparty();
		cptyOrganization = new Organization();
		
	}
	
	@After 
	public void tearDown() throws Exception {
		
		tradeFxSpot = null;
		spotMt300Generator = null;
		tradeEventJournal = null;
		originalTradeFxSpot = null;
		organization = null;
		counterparty = null;
		cptyOrganization = null;
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 20
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Transaction Reference Present
	 * Expected Result: Formatted Field 20 Returned
	 * 
	 */
	@Test
	public void getSequenceAField20_txnRefPresent_returnField20() {
		
		/* test value of trade transaction reference */
		String transactionReference = "HKHSPT81179866";
		
		/* expected result */
		expectedFieldContent = transactionReference;
		
		tradeFxSpot.setTransactionReference(transactionReference);
		
		Field20 field20 = spotMt300Generator.getSequenceAField20(tradeFxSpot);
		
		assertEquals(field20.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 20
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Transaction Reference Absent
	 * Expected Result: Empty Field 20 Returned
	 * 
	 */
	@Test
	public void getSequenceAField20_txnRefAbsent_emptyField20() {

		/* sub test case 1 - transaction reference is empty */
		tradeFxSpot.setTransactionReference(EMPTY_STRING);
		
		Field20 field20 = spotMt300Generator.getSequenceAField20(tradeFxSpot);
		
		assertTrue(field20.isEmpty());
		
		/* sub test case 2 - transaction reference contains only space */
		tradeFxSpot.setTransactionReference(STRING_ONE_SPACE);
		
		field20 = spotMt300Generator.getSequenceAField20(tradeFxSpot);
		
		assertTrue(field20.isEmpty());
		
		/* sub test case 3 - transaction reference is null */
		tradeFxSpot.setTransactionReference(null);
		
		field20 = spotMt300Generator.getSequenceAField20(tradeFxSpot);
		
		assertTrue(field20.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade Activity NEW
	 * Expected Result: Formatted Field 21 Returned
	 * 
	 */
	@Test
	public void getSequenceAField21_activityNew_returnField21() {
		
		/* test value of trade activity */
		String tradeAcivity = "NEW";
		
		/* expected result */
		expectedFieldContent = tradeAcivity;
		
		tradeEventJournal.setTradeActivity(TradeActivity.NEW);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		
		Field21 field21 = spotMt300Generator.getSequenceAField21(tradeFxSpot);
		
		assertEquals(field21.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade Activity AMEND
	 * Expected Result: Formatted Field 21 Returned
	 * 
	 */
	@Test
	public void getSequenceAField21_activityAmend_returnField21() {
		
		/* test value of original transaction reference */
		String originalTransactionReference = "HKHSPT81179865";
		
		/* expected result */
		expectedFieldContent = originalTransactionReference;
		
		tradeEventJournal.setTradeActivity(TradeActivity.AMEND);
		
		originalTradeFxSpot.setTransactionReference(originalTransactionReference);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		tradeFxSpot.setOriginalTrade(originalTradeFxSpot);
		
		Field21 field21 = spotMt300Generator.getSequenceAField21(tradeFxSpot);
		
		assertEquals(field21.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade Activity CANCEL
	 * Expected Result: Formatted Field 21 Returned
	 * 
	 */
	@Test
	public void getSequenceAField21_activityCancel_returnField21() {
		
		/* test value of original transaction reference */
		String transactionReference = "HKHSPT81179866";
		
		/* expected result */
		expectedFieldContent = transactionReference;
		
		tradeEventJournal.setTradeActivity(TradeActivity.CANCEL);
		
		originalTradeFxSpot.setTransactionReference(transactionReference);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		tradeFxSpot.setOriginalTrade(originalTradeFxSpot);
		
		Field21 field21 = spotMt300Generator.getSequenceAField21(tradeFxSpot);
		
		assertEquals(field21.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Activity DUMMY SAVE
	 * Expected Result: Empty Field 21 Returned
	 * 
	 */
	@Test
	public void getSequenceAField21_activityDummySave_emptyField21() {

		tradeEventJournal.setTradeActivity(TradeActivity.DUMMY_SAVE);

		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		
		Field21 field21 = spotMt300Generator.getSequenceAField21(tradeFxSpot);
		
		assertTrue(field21.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Event Journal Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField21_tradeEventJournalAbsent_throwException() {
		
		tradeFxSpot.setTradeEventJournal(null);
		
		spotMt300Generator.getSequenceAField21(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Activity Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField21_tradeActivityAbsent_throwException() {
		
		tradeEventJournal.setTradeActivity(null);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		
		spotMt300Generator.getSequenceAField21(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade Activity NEW
	 * Expected Result: Formatted Field 22A Returned
	 * 
	 */
	@Test
	public void getSequenceAField22A_activityNew_returnField22A() {
		
		/* expected result */
		expectedFieldContent = "NEWT";
		
		tradeEventJournal.setTradeActivity(TradeActivity.NEW);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		
		Field22A field22A = spotMt300Generator.getSequenceAField22A(tradeFxSpot);
		
		assertEquals(field22A.getValue(), expectedFieldContent);

	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade Activity AMEND
	 * Expected Result: Formatted Field 22A Returned
	 * 
	 */
	@Test
	public void getSequenceAField22A_activityAmend_returnField22A() {
		
		/* expected result */
		expectedFieldContent = "AMND";
		
		tradeEventJournal.setTradeActivity(TradeActivity.AMEND);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		
		Field22A field22A = spotMt300Generator.getSequenceAField22A(tradeFxSpot);
		
		assertEquals(field22A.getValue(), expectedFieldContent);

	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade Activity CANCEL
	 * Expected Result: Formatted Field 22A Returned
	 * 
	 */
	@Test
	public void getSequenceAField22A_activityCancel_returnField22A() {
		
		/* expected result */
		expectedFieldContent = "CANC";
		
		tradeEventJournal.setTradeActivity(TradeActivity.CANCEL);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		
		Field22A field22A = spotMt300Generator.getSequenceAField22A(tradeFxSpot);
		
		assertEquals(field22A.getValue(), expectedFieldContent);

	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade Activity DUMMY SAVE
	 * Expected Result: Formatted Field 22A Returned
	 * 
	 */
	@Test
	public void getSequenceAField22A_activityDummySave_returnField22A() {
		
		/* expected result */
		expectedFieldContent = "DUPL";
		
		tradeEventJournal.setTradeActivity(TradeActivity.DUMMY_SAVE);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		
		Field22A field22A = spotMt300Generator.getSequenceAField22A(tradeFxSpot);
		
		assertEquals(field22A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade Activity OPTION EXERCISE
	 * Expected Result: Formatted Field 22A Returned
	 * 
	 */
	@Test
	public void getSequenceAField22A_activityOptionExercise_returnField22A() {
		
		/* expected result */
		expectedFieldContent = "EXOP";
		
		tradeEventJournal.setTradeActivity(TradeActivity.OPTION_EXERCISE);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		
		Field22A field22A = spotMt300Generator.getSequenceAField22A(tradeFxSpot);
		
		assertEquals(field22A.getValue(), expectedFieldContent);

	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22A
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Event Journal is Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField22A_tradeEventJournalAbsent_throwException() {

		tradeFxSpot.setTradeEventJournal(null);
		
		spotMt300Generator.getSequenceAField22A(tradeFxSpot);

	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22A
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Activity Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField22A_tradeActivityAbsent_throwException() {

		tradeEventJournal.setTradeActivity(null);
		
		tradeFxSpot.setTradeEventJournal(tradeEventJournal);
		
		spotMt300Generator.getSequenceAField22A(tradeFxSpot);

	}
	
	/**
	 * Test Case for MT300 Sequence A Field 94A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Scope of Operation AGENT
	 * Expected Result: Formatted Field 94A Returned
	 * 
	 */
	@Test
	public void getSequenceAField94A_scopeAgent_returnField94A() {
		
		/* expected result */
		expectedFieldContent = "AGNT";
		
		counterparty.setScopeOfOperation(ScopeOfOperation.AGENT);
		tradeFxSpot.setCounterparty(counterparty);
		//tradeFxSpot.setScopeOfOperation(ScopeOfOperation.AGENT);
		
		Field94A field94A = spotMt300Generator.getSequenceAField94A(tradeFxSpot);
		
		assertEquals(field94A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 94A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Scope of Operation BILATERAL
	 * Expected Result: Formatted Field 94A Returned
	 * 
	 */
	@Test
	public void getSequenceAField94A_scopeBilateral_returnField94A() {
		
		/* expected result */
		expectedFieldContent = "BILA";
		
		counterparty.setScopeOfOperation(ScopeOfOperation.BILATERAL);
		tradeFxSpot.setCounterparty(counterparty);
		//tradeFxSpot.setScopeOfOperation(ScopeOfOperation.BILATERAL);
		
		Field94A field94A = spotMt300Generator.getSequenceAField94A(tradeFxSpot);
		
		assertEquals(field94A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 94A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Scope of Operation BROKER
	 * Expected Result: Formatted Field 94A Returned
	 * 
	 */
	@Test
	public void getSequenceAField94A_scopeBroker_returnField94A() {
		
		/* expected result */
		expectedFieldContent = "BROK";
		
		counterparty.setScopeOfOperation(ScopeOfOperation.BROKER);
		tradeFxSpot.setCounterparty(counterparty);
		//tradeFxSpot.setScopeOfOperation(ScopeOfOperation.BROKER);
		
		Field94A field94A = spotMt300Generator.getSequenceAField94A(tradeFxSpot);
		
		assertEquals(field94A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 94A
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Scope of Operation is Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField94A_scopeAbsent_throwException() {
	
		counterparty.setScopeOfOperation(null);
		tradeFxSpot.setCounterparty(counterparty);
		//tradeFxSpot.setScopeOfOperation(null);
		
		spotMt300Generator.getSequenceAField94A(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 82a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Our Swift Bic Present
	 * Expected Result: Formatted Field 82A Returned
	 * 
	 */
	@Test
	public void getSequenceAField82a_ourSwiftBicPresent_returnField82A() {
		
		/* test value of our swift bic */
		String ourSwiftBic = "HXXCHKH0";
		
		/* expected result */
		expectedFieldContent = ourSwiftBic;
		
		organization.setBicCode(ourSwiftBic);
		
		tradeFxSpot.setOrganization(organization);
		
		Field field82a = spotMt300Generator.getSequenceAField82a(tradeFxSpot);
		
		assertTrue(field82a instanceof Field82A);
		assertFalse(field82a instanceof Field82D);
		assertEquals(field82a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 82a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Our Swift Bic Absent & Our Addresses Present
	 * Expected Result: Formatted Field 82D Returned
	 * 
	 */
	@Test
	public void getSequenceAField82a_ourSwiftBicAbsent_returnField82D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		/* expected result */
		expectedFieldContent = addressLine1_leng35.concat("\r\n").
									concat(addressLine2_leng35).concat("\r\n").
									concat(addressLine3_leng35).concat("\r\n").
									concat(addressLine4_leng35);

		organization.setOrganizationNameAndAddressLine1(addressLine1_leng35);
		organization.setOrganizationNameAndAddressLine2(addressLine2_leng35);
		organization.setOrganizationNameAndAddressLine3(addressLine3_leng35);
		organization.setOrganizationNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setOrganization(organization);
		
		/* sub test case 1 - our swift bic code is empty */
		organization.setBicCode(EMPTY_STRING);
		
		Field field82a = spotMt300Generator.getSequenceAField82a(tradeFxSpot);
		
		assertTrue(field82a instanceof Field82D);
		assertFalse(field82a instanceof Field82A);
		assertEquals(field82a.getValue(), expectedFieldContent);
		
		/* sub test case 2 - our swift bic code contains only space */
		organization.setBicCode(STRING_ONE_SPACE);
		
		field82a = spotMt300Generator.getSequenceAField82a(tradeFxSpot);
		
		assertTrue(field82a instanceof Field82D);
		assertFalse(field82a instanceof Field82A);
		assertEquals(field82a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - our swift bic code is null */
		organization.setBicCode(null);
		
		field82a = spotMt300Generator.getSequenceAField82a(tradeFxSpot);
		
		assertTrue(field82a instanceof Field82D);
		assertFalse(field82a instanceof Field82A);
		assertEquals(field82a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 82a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Our Swift Bic Absent & Our Addresses Absent
	 * Expected Result: Empty Field 82D Returned
	 * 
	 */
	
	@Test
	public void getSequenceAField82a_ourSwiftBicAbsentOurAddressesAbsent_emptyField82D() {
		
		organization.setBicCode(EMPTY_STRING);
		
		tradeFxSpot.setOrganization(organization);
		
		/* sub test case 1 - address lines are empty */
		organization.setOrganizationNameAndAddressLine1(EMPTY_STRING);
		organization.setOrganizationNameAndAddressLine2(EMPTY_STRING);
		organization.setOrganizationNameAndAddressLine3(EMPTY_STRING);
		organization.setOrganizationNameAndAddressLine4(EMPTY_STRING);
		
		Field field82a = spotMt300Generator.getSequenceAField82a(tradeFxSpot);
		
		assertTrue(field82a instanceof Field82D);
		assertFalse(field82a instanceof Field82A);
		assertTrue(field82a.isEmpty());
		
		/* sub test case 2 - address lines contain only space */
		organization.setOrganizationNameAndAddressLine1(STRING_ONE_SPACE);
		organization.setOrganizationNameAndAddressLine2(STRING_ONE_SPACE);
		organization.setOrganizationNameAndAddressLine3(STRING_ONE_SPACE);
		organization.setOrganizationNameAndAddressLine4(STRING_ONE_SPACE);
		
		field82a = spotMt300Generator.getSequenceAField82a(tradeFxSpot);
		
		assertTrue(field82a instanceof Field82D);
		assertFalse(field82a instanceof Field82A);
		assertTrue(field82a.isEmpty());
		
		/* sub test case 3 - address lines are null */
		organization.setOrganizationNameAndAddressLine1(null);
		organization.setOrganizationNameAndAddressLine2(null);
		organization.setOrganizationNameAndAddressLine3(null);
		organization.setOrganizationNameAndAddressLine4(null);
		
		field82a = spotMt300Generator.getSequenceAField82a(tradeFxSpot);
		
		assertTrue(field82a instanceof Field82D);
		assertFalse(field82a instanceof Field82A);
		assertTrue(field82a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 82a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Organization is Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField82a_organizationAbsent_throwException() {
		
		tradeFxSpot.setOrganization(null);
		
		spotMt300Generator.getSequenceAField82a(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 87a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Counterparty CMS ID Present
	 * Expected Result: Formatted Field 87J Returned
	 * 
	 */
	@Test
	public void getSequenceAField87a_cmsIdPresent_returnField87J() {
		
		/* test value of counterparty cms id */
		String cptyCmsId = "HXXXHKH";
		
		/* expected result */
		expectedFieldContent = "/ABIC/UKWN/NAME/".concat(cptyCmsId);
		
		counterparty.setCmsIdentifier(cptyCmsId);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		Field field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87J);
		assertFalse(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87D);
		
		assertEquals(field87a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 87a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Counterparty CMS ID Absent & Counterparty Swift Bic Present
	 * Expected Result: Formatted Field 87A Returned
	 * 
	 */
	@Test
	public void getSequenceAField87a_cmsIdAbsentCptySwiftBicPresent_returnField87A() {
		
		/* test values of counterparty swift bic code */
		String counterpartySwiftBic = "AXXAGB22";
		
		/* expected result */
		expectedFieldContent = counterpartySwiftBic;
		
		counterparty.setBicCode(counterpartySwiftBic);
		
		/* sub test case 1 - counterparty cms id is empty */
		counterparty.setCmsIdentifier(EMPTY_STRING);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		Field field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87D);
		assertFalse(field87a instanceof Field87J);
		
		assertEquals(field87a.getValue(), expectedFieldContent);
		
		/* sub test case 2 - counterparty cms id contains only space */
		counterparty.setCmsIdentifier(STRING_ONE_SPACE);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87D);
		assertFalse(field87a instanceof Field87J);
		
		assertEquals(field87a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - counterparty cms id is null */
		counterparty.setCmsIdentifier(null);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87D);
		assertFalse(field87a instanceof Field87J);
		
		assertEquals(field87a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 87a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Counterparty CMS ID Absent & Counterparty Swift Bic Absent
	 * Expected Result: Formatted Field 87D Returned
	 * 
	 */
	@Test
	public void getSequenceAField87a_cmsIdAbsentCptySwiftBicAbsent_returnField87D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		/* expected result */
		expectedFieldContent = addressLine1_leng35.concat("\r\n").
									concat(addressLine2_leng35).concat("\r\n").
									concat(addressLine3_leng35).concat("\r\n").
									concat(addressLine4_leng35);
		
		cptyOrganization.setOrganizationNameAndAddressLine1(addressLine1_leng35);
		cptyOrganization.setOrganizationNameAndAddressLine2(addressLine2_leng35);
		cptyOrganization.setOrganizationNameAndAddressLine3(addressLine3_leng35);
		cptyOrganization.setOrganizationNameAndAddressLine4(addressLine4_leng35);
		counterparty.setOrganization(cptyOrganization);
		counterparty.setCmsIdentifier(EMPTY_STRING);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		/* sub test case 1 - counterparty swift bic code is empty */
		counterparty.setBicCode(EMPTY_STRING);
		
		Field field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87D);
		assertFalse(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87J);
		
		assertEquals(field87a.getValue(), expectedFieldContent);
		
		/* sub test case 2 - counterparty swift bic code contains only space */
		counterparty.setBicCode(STRING_ONE_SPACE);
		
		field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87D);
		assertFalse(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87J);
		
		assertEquals(field87a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - counterparty swift bic code is null */
		counterparty.setBicCode(null);
		
		field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87D);
		assertFalse(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87J);
		
		assertEquals(field87a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 87a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Counterparty CMS ID Absent & Counterparty Swift Bic Absent & Counterparty Address Absent
	 * Expected Result: Empty Field 87D Returned
	 * 
	 */
	@Test
	public void getSequenceAField87a_cmsIdAbsentCptySwiftBicAbsentCptyAddrAbsent_emptyField87D() {
		
		counterparty.setCmsIdentifier(EMPTY_STRING);
		counterparty.setBicCode(EMPTY_STRING);
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		/* sub test case - counterparty addresses are empty */
		cptyOrganization.setOrganizationNameAndAddressLine1(EMPTY_STRING);
		cptyOrganization.setOrganizationNameAndAddressLine2(EMPTY_STRING);
		cptyOrganization.setOrganizationNameAndAddressLine3(EMPTY_STRING);
		cptyOrganization.setOrganizationNameAndAddressLine4(EMPTY_STRING);
		
		Field field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87D);
		assertFalse(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87J);
		assertTrue(field87a.isEmpty());
		
		/* sub test case - counterparty addresses contain only space */
		cptyOrganization.setOrganizationNameAndAddressLine1(STRING_ONE_SPACE);
		cptyOrganization.setOrganizationNameAndAddressLine2(STRING_ONE_SPACE);
		cptyOrganization.setOrganizationNameAndAddressLine3(STRING_ONE_SPACE);
		cptyOrganization.setOrganizationNameAndAddressLine4(STRING_ONE_SPACE);
		
		field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87D);
		assertFalse(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87J);
		assertTrue(field87a.isEmpty());
		
		/* sub test case - counterparty addresses are null */
		cptyOrganization.setOrganizationNameAndAddressLine1(null);
		cptyOrganization.setOrganizationNameAndAddressLine2(null);
		cptyOrganization.setOrganizationNameAndAddressLine3(null);
		cptyOrganization.setOrganizationNameAndAddressLine4(null);
		
		field87a = spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
		assertTrue(field87a instanceof Field87D);
		assertFalse(field87a instanceof Field87A);
		assertFalse(field87a instanceof Field87J);
		assertTrue(field87a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 87a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Counterparty CMS ID Absent & Counterparty Swift Bic Absent & Counterparty Organization Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField87a_cmsIdAbsentCptySwiftBicAbsentOrganizationAbsent_throwException() {
		
		counterparty.setCmsIdentifier(EMPTY_STRING);
		counterparty.setBicCode(EMPTY_STRING);
		counterparty.setOrganization(null);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 87a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Counterparty is Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField87a_counterpartyAbsent_throwException() {
		
		tradeFxSpot.setCounterparty(null);
		
		spotMt300Generator.getSequenceAField87a(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 83a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Counterparty CMS ID Present
	 * Expected Result: Formatted Field 83J Returned
	 * 
	 */
	@Test
	public void getSequenceAField83a_cmsIdPresent_return83J() {
		
		/* test value of entity of fund */
		String entityOfFund = "TEST VALUE - ENTITY OF FUND";
		
		/* test value of Counterparty cms id */
		String cptyCmsId = "HXXXHKH";
		
		/* expected result */
		expectedFieldContent = "/NAME/".concat(entityOfFund);
		
		counterparty.setCmsIdentifier(cptyCmsId);
		counterparty.setEntityOfFund(entityOfFund);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		Field field83a = spotMt300Generator.getSequenceAField83a(tradeFxSpot);
		
		assertTrue(field83a instanceof Field83J);
		assertEquals(field83a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 83a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Counterparty CMS ID Absent
	 * Expected Result: Empty Field 83J Returned
	 * 
	 */
	@Test
	public void getSequenceAField83a_cmsIdAbsent_empty83J() {
		
		/* test value of entity of fund */
		String entityOfFund = "TEST VALUE - ENTITY OF FUND";
		
		counterparty.setEntityOfFund(entityOfFund);
		tradeFxSpot.setCounterparty(counterparty);
		
		/* sub test case 1 - counterparty cms id is empty */
		counterparty.setCmsIdentifier(EMPTY_STRING);
		
		Field field83a = spotMt300Generator.getSequenceAField83a(tradeFxSpot);
		
		assertTrue(field83a instanceof Field83J);
		assertTrue(field83a.isEmpty());
		
		/* sub test case 2 - counterparty cms id contains only space */
		counterparty.setCmsIdentifier(STRING_ONE_SPACE);
		
		field83a = spotMt300Generator.getSequenceAField83a(tradeFxSpot);
		
		assertTrue(field83a instanceof Field83J);
		assertTrue(field83a.isEmpty());
		
		/* sub test case 3 - counterparty cms id is null */
		counterparty.setCmsIdentifier(null);
		
		field83a = spotMt300Generator.getSequenceAField83a(tradeFxSpot);
		
		assertTrue(field83a instanceof Field83J);
		assertTrue(field83a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Bought Currency is CNH
	 * Expected Result: Formatted Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_buyCNH_returnField77H() {
		
		/* expected result */
		expectedFieldContent = "ISDACN";
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("CNH");
		soldCurrency.setCurrencyCode("USD");
		
		/* pay & receive si */
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertEquals(field77H.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Sold Currency is CNH
	 * Expected Result: Formatted Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_sellCNH_returnField77H() {
		
		/* expected result */
		expectedFieldContent = "ISDACN";
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("USD");
		soldCurrency.setCurrencyCode("CNH");
		
		/* pay & receive si */
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertEquals(field77H.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Bought Currency is CNY & Trade Book is for Offshore RMB
	 * Expected Result: Formatted Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_buyCNYOffshoreRMBBook_returnField77H() {
		
		/* expected result */
		expectedFieldContent = "ISDACN";
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("CNY");
		soldCurrency.setCurrencyCode("USD");
		
		/* trading book */
		Book book = new Book();
		book.setOffshoreRmbIndicator(true);
		
		/* pay & receive si */
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setMasterBook(book);
		//tradeFxSpot.setBook(book);
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertEquals(field77H.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Sold Currency is CNY & Trade Book is for Offshore RMB
	 * Expected Result: Formatted Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_sellCNYOffshoreRMBBook_returnField77H() {
		
		/* expected result */
		expectedFieldContent = "ISDACN";
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("USD");
		soldCurrency.setCurrencyCode("CNY");

		/* trading book */
		Book book = new Book();
		book.setOffshoreRmbIndicator(true);
		
		/* pay & receive si */
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setMasterBook(book);
		//tradeFxSpot.setBook(book);
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertEquals(field77H.getValue(), expectedFieldContent);
		
	}

	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Bought Currency is CNY & Settlement Location is outside China
	 * Expected Result: Formatted Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_buyCNYSettleOutsideCN_returnField77H() {
		
		/* expected result */
		expectedFieldContent = "ISDACN";
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("CNY");
		soldCurrency.setCurrencyCode("USD");
		
		/* pay & receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setOurSettlementCountry("HK");
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementCountry("US");
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementCountry("HK");
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementCountry("US");
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		/* test book */
		Book book = new Book();
		book.setOffshoreRmbIndicator(false);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setMasterBook(book);
		//tradeFxSpot.setBook(book);
		//tradeFxSpot.setTradeSsis(tradeSsis);
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertEquals(field77H.getValue(), expectedFieldContent);
		
	}
	

	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Sold Currency is CNY & Settlement Location is outside China
	 * Expected Result: Formatted Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_sellCNYSettleOutsideCN_returnField77H() {
	
		/* expected result */
		expectedFieldContent = "ISDACN";
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
	
		boughtCurrency.setCurrencyCode("USD");
		soldCurrency.setCurrencyCode("CNY");
		
		/* pay & receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setOurSettlementCountry("US");
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementCountry("HK");
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementCountry("US");
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementCountry("HK");
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		/* test book */
		Book book = new Book();
		book.setOffshoreRmbIndicator(false);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setMasterBook(book);
		tradeFxSpot.setCashFlows(cashFlowList);
		//tradeFxSpot.setBook(book);
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertEquals(field77H.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Bought Currency is CNY & Settlement Location is China
	 * Expected Result: Empty Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_buyCNYSettleInCN_emptyField77H() {
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("CNY");
		soldCurrency.setCurrencyCode("USD");
		
		/* pay & receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setOurSettlementCountry("CN");
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementCountry("US");
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementCountry("CN");
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementCountry("US");
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		/* test book */
		Book book = new Book();
		book.setOffshoreRmbIndicator(false);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setMasterBook(book);
		tradeFxSpot.setCashFlows(cashFlowList);
		//tradeFxSpot.setBook(book);
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Sold Currency is CNY & Settlement Location is China
	 * Expected Result: Formatted Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_sellCNYSettleInCN_returnField77H() {

		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("USD");
		soldCurrency.setCurrencyCode("CNY");
		
		/* pay & receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setOurSettlementCountry("US");
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementCountry("CN");
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementCountry("US");
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementCountry("CN");
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		/* test book */
		Book book = new Book();
		book.setOffshoreRmbIndicator(false);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setMasterBook(book);
		tradeFxSpot.setCashFlows(cashFlowList);
		//tradeFxSpot.setBook(book);
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Bought Currency Code is Empty
	 * Expected Result: Empty Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_boughtCurrencyCodeAbsent_emptyField77H() {
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		soldCurrency.setCurrencyCode("USD");
		
		/* pay & receive si */
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementCountry("US");
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementCountry("CN");
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - bought currency code is empty */
		boughtCurrency.setCurrencyCode(EMPTY_STRING);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
		/* sub test case 2 - bought currency code contains only space */
		boughtCurrency.setCurrencyCode(STRING_ONE_SPACE);
		
		field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
		/* sub test case 3 - bought currency code is Null*/
		boughtCurrency.setCurrencyCode(null);
		
		field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Sold Currency Code is Empty
	 * Expected Result: Empty Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_soldCurrencyCodeAbsent_emptyField77H() {
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("USD");
		
		/* pay & receive si */
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementCountry("US");
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementCountry("CN");
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - sold currency code is empty */
		soldCurrency.setCurrencyCode(EMPTY_STRING);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
		/* sub test case 2 - sold currency code contains only space */
		soldCurrency.setCurrencyCode(STRING_ONE_SPACE);
		
		field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
		/* sub test case 3 - sold currency code is null */
		soldCurrency.setCurrencyCode(null);
		
		field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Bought Currency Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField77H_boughtCurrencyAbsent_throwException() {
		
		/* test value of bought currency */
		Currency boughtCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("USD");
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(null);
		
		spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Sold Currency Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField77H_soldCurrencyAbsent_throwException() {
		
		/* test value of sold currency */
		Currency soldCurrency = new Currency();
		
		soldCurrency.setCurrencyCode("USD");
		
		tradeFxSpot.setBoughtCurrency(null);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		
		spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Bought Currency is CNY & Settlement Location is Absent
	 * Expected Result: Empty Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_buyCNYSettleCountryAbsent_emptyField77H() {
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("CNY");
		soldCurrency.setCurrencyCode("USD");
		
		/* pay & receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementCountry("US");
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementCountry("US");
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		/* test book */
		Book book = new Book();
		book.setOffshoreRmbIndicator(false);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setMasterBook(book);
		tradeFxSpot.setCashFlows(cashFlowList);
		//tradeFxSpot.setBook(book);
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		/* sub test case 1 - receive ssi our settlement country is empty */
		
		receiveCashFlowSi.setOurSettlementCountry(EMPTY_STRING);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
		/* sub test case 2 - receive ssi our settlement country contains only space */
		
		receiveCashFlowSi.setOurSettlementCountry(STRING_ONE_SPACE);
		
		field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
		/* sub test case 3 - receive ssi our settlement country is null */
		
		receiveCashFlowSi.setOurSettlementCountry(null);
		
		field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77H
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Sold Currency is CNY & Settlement Location is China
	 * Expected Result: Empty Field 77H Returned
	 * 
	 */
	@Test
	public void getSequenceAField77H_sellCNYSettleCountryAbsent_emptyField77H() {
		
		/* test values of trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode("USD");
		soldCurrency.setCurrencyCode("CNY");
		
		/* pay & receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setOurSettlementCountry("US");
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementCountry("US");
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		/* test book */
		Book book = new Book();
		book.setOffshoreRmbIndicator(false);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setMasterBook(book);
		tradeFxSpot.setCashFlows(cashFlowList);
		//tradeFxSpot.setBook(book);
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		/* sub test case 1 - pay ssi our settlement country is empty */
		
		payCashFlowSi.setOurSettlementCountry(EMPTY_STRING);
		
		Field77H field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
		/* sub test case 2 - pay ssi our settlement country contain only space */
		
		payCashFlowSi.setOurSettlementCountry(STRING_ONE_SPACE);
		
		field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
		/* sub test case 3 - pay ssi our settlement country is null */
		
		payCashFlowSi.setOurSettlementCountry(null);
		
		field77H = spotMt300Generator.getSequenceAField77H(tradeFxSpot);
		
		assertTrue(field77H.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence B Field 30T
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Capture Date Present
	 * Expected Result: Formatted Field 30T Returned
	 * 
	 */
	@Test 
	public void getSequenceBField30T_captureDatePresent_returnField30T() {
		
		/* expected result */
		expectedFieldContent = "20180530";
		
		Calendar captureDateCalendar = new GregorianCalendar(2018, 04, 30);
		
		Date captureDate = captureDateCalendar.getTime();
		
		tradeFxSpot.setCaptureDateTime(captureDate);
		
		Field30T field30T = spotMt300Generator.getSequenceBField30T(tradeFxSpot);
		
		assertEquals(field30T.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B Field 30T
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Capture Date is Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceBField30T_captureDateAbsent_throwException() {
		
		tradeFxSpot.setCaptureDateTime(null);
		
		spotMt300Generator.getSequenceBField30T(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B Field 30V
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Value Date Present
	 * Expected Result: Formatted Field 30V Returned
	 * 
	 */
	@Test 
	public void getSequenceBField30V_valueDatePresent_returnField30V() {
		
		/* expected result */
		expectedFieldContent = "20180604";
		
		Calendar valueDateCalendar = new GregorianCalendar(2018, 05, 04);
		
		Date valueDate = valueDateCalendar.getTime();
		
		tradeFxSpot.setValueDate(valueDate);
		
		Field30V field30V = spotMt300Generator.getSequenceBField30V(tradeFxSpot);
		
		assertEquals(field30V.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B Field 30V
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Value Date is Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceBField30V_valueDateAbsent_throwException() {
		
		tradeFxSpot.setValueDate(null);
		
		spotMt300Generator.getSequenceBField30V(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 53a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: FTS Code CUA & Counterparty Settlement Swift Bic Code Present
	 * Expected Result: Formatted Field 53A Returned
	 * 
	 */
	@Test
	public void getSequenceB1Field53a_ftsCUACptySwiftBicPresent_returnField53A() {
		
		/* test value of counterparty settlement account */
		String counterpartySettlementAccount = "820800099998";
		
		/* test value of counterparty swift bic code */
		String counterpartySwiftBic = "AXXAGB22";
		
		/* expected result */
		expectedFieldContent = "/820800099998".concat("\r\n").
									concat(counterpartySwiftBic);
		
		/* receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setFtsCode(FtsCode.CUA);
		//receiveSsi.setCptySettlementSwiftBicCode(counterpartySwiftBic);
		//receiveSsi.setCptySettlementAccount(counterpartySettlementAccount);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setFts(new FundTransferSystem("CUA"));
		receiveCashFlowSi.setSettlementBankSwiftBicCode(counterpartySwiftBic);
		receiveCashFlowSi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field53a = spotMt300Generator.getSequenceB1Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 53a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: FTS Code CUA & Counterparty Settlement Swift Bic Code Present & Counterparty Settlement Account Absent
	 * Expected Result: Formatted Field 53A Returned
	 * 
	 */
	@Test
	public void getSequenceB1Field53a_ftsCUACptySwiftBicPresentCptyAccountAbsent_returnField53A() {
		
		/* test value of counterparty swift bic code */
		String counterpartySwiftBic = "AXXAGB22";
		
		/* expected result */
		expectedFieldContent = counterpartySwiftBic;
		
		/* receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setFtsCode(FtsCode.CUA);
		//receiveSsi.setCptySettlementSwiftBicCode(counterpartySwiftBic);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setFts(new FundTransferSystem("CUA"));
		receiveCashFlowSi.setSettlementBankSwiftBicCode(counterpartySwiftBic);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - cpty settlement account is empty */
		receiveCashFlowSi.setCounterpartyAccountOnSettlementBank(EMPTY_STRING);
		
		Field field53a = spotMt300Generator.getSequenceB1Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
		/* sub test case 2 - cpty settlement account contains only space */
		receiveCashFlowSi.setCounterpartyAccountOnSettlementBank(STRING_ONE_SPACE);
		
		field53a = spotMt300Generator.getSequenceB1Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - cpty settlement account is null */
		receiveCashFlowSi.setCounterpartyAccountOnSettlementBank(null);
		
		field53a = spotMt300Generator.getSequenceB1Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 53a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Receive SI Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB1Field53a_receiveSiAbsent_emptyField53A() {
		
		/* pay si */
		//TradeSsi paySsi = new TradeSsi();
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		//cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field53a = spotMt300Generator.getSequenceB1Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertTrue(field53a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 53a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: FTS Code Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB1Field53a_ftsAbsent_throwException() {
		
		/* test value of counterparty settlement account */
		String counterpartySettlementAccount = "820800099998";
		
		/* test value of counterparty swift bic code */
		String counterpartySwiftBic = "AXXAGB22";
		
		/* receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setFtsCode(null);
		//receiveSsi.setCptySettlementSwiftBicCode(counterpartySwiftBic);
		//receiveSsi.setCptySettlementAccount(counterpartySettlementAccount);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setFts(null);
		receiveCashFlowSi.setSettlementBankSwiftBicCode(counterpartySwiftBic);
		receiveCashFlowSi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		//cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		spotMt300Generator.getSequenceB1Field53a(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 53a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Counterparty Settlement Swift Bic Code Absent
	 * Expected Result: Empty Field 53A returned
	 * 
	 */
	@Test
	public void getSequenceB1Field53a_cptySettlementSwiftBicAbsent_emptyField53A() {
		
		/* receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setFtsCode(FtsCode.CUA);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setFts(new FundTransferSystem("CUA"));
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		//cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - counterparty settlement swift bic code is empty */
		receiveCashFlowSi.setCounterpartyAccountOnSettlementBank(EMPTY_STRING);
		
		Field field53a = spotMt300Generator.getSequenceB1Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertTrue(field53a.isEmpty());
		
		/* sub test case 2 - counterparty settlement swift bic code contains only space */
		receiveCashFlowSi.setCounterpartyAccountOnSettlementBank(STRING_ONE_SPACE);
		
		field53a = spotMt300Generator.getSequenceB1Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertTrue(field53a.isEmpty());
		
		/* sub test case 3 - counterparty settlement swift bic code is null */
		receiveCashFlowSi.setCounterpartyAccountOnSettlementBank(null);
		
		field53a = spotMt300Generator.getSequenceB1Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertTrue(field53a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 57a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Our Settlement Swift Bic Code Present
	 * Expected Result: Formatted Field 57A returned
	 * 
	 */
	@Test
	public void getSequenceB1Field57a_ourSettlementSwiftBicPresent_returnField57A() {
		
		/* test value of our swift bic code */
		String ourSwiftBic = "HXXCHKH0";
		
		/* expected result */
		expectedFieldContent = ourSwiftBic;
		
		/* receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setOurSettlementSwiftBicCode(ourSwiftBic);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementSwiftBicCode(ourSwiftBic);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		//cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field57a = spotMt300Generator.getSequenceB1Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57J);
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 57a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Our Settlement Swift Bic Code Absent
	 * Expected Result: Formatted Field 57D returned
	 * 
	 */
	@Test
	public void getSequenceB1Field57a_ourSettlementSwiftBicAbsent_returnField57D() {
		
		/* test values of address fields */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		/* expected result */
		expectedFieldContent = addressLine1_leng35.concat("\r\n").
									concat(addressLine2_leng35);
		
		/* receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setOurSettlementNameAndAddressLine1(addressLine1_leng35);
		//receiveSsi.setOurSettlementNameAndAddressLine2(addressLine2_leng35);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementNameAndAddressLine1(addressLine1_leng35);
		receiveCashFlowSi.setOurSettlementNameAndAddressLine2(addressLine2_leng35);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		//cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - our settlement swift bic code is empty */
		payCashFlowSi.setOurSettlementSwiftBicCode(EMPTY_STRING);
		
		Field field57a = spotMt300Generator.getSequenceB1Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertEquals(field57a.getValue(), expectedFieldContent);
		
		/* sub test case 2 - our settlement swift bic code contains only space */
		payCashFlowSi.setOurSettlementSwiftBicCode(STRING_ONE_SPACE);
		
		field57a = spotMt300Generator.getSequenceB1Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertEquals(field57a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - our settlement swift bic code is null */
		payCashFlowSi.setOurSettlementSwiftBicCode(null);
		
		field57a = spotMt300Generator.getSequenceB1Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 57a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Receive SI Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB1Field57a_receiveSiAbsent_emptyField57A() {
		
		/* pay si */
		//TradeSsi paySsi = new TradeSsi();
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		//cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field57a = spotMt300Generator.getSequenceB1Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57J);
		assertTrue(field57a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 57a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Our Settlement Name and Address lines Absent
	 * Expected Result: Empty Field 57D returned
	 * 
	 */
	@Test
	public void getSequenceB1Field57a_ourSettlementNameAndAddressLinesAbsent_emptyField57D() {
		
		/* receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		//receiveSsi.setOurSettlementSwiftBicCode(EMPTY_STRING);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		receiveCashFlowSi.setOurSettlementSwiftBicCode(EMPTY_STRING);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		//cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - our settlement name and address lines are empty */
		receiveCashFlowSi.setOurSettlementNameAndAddressLine1(EMPTY_STRING);
		receiveCashFlowSi.setOurSettlementNameAndAddressLine2(EMPTY_STRING);
		
		Field field57a = spotMt300Generator.getSequenceB1Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertTrue(field57a.isEmpty());
		
		/* sub test case 2 - our settlement name and address lines contain only space */
		receiveCashFlowSi.setOurSettlementNameAndAddressLine1(STRING_ONE_SPACE);
		receiveCashFlowSi.setOurSettlementNameAndAddressLine2(STRING_ONE_SPACE);
		
		field57a = spotMt300Generator.getSequenceB1Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertTrue(field57a.isEmpty());
		
		/* sub test case 3 - our settlement name and address lines are null */
		receiveCashFlowSi.setOurSettlementNameAndAddressLine1(null);
		receiveCashFlowSi.setOurSettlementNameAndAddressLine2(null);
		
		field57a = spotMt300Generator.getSequenceB1Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertTrue(field57a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 53a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Our Settlement Swift Bic Code Present, FTS CHIPS & Our Settlement Clearing Sort Code Present
	 * Expected Result: Formatted Field 53A returned
	 * 
	 */
	@Test
	public void getSequenceB2Field53a_ourSwiftBicPresentFtsChipsOurClearingSortCodePresent_returnField53A() {
		
		/* test value of our swift bic code */
		String ourSwiftBic = "HXXCHKH0";
		
		/* test value our clearing sort code */
		String ourClearingSortCode = "280797";
		
		expectedFieldContent = "//CH".concat(ourClearingSortCode.substring(0, 6)).
									concat("\r\n").concat(ourSwiftBic);
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementSwiftBicCode(ourSwiftBic);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		//paySsi.setFtsCode(FtsCode.CHIPS);
		//paySsi.setOurSettlementClearingSortCode(ourClearingSortCode);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementSwiftBicCode(ourSwiftBic);
		payCashFlowSi.setFts(new FundTransferSystem("CHIPS"));
		payCashFlowSi.setOurSettlementClearingSortCode(ourClearingSortCode);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertFalse(field53a instanceof Field53D);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 53a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Our Settlement Swift Bic Code Present, FTS CHIPS, Our Settlement Clearing Sort Code Absent & Our Account with Them Present
	 * Expected Result: Formatted Field 53A returned
	 * 
	 */
	@Test
	public void getSequenceB2Field53a_ourSwiftBicPresentFtsChipsOurSortCodeAbsentOurAccountWithThemPresent_returnField53A() {
		
		/* test value of our swift bic code */
		String ourSwiftBic = "HXXCHKH0";
		
		/* test value of our account with them in their book */
		String ourAccountWithThemInTheirBook = "000038865";
		
		expectedFieldContent = ourSwiftBic;
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementSwiftBicCode(ourSwiftBic);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		//paySsi.setFtsCode(FtsCode.CHIPS);
		//paySsi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementSwiftBicCode(ourSwiftBic);
		payCashFlowSi.setFts(new FundTransferSystem("CHIPS"));
		payCashFlowSi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - our settlement clearing sort code is empty & our account with them in their book present */
		
		expectedFieldContent = "/".concat(ourAccountWithThemInTheirBook).
									concat("\r\n").concat(ourSwiftBic);
		
		payCashFlowSi.setOurSettlementClearingSortCode(EMPTY_STRING);
		
		Field field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertFalse(field53a instanceof Field53D);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
		/* sub test case 2 - our settlement clearing sort code contains only space & our account with them in their book present */
		
		expectedFieldContent = "/".concat(ourAccountWithThemInTheirBook).
									concat("\r\n").concat(ourSwiftBic);
		
		payCashFlowSi.setOurSettlementClearingSortCode(STRING_ONE_SPACE);
		
		field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertFalse(field53a instanceof Field53D);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - our settlement clearing sort code is null & our account with them in their book present */
		
		expectedFieldContent = "/".concat(ourAccountWithThemInTheirBook).
									concat("\r\n").concat(ourSwiftBic);
		
		payCashFlowSi.setOurSettlementClearingSortCode(null);
		
		field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertFalse(field53a instanceof Field53D);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 53a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Our Settlement Swift Bic Code Present, FTS Not CHIPS & Our Account with Them Present
	 * Expected Result: Formatted Field 53A returned
	 * 
	 */
	@Test
	public void getSequenceB2Field53a_ourSwiftBicPresentFtsNotChipsOurAccountWithThemPresent_returnField53A() {
		
		/* test value of our swift bic code */
		String ourSwiftBic = "HXXCHKH0";
		
		/* test value our clearing sort code */
		String ourClearingSortCode = "280797";
		
		/* test value of our account with them in their book */
		String ourAccountWithThemInTheirBook = "000038865";
		
		expectedFieldContent = "/".concat(ourAccountWithThemInTheirBook).
									concat("\r\n").concat(ourSwiftBic);
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementSwiftBicCode(ourSwiftBic);
		//paySsi.setFtsCode(FtsCode.CUA);
		//paySsi.setOurSettlementClearingSortCode(ourClearingSortCode);
		//paySsi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementSwiftBicCode(ourSwiftBic);
		payCashFlowSi.setFts(new FundTransferSystem("CUA"));
		payCashFlowSi.setOurSettlementClearingSortCode(ourClearingSortCode);
		payCashFlowSi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertFalse(field53a instanceof Field53D);
		assertEquals(field53a.getValue(), expectedFieldContent);
	
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 53a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Our Settlement Swift Bic Code Present, FTS not CHIPS & Our Account with Them Absent
	 * Expected Result: Formatted Field 53A returned
	 * 
	 */
	@Test
	public void getSequenceB2Field53a_ourSwiftBicPresentFtsNotChipsOurAccountWithThemAbsent_returnField53A() {
		
		/* test value of our swift bic code */
		String ourSwiftBic = "HXXCHKH0";
		
		/* test value of our clearing sort code */
		String ourClearingSortCode = "280797";
		
		expectedFieldContent = ourSwiftBic;
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementSwiftBicCode(ourSwiftBic);
		//paySsi.setFtsCode(FtsCode.CUA);
		//paySsi.setOurSettlementClearingSortCode(ourClearingSortCode);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementSwiftBicCode(ourSwiftBic);
		payCashFlowSi.setFts(new FundTransferSystem("CUA"));
		payCashFlowSi.setOurSettlementClearingSortCode(ourClearingSortCode);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - our account with them in their book is empty */
		payCashFlowSi.setOurAccountWithThemInTheirBook(EMPTY_STRING);
		
		Field field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertFalse(field53a instanceof Field53D);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
		/* sub test case 2 - our account with them in their book contains only space */
		payCashFlowSi.setOurAccountWithThemInTheirBook(STRING_ONE_SPACE);
		
		field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertFalse(field53a instanceof Field53D);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - our account with them in their book is null */
		payCashFlowSi.setOurAccountWithThemInTheirBook(null);
		
		field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertFalse(field53a instanceof Field53D);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 53a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Our Settlement Swift Bic Code Absent
	 * Expected Result: Formatted Field 53D returned
	 * 
	 */
	@Test
	public void getSequenceB2Field53a_ourSwiftBicAbsentOurNameAndAddressLinesPresent_returnField53D() {
		
		/* test values of address fields */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		expectedFieldContent = addressLine1_leng35.concat("\r\n").
									concat(addressLine2_leng35);

		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementNameAndAddressLine1(addressLine1_leng35);
		//paySsi.setOurSettlementNameAndAddressLine2(addressLine2_leng35);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementNameAndAddressLine1(addressLine1_leng35);
		payCashFlowSi.setOurSettlementNameAndAddressLine2(addressLine2_leng35);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - our settlement swift bic is empty */
		payCashFlowSi.setOurSettlementSwiftBicCode(EMPTY_STRING);
		
		Field field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53D);
		assertFalse(field53a instanceof Field53A);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
		/* sub test case 2 - our settlement swift bic contains only space */
		payCashFlowSi.setOurSettlementSwiftBicCode(STRING_ONE_SPACE);
		
		field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53D);
		assertFalse(field53a instanceof Field53A);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - our settlement swift bic is null */
		payCashFlowSi.setOurSettlementSwiftBicCode(null);
		
		field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53D);
		assertFalse(field53a instanceof Field53A);
		assertEquals(field53a.getValue(), expectedFieldContent);
	
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 53a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Our Swift Bic Code Absent & Our Settlement Name And Address Lines Absent
	 * Expected Result: Empty Field 53D Returned
	 * 
	 */
	@Test
	public void getSequenceB2Field53a_ourSwiftBicAbsentOurNameAndAddressLinesAbsent_emptyField53D() {
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setOurSettlementSwiftBicCode(EMPTY_STRING);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setOurSettlementSwiftBicCode(EMPTY_STRING);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - our settlement name and address lines are empty */
		payCashFlowSi.setOurSettlementNameAndAddressLine1(EMPTY_STRING);
		payCashFlowSi.setOurSettlementNameAndAddressLine2(EMPTY_STRING);
		
		Field field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53D);
		assertFalse(field53a instanceof Field53A);
		assertTrue(field53a.isEmpty());
		
		/* sub test case 2 - our settlement name and address lines contain only space */
		payCashFlowSi.setOurSettlementNameAndAddressLine1(STRING_ONE_SPACE);
		payCashFlowSi.setOurSettlementNameAndAddressLine2(STRING_ONE_SPACE);
		
		field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53D);
		assertFalse(field53a instanceof Field53A);
		assertTrue(field53a.isEmpty());
		
		/* sub test case 3 - our settlement name and address lines are null */
		payCashFlowSi.setOurSettlementNameAndAddressLine1(null);
		payCashFlowSi.setOurSettlementNameAndAddressLine2(null);
		
		field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53D);
		assertFalse(field53a instanceof Field53A);
		assertTrue(field53a.isEmpty());

	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 53a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Pay SI Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB2Field53a_paySiAbsent_emptyField53A() {
		
		/* receive si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		//cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field53a = spotMt300Generator.getSequenceB2Field53a(tradeFxSpot);
		
		assertTrue(field53a instanceof Field53A);
		assertFalse(field53a instanceof Field53D);
		assertTrue(field53a.isEmpty());
		
	}

	
	/**
	 * Test Case for MT300 Sequence B2 Field 56a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Counterparty Intermediary Swift Bic Code Present
	 * Expected Result: Formatted Field 56A returned
	 * 
	 */
	@Test
	public void getSequenceB2Field56a_cptyIntermediarySwiftBicPresent_returnField56A() {
		
		/* test value of counterpraty intermediary swift bic */
		String counterpartyIntermediarySwiftBic = "IXXXUS50SNG";
		
		/* expected result */
		expectedFieldContent = counterpartyIntermediarySwiftBic;
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setCptyIntermediarySwiftBicCode(counterpartyIntermediarySwiftBic);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setIntermediaryBankSwiftBicCode(counterpartyIntermediarySwiftBic);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field56a = spotMt300Generator.getSequenceB2Field56a(tradeFxSpot);
		
		assertTrue(field56a instanceof Field56A);
		assertFalse(field56a instanceof Field56D);
		assertFalse(field56a instanceof Field56J);
		assertEquals(field56a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 56a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Counterparty Intermediary Swift Bic Code Absent
	 * Expected Result: Formatted Field 56D returned
	 * 
	 */
	@Test
	public void getSequenceB2Field56a_cptySettlementSwiftBicAbsent_returnField56D() {
		
		/* test value of counterparty intermediary bank code */
		String counterpartyIntermediaryBankCode = "888";
		
		/* test values of address fields */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		/* expected result */
		expectedFieldContent = "/".concat("/CP").concat(counterpartyIntermediaryBankCode).
				concat("\r\n").concat(addressLine1_leng35).
				concat("\r\n").concat(addressLine2_leng35);
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setCptyIntermediaryBankCode(counterpartyIntermediaryBankCode);
		//paySsi.setCptyIntermediaryNameLine1(addressLine1_leng35);
		//paySsi.setCptyIntermediaryNameLine2(addressLine2_leng35);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setIntermediaryBankClearingBankCode(counterpartyIntermediaryBankCode);
		payCashFlowSi.setIntermediaryBankNameLine1(addressLine1_leng35);
		payCashFlowSi.setIntermediaryBankNameLine2(addressLine2_leng35);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - our settlement swift bic code is empty */
		payCashFlowSi.setIntermediaryBankSwiftBicCode(EMPTY_STRING);
		
		Field field56a = spotMt300Generator.getSequenceB2Field56a(tradeFxSpot);
		
		assertTrue(field56a instanceof Field56D);
		assertFalse(field56a instanceof Field56A);
		assertFalse(field56a instanceof Field56J);
		assertEquals(field56a.getValue(), expectedFieldContent);
		
		
		/* sub test case 2 - our settlement swift bic code contains only space */
		payCashFlowSi.setIntermediaryBankSwiftBicCode(STRING_ONE_SPACE);
		
		field56a = spotMt300Generator.getSequenceB2Field56a(tradeFxSpot);
		
		assertTrue(field56a instanceof Field56D);
		assertFalse(field56a instanceof Field56A);
		assertFalse(field56a instanceof Field56J);
		assertEquals(field56a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - our settlement swift bic code is null */
		payCashFlowSi.setIntermediaryBankSwiftBicCode(null);
		
		field56a = spotMt300Generator.getSequenceB2Field56a(tradeFxSpot);
		
		assertTrue(field56a instanceof Field56D);
		assertFalse(field56a instanceof Field56A);
		assertFalse(field56a instanceof Field56J);
		assertEquals(field56a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 56a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Pay SI Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB2Field56a_paySiAbsent_emptyField56A() {
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		//cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field56a = spotMt300Generator.getSequenceB2Field56a(tradeFxSpot);
		
		assertTrue(field56a instanceof Field56A);
		assertFalse(field56a instanceof Field56D);
		assertFalse(field56a instanceof Field56J);
		assertTrue(field56a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 57a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Counterparty Settlement Swift Bic Code Present
	 * Expected Result: Formatted Field 57A returned
	 * 
	 */
	@Test
	public void getSequenceB2Field57a_cptySettlementSwiftBicPresent_returnField57A() {
		
		/* test values of counterparty swift bic code */
		String counterpartySwiftBic = "AXXAGB22";
		
		/* expected result */
		expectedFieldContent = counterpartySwiftBic;
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setCptySettlementSwiftBicCode(counterpartySwiftBic);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setSettlementBankSwiftBicCode(counterpartySwiftBic);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field57a = spotMt300Generator.getSequenceB2Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57J);
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 57a
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Counterparty Settlement Swift Bic Code Absent
	 * Expected Result: Formatted Field 57D returned
	 * 
	 */
	@Test
	public void getSequenceB2Field57a_cptySettlementSwiftBicAbsent_returnField57D() {
		
		/* test values of address fields */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		/* expected result */
		expectedFieldContent = addressLine1_leng35.concat("\r\n").concat(addressLine2_leng35);
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setCptySettlementNameLine1(addressLine1_leng35);
		//paySsi.setCptySettlementNameLine2(addressLine2_leng35);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setSettlementBankNameLine1(addressLine1_leng35);
		payCashFlowSi.setSettlementBankNameLine2(addressLine2_leng35);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - our settlement swift bic code is empty */
		payCashFlowSi.setSettlementBankSwiftBicCode(EMPTY_STRING);
		
		Field field57a = spotMt300Generator.getSequenceB2Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertEquals(field57a.getValue(), expectedFieldContent);
		
		/* sub test case 2 - our settlement swift bic code contains only space */
		payCashFlowSi.setSettlementBankSwiftBicCode(STRING_ONE_SPACE);
		
		field57a = spotMt300Generator.getSequenceB2Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertEquals(field57a.getValue(), expectedFieldContent);
		
		/* sub test case 3 - our settlement swift bic code is null */
		payCashFlowSi.setSettlementBankSwiftBicCode(null);
		
		field57a = spotMt300Generator.getSequenceB2Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 57a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade Pay SI Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB2Field57a_paySiAbsent_emptyField57A() {
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		//cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		Field field57a = spotMt300Generator.getSequenceB2Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57J);
		assertTrue(field57a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 57a
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Counterparty Settlement Name and Address lines Absent
	 * Expected Result: Empty Field 57D returned
	 * 
	 */
	@Test
	public void getSequenceB2Field57a_cptySettlementNameAndAddressLinesAbsent_emptyField57D() {
		
		/* receive & pay si */
		//TradeSsi receiveSsi = new TradeSsi();
		//TradeSsi paySsi = new TradeSsi();
		
		//receiveSsi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		//paySsi.setPaymentIndicator(PaymentIndicator.PAY);
		//paySsi.setCptySettlementSwiftBicCode(EMPTY_STRING);
		
		//List<TradeSsi> tradeSsis = new ArrayList<TradeSsi>();
		
		//tradeSsis.add(receiveSsi);
		//tradeSsis.add(paySsi);
		
		//tradeFxSpot.setTradeSsis(tradeSsis);
		
		CashFlow payCashFlow = new CashFlow();
		CashFlow receiveCashFlow = new CashFlow();
		
		CashFlowSi payCashFlowSi = new CashFlowSi();
		CashFlowSi receiveCashFlowSi = new CashFlowSi();
		
		receiveCashFlow.setCashFlowSi(receiveCashFlowSi);
		receiveCashFlowSi.setPaymentIndicator(PaymentIndicator.RECEIVE);
		
		payCashFlow.setCashFlowSi(payCashFlowSi);
		payCashFlowSi.setPaymentIndicator(PaymentIndicator.PAY);
		payCashFlowSi.setSettlementBankSwiftBicCode(EMPTY_STRING);
		
		ArrayList<CashFlow> cashFlowList = new ArrayList<CashFlow>();
		cashFlowList.add(payCashFlow);
		cashFlowList.add(receiveCashFlow);
		
		tradeFxSpot.setCashFlows(cashFlowList);
		
		/* sub test case 1 - our settlement name and address lines are empty */
		payCashFlowSi.setSettlementBankNameLine1(EMPTY_STRING);
		payCashFlowSi.setSettlementBankNameLine2(EMPTY_STRING);
		
		Field field57a = spotMt300Generator.getSequenceB2Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertTrue(field57a.isEmpty());
		
		/* sub test case 2 - our settlement name and address lines contain only space */
		payCashFlowSi.setSettlementBankNameLine1(STRING_ONE_SPACE);
		payCashFlowSi.setSettlementBankNameLine2(STRING_ONE_SPACE);
		
		field57a = spotMt300Generator.getSequenceB2Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertTrue(field57a.isEmpty());
		
		/* sub test case 3 - our settlement name and address lines are null */
		payCashFlowSi.setSettlementBankNameLine1(null);
		payCashFlowSi.setSettlementBankNameLine2(null);
		
		field57a = spotMt300Generator.getSequenceB2Field57a(tradeFxSpot);
		
		assertTrue(field57a instanceof Field57D);
		assertFalse(field57a instanceof Field57A);
		assertFalse(field57a instanceof Field57J);
		assertTrue(field57a.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence C Field 29A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: All FXMM Contact Information Present
	 * Expected Result: Formatted Field 29A Returned
	 * 
	 */
	@Test
	public void getSequenceCField29A_allFxmmContactInfoPresent_returnField29A() {
		
		/* test value of fxmm contact information */
		String fxmmDepartmentName = "GLOBAL MARKETS OPERATIONS";
		String fxmmContactPerson = "PETER PAN";
		String fxmmPhoneNumber = "+852 23456789";
		String fxmmFaxNumber = "+852 29876543";
		String fxmmTelexNumber = "+852 98765432";
		
		/* expected result */
		expectedFieldContent = "/DEPT/GLOBAL MARKETS OPERATIONS".concat("\r\n").
									concat("/NAME/PETER PAN").concat("\r\n").
									concat("/PHON/+852 23456789").concat("\r\n").
									concat("/FAXT/+852 29876543");
		
		/* Organization Department */
		Department organizationDepartment_fxmm = new Department();
		Department organizationDepartment_fi = new Department();
		List<Department> organizationDepartmentList = new ArrayList<Department>();
		
		organizationDepartment_fxmm.setBusinessClass(BusinessClass.FXMM_METALS);
		organizationDepartment_fxmm.setDepartmentName(fxmmDepartmentName);
		organizationDepartment_fxmm.setContactPerson(fxmmContactPerson);
		organizationDepartment_fxmm.setPhoneNumber(fxmmPhoneNumber);
		organizationDepartment_fxmm.setFaxNumber(fxmmFaxNumber);
		organizationDepartment_fxmm.setTelexNumber(fxmmTelexNumber);
		
		organizationDepartment_fi.setBusinessClass(BusinessClass.FIXED_INCOME);
		
		organizationDepartmentList.add(organizationDepartment_fxmm);
		organizationDepartmentList.add(organizationDepartment_fi);
		
		organization.setDepartmentList(organizationDepartmentList);
		
		tradeFxSpot.setOrganization(organization);
		
		Field29A field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 29A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: FXMM Contact Information Present, Except Department Name
	 * Expected Result: Formatted Field 29A Returned
	 * 
	 */
	@Test
	public void getSequenceCField29A_fxmmContactInfoPresentDepartmentNameAbsent_returnField29A() {
		
		/* test value of fxmm contact information */
		String fxmmContactPerson = "PETER PAN";
		String fxmmPhoneNumber = "+852 23456789";
		String fxmmFaxNumber = "+852 29876543";
		String fxmmTelexNumber = "+852 98765432";
		
		/* expected result */
		expectedFieldContent = "/NAME/PETER PAN".concat("\r\n").
									concat("/PHON/+852 23456789").concat("\r\n").
									concat("/FAXT/+852 29876543").concat("\r\n").
									concat("/TELX/+852 98765432");
		
		Department organizationDepartment_fxmm = new Department();
		Department organizationDepartment_fi = new Department();
		List<Department> organizationDepartmentList = new ArrayList<Department>();
		
		organizationDepartment_fxmm.setBusinessClass(BusinessClass.FXMM_METALS);
		organizationDepartment_fxmm.setContactPerson(fxmmContactPerson);
		organizationDepartment_fxmm.setPhoneNumber(fxmmPhoneNumber);
		organizationDepartment_fxmm.setFaxNumber(fxmmFaxNumber);
		organizationDepartment_fxmm.setTelexNumber(fxmmTelexNumber);
		
		organizationDepartment_fi.setBusinessClass(BusinessClass.FIXED_INCOME);
		
		organizationDepartmentList.add(organizationDepartment_fxmm);
		organizationDepartmentList.add(organizationDepartment_fi);
		
		organization.setDepartmentList(organizationDepartmentList);
		
		tradeFxSpot.setOrganization(organization);
		
		/* sub test case 1 - department name is empty */
		organizationDepartment_fxmm.setDepartmentName(EMPTY_STRING);
		
		Field29A field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 2 - department name contains only space */
		organizationDepartment_fxmm.setDepartmentName(STRING_ONE_SPACE);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 3 - department name is null */
		organizationDepartment_fxmm.setDepartmentName(null);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 29A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: FXMM Contact Information Present, Except Contact Person
	 * Expected Result: Formatted Field 29A Returned
	 * 
	 */
	@Test
	public void getSequenceCField29A_fxmmContactInfoPresentContactPersonAbsent_returnField29A() {
		
		/* test value of fxmm contact information */
		String fxmmDepartmentName = "GLOBAL MARKETS OPERATIONS";
		String fxmmPhoneNumber = "+852 23456789";
		String fxmmFaxNumber = "+852 29876543";
		String fxmmTelexNumber = "+852 98765432";
		
		/* expected result */
		expectedFieldContent = "/DEPT/GLOBAL MARKETS OPERATIONS".concat("\r\n").
									concat("/PHON/+852 23456789").concat("\r\n").
									concat("/FAXT/+852 29876543").concat("\r\n").
									concat("/TELX/+852 98765432");
		
		Department organizationDepartment_fxmm = new Department();
		Department organizationDepartment_fi = new Department();
		List<Department> organizationDepartmentList = new ArrayList<Department>();
		
		organizationDepartment_fxmm.setBusinessClass(BusinessClass.FXMM_METALS);
		organizationDepartment_fxmm.setDepartmentName(fxmmDepartmentName);
		organizationDepartment_fxmm.setPhoneNumber(fxmmPhoneNumber);
		organizationDepartment_fxmm.setFaxNumber(fxmmFaxNumber);
		organizationDepartment_fxmm.setTelexNumber(fxmmTelexNumber);
		
		organizationDepartment_fi.setBusinessClass(BusinessClass.FIXED_INCOME);
		
		organizationDepartmentList.add(organizationDepartment_fxmm);
		organizationDepartmentList.add(organizationDepartment_fi);
		
		organization.setDepartmentList(organizationDepartmentList);
		
		tradeFxSpot.setOrganization(organization);
		
		/* sub test case 1 - contact person is empty */
		organizationDepartment_fxmm.setContactPerson(EMPTY_STRING);
		
		Field29A field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 2 - contact person contains only space */
		organizationDepartment_fxmm.setContactPerson(STRING_ONE_SPACE);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 3 - contact person is null */
		organizationDepartment_fxmm.setContactPerson(null);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 29A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: FXMM Contact Information Present, Except Phone Number
	 * Expected Result: Formatted Field 29A Returned
	 * 
	 */
	@Test
	public void getSequenceCField29A_allFxmmContactInfoPresentPhoneNumberAbsent_returnField29A() {
		
		/* test value of fxmm contact information */
		String fxmmDepartmentName = "GLOBAL MARKETS OPERATIONS";
		String fxmmContactPerson = "PETER PAN";
		String fxmmFaxNumber = "+852 29876543";
		String fxmmTelexNumber = "+852 98765432";
		
		/* expected result */
		expectedFieldContent = "/DEPT/GLOBAL MARKETS OPERATIONS".concat("\r\n").
									concat("/NAME/PETER PAN").concat("\r\n").
									concat("/FAXT/+852 29876543").concat("\r\n").
									concat("/TELX/+852 98765432");
		
		Department organizationDepartment_fxmm = new Department();
		Department organizationDepartment_fi = new Department();
		List<Department> organizationDepartmentList = new ArrayList<Department>();
		
		organizationDepartment_fxmm.setBusinessClass(BusinessClass.FXMM_METALS);
		organizationDepartment_fxmm.setDepartmentName(fxmmDepartmentName);
		organizationDepartment_fxmm.setContactPerson(fxmmContactPerson);
		organizationDepartment_fxmm.setFaxNumber(fxmmFaxNumber);
		organizationDepartment_fxmm.setTelexNumber(fxmmTelexNumber);
		
		organizationDepartment_fi.setBusinessClass(BusinessClass.FIXED_INCOME);
		
		organizationDepartmentList.add(organizationDepartment_fxmm);
		organizationDepartmentList.add(organizationDepartment_fi);
		
		organization.setDepartmentList(organizationDepartmentList);
		
		tradeFxSpot.setOrganization(organization);
		
		/* sub test case 1 - phone number is empty */
		organizationDepartment_fxmm.setPhoneNumber(EMPTY_STRING);
		
		Field29A field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 2 - phone number contains only space */
		organizationDepartment_fxmm.setPhoneNumber(STRING_ONE_SPACE);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 3 - phone number is null */
		organizationDepartment_fxmm.setPhoneNumber(null);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 29A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: FXMM Contact Information Present, Except Fax Number
	 * Expected Result: Formatted Field 29A Returned
	 * 
	 */
	@Test
	public void getSequenceCField29A_allFxmmContactInfoPresentFaxNumberAbsent_returnField29A() {
		
		/* test value of fxmm contact information */
		String fxmmDepartmentName = "GLOBAL MARKETS OPERATIONS";
		String fxmmContactPerson = "PETER PAN";
		String fxmmPhoneNumber = "+852 23456789";
		String fxmmTelexNumber = "+852 98765432";
		
		/* expected result */
		expectedFieldContent = "/DEPT/GLOBAL MARKETS OPERATIONS".concat("\r\n").
									concat("/NAME/PETER PAN").concat("\r\n").
									concat("/PHON/+852 23456789").concat("\r\n").
									concat("/TELX/+852 98765432");
		
		Department organizationDepartment_fxmm = new Department();
		Department organizationDepartment_fi = new Department();
		List<Department> organizationDepartmentList = new ArrayList<Department>();
		
		organizationDepartment_fxmm.setBusinessClass(BusinessClass.FXMM_METALS);
		organizationDepartment_fxmm.setDepartmentName(fxmmDepartmentName);
		organizationDepartment_fxmm.setContactPerson(fxmmContactPerson);
		organizationDepartment_fxmm.setPhoneNumber(fxmmPhoneNumber);
		organizationDepartment_fxmm.setTelexNumber(fxmmTelexNumber);
		
		organizationDepartment_fi.setBusinessClass(BusinessClass.FIXED_INCOME);
		
		organizationDepartmentList.add(organizationDepartment_fxmm);
		organizationDepartmentList.add(organizationDepartment_fi);
		
		organization.setDepartmentList(organizationDepartmentList);
		
		tradeFxSpot.setOrganization(organization);
		
		/* sub test case 1 - phone number is empty */
		organizationDepartment_fxmm.setFaxNumber(EMPTY_STRING);
		
		Field29A field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 2 - phone number contains only space */
		organizationDepartment_fxmm.setFaxNumber(STRING_ONE_SPACE);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 3 - phone number is null */
		organizationDepartment_fxmm.setFaxNumber(null);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 29A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: FXMM Contact Information Present, Except Telex Number
	 * Expected Result: Formatted Field 29A Returned
	 * 
	 */
	@Test
	public void getSequenceCField29A_allFxmmContactInfoPresentTelexNumberAbsent_returnField29A() {
		
		/* test value of fxmm contact information */
		String fxmmDepartmentName = "GLOBAL MARKETS OPERATIONS";
		String fxmmContactPerson = "PETER PAN";
		String fxmmPhoneNumber = "+852 23456789";
		String fxmmFaxNumber = "+852 29876543";
		
		/* expected result */
		expectedFieldContent = "/DEPT/GLOBAL MARKETS OPERATIONS".concat("\r\n").
									concat("/NAME/PETER PAN").concat("\r\n").
									concat("/PHON/+852 23456789").concat("\r\n").
									concat("/FAXT/+852 29876543");
		
		Department organizationDepartment_fxmm = new Department();
		Department organizationDepartment_fi = new Department();
		List<Department> organizationDepartmentList = new ArrayList<Department>();
		
		organizationDepartment_fxmm.setBusinessClass(BusinessClass.FXMM_METALS);
		organizationDepartment_fxmm.setDepartmentName(fxmmDepartmentName);
		organizationDepartment_fxmm.setContactPerson(fxmmContactPerson);
		organizationDepartment_fxmm.setPhoneNumber(fxmmPhoneNumber);
		organizationDepartment_fxmm.setFaxNumber(fxmmFaxNumber);
		
		organizationDepartment_fi.setBusinessClass(BusinessClass.FIXED_INCOME);
		
		organizationDepartmentList.add(organizationDepartment_fxmm);
		organizationDepartmentList.add(organizationDepartment_fi);
		
		organization.setDepartmentList(organizationDepartmentList);
		
		tradeFxSpot.setOrganization(organization);
		
		/* sub test case 1 - telex number is empty */
		organizationDepartment_fxmm.setTelexNumber(EMPTY_STRING);
		
		Field29A field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 2 - fax number contains only space */
		organizationDepartment_fxmm.setTelexNumber(STRING_ONE_SPACE);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
		/* sub test case 3 - fax number is null */
		organizationDepartment_fxmm.setTelexNumber(null);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertEquals(field29A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence C Field 29A
	 * <P>
	 * Category: Functional + Negative
	 * Situation: All FXMM Contact Information Absent
	 * Expected Result: Empty Field 29A Returned
	 * 
	 */
	@Test
	public void getSequenceCField29A_allFxmmContactInfoAbsent_emptyField29A() {
		
		Department organizationDepartment_fxmm = new Department();
		Department organizationDepartment_fi = new Department();
		List<Department> organizationDepartmentList = new ArrayList<Department>();
		
		organizationDepartment_fxmm.setBusinessClass(BusinessClass.FXMM_METALS);
		
		organizationDepartment_fi.setBusinessClass(BusinessClass.FIXED_INCOME);
		
		organizationDepartmentList.add(organizationDepartment_fxmm);
		organizationDepartmentList.add(organizationDepartment_fi);
		
		organization.setDepartmentList(organizationDepartmentList);
		
		tradeFxSpot.setOrganization(organization);
		
		/* sub test case 1 - all contact info are empty */
		organizationDepartment_fxmm.setDepartmentName(EMPTY_STRING);
		organizationDepartment_fxmm.setContactPerson(EMPTY_STRING);
		organizationDepartment_fxmm.setPhoneNumber(EMPTY_STRING);
		organizationDepartment_fxmm.setFaxNumber(EMPTY_STRING);
		organizationDepartment_fxmm.setFaxNumber(EMPTY_STRING);
		
		Field29A field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		assertTrue(field29A.isEmpty());
		
		/* sub test case 2 - all contact info contain only space */
		organizationDepartment_fxmm.setDepartmentName(STRING_ONE_SPACE);
		organizationDepartment_fxmm.setContactPerson(STRING_ONE_SPACE);
		organizationDepartment_fxmm.setPhoneNumber(STRING_ONE_SPACE);
		organizationDepartment_fxmm.setFaxNumber(STRING_ONE_SPACE);
		organizationDepartment_fxmm.setFaxNumber(STRING_ONE_SPACE);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		assertTrue(field29A.isEmpty());
		
		/* sub test case 3 - all contact info are null */
		organizationDepartment_fxmm.setDepartmentName(null);
		organizationDepartment_fxmm.setContactPerson(null);
		organizationDepartment_fxmm.setPhoneNumber(null);
		organizationDepartment_fxmm.setFaxNumber(null);
		organizationDepartment_fxmm.setFaxNumber(null);
		
		organization.setDepartmentList(organizationDepartmentList);
		
		tradeFxSpot.setOrganization(organization);
		
		field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		assertTrue(field29A.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence C Field 29A
	 * <P>
	 * Category: Functional + Negative
	 * Situation: FXMM Department is Absent
	 * Expected Result: Empty Field 29A Returned
	 * 
	 */
	@Test
	public void getSequenceCField29A_fxmmDepartmentAbsent_emptyField29A() {
		
		Department organizationDepartment_fi = new Department();
		List<Department> organizationDepartmentList = new ArrayList<Department>();
		
		organizationDepartment_fi.setBusinessClass(BusinessClass.FIXED_INCOME);
		organizationDepartment_fi.setDepartmentName(EMPTY_STRING);
		organizationDepartment_fi.setContactPerson(EMPTY_STRING);
		organizationDepartment_fi.setPhoneNumber(EMPTY_STRING);
		organizationDepartment_fi.setFaxNumber(EMPTY_STRING);
		organizationDepartment_fi.setFaxNumber(EMPTY_STRING);
		
		organizationDepartmentList.add(organizationDepartment_fi);
		
		organization.setDepartmentList(organizationDepartmentList);
		
		tradeFxSpot.setOrganization(organization);
		
		Field29A field29A = spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
		assertTrue(field29A.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence C Field 29A
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Department List is Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceCField29A_departmentListAbsent_throwException() {
		
		organization.setDepartmentList(null);
		
		tradeFxSpot.setOrganization(organization);
		
		spotMt300Generator.getSequenceCField29A(tradeFxSpot);
		
	}

}