package confirmation;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
import com.prowidesoftware.swift.model.field.Field32B;
import com.prowidesoftware.swift.model.field.Field32E;
import com.prowidesoftware.swift.model.field.Field33B;
import com.prowidesoftware.swift.model.field.Field36;
import com.prowidesoftware.swift.model.field.Field72;
import com.prowidesoftware.swift.model.field.Field77D;
import com.prowidesoftware.swift.model.field.Field98D;

import confirmation.swift.TradeFxSpotMt300SwiftMessageGenerator;

import model.organization.Organization;
import model.party.Counterparty;
import model.trade.Currency;
import model.trade.DoddFrankReportingInfo;
import model.trade.FxSupplementaryInformation;
import model.trade.LegalAgreement;
import model.trade.TradeFxForward;
import model.trade.TradeFxSpot;

public class TestTradeFxSpotMt300SwiftMessageGenerator {
	
	/* FX Spot Trade */
	private TradeFxSpot tradeFxSpot;
	
	/* Counterparty */
	private Counterparty counterparty;
	
	/* Organization */
	private Organization organization;
	
	/* Legal Agreement */
	private LegalAgreement legalAgreement;
	
	/* Associated NDF Trade */
	private TradeFxForward associatedNdfTrade;
	
	/* FX Supplementary Information */
	private FxSupplementaryInformation fxSupplementaryInfo;
	
	/* Dodd Frank Reporting Information */
	DoddFrankReportingInfo doddFrankReportingInfo;
	
