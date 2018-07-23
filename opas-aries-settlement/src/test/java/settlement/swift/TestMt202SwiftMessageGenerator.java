package settlement.swift;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.prowidesoftware.swift.model.field.Field;
import com.prowidesoftware.swift.model.field.Field20;
import com.prowidesoftware.swift.model.field.Field21;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field72;
import com.prowidesoftware.swift.model.mt.mt2xx.MT202;

import model.cashflow.CashFlow;
import model.cashflow.CashFlowSi;
import model.organization.Organization;
import model.party.Counterparty;
import model.trade.Currency;
import model.trade.FundTransferSystem;
import model.trade.PaymentIndicator;
import model.trade.TradeFxSpot;

public class TestMt202SwiftMessageGenerator {

	/* cash flow */
	private static CashFlow cashFlow;
	
	private static TradeFxSpot tradeFxSpot;
	
	private static Organization organization;
	
	private static Organization cptyOrganization;
	
	private static Counterparty counterparty;
	
	private String expectedFieldContent;
	
	private static Mt202SwiftMessageGenerator mt202Generator;
	
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
		
		cashFlow = new CashFlow();
		tradeFxSpot = new TradeFxSpot();
		organization = new Organization();
		counterparty = new Counterparty();
		cptyOrganization = new Organization();
		mt202Generator = new Mt202SwiftMessageGenerator();
		
	}
	
	@After 
	public void tearDown() throws Exception {
		
		cashFlow = null;
		tradeFxSpot = null;
		organization = null;
		counterparty = null;
		cptyOrganization = null;
		mt202Generator = null;
		expectedFieldContent = null;
		
	}
	
	@Test
	public void getField20_cashFlowReferencePresent_returnField20() { 
		
		String cashFlowReference = "FWDHKH8117972101";
		
		expectedFieldContent = cashFlowReference;
		
		cashFlow.setCashFlowReference(cashFlowReference);
		
		Field20 field20 = mt202Generator.getField20(cashFlow);
		
		assertEquals(field20.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField21_cashFlowReferencePresent_returnField21() { 
		
		String cashFlowReference = "FWDHKH8117972101";
		
		expectedFieldContent = cashFlowReference;
		
		cashFlow.setCashFlowReference(cashFlowReference);
		
		Field21 field21 = mt202Generator.getField21(cashFlow);
		
		assertEquals(field21.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField32A_valueDateCurrencyAmountValid_returnField32A() { 
		
		expectedFieldContent = "180530USD98765,43";
		
		Calendar valueDateCalendar = new GregorianCalendar(2018, 04, 30);
		Date valueDate = valueDateCalendar.getTime();
		
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("USD");
		cashFlowCurrency.setSettlementCurrencyCode("USD");

		BigDecimal cashFlowAmount = new BigDecimal(98765.43);
		
		cashFlow.setValueDate(valueDate);
		cashFlow.setCurrency(cashFlowCurrency);
		cashFlow.setCashFlowAmount(cashFlowAmount);
		
		Field32A field32A = mt202Generator.getField32A(cashFlow);
		
		assertEquals(field32A.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField53a_ourAccountWithThemInTheirBookPresent_returnField53B() {
		
		expectedFieldContent = "/000038865";
		
		String ourAccountWithThemInTheirBook = "000038865";
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		
		Field field53a = mt202Generator.getField53a(cashFlow, paySi);
		assertEquals(field53a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicPresentBeneficiaryBankCodePresentFtsCHIPS_returnField57A() {
		
		String expectedFieldContent = "//CP0123".concat("\r\n").concat("HXXXHKHH");
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "HXXXHKHH";
		String beneficiaryBankClearingBankCode = "0123";
		String beneficiaryBankClearingSortCode = "219994";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicPresentBeneficiarySortCodePresentFtsCHIPS_returnField57A() {
		
		String expectedFieldContent = "//CH219994".concat("\r\n").concat("HXXXHKHH");
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "HXXXHKHH";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "219994";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicPresentBeneficiarySortCodePresentFtsBlank_returnField57A() {
		
		String expectedFieldContent = "/219994".concat("\r\n").concat("HXXXHKHH");
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "HXXXHKHH";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "219994";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicPresentBeneficiarySortCodePresentFtsRTGSP_returnField57A() {
		
		String expectedFieldContent = "//RT".concat("\r\n").concat("HXXXHKHH");
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "HXXXHKHH";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "219994";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("RTGSP");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicPresentBeneficiaryBankAndSortCodeAbsentBeneficiaryAccountPresent_returnField57A() {
		
		String expectedFieldContent = "/8088188721".concat("\r\n").concat("HXXXHKHH");
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "HXXXHKHH";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicPresentBeneficiaryBankAndSortCodeAbsentBeneficiaryAccountAbsent_returnField57A() {
		
		String expectedFieldContent = "HXXXHKHH";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "HXXXHKHH";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "";
		String beneficiaryBankAccountOnIntermediary = "";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicAbsentBeneficiaryBankCodePresentFtsCHIPS_returnField57D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "";
		String beneficiaryBankClearingBankCode = "0123";
		String beneficiaryBankClearingSortCode = "219994";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		String expectedFieldContent = "//CP0123".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setSettlementBankNameLine1(addressLine1_leng35);
		paySi.setSettlementBankNameLine2(addressLine2_leng35);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicAbsentBeneficiarySortCodePresentFtsCHIPS_returnField57D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "219994";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		String expectedFieldContent = "//CH219994".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setSettlementBankNameLine1(addressLine1_leng35);
		paySi.setSettlementBankNameLine2(addressLine2_leng35);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicAbsentBeneficiarySortCodePresentFtsBlank_returnField57D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "219994";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("");
		
		String expectedFieldContent = "/219994".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setSettlementBankNameLine1(addressLine1_leng35);
		paySi.setSettlementBankNameLine2(addressLine2_leng35);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicAbsentBeneficiarySortCodePresentFtsRTGSP_returnField57D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "219994";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("RTGSP");
		
		String expectedFieldContent = "//RT".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setSettlementBankNameLine1(addressLine1_leng35);
		paySi.setSettlementBankNameLine2(addressLine2_leng35);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicAbsentBeneficiaryBankAndSortCodeAbsentBeneficiaryAccountPresent_returnField57D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "";
		String beneficiaryBankAccountOnIntermediary = "8088188721";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		String expectedFieldContent = "/8088188721".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setSettlementBankNameLine1(addressLine1_leng35);
		paySi.setSettlementBankNameLine2(addressLine2_leng35);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicAbsentBeneficiaryBankAndSortCodeAbsentBeneficiaryAccountAbsent_returnField57D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String beneficiaryBankSwiftCode = "";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "";
		String beneficiaryBankAccountOnIntermediary = "";
		String intermediaryBankClearingBankCode = "";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");

		String expectedFieldContent = addressLine1_leng35.concat("\r\n").concat(addressLine2_leng35);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setSettlementBankNameLine1(addressLine1_leng35);
		paySi.setSettlementBankNameLine2(addressLine2_leng35);
		paySi.setFts(fts);
		
		Field field57a = mt202Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField58a_cptySwiftBicPresentIbanPresent_returnField58A() {
		
		String expectedFieldContent = "/BE68539007547034".concat("\r\n").concat("CIXXHKHHH");
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String ibanNumber = "BE68539007547034";
		String counterpartyClearingSortCode = "219994";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField58a_cptySwiftBicPresentIbanAbsentCptySortCodePresentFtsChips_returnField58A() {
		
		String expectedFieldContent = "//CH219994".concat("\r\n").concat("CIXXHKHHH");
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String ibanNumber = "";
		String counterpartyClearingSortCode = "219994";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField58a_cptySwiftBicPresentIbanAbsentCptySortCodePresentFtsBlank_returnField58A() {
		
		String expectedFieldContent = "/219994".concat("\r\n").concat("CIXXHKHHH");
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String ibanNumber = "";
		String counterpartyClearingSortCode = "219994";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
		//System.out.println("Field 58A(cptySwiftBicPresentIbanAbsentCptySortCodePresentFtsBlank): ");
		//System.out.println(field58a.getValue());
		
	}
	
	@Test
	public void getField58a_cptySwiftBicPresentIbanAbsentCptySortCodePresentFtsRTGSP_returnField58A() {
		
		String expectedFieldContent = "//RT".concat("\r\n").concat("CIXXHKHHH");
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String ibanNumber = "";
		String counterpartyClearingSortCode = "219994";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("RTGSP");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
		//System.out.println("Field 58A(cptySwiftBicPresentIbanAbsentCptySortCodePresentFtsRTGSP): ");
		//System.out.println(field58a.getValue());
		
	}
	
	@Test
	public void getField58a_cptySwiftBicPresentIbanAbsentCptySortCodeAbsentCptyAccountPresent_returnField58A() {
		
		String expectedFieldContent = "/8088188721".concat("\r\n").concat("CIXXHKHHH");
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String counterpartySwiftBic = "CIXXHKHHH";
		String ibanNumber = "";
		String counterpartyClearingSortCode = "";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
		//System.out.println("Field 58A(cptySwiftBicPresentIbanAbsentCptySortCodeAbsentCptyAccountPresent): ");
		//System.out.println(field58a.getValue());
		
	}
	
	@Test
	public void getField58a_cptySwiftBicAabsentIbanPresent_returnField58D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String countryDescription = "Hong Kong";
		String counterpartySwiftBic = "";
		String ibanNumber = "BE68539007547034";
		String counterpartyClearingSortCode = "219994";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		String expectedFieldContent = "/BE68539007547034".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35).concat("\r\n").
										concat(addressLine3_leng35).concat("\r\n").
										concat(countryDescription);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField58a_cptySwiftBicAbsentIbanAbsentCptySortCodePresentFtsChips_returnField58D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String countryDescription = "Hong Kong";
		String counterpartySwiftBic = "";
		String ibanNumber = "";
		String counterpartyClearingSortCode = "219994";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		String expectedFieldContent = "//CH219994".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35).concat("\r\n").
										concat(addressLine3_leng35).concat("\r\n").
										concat(countryDescription);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField58a_cptySwiftBicAbsentIbanAbsentCptySortCodePresentFtsBlank_returnField58D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String countryDescription = "Hong Kong";
		String counterpartySwiftBic = "";
		String ibanNumber = "";
		String counterpartyClearingSortCode = "219994";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("");
		
		String expectedFieldContent = "/219994".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35).concat("\r\n").
										concat(addressLine3_leng35).concat("\r\n").
										concat(countryDescription);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
		//System.out.println("Field 58A(cptySwiftBicPresentIbanAbsentCptySortCodePresentFtsBlank): ");
		//System.out.println(field58a.getValue());
		
	}
	
	@Test
	public void getField58a_cptySwiftBicAbsentIbanAbsentCptySortCodePresentFtsRTGSP_returnField58D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String countryDescription = "Hong Kong";
		String counterpartySwiftBic = "";
		String ibanNumber = "";
		String counterpartyClearingSortCode = "219994";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("RTGSP");
		
		String expectedFieldContent = "//RT".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35).concat("\r\n").
										concat(addressLine3_leng35).concat("\r\n").
										concat(countryDescription);
		
		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
		//System.out.println("Field 58A(cptySwiftBicPresentIbanAbsentCptySortCodePresentFtsRTGSP): ");
		//System.out.println(field58a.getValue());
		
	}
	
	@Test
	public void getField58a_cptySwiftBicAbsentIbanAbsentCptySortCodeAbsentCptyAccountPresent_returnField58D() {
		
		/* test values for address lines */
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		
		String countryCode = "HK";
		String countryDescription = "Hong Kong";
		String counterpartySwiftBic = "";
		String ibanNumber = "";
		String counterpartyClearingSortCode = "";
		String counterpartySettlementAccount = "8088188721";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		String expectedFieldContent = "/8088188721".concat("\r\n").
										concat(addressLine1_leng35).concat("\r\n").
										concat(addressLine2_leng35).concat("\r\n").
										concat(addressLine3_leng35).concat("\r\n").
										concat(countryDescription);

		organization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		CashFlowSi paySi = new CashFlowSi();
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setFts(fts);
		
		Field field58a = mt202Generator.getField58a(cashFlow, paySi);
		
		assertEquals(field58a.getValue(), expectedFieldContent);
		
		//System.out.println("Field 58A(cptySwiftBicPresentIbanAbsentCptySortCodeAbsentCptyAccountPresent): ");
		//System.out.println(field58a.getValue());
		
	}
	
	@Test
	public void getField72_senderToReceiverNarrativesPresent_returnField72() {
		
		/* test values of cash flow si */
		String senderToReceiverNarrativeLine1 = "++ Sender To Receiver Info Line 1++";
		String senderToReceiverNarrativeLine2 = "++ Sender To Receiver Info Line 2++";
		String senderToReceiverNarrativeLine3 = "++ Sender To Receiver Info Line 3++";
		String senderToReceiverNarrativeLine4 = "++ Sender To Receiver Info Line 4++";
		String senderToReceiverNarrativeLine5 = "++ Sender To Receiver Info Line 5++";
		String senderToReceiverNarrativeLine6 = "++ Sender To Receiver Info Line 6++";
		
		/* expected test results */
		String expectedFieldContent = senderToReceiverNarrativeLine1.concat("\r\n").
										concat(senderToReceiverNarrativeLine2).concat("\r\n").
										concat(senderToReceiverNarrativeLine3).concat("\r\n").
										concat(senderToReceiverNarrativeLine4).concat("\r\n").
										concat(senderToReceiverNarrativeLine5).concat("\r\n").
										concat(senderToReceiverNarrativeLine6);
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setSenderToReceiverNarrativeLine1(senderToReceiverNarrativeLine1);
		paySi.setSenderToReceiverNarrativeLine2(senderToReceiverNarrativeLine2);
		paySi.setSenderToReceiverNarrativeLine3(senderToReceiverNarrativeLine3);
		paySi.setSenderToReceiverNarrativeLine4(senderToReceiverNarrativeLine4);
		paySi.setSenderToReceiverNarrativeLine5(senderToReceiverNarrativeLine5);
		paySi.setSenderToReceiverNarrativeLine6(senderToReceiverNarrativeLine6);
		
		Field72 field72 = mt202Generator.getField72(cashFlow, paySi);
		
		assertEquals(field72.getValue(), expectedFieldContent);
		
	}	
	
	@Test
	public void generate_case1BeneficiaryBankIntermediaryBankPresentFtsCHIP_returnMt202() {
		
		MT202 mt202 = new MT202();
		
		/* ** Counterparty Details ** */
		
		String countryCode = "HK";
		//String countryDescription = "Hong Kong";
		String branchSwiftBic = "HXXCHKH0";
		String counterpartySwiftBic = "BKCHHKH0";
		String messageUserReference = "UH1814500483";
		
		organization.setCountryCode(countryCode);
		organization.setBicCode(branchSwiftBic);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		/* ** Cash Flow ** */
		
		String cashFlowReference = "HKHFWD7236950601";

		Calendar valueDateCalendar = new GregorianCalendar(2018, 04, 29);
		Date valueDate = valueDateCalendar.getTime();
		
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("USD");
		cashFlowCurrency.setSettlementCurrencyCode("USD");

		BigDecimal cashFlowAmount = new BigDecimal(20000000);
		
		cashFlow.setCashFlowReference(cashFlowReference);
		cashFlow.setValueDate(valueDate);
		cashFlow.setCurrency(cashFlowCurrency);
		cashFlow.setCashFlowAmount(cashFlowAmount);
		cashFlow.setMessageUserReference(messageUserReference);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		/* ** Cash Flow SI ** */
		
		CashFlowSi paySi = new CashFlowSi();
		
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		/* Our Pay Details */
		String ourSettlementBankAcronym = "MMBKNYK";
		String ourSettlementSwiftBic = "MRMDUS30";
		String ourAccountWithThemInTheirBook = "000044407";
		
		String cptySettlementBankAcronym = "BCHKHKH";
		
		/* Intermediary Bank Details */
		String intermediaryBankNameLine1 = "BANK OF CHINA";
		String intermediaryBankNameLine2 = "NEW YORK,NY";
		String intermediaryBankClearingBankCode = "0326";
		String intermediaryBankClearingSortCode = "";
		String intermediaryBankSwiftCode = "BKCHUS30";
		
		/* Beneficiary Bank Details */
		String beneficiaryBankNameLine1 = "BANK OF CHINA (HONG KONG) LIMITED";
		String beneficiaryBankNameLine2 = "HONG KONG";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankSwiftCode = "BKCHHKH0";
		String beneficiaryBankClearingSortCode = "101779";
		String beneficiaryBankAccountOnIntermediary = "01002285";

		/* Beneficiary Counterparty Details */
		String ibanNumber = "";
		String counterpartySettlementAccount = "01287560122414";
		String counterpartyClearingSortCode = "";
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setFts(fts);
		
		/* Our Pay Details - Set Values */
		paySi.setOurSettlementBankAcronym(ourSettlementBankAcronym);
		paySi.setOurSettlementSwiftBicCode(ourSettlementSwiftBic);
		paySi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		
		paySi.setCptySettlementBankAcronym(cptySettlementBankAcronym);
		
		/* Intermediary Bank Details - Set Values */
		paySi.setIntermediaryBankNameLine1(intermediaryBankNameLine1);
		paySi.setIntermediaryBankNameLine2(intermediaryBankNameLine2);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setIntermediaryBankClearingSortCode(intermediaryBankClearingSortCode);
		paySi.setIntermediaryBankSwiftBicCode(intermediaryBankSwiftCode);
		
		/* Beneficiary Bank Details - Set Values */
		paySi.setSettlementBankNameLine1(beneficiaryBankNameLine1);
		paySi.setSettlementBankNameLine2(beneficiaryBankNameLine2);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		
		/* Beneficiary Counterparty Details - Set Values */
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		
		mt202 = mt202Generator.generate(cashFlow, paySi);
		
		System.out.println(mt202.message());
		
	}
	
	@Test
	public void generate_case2BeneficiaryBankPresentIntermediaryBankAbsentFtsBlank_returnMt202() {
		
		MT202 mt202 = new MT202();
		
		/* ** Counterparty Details ** */
		
		String countryCode = "HK";
		//String countryDescription = "Hong Kong";
		String branchSwiftBic = "HXXCHKH0";
		String counterpartySwiftBic = "HXXCSGS0";
		String messageUserReference = "UH1814512641";
		
		organization.setCountryCode(countryCode);
		organization.setBicCode(branchSwiftBic);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		/* ** Cash Flow ** */
		
		String cashFlowReference = "SPTHKH8082997403";

		Calendar valueDateCalendar = new GregorianCalendar(2018, 04, 28);
		Date valueDate = valueDateCalendar.getTime();
		
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("JPY");
		cashFlowCurrency.setSettlementCurrencyCode("JPY");

		BigDecimal cashFlowAmount = new BigDecimal(600000000);
		
		cashFlow.setCashFlowReference(cashFlowReference);
		cashFlow.setValueDate(valueDate);
		cashFlow.setCurrency(cashFlowCurrency);
		cashFlow.setCashFlowAmount(cashFlowAmount);
		cashFlow.setMessageUserReference(messageUserReference);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		/* ** Cash Flow SI ** */
		
		CashFlowSi paySi = new CashFlowSi();
		
		FundTransferSystem fts = new FundTransferSystem("");
		
		/* Our Pay Details */
		String ourSettlementBankAcronym = "HXXCTKY";
		String ourSettlementSwiftBic = "HXXCJPJ0";
		String ourAccountWithThemInTheirBook = "009-105461-092";
		
		String cptySettlementBankAcronym = "HXXCTKY";
		
		/* Intermediary Bank Details */
		String intermediaryBankNameLine1 = "";
		String intermediaryBankNameLine2 = "";
		String intermediaryBankClearingBankCode = "";
		String intermediaryBankClearingSortCode = "";
		String intermediaryBankSwiftCode = "";
		
		/* Beneficiary Bank Details */
		String beneficiaryBankNameLine1 = "THE HXXGKXXG AND SHANGHAI BKG CORP";
		String beneficiaryBankNameLine2 = "LTD TOKYO";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankSwiftCode = "";
		String beneficiaryBankClearingSortCode = "";
		String beneficiaryBankAccountOnIntermediary = "";

		/* Beneficiary Counterparty Details */
		String ibanNumber = "";
		String counterpartySettlementAccount = "009105685091";
		String counterpartyClearingSortCode = "";
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setFts(fts);
		
		/* Our Pay Details - Set Values */
		paySi.setOurSettlementBankAcronym(ourSettlementBankAcronym);
		paySi.setOurSettlementSwiftBicCode(ourSettlementSwiftBic);
		paySi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		
		paySi.setCptySettlementBankAcronym(cptySettlementBankAcronym);
		
		/* Intermediary Bank Details - Set Values */
		paySi.setIntermediaryBankNameLine1(intermediaryBankNameLine1);
		paySi.setIntermediaryBankNameLine2(intermediaryBankNameLine2);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setIntermediaryBankClearingSortCode(intermediaryBankClearingSortCode);
		paySi.setIntermediaryBankSwiftBicCode(intermediaryBankSwiftCode);
		
		/* Beneficiary Bank Details - Set Values */
		paySi.setSettlementBankNameLine1(beneficiaryBankNameLine1);
		paySi.setSettlementBankNameLine2(beneficiaryBankNameLine2);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		
		/* Beneficiary Counterparty Details - Set Values */
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		
		mt202 = mt202Generator.generate(cashFlow, paySi);
		
		System.out.println(mt202.message());
		
	}
	
	@Test
	public void generate_case3_JP_SPT172649975_returnMt202() {
		
		MT202 mt202 = new MT202();
		
		/* ** Counterparty Details ** */
		
		String countryCode = "JP";
		//String countryDescription = "Hong Kong";
		String branchSwiftBic = "HSBCJPJ0TRS";
		String counterpartySwiftBic = "SCBLSGS0";
		String messageUserReference = "TJ1726400739";
		
		organization.setCountryCode(countryCode);
		organization.setBicCode(branchSwiftBic);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		
		counterparty.setOrganization(cptyOrganization);
		
		tradeFxSpot.setCounterparty(counterparty);
		
		/* ** Cash Flow ** */
		
		String cashFlowReference = "SPTTRJ7264997501";

		Calendar valueDateCalendar = new GregorianCalendar(2017, 8, 21);
		Date valueDate = valueDateCalendar.getTime();
		
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("SGD");
		cashFlowCurrency.setSettlementCurrencyCode("SGD");

		BigDecimal cashFlowAmount = new BigDecimal(3891734.81);
		
		cashFlow.setCashFlowReference(cashFlowReference);
		cashFlow.setValueDate(valueDate);
		cashFlow.setCurrency(cashFlowCurrency);
		cashFlow.setCashFlowAmount(cashFlowAmount);
		cashFlow.setMessageUserReference(messageUserReference);
		
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		/* ** Cash Flow SI ** */
		
		CashFlowSi paySi = new CashFlowSi();
		
		FundTransferSystem fts = new FundTransferSystem("");
		
		/* Our Pay Details */
		String ourSettlementBankAcronym = "HSBCSGH";
		String ourSettlementSwiftBic = "HSBCSGS0";
		String ourAccountWithThemInTheirBook = "141-311936-001";
		
		String cptySettlementBankAcronym = "STCHSGH";
		
		/* Intermediary Bank Details */
		String intermediaryBankNameLine1 = "";
		String intermediaryBankNameLine2 = "";
		String intermediaryBankClearingBankCode = "";
		String intermediaryBankClearingSortCode = "";
		String intermediaryBankSwiftCode = "";
		
		/* Beneficiary Bank Details */
		String beneficiaryBankNameLine1 = "STCHSGH";
		String beneficiaryBankNameLine2 = "";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankSwiftCode = "";
		String beneficiaryBankClearingSortCode = "";
		String beneficiaryBankAccountOnIntermediary = "";

		/* Beneficiary Counterparty Details */
		String ibanNumber = "";
		String counterpartySettlementAccount = "";
		String counterpartyClearingSortCode = "";
		
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setFts(fts);
		
		/* Our Pay Details - Set Values */
		paySi.setOurSettlementBankAcronym(ourSettlementBankAcronym);
		paySi.setOurSettlementSwiftBicCode(ourSettlementSwiftBic);
		paySi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		
		paySi.setCptySettlementBankAcronym(cptySettlementBankAcronym);
		
		/* Intermediary Bank Details - Set Values */
		paySi.setIntermediaryBankNameLine1(intermediaryBankNameLine1);
		paySi.setIntermediaryBankNameLine2(intermediaryBankNameLine2);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setIntermediaryBankClearingSortCode(intermediaryBankClearingSortCode);
		paySi.setIntermediaryBankSwiftBicCode(intermediaryBankSwiftCode);
		
		/* Beneficiary Bank Details - Set Values */
		paySi.setSettlementBankNameLine1(beneficiaryBankNameLine1);
		paySi.setSettlementBankNameLine2(beneficiaryBankNameLine2);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		
		/* Beneficiary Counterparty Details - Set Values */
		paySi.setIbanNumber(ibanNumber);
		paySi.setCounterpartyAccountOnSettlementBank(counterpartySettlementAccount);
		paySi.setCounterpartyClearingSortCode(counterpartyClearingSortCode);
		
		mt202 = mt202Generator.generate(cashFlow, paySi);
		
		System.out.println(mt202.message());
		
	}
	
	
}