	/* FX Spot MT300 message generator */
	private TradeFxSpotMt300SwiftMessageGenerator spotMt300Generator;
	
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
		counterparty = new Counterparty();
		organization = new Organization();
		legalAgreement = new LegalAgreement();
		associatedNdfTrade = new TradeFxForward();
		fxSupplementaryInfo = new FxSupplementaryInformation();
		doddFrankReportingInfo = new DoddFrankReportingInfo();
		spotMt300Generator = new TradeFxSpotMt300SwiftMessageGenerator();
		
	}
	
	@After 
	public void tearDown() throws Exception {
		
		tradeFxSpot = null;
		counterparty = null;
		organization = null;
		legalAgreement = null;
		associatedNdfTrade = null;
		fxSupplementaryInfo = null;
		doddFrankReportingInfo = null;
		spotMt300Generator = null;
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22C
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Valid Sender Code, Receiver Code & Contract Rate with Sender Code Precedes Receiver Code
	 * Expected Result: Formatted Field 83J Returned
	 * 
	 */
	@Test
	public void getSequenceAField22C_senderCodePrecedesReceiverCode_returnField22C() {
		
		/* expected result */
		expectedFieldContent = "HXXCH02345IXXX20";
		
		/* test value of counterparty swift bic */
		String counterpartySwiftBic = "IXXXUS20SNG";
		
		/* test value of our swift bic code */
		String ourSwiftBic = "HXXCHKH0";
		
		/* test value of contract rate */
		BigDecimal contractRate = new BigDecimal(1.2345);
		
		counterparty.setBicCode(counterpartySwiftBic);
		organization.setBicCode(ourSwiftBic);
		
		tradeFxSpot.setCounterparty(counterparty);
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setContractRate(contractRate);
		
		Field22C field22C = spotMt300Generator.getSequenceAField22C(tradeFxSpot);
		
		assertEquals(field22C.getValue(), expectedFieldContent);
		
	}
		
	/**
	 * Test Case for MT300 Sequence A Field 22C
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Valid Sender Code, Receiver Code & Contract Rate with Sender Code Follows Receiver Code
	 * Expected Result: Formatted Field 22C Returned
	 * 
	 */
	@Test
	public void getSequenceAField22C_senderCodeFollowsReceiverCode_returnField22C() {
		
		/* expected result */
		expectedFieldContent = "AXXA222345HXXCH0";
		
		/* test value of counterparty swift bic */
		String counterpartySwiftBic = "AXXAGB22";
		
		/* test value of our swift bic code */
		String ourSwiftBic = "HXXCHKH0";
		
		/* test value of contract rate */
		BigDecimal contractRate = new BigDecimal(1.2345);
		
		counterparty.setBicCode(counterpartySwiftBic);
		organization.setBicCode(ourSwiftBic);
		
		tradeFxSpot.setCounterparty(counterparty);
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setContractRate(contractRate);
		
		Field22C field22C = spotMt300Generator.getSequenceAField22C(tradeFxSpot);
		
		assertEquals(field22C.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22C
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Valid Sender Code, Receiver Code & Contract Rate Patched with Leading Zeros
	 * Expected Result: Formatted Field 22C Returned
	 * 
	 */
	@Test
	public void getSequenceAField22C_contracteRateNeedsLeadingZeros_returnField22C() {
		
		/* expected result */
		expectedFieldContent = "AXXA220012HXXCH0";
		
		/* test value of counterparty swift bic */
		String counterpartySwiftBic = "AXXAGB22";
		
		/* test value of our swift bic code */
		String ourSwiftBic = "HXXCHKH0";
		
		/* test value of contract rate */
		BigDecimal contractRate = new BigDecimal(1.2);
		
		counterparty.setBicCode(counterpartySwiftBic);
		organization.setBicCode(ourSwiftBic);
		
		tradeFxSpot.setCounterparty(counterparty);
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setContractRate(contractRate);
		
		Field22C field22C = spotMt300Generator.getSequenceAField22C(tradeFxSpot);
		assertEquals(field22C.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22C
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Our Swift Bic Code is Absent
	 * Expected Result: Empty Field 22C Returned
	 * 
	 */
	@Test
	public void getSequenceAField22C_ourSwiftBicCodeAbsent_emptyField22C() {
		
		/* test value of counterparty swift bic */
		String counterpartySwiftBic = "AXXAGB22";
		
		/* test value of contract rate */
		BigDecimal contractRate = new BigDecimal(1.2345);
		
		counterparty.setBicCode(counterpartySwiftBic);
		
		tradeFxSpot.setCounterparty(counterparty);
		tradeFxSpot.setContractRate(contractRate);
		tradeFxSpot.setOrganization(organization);
		
		/* sub test case 1 - our swift bic code is empty */
		organization.setBicCode(EMPTY_STRING);
		
		Field22C field22C = spotMt300Generator.getSequenceAField22C(tradeFxSpot);
		
		assertTrue(field22C.isEmpty());
		
		/* sub test case 2 - our swift bic code contains only space */
		organization.setBicCode(STRING_ONE_SPACE);
		
		field22C = spotMt300Generator.getSequenceAField22C(tradeFxSpot);
		
		assertTrue(field22C.isEmpty());
		
		/* sub test case 3 - our swift bic code is null */
		organization.setBicCode(null);
		
		field22C = spotMt300Generator.getSequenceAField22C(tradeFxSpot);
		
		assertTrue(field22C.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 22C
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Counterparty Swift Bic Code is Absent
	 * Expected Result: Empty Field 22C Returned
	 * 
	 */
	@Test
	public void getSequenceAField22C_cptySwiftBicCodeAbsent_emptyField22C() {
		
		/* test value of our swift bic code */
		String ourSwiftBic = "HXXCHKH0";
		
		/* test value of contract rate */
		BigDecimal contractRate = new BigDecimal(1.2345);
		
		organization.setBicCode(ourSwiftBic);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setCounterparty(counterparty);
		tradeFxSpot.setContractRate(contractRate);
		
		/* sub test case 1 - counterparty swift bic code is empty */
		counterparty.setBicCode(EMPTY_STRING);
		
		Field22C field22C = spotMt300Generator.getSequenceAField22C(tradeFxSpot);
		
		assertTrue(field22C.isEmpty());
		
		/* sub test case 2 - counterparty swift bic code contains only space */
		counterparty.setBicCode(STRING_ONE_SPACE);
		
		field22C = spotMt300Generator.getSequenceAField22C(tradeFxSpot);
		
		assertTrue(field22C.isEmpty());
		
		/* sub test case 3 - counterparty swift bic code is null */
		counterparty.setBicCode(null);
		
		field22C = spotMt300Generator.getSequenceAField22C(tradeFxSpot);
		
		assertTrue(field22C.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 17I
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade Settles via PVP
	 * Expected Result: Formatted Field 17I Returned
	 * 
	 */
	@Test
	public void getSequenceAField17I_pvpTxn_returnField17I() {
		
		/* expected result */
		expectedFieldContent = "Y";
		
		tradeFxSpot.setPvpIndicator(true);
		
		Field17I field17I = spotMt300Generator.getSequenceAField17I(tradeFxSpot);
		
		assertEquals(field17I.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 17I
	 * <P>
	 * Category: Functional + Negtive
	 * Situation: Trade Not Settles via PVP
	 * Expected Result: Empty Field 17I Returned
	 * 
	 */
	@Test
	public void getSequenceAField17I_notPvpTxn_emptyField17I() {
		
		tradeFxSpot.setPvpIndicator(false);
		
		Field17I field17I = spotMt300Generator.getSequenceAField17I(tradeFxSpot);
		
		assertTrue(field17I.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77D
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Legal Agreement Present
	 * Expected Result: Formatted Field 77D Returned
	 * 
	 */
	@Test
	public void getSequenceAField77D_legalAgreementPresent_returnField77D() {
		
		/* test values of legal agreement */
		String legalAgreementLine1_leng35 = "+Legal Agreement Legal Agreement 1+";
		String legalAgreementLine2_leng35 = "+Legal Agreement Legal Agreement 2+";
		String legalAgreementLine3_leng35 = "+Legal Agreement Legal Agreement 3+";
		String legalAgreementLine4_leng35 = "+Legal Agreement Legal Agreement 4+";
		String legalAgreementLine5_leng35 = "+Legal Agreement Legal Agreement 5+";
		String legalAgreementLine6_leng35 = "+Legal Agreement Legal Agreement 6+";
		
		/* expected result */
		expectedFieldContent = legalAgreementLine1_leng35.concat("\r\n").
									concat(legalAgreementLine2_leng35).concat("\r\n").
									concat(legalAgreementLine3_leng35).concat("\r\n").
									concat(legalAgreementLine4_leng35).concat("\r\n").
									concat(legalAgreementLine5_leng35).concat("\r\n").
									concat(legalAgreementLine6_leng35);
		
		legalAgreement.setLegalAgreementLine1(legalAgreementLine1_leng35);
		legalAgreement.setLegalAgreementLine2(legalAgreementLine2_leng35);
		legalAgreement.setLegalAgreementLine3(legalAgreementLine3_leng35);
		legalAgreement.setLegalAgreementLine4(legalAgreementLine4_leng35);
		legalAgreement.setLegalAgreementLine5(legalAgreementLine5_leng35);
		legalAgreement.setLegalAgreementLine6(legalAgreementLine6_leng35);
		
		tradeFxSpot.setLegalAgreement(legalAgreement);
		
		Field77D field77D = spotMt300Generator.getSequenceAField77D(tradeFxSpot);
		
		assertEquals(field77D.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77D
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Legal Agreement is Absent
	 * Expected Result: Empty Field 77D Returned
	 * 
	 */
	@Test
	public void getSequenceAField77D_legalAgreementAbsent_emptyField77D() {

		/* sub test case 1 - legal agreement lines are empty */
		legalAgreement.setLegalAgreementLine1(EMPTY_STRING);
		legalAgreement.setLegalAgreementLine2(EMPTY_STRING);
		legalAgreement.setLegalAgreementLine3(EMPTY_STRING);
		legalAgreement.setLegalAgreementLine4(EMPTY_STRING);
		legalAgreement.setLegalAgreementLine5(EMPTY_STRING);
		legalAgreement.setLegalAgreementLine6(EMPTY_STRING);
		
		tradeFxSpot.setLegalAgreement(legalAgreement);
		
		Field77D field77D = spotMt300Generator.getSequenceAField77D(tradeFxSpot);
		
		assertTrue(field77D.isEmpty());
		
		/* sub test case 2 - legal agreement lines contain only space */
		legalAgreement.setLegalAgreementLine1(STRING_ONE_SPACE);
		legalAgreement.setLegalAgreementLine2(STRING_ONE_SPACE);
		legalAgreement.setLegalAgreementLine3(STRING_ONE_SPACE);
		legalAgreement.setLegalAgreementLine4(STRING_ONE_SPACE);
		legalAgreement.setLegalAgreementLine5(STRING_ONE_SPACE);
		legalAgreement.setLegalAgreementLine6(STRING_ONE_SPACE);
		
		tradeFxSpot.setLegalAgreement(legalAgreement);
		
		field77D = spotMt300Generator.getSequenceAField77D(tradeFxSpot);
		
		assertTrue(field77D.isEmpty());
		
		/* sub test case 3 - legal agreement lines are null */
		legalAgreement.setLegalAgreementLine1(null);
		legalAgreement.setLegalAgreementLine2(null);
		legalAgreement.setLegalAgreementLine3(null);
		legalAgreement.setLegalAgreementLine4(null);
		legalAgreement.setLegalAgreementLine5(null);
		legalAgreement.setLegalAgreementLine6(null);
		
		tradeFxSpot.setLegalAgreement(legalAgreement);
		
		field77D = spotMt300Generator.getSequenceAField77D(tradeFxSpot);
		
		assertTrue(field77D.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 77D
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Legal Agreement is Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test  (expected = NullPointerException.class)
	public void getSequenceAField77D_legalAgreementAbsent_throwException() {
		
		tradeFxSpot.setLegalAgreement(null);
		
		spotMt300Generator.getSequenceAField77D(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 14C
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Legal Agreement Year of Version Present
	 * Expected Result: Formatted Field 14C Returned
	 * 
	 */
	@Test
	public void getSequenceAField14C_yearOfVersionPresent_returnField14C() {
		
		/* test value of year of version */
		String yearOfVersion = "1998";
		
		/* expected result */
		expectedFieldContent = yearOfVersion;
		
		legalAgreement.setYearOfVersion(yearOfVersion);
		
		tradeFxSpot.setLegalAgreement(legalAgreement);
		
		Field14C field14C = spotMt300Generator.getSequenceAField14C(tradeFxSpot);
		
		assertEquals(field14C.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 14C
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Legal Agreement Year of Version is Absent
	 * Expected Result: Empty Field 14C Returned
	 * 
	 */
	@Test
	public void getSequenceAField14C_yearOfVersionAbsent_emptyField14C() {
	
		/* sub test case 1 - year of version is empty */
		legalAgreement.setYearOfVersion(EMPTY_STRING);
		
		tradeFxSpot.setLegalAgreement(legalAgreement);
		
		Field14C field14C = spotMt300Generator.getSequenceAField14C(tradeFxSpot);
		
		assertTrue(field14C.isEmpty());
		
		/* sub test case 2 - year of version contains only space */
		legalAgreement.setYearOfVersion(STRING_ONE_SPACE);
		
		tradeFxSpot.setLegalAgreement(legalAgreement);
		
		field14C = spotMt300Generator.getSequenceAField14C(tradeFxSpot);
		
		assertTrue(field14C.isEmpty());
		
		/* sub test case 3 - year of version is null */
		legalAgreement.setYearOfVersion(null);
		
		tradeFxSpot.setLegalAgreement(legalAgreement);
		
		field14C = spotMt300Generator.getSequenceAField14C(tradeFxSpot);
		
		assertTrue(field14C.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 14C
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Legal Agreement is Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField14C_legalAgreementAbsent_throwException() {
		
		tradeFxSpot.setLegalAgreement(null);
		
		spotMt300Generator.getSequenceAField14C(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 17F
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade is Non-Deliverable
	 * Expected Result: Formatted Field 17F Returned
	 * 
	 */
	@Test
	public void getSequenceAField17F_nonDeliverableTrade_returnField17F() {
	
		/* expected result */
		expectedFieldContent = "Y";
		
		tradeFxSpot.setNonDeliverableIndicator(true);
		
		Field17F field17F = spotMt300Generator.getSequenceAField17F(tradeFxSpot);
		
		assertEquals(field17F.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 17F
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Deliverable
	 * Expected Result: Empty Field 17F Returned
	 * 
	 */
	@Test
	public void getSequenceAField17F_deliverableTrade_emptyField17F() {
	
		tradeFxSpot.setNonDeliverableIndicator(false);
		
		Field17F field17F = spotMt300Generator.getSequenceAField17F(tradeFxSpot);
		
		assertTrue(field17F.isEmpty());
		
	}
		
	/**
	 * Test Case for MT300 Sequence A Field 17O
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade is Non-Deliverable
	 * Expected Result: Formatted Field 17O Returned
	 * 
	 */
	@Test
	public void getSequenceAField17O_nonDeliverableTrade_returnField17O() {
		
		/* expected result */
		expectedFieldContent = "N";
		
		tradeFxSpot.setNonDeliverableIndicator(true);
		
		Field17O field17O = spotMt300Generator.getSequenceAField17O(tradeFxSpot);
		
		assertEquals(field17O.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 17O
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Deliverable
	 * Expected Result: Empty Field 17O Returned
	 * 
	 */
	@Test
	public void getSequenceAField17O_deliverableTrade_emptyField17O() {
		
		tradeFxSpot.setNonDeliverableIndicator(false);
		
		Field17O field17O = spotMt300Generator.getSequenceAField17O(tradeFxSpot);
		
		assertTrue(field17O.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 32E
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Non-Deliverable Trade with Non-Deliverable Bought Currency
	 * Expected Result: Formatted Field 32E Returned
	 * 
	 */
	@Test
	public void getSequenceAField32E_nonDeliverableBoughtCurrency_returnField32E() {
		
		/* test values of currency codes */
		String boughtCurrencyCode = "CNY";
		String soldCurrencyCode = "USD";
		
		/* expected result */
		expectedFieldContent = soldCurrencyCode;
		
		/* trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode(boughtCurrencyCode);
		boughtCurrency.setNonDeliverableCurrencyIndicator(true);
		boughtCurrency.setSettlementCurrencyCode(boughtCurrencyCode);
		
		soldCurrency.setCurrencyCode(soldCurrencyCode);
		soldCurrency.setNonDeliverableCurrencyIndicator(false);
		soldCurrency.setSettlementCurrencyCode(soldCurrencyCode);
		
		tradeFxSpot.setNonDeliverableIndicator(true);
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		
		Field32E field32E = spotMt300Generator.getSequenceAField32E(tradeFxSpot);
		assertEquals(field32E.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 32E
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Non-Deliverable Trade with Non-Deliverable Sold Currency
	 * Expected Result: Formatted Field 32E Returned
	 * 
	 */
	@Test
	public void getSequenceAField32E_nonDeliverableSoldCurrency_returnField32E() {
		
		/* test values of currency codes */
		String boughtCurrencyCode = "USD";
		String soldCurrencyCode = "CNY";
		
		/* expected result */
		expectedFieldContent = boughtCurrencyCode;
		
		/* trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode(boughtCurrencyCode);
		boughtCurrency.setNonDeliverableCurrencyIndicator(false);
		boughtCurrency.setSettlementCurrencyCode(boughtCurrencyCode);
		
		soldCurrency.setCurrencyCode(soldCurrencyCode);
		soldCurrency.setNonDeliverableCurrencyIndicator(true);
		soldCurrency.setSettlementCurrencyCode(soldCurrencyCode);
		
		tradeFxSpot.setNonDeliverableIndicator(true);
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		
		Field32E field32E = spotMt300Generator.getSequenceAField32E(tradeFxSpot);
		
		assertEquals(field32E.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 32E
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Deliverable
	 * Expected Result: Empty Field 32E Returned
	 * 
	 */
	@Test
	public void getSequenceAField32E_deliverableTrade_emptyField32E() {
		
		/* test values of currency codes */
		String boughtCurrencyCode = "HKD";
		String soldCurrencyCode = "USD";
		
		/* trading currencies */
		Currency boughtCurrency = new Currency();
		Currency soldCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode(boughtCurrencyCode);
		boughtCurrency.setNonDeliverableCurrencyIndicator(false);
		boughtCurrency.setSettlementCurrencyCode(boughtCurrencyCode);
		
		soldCurrency.setCurrencyCode(soldCurrencyCode);
		soldCurrency.setNonDeliverableCurrencyIndicator(false);
		soldCurrency.setSettlementCurrencyCode(soldCurrencyCode);
		
		tradeFxSpot.setNonDeliverableIndicator(false);
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setSoldCurrency(soldCurrency);
		
		Field32E field32E = spotMt300Generator.getSequenceAField32E(tradeFxSpot);
		
		assertTrue(field32E.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 30U
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Non-Deliverable
	 * Expected Result: Empty Field 30U Returned
	 * 
	 */
	@Test
	public void getSequenceAField30U_nonDeliverableTrade_emptyField30U() {
		
		tradeFxSpot.setNonDeliverableIndicator(true);
		
		Field30U field30U = spotMt300Generator.getSequenceAField30U(tradeFxSpot);
		
		assertTrue(field30U.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 30U
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Deliverable
	 * Expected Result: Empty Field 30U Returned
	 * 
	 */
	@Test
	public void getSequenceAField30U_deliverableTrade_emptyField30U() {
		
		tradeFxSpot.setNonDeliverableIndicator(false);
		
		Field30U field30U = spotMt300Generator.getSequenceAField30U(tradeFxSpot);
		
		assertTrue(field30U.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 14S
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Non-Deliverable
	 * Expected Result: Empty Field 14S Returned
	 * 
	 */
	@Test
	public void getSequenceAField14S_nonDeliverableTrade_emptyField14S() {
		
		tradeFxSpot.setNonDeliverableIndicator(true);
		
		Field14S field14S = spotMt300Generator.getSequenceAField14S(tradeFxSpot);
		
		assertTrue(field14S.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 14S
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Deliverable
	 * Expected Result: Empty Field 14S Returned
	 * 
	 */
	@Test
	public void getSequenceAField14S_deliverableTrade_emptyField14S() {
		
		tradeFxSpot.setNonDeliverableIndicator(false);
		
		Field14S field14S = spotMt300Generator.getSequenceAField14S(tradeFxSpot);
		
		assertTrue(field14S.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21A
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Trade is Non-Deliverable
	 * Expected Result: Formatted Field 21A Returned
	 * 
	 */
	@Test
	public void getSequenceAField21A_nonDeliverableTrade_returnField21A() {
		
		/* test value of associated NDF transaction reference */
		String associatedNdfTransactionReference = "NFWSEL189989";
		
		expectedFieldContent = associatedNdfTransactionReference;
		
		associatedNdfTrade.setTransactionReference(associatedNdfTransactionReference);
		
		tradeFxSpot.setNonDeliverableIndicator(true);
		tradeFxSpot.setAssociatedTrade(associatedNdfTrade);
		
		Field21A field21A = spotMt300Generator.getSequenceAField21A(tradeFxSpot);
		
		assertEquals(field21A.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21A
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Deliverable
	 * Expected Result: Empty Field 21A Returned
	 * 
	 */
	@Test
	public void getSequenceAField21A_deliverableTrade_emptyField21A() {
		
		/* test value of associated NDF transaction reference */
		String associatedNdfTransactionReference = "NFWSEL189989";
		
		associatedNdfTrade.setTransactionReference(associatedNdfTransactionReference);
		
		tradeFxSpot.setNonDeliverableIndicator(false);
		tradeFxSpot.setAssociatedTrade(associatedNdfTrade);
		
		Field21A field21A = spotMt300Generator.getSequenceAField21A(tradeFxSpot);
		
		assertTrue(field21A.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21A
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Non-Deliverable & Associated NDF Transaction Reference Absent
	 * Expected Result: Empty Field 21A Returned
	 * 
	 */
	@Test
	public void getSequenceAField21A_nonDeliverableTradeAssociatedNDFReferenceAbsent_emptyField21A() {
		
		tradeFxSpot.setNonDeliverableIndicator(true);
		tradeFxSpot.setAssociatedTrade(associatedNdfTrade);
		
		/* sub test case 1 - associated NDF transaction reference is empty */
		associatedNdfTrade.setTransactionReference(EMPTY_STRING);
		
		Field21A field21A = spotMt300Generator.getSequenceAField21A(tradeFxSpot);
		
		assertTrue(field21A.isEmpty());
		
		/* sub test case 2 - associated NDF transaction reference contains only space */
		associatedNdfTrade.setTransactionReference(STRING_ONE_SPACE);
		
		field21A = spotMt300Generator.getSequenceAField21A(tradeFxSpot);
		
		assertTrue(field21A.isEmpty());
		
		/* sub test case 3 - associated NDF transaction reference is null */
		associatedNdfTrade.setTransactionReference(null);
		
		field21A = spotMt300Generator.getSequenceAField21A(tradeFxSpot);
		
		assertTrue(field21A.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence A Field 21A
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Trade is Non-Deliverable & Associated NDF Trade Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceAField21A_nonDeliverableTradeAssociatedNdfAbsent_throwException() {
		
		tradeFxSpot.setNonDeliverableIndicator(true);
		tradeFxSpot.setAssociatedTrade(null);
		
		spotMt300Generator.getSequenceAField21A(tradeFxSpot);
	
	}
	
	/**
	 * Test Case for MT300 Sequence B Field 36
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Exchange Rate Present
	 * Expected Result: Formatted Field 36 Returned
	 * 
	 */
	@Test
	public void getSequenceBField36_exchangeRatePresent_returnField36() {
		
		/* expected result */
		expectedFieldContent = "1,2345";
		
		/* test value of contract rate */
		BigDecimal contractRate = new BigDecimal(1.2345);
		
		tradeFxSpot.setContractRate(contractRate);
		
		Field36 field36 = spotMt300Generator.getSequenceBField36(tradeFxSpot);
		
		assertEquals(field36.getValue(), expectedFieldContent);
	
	}
	
	/**
	 * Test Case for MT300 Sequence B Field 36
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Exchange Rate Absent
	 * Expected Result: Empty Field 36 Returned
	 * 
	 */
	@Test
	public void getSequenceBField36_exchangeRateAbsent_emptyField36() {
		
		tradeFxSpot.setContractRate(null);
		
		Field36 field36 = spotMt300Generator.getSequenceBField36(tradeFxSpot);
		
		assertTrue(field36.isEmpty());
	
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 32B
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Bought Currency Present + Positive Bought Amount
	 * Expected Result: Formatted Field 32B Returned
	 * 
	 */
	@Test
	public void getSequenceB1Field32B_boughtCurrencyPresentPositiveBoughtAmt_returnField32B() {
		
		/* expected result */
		expectedFieldContent = "HKD98765,43";
		
		/* test value of bought amount */
		BigDecimal boughtAmount = new BigDecimal(98765.43);
		
		/* test value of currency code */
		String boughtCurrencyCode = "HKD";
		
		/* trading currencies */
		Currency boughtCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode(boughtCurrencyCode);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setBoughtCurrencyAmount(boughtAmount);
		
		Field32B field32B = spotMt300Generator.getSequenceB1Field32B(tradeFxSpot);
		
		assertEquals(field32B.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 32B
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Bought Currency Present + Negative Bought Amount
	 * Expected Result: Formatted Field 32B Returned
	 * 
	 */
	@Test
	public void getSequenceB1Field32B_boughtCurrencyPresentNegativeBoughtAmt_returnField32B() {
		
		/* expected result */
		expectedFieldContent = "HKD98765,43";
		
		/* test value of bought amount */
		BigDecimal boughtAmount = new BigDecimal(-98765.43);
		
		/* test value of currency code */
		String boughtCurrencyCode = "HKD";
		
		/* trading currencies */
		Currency boughtCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode(boughtCurrencyCode);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setBoughtCurrencyAmount(boughtAmount);
		
		Field32B field32B = spotMt300Generator.getSequenceB1Field32B(tradeFxSpot);
		
		assertEquals(field32B.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 32B
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Bought Currency Present + Zero Bought Amount
	 * Expected Result: Formatted Field 32B Returned
	 * 
	 */
	@Test
	public void getSequenceB1Field32B_boughtCurrencyPresentZeroBoughtAmt_returnField32B() {
		
		/* expected result */
		expectedFieldContent = "HKD0,";
		
		/* test value of bought amount */
		BigDecimal boughtAmount = new BigDecimal(0);
		
		/* test value of currency code */
		String boughtCurrencyCode = "HKD";
		
		/* trading currencies */
		Currency boughtCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode(boughtCurrencyCode);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setBoughtCurrencyAmount(boughtAmount);
		
		Field32B field32B = spotMt300Generator.getSequenceB1Field32B(tradeFxSpot);
		
		assertEquals(field32B.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 32B
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Bought Currency Present + Bought Amount Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB1Field32B_boughtCurrencyPresentBoughtAmtAbsent_throwException() {
		
		/* test value of currency code */
		String boughtCurrencyCode = "HKD";
		
		/* trading currencies */
		Currency boughtCurrency = new Currency();
		
		boughtCurrency.setCurrencyCode(boughtCurrencyCode);
		
		tradeFxSpot.setBoughtCurrency(boughtCurrency);
		tradeFxSpot.setBoughtCurrencyAmount(null);
		
		spotMt300Generator.getSequenceB1Field32B(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 32B
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Bought Currency Absent + Bought Amount Present
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB1Field32B_boughtCurrencyAbsentBoughtAmtPresent_throwException() {
		
		/* test value of bought amount */
		BigDecimal boughtAmount = new BigDecimal(98765.43);
		
		tradeFxSpot.setBoughtCurrency(null);
		tradeFxSpot.setBoughtCurrencyAmount(boughtAmount);
		
		spotMt300Generator.getSequenceB1Field32B(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B1 Field 32B
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Bought Currency Absent + Bought Amount Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB1Field32B_boughtCurrencyAbsentBoughtAmtAbsent_throwException() {
		
		tradeFxSpot.setBoughtCurrency(null);
		tradeFxSpot.setBoughtCurrencyAmount(null);
		
		spotMt300Generator.getSequenceB1Field32B(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 33B
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Sold Currency Present + Positive Sold Amount
	 * Expected Result: Formatted Field 33B Returned
	 * 
	 */
	@Test
	public void getSequenceB2Field33B_soldCurrencyPresentPositiveSoldAmt_returnField33B() {
		
		/* expected result */
		expectedFieldContent = "HKD98765,43";
		
		/* test value of currency code */
		String soldCurrencyCode = "HKD";
		
		/* test value of sold amount */
		BigDecimal soldAmount = new BigDecimal(98765.43);
		
		/* trading currencies */
		Currency soldCurrency = new Currency();
		
		soldCurrency.setCurrencyCode(soldCurrencyCode);
		
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setSoldCurrencyAmount(soldAmount);
		
		Field33B field33B = spotMt300Generator.getSequenceB2Field33B(tradeFxSpot);
		
		assertEquals(field33B.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 33B
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Sold Currency Present + Negative Sold Amount
	 * Expected Result: Formatted Field 33B Returned
	 * 
	 */
	@Test
	public void getSequenceB2Field33B_soldCurrencyPresentNegativeSoldAmt_returnField33B() {
		
		/* expected result */
		expectedFieldContent = "HKD98765,43";
		
		/* test value of currency code */
		String soldCurrencyCode = "HKD";
		
		/* test value of sold amount */
		BigDecimal soldAmount = new BigDecimal(-98765.43);
		
		/* trading currencies */
		Currency soldCurrency = new Currency();
		
		soldCurrency.setCurrencyCode(soldCurrencyCode);
		
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setSoldCurrencyAmount(soldAmount);
		
		Field33B field33B = spotMt300Generator.getSequenceB2Field33B(tradeFxSpot);
		
		assertEquals(field33B.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 33B
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Sold Currency Present + Zero Sold Amount
	 * Expected Result: Formatted Field 33B Returned
	 * 
	 */
	@Test
	public void getSequenceB2Field33B_soldCurrencyPresentZeroSoldAmt_returnField33B() {
		
		/* expected result */
		expectedFieldContent = "HKD0,";
		
		/* test value of currency code */
		String soldCurrencyCode = "HKD";
		
		/* test value of sold amount */
		BigDecimal soldAmount = new BigDecimal(0);
		
		/* trading currencies */
		Currency soldCurrency = new Currency();
		
		soldCurrency.setCurrencyCode(soldCurrencyCode);
		
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setSoldCurrencyAmount(soldAmount);
		
		Field33B field33B = spotMt300Generator.getSequenceB2Field33B(tradeFxSpot);
		
		assertEquals(field33B.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 33B
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Sold Currency Present + Sold Amount Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB2Field33B_soldCurrencyPresentSoldAmtAbsent_throwException() {
		
		/* test value of currency code */
		String soldCurrencyCode = "HKD";
		
		/* trading currencies */
		Currency soldCurrency = new Currency();
		
		soldCurrency.setCurrencyCode(soldCurrencyCode);
		
		tradeFxSpot.setSoldCurrency(soldCurrency);
		tradeFxSpot.setSoldCurrencyAmount(null);
		
		spotMt300Generator.getSequenceB2Field33B(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 33B
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Sold Currency Absent + Sold Amount Present
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB2Field33B_SoldCurrencyAbsentSoldAmtPresent_throwException() {
		
		/* test value of sold amount */
		BigDecimal soldAmount = new BigDecimal(1.2345);
		
		tradeFxSpot.setSoldCurrency(null);
		tradeFxSpot.setSoldCurrencyAmount(soldAmount);
		
		spotMt300Generator.getSequenceB2Field33B(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence B2 Field 33B
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Sold Currency Absent + Sold Amount Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceB2Field33B_soldCurrencyAbsentSoldAmtAbsent_throwException() {
		
		tradeFxSpot.setSoldCurrency(null);
		tradeFxSpot.setSoldCurrencyAmount(null);
		
		spotMt300Generator.getSequenceB2Field33B(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence C Field 72
	 * <P>
	 * Category: Functional + Positive
	 * Situation: FX Supplementary Info Present
	 * Expected Result: Formatted Field 72 Returned
	 * 
	 */
	@Test
	public void getSequenceCField72_fxSupplementaryInfoPresent_returnField72() {
		
		/* test values of sender to receiver info */
		String senderToReceiverLine1 = "Sender to Receiver Info Line 1";
		String senderToReceiverLine2 = "Sender to Receiver Info Line 2";
		String senderToReceiverLine3 = "Sender to Receiver Info Line 3";
		
		
		expectedFieldContent = senderToReceiverLine1.concat("\r\n").
									concat(senderToReceiverLine2).concat("\r\n").
									concat(senderToReceiverLine3);
		
		fxSupplementaryInfo.setSenderToReceiverInfoLine1(senderToReceiverLine1);
		fxSupplementaryInfo.setSenderToReceiverInfoLine2(senderToReceiverLine2);
		fxSupplementaryInfo.setSenderToReceiverInfoLine3(senderToReceiverLine3);
		
		tradeFxSpot.setFxSupplementaryInfo(fxSupplementaryInfo);
		
		Field72 field72 = spotMt300Generator.getSequenceCField72(tradeFxSpot);
		
		assertEquals(field72.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence C Field 72
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Sender to Receiver Information Absent
	 * Expected Result: Empty Field 72 Returned
	 * 
	 */
	@Test
	public void getSequenceCField72_senderToReceiverInfoAbsent_emptyField72() {
		
		/* sub test case 1 - sender to receiver info are empty */
		fxSupplementaryInfo.setSenderToReceiverInfoLine1(EMPTY_STRING);
		fxSupplementaryInfo.setSenderToReceiverInfoLine2(EMPTY_STRING);
		fxSupplementaryInfo.setSenderToReceiverInfoLine3(EMPTY_STRING);
		
		tradeFxSpot.setFxSupplementaryInfo(fxSupplementaryInfo);
		
		Field72 field72 = spotMt300Generator.getSequenceCField72(tradeFxSpot);
		
		assertTrue(field72.isEmpty());
		
		/* sub test case 2 - sender to receiver info contain only space */
		fxSupplementaryInfo.setSenderToReceiverInfoLine1(STRING_ONE_SPACE);
		fxSupplementaryInfo.setSenderToReceiverInfoLine2(STRING_ONE_SPACE);
		fxSupplementaryInfo.setSenderToReceiverInfoLine3(STRING_ONE_SPACE);
		
		tradeFxSpot.setFxSupplementaryInfo(fxSupplementaryInfo);
		
		field72 = spotMt300Generator.getSequenceCField72(tradeFxSpot);
		
		assertTrue(field72.isEmpty());
		
		/* sub test case 3 - sender to receiver info are null */
		fxSupplementaryInfo.setSenderToReceiverInfoLine1(null);
		fxSupplementaryInfo.setSenderToReceiverInfoLine2(null);
		fxSupplementaryInfo.setSenderToReceiverInfoLine3(null);
		
		tradeFxSpot.setFxSupplementaryInfo(fxSupplementaryInfo);
		
		field72 = spotMt300Generator.getSequenceCField72(tradeFxSpot);
		
		assertTrue(field72.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence C Field 72
	 * <P>
	 * Category: Functional + Negative
	 * Situation: FX Supplementary Information Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceCField72_fxSupplementaryInfoAbsent_throwException() {
		
		tradeFxSpot.setFxSupplementaryInfo(null);
		
		spotMt300Generator.getSequenceCField72(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22L
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Formatted Field 22L Returned
	 * 
	 */
	@Test
	public void getSequenceEField22L_doddFrankSiteFxSwapIndicatorOn_returnField22L() {
		
		/* expected field content */
		expectedFieldContent = "AS REQUIRED";
		
		organization.setDoddFrankReportingFlag(true);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		
		Field22L field22L = spotMt300Generator.getSequenceEField22L(tradeFxSpot);
		
		assertEquals(field22L.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22L
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22L Returned
	 * 
	 */
	@Test
	public void getSequenceEField22L_doddFrankSiteFxSwapIndicatorOff_emptyField22L() {
		
		organization.setDoddFrankReportingFlag(true);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22L field22L = spotMt300Generator.getSequenceEField22L(tradeFxSpot);
		
		assertTrue(field22L.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22L
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Empty Field 22L Returned
	 * 
	 */
	@Test
	public void getSequenceEField22L_notDoddFrankSiteFxSwapIndicatorOn_emptyField22L() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		
		Field22L field22L = spotMt300Generator.getSequenceEField22L(tradeFxSpot);
		
		assertTrue(field22L.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22L
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22L Returned
	 * 
	 */
	@Test
	public void getSequenceEField22L_notDoddFrankSiteFxSwapIndicatorOff_emptyField22L() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22L field22L = spotMt300Generator.getSequenceEField22L(tradeFxSpot);
		
		assertTrue(field22L.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22L
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Our Organization is Absent
	 * Expected Result: Throw Exception
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceEField22L_ourOrganizationAbsent_throwException() {
		
		tradeFxSpot.setOrganization(null);
		tradeFxSpot.setFxSwapIndicator(true);
		
		spotMt300Generator.getSequenceEField22L(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22M
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Formatted Field 22L Returned
	 * 
	 */
	@Test
	public void getSequenceEField22M_doddFrankSiteFxSwapIndicatorOn_returnField22M() {
		
		/* test value of deal usi */
		String dealUsi = "1030444408NFO201407040018000088885279";
		
		/* expected result */
		expectedFieldContent = dealUsi.substring(0, 10);
		
		organization.setDoddFrankReportingFlag(true);
		
		doddFrankReportingInfo.setDealUsi(dealUsi);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		tradeFxSpot.setDoddFrankReportingInfo(doddFrankReportingInfo);
		
		Field22M field22M = spotMt300Generator.getSequenceEField22M(tradeFxSpot);
		
		assertEquals(field22M.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22M
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22M Returned
	 * 
	 */
	@Test
	public void getSequenceEField22M_doddFrankSiteFxSwapIndicatorOff_emptyField22M() {
		
		organization.setDoddFrankReportingFlag(true);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22M field22M = spotMt300Generator.getSequenceEField22M(tradeFxSpot);
		
		assertTrue(field22M.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22M
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Empty Field 22M Returned
	 * 
	 */
	@Test
	public void getSequenceEField22M_notDoddFrankSiteFxSwapIndicatorOn_emptyField22M() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		
		Field22M field22M = spotMt300Generator.getSequenceEField22M(tradeFxSpot);
		
		assertTrue(field22M.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22M
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22M Returned
	 * 
	 */
	@Test
	public void getSequenceEField22M_notDoddFrankSiteFxSwapIndicatorOff_emptyField22M() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22M field22M = spotMt300Generator.getSequenceEField22M(tradeFxSpot);
		
		assertTrue(field22M.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22M
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Our Organization is Absent
	 * Expected Result: Throw Exception
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceEField22M_ourOrganizationAbsent_throwException() {
		
		tradeFxSpot.setOrganization(null);
		tradeFxSpot.setFxSwapIndicator(true);
		
		spotMt300Generator.getSequenceEField22M(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22N
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Formatted Field 22N Returned
	 * 
	 */
	@Test
	public void getSequenceEField22N_doddFrankSiteFxSwapIndicatorOn_returnField22N() {
		
		/* test value of deal usi */
		String dealUsi = "1030444408NFO201407040018000088885279";
		
		/* expected result */
		expectedFieldContent = dealUsi.substring(10);
		
		organization.setDoddFrankReportingFlag(true);
		
		doddFrankReportingInfo.setDealUsi(dealUsi);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		tradeFxSpot.setDoddFrankReportingInfo(doddFrankReportingInfo);
		
		Field22N field22N = spotMt300Generator.getSequenceEField22N(tradeFxSpot);
		
		assertEquals(field22N.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22N
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22N Returned
	 * 
	 */
	@Test
	public void getSequenceEField22N_doddFrankSiteFxSwapIndicatorOff_emptyField22N() {
		
		organization.setDoddFrankReportingFlag(true);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22N field22N = spotMt300Generator.getSequenceEField22N(tradeFxSpot);
		
		assertTrue(field22N.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22N
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Empty Field 22N Returned
	 * 
	 */
	@Test
	public void getSequenceEField22N_notDoddFrankSiteFxSwapIndicatorOn_emptyField22N() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		
		Field22N field22N = spotMt300Generator.getSequenceEField22N(tradeFxSpot);
		
		assertTrue(field22N.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22N
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22N Returned
	 * 
	 */
	@Test
	public void getSequenceEField22N_notDoddFrankSiteFxSwapIndicatorOff_emptyField22N() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22N field22N = spotMt300Generator.getSequenceEField22N(tradeFxSpot);
		
		assertTrue(field22N.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22N
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Our Organization is Absent
	 * Expected Result: Throw Exception
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceEField22N_ourOrganizationAbsent_throwException() {
		
		tradeFxSpot.setOrganization(null);
		tradeFxSpot.setFxSwapIndicator(true);
		
		spotMt300Generator.getSequenceEField22N(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22P
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Formatted Field 22P Returned
	 * 
	 */
	@Test
	public void getSequenceEField22P_doddFrankSiteFxSwapIndicatorOn_returnField22P() {
		
		/* test value of deal prior usi */
		String dealPriorUsi = "1030444408NFO201404250018000085803908";
		
		/* expected result */
		expectedFieldContent = dealPriorUsi.substring(0, 10);
		
		organization.setDoddFrankReportingFlag(true);
		
		doddFrankReportingInfo.setDealPriorUsi(dealPriorUsi);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		tradeFxSpot.setDoddFrankReportingInfo(doddFrankReportingInfo);
		
		Field22P field22P = spotMt300Generator.getSequenceEField22P(tradeFxSpot);
		
		assertEquals(field22P.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22P
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22P Returned
	 * 
	 */
	@Test
	public void getSequenceEField22P_doddFrankSiteFxSwapIndicatorOff_emptyField22P() {
		
		organization.setDoddFrankReportingFlag(true);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22P field22P = spotMt300Generator.getSequenceEField22P(tradeFxSpot);
		
		assertTrue(field22P.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22P
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Empty Field 22P Returned
	 * 
	 */
	@Test
	public void getSequenceEField22P_notDoddFrankSiteFxSwapIndicatorOn_emptyField22P() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		
		Field22P field22P = spotMt300Generator.getSequenceEField22P(tradeFxSpot);
		
		assertTrue(field22P.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22P
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22P Returned
	 * 
	 */
	@Test
	public void getSequenceEField22P_notDoddFrankSiteFxSwapIndicatorOff_emptyField22P() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22P field22P = spotMt300Generator.getSequenceEField22P(tradeFxSpot);
		
		assertTrue(field22P.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22P
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Our Organization is Absent
	 * Expected Result: Throw Exception
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceEField22P_ourOrganizationAbsent_throwException() {
		
		tradeFxSpot.setOrganization(null);
		tradeFxSpot.setFxSwapIndicator(true);
		
		spotMt300Generator.getSequenceEField22P(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22R
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Formatted Field 22R Returned
	 * 
	 */
	@Test
	public void getSequenceEField22R_doddFrankSiteFxSwapIndicatorOn_returnField22R() {
		
		/* test value of deal prior usi */
		String dealPriorUsi = "1030444408NFO201404250018000085803908";
		
		/* expected result */
		expectedFieldContent = dealPriorUsi.substring(10);
		
		organization.setDoddFrankReportingFlag(true);
		
		doddFrankReportingInfo.setDealPriorUsi(dealPriorUsi);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		tradeFxSpot.setDoddFrankReportingInfo(doddFrankReportingInfo);
		
		Field22R field22R = spotMt300Generator.getSequenceEField22R(tradeFxSpot);
		
		assertEquals(field22R.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22R
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22R Returned
	 * 
	 */
	@Test
	public void getSequenceEField22R_doddFrankSiteFxSwapIndicatorOff_emptyField22R() {
		
		organization.setDoddFrankReportingFlag(true);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22R field22R = spotMt300Generator.getSequenceEField22R(tradeFxSpot);
		
		assertTrue(field22R.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22R
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Part of The FX Swap Package
	 * Expected Result: Empty Field 22R Returned
	 * 
	 */
	@Test
	public void getSequenceEField22R_notDoddFrankSiteFxSwapIndicatorOn_emptyField22R() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(true);
		
		Field22R field22R = spotMt300Generator.getSequenceEField22R(tradeFxSpot);
		
		assertTrue(field22R.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22R
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Not Dodd-Frank Site & The Spot Trade is Not Part of The FX Swap Package
	 * Expected Result: Empty Field 22R Returned
	 * 
	 */
	@Test
	public void getSequenceEField22R_notDoddFrankSiteFxSwapIndicatorOff_emptyField22R() {
		
		organization.setDoddFrankReportingFlag(false);
		
		tradeFxSpot.setOrganization(organization);
		tradeFxSpot.setFxSwapIndicator(false);
		
		Field22R field22R = spotMt300Generator.getSequenceEField22R(tradeFxSpot);
		
		assertTrue(field22R.isEmpty());
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 22R
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Our Organization is Absent
	 * Expected Result: Throw Exception
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceEField22R_ourOrganizationAbsent_throwException() {
		
		tradeFxSpot.setOrganization(null);
		tradeFxSpot.setFxSwapIndicator(true);
		
		spotMt300Generator.getSequenceEField22R(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 98D
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Execution Date Time Present + Positive UTC Offset
	 * Expected Result: Formatted Field 98D Returned
	 * 
	 */
	@Test
	public void getSequenceEField98D_executionDateTimePresentPositiveUtcOffset_returnField98D() {
		
		/* expected result */
		expectedFieldContent = "20140507205859/0800";
		
		/* test value of execution date time */
		Calendar executionDateTime = new GregorianCalendar();
		
		executionDateTime.set(2014, 04, 07, 20, 58, 59);
		executionDateTime.setTimeZone(TimeZone.getTimeZone("Hongkong"));
		
		doddFrankReportingInfo.setExecutionDateTime(executionDateTime);
		
		tradeFxSpot.setDoddFrankReportingInfo(doddFrankReportingInfo);
		
		Field98D field98D = spotMt300Generator.getSequenceEField98D(tradeFxSpot);
		
		assertEquals(field98D.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 98D
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Execution Date Time Present + Zero UTC Offset
	 * Expected Result: Formatted Field 98D Returned
	 * 
	 */
	@Test
	public void getSequenceEField98D_executionDateTimePresentZeroUtcOffset_returnField98D() {
		
		/* expected result */
		expectedFieldContent = "20180508045859/00";
		
		/* test value of execution date time */
		Calendar executionDateTime = new GregorianCalendar();
		
		executionDateTime.set(2018, 04, 07, 20, 58, 59);
		executionDateTime.setTimeZone(TimeZone.getTimeZone("Greenwich"));
		
		doddFrankReportingInfo.setExecutionDateTime(executionDateTime);
		
		tradeFxSpot.setDoddFrankReportingInfo(doddFrankReportingInfo);
		
		Field98D field98D = spotMt300Generator.getSequenceEField98D(tradeFxSpot);
		
		assertEquals(field98D.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 98D
	 * <P>
	 * Category: Functional + Positive
	 * Situation: Execution Date Time Present + Negative UTC Offset
	 * Expected Result: Formatted Field 98D Returned
	 * 
	 */
	public void getSequenceEField98D_executionDateTimePresentNegativeUtcOffset_returnField98D() {
		
		/* expected result */
		expectedFieldContent = "20180508085859/N0400";
		
		/* test value of execution date time */
		Calendar executionDateTime = new GregorianCalendar();
		
		executionDateTime.set(2018, 04, 07, 20, 58, 59);
		executionDateTime.setTimeZone(TimeZone.getTimeZone("America/New_York"));
		
		doddFrankReportingInfo.setExecutionDateTime(executionDateTime);
		
		tradeFxSpot.setDoddFrankReportingInfo(doddFrankReportingInfo);
		
		Field98D field98D = spotMt300Generator.getSequenceEField98D(tradeFxSpot);
		
		assertEquals(field98D.getValue(), expectedFieldContent);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 98D
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Execution Date Time Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceEField98D_executionDateTimeAbsent_throwException() {
		
		doddFrankReportingInfo.setExecutionDateTime(null);
		
		tradeFxSpot.setDoddFrankReportingInfo(doddFrankReportingInfo);
		
		spotMt300Generator.getSequenceEField98D(tradeFxSpot);
		
	}
	
	/**
	 * Test Case for MT300 Sequence E Field 98D
	 * <P>
	 * Category: Functional + Negative
	 * Situation: Dodd Frank Reporting Info Absent
	 * Expected Result: Exception Thrown
	 * 
	 */
	@Test (expected = NullPointerException.class)
	public void getSequenceEField98D_doddFrankReportingInfoAbsent_throwException() {
		
		tradeFxSpot.setDoddFrankReportingInfo(null);
		
		spotMt300Generator.getSequenceEField98D(tradeFxSpot);
		
	}
	
		
}