package settlement.swift;

import static org.junit.Assert.*;

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
import com.prowidesoftware.swift.model.field.Field23B;
import com.prowidesoftware.swift.model.field.Field23E;
import com.prowidesoftware.swift.model.field.Field32A;
import com.prowidesoftware.swift.model.field.Field33B;
import com.prowidesoftware.swift.model.field.Field50A;
import com.prowidesoftware.swift.model.field.Field50F;
import com.prowidesoftware.swift.model.field.Field50K;
import com.prowidesoftware.swift.model.field.Field56A;
import com.prowidesoftware.swift.model.field.Field56C;
import com.prowidesoftware.swift.model.field.Field56D;
import com.prowidesoftware.swift.model.field.Field59;
import com.prowidesoftware.swift.model.field.Field59A;
import com.prowidesoftware.swift.model.field.Field59F;
import com.prowidesoftware.swift.model.field.Field71A;
import com.prowidesoftware.swift.model.field.Field71F;
import com.prowidesoftware.swift.model.field.Field72;
import com.prowidesoftware.swift.model.mt.mt1xx.MT103;

import model.cashflow.CashFlow;
import model.cashflow.CashFlowSi;
import model.cashflow.ChargesForIndicator;
import model.cashflow.PayWireType;
import model.organization.Organization;
import model.party.Counterparty;
import model.party.ERDSLegalEntity;
import model.product.Product;
import model.product.ProductCategory;
import model.trade.Currency;
import model.trade.FundTransferSystem;
import model.trade.PaymentIndicator;
import model.trade.TradeFxForward;
import model.trade.TradeFxSpot;

public class TestMt103SwiftMessageGenerator {
	
	/* cash flow */
	private static CashFlow cashFlow;
	
	private static TradeFxSpot tradeFxSpot;
	
	private static TradeFxForward tradeFxForward;
	
	private static Organization organization;
	
	private static Organization cptyOrganization;
	
	private static Counterparty counterparty;
	
	private static Product product;
	
	private String expectedFieldContent;
	
	private static Mt103SwiftMessageGenerator mt103Generator;
	
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
		tradeFxForward = new TradeFxForward();
		organization = new Organization();
		counterparty = new Counterparty();
		cptyOrganization = new Organization();
		product = new Product();
		mt103Generator = new Mt103SwiftMessageGenerator();
		
	}
	
	@After 
	public void tearDown() throws Exception {
		
		cashFlow = null;
		tradeFxSpot = null;
		tradeFxForward = null;
		organization = null;
		counterparty = null;
		cptyOrganization = null;
		product = null;
		mt103Generator = null;
		expectedFieldContent = null;
		
	}
	
	@Test
	public void getField20_cashFlowReferencePresent_returnField20() { 
		
		/* test values of cash flow */
		String cashFlowReference = "SPTHKH8145767708";
		
		/* expected test results */
		expectedFieldContent = cashFlowReference;
		
		/* setup cash flow */
		cashFlow.setCashFlowReference(cashFlowReference);
		
		Field20 field20 = mt103Generator.getField20(cashFlow);
		
		assertEquals(field20.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField23B_returnField23B() { 
		
		/* expected test results */
		expectedFieldContent = "CRED";
		
		/* setup cash cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		
		Field23B field23B = mt103Generator.getField23B(cashFlow, paySi);
		
		assertEquals(field23B.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField23E_returnField23E() { 
		
		/* expected test results */
		expectedFieldContent = "CORT";
		
		/* setup cash cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		
		Field23E field23E = mt103Generator.getField23E(cashFlow, paySi);
		
		assertEquals(field23E.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField32A_valueDateCurrencyAmountValid_returnField32A() { 
		
		/* expected test results */
		expectedFieldContent = "180525USD8243333,25";
		
		/* test values of cash flow */
		Calendar valueDateCalendar = new GregorianCalendar(2018, 04, 25);
		Date valueDate = valueDateCalendar.getTime();
		
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("USD");
		cashFlowCurrency.setSettlementCurrencyCode("USD");

		BigDecimal cashFlowAmount = new BigDecimal(8243333.25);
		
		/* setup cash flow */
		cashFlow.setValueDate(valueDate);
		cashFlow.setCurrency(cashFlowCurrency);
		cashFlow.setCashFlowAmount(cashFlowAmount);
		
		Field32A field32A = mt103Generator.getField32A(cashFlow);
		
		assertEquals(field32A.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField33B_SwiftBicCountryNotOnListChargesForBeneficiaryBank_returnField33B() { 
	
		/* expected test results */
		expectedFieldContent = "USD8243333,25";
		
		/* test values of cash flow */
		String branchSwiftBic = "HXXCHKH0";
		
		Calendar valueDateCalendar = new GregorianCalendar(2018, 04, 25);
		Date valueDate = valueDateCalendar.getTime();
		
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("USD");
		cashFlowCurrency.setSettlementCurrencyCode("USD");
		
		BigDecimal cashFlowAmount = new BigDecimal(8243333.25);
		
		/* test values of cash flow si */
		String ourSettlementSwiftBic = "MRMDUS30";
		
		/* setup cash flow */
		organization.setBicCode(branchSwiftBic);
		cashFlow.setOrganization(organization);
		cashFlow.setValueDate(valueDate);
		cashFlow.setCurrency(cashFlowCurrency);
		cashFlow.setCashFlowAmount(cashFlowAmount);
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setOurSettlementSwiftBicCode(ourSettlementSwiftBic);
		paySi.setChargesForIndicator(ChargesForIndicator.BEN);
		
		Field33B field33B = mt103Generator.getField33B(cashFlow, paySi);
		
		assertEquals(field33B.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField71A_ChargesForBeneficiaryBank_returnField71A() { 
	
		/* expected test results */
		expectedFieldContent = "BEN";
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setChargesForIndicator(ChargesForIndicator.BEN);
		
		Field71A field71A = mt103Generator.getField71A(paySi);
		
		assertEquals(field71A.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField71F_ChargesForBeneficiaryBank_returnField71F() { 
	
		/* expected test results */
		expectedFieldContent = "USD0,";
		
		/* test values of cash flow */
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("USD");
		cashFlowCurrency.setSettlementCurrencyCode("USD");
		
		/* setup cash flow */
		cashFlow.setCurrency(cashFlowCurrency);
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setChargesForIndicator(ChargesForIndicator.BEN);
		
		Field71F field71F = mt103Generator.getField71F(cashFlow, paySi);
		
		assertEquals(field71F.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField50a_notGSCompCashFlowSiOurBankAsOrderingCustomerSwiftCompERDSInfoPresent_returnField50F() {
		
		/* expected test results */
		expectedFieldContent = "/0000000000001729672".concat("\r\n").
									concat("1/").concat("ATLAS COPCO CHINA/HONG KONG").concat("\r\n").
									concat("1/").concat("LIMITED").concat("\r\n").
									concat("2/").concat("16TH FLOOR, TOWER 2, UNIT 1601").concat("\r\n").
									concat("3/").concat("HK/HONG KONG");
		
		/* test values of counterparty legal entity */
		String cptyGridId = "0000000000001729672";
		boolean erdsSwiftCompliantFlag = true;
		String cptyLegalEntityNameLine1 = "ATLAS COPCO CHINA/HONG KONG";
		String cptyLegalEntityNameLine2 = "LIMITED";
		String cptyLegalEntityNameLine3 = "";
		String cptyLegalEntityAddressLine1 = "16TH FLOOR, TOWER 2, UNIT 1601";
		String cptyLegalEntityAddressLine2 = "GRAND CENTRAL PLAZA, SHA TIN, NT";
		String cptyLegalEntityCountryStateCity = "3/HK/HONG KONG";
		
		/* test values of counterparty */
		String counterpartyAcronym = "ACCHHKH";
		String counterpartySwiftBic = "";
		String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		String cptyCountryOfResidence = "HK";
	
		/* test values of cash flow */
		String groupMemberAbbreviation = "HSBC";
		String branchMneumonic = "HKH";
		String branchSwiftBic = "HSBCHKH0";
		String cashFlowReference = "SPTHKH8145767708";
		boolean EURegulationFlag = true;
		
		/* test values cash flow si */
		PayWireType payWireType = PayWireType.GENERATE;
		boolean globalStandardCompliantFlag = true;

		/* test values of trade */
		boolean ourBankAsOrderingCustomerFlag = false;
		
		/* setup counterparty legal entity */
		ERDSLegalEntity legalEntity = new ERDSLegalEntity();
		legalEntity.setGridId(cptyGridId);
		legalEntity.setSwiftCompliantFlag(erdsSwiftCompliantFlag);
		legalEntity.setLegalEntityNameLine1(cptyLegalEntityNameLine1);
		legalEntity.setLegalEntityNameLine2(cptyLegalEntityNameLine2);
		legalEntity.setLegalEntityNameLine3(cptyLegalEntityNameLine3);
		legalEntity.setLegalEntityAddressLine1(cptyLegalEntityAddressLine1);
		legalEntity.setLegalEntityAddressLine2(cptyLegalEntityAddressLine2);
		legalEntity.setCountryStateCity(cptyLegalEntityCountryStateCity);
		
		/* setup counterparty */
		cptyOrganization.setBicCode(counterpartySwiftBic);
		counterparty.setOrganization(cptyOrganization);
		counterparty.setAcronym(counterpartyAcronym);
		counterparty.setCountryOfResidence(cptyCountryOfResidence);
		counterparty.setErdsLegalEntity(legalEntity);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setGlobalStandardCompliantFlag(globalStandardCompliantFlag);
		paySi.setPayWireType(payWireType);
		
		/* setup trade */
		product.setOurBankAsOrderingCustomer(ourBankAsOrderingCustomerFlag);
		tradeFxSpot.setProduct(product);
		tradeFxSpot.setCounterparty(counterparty);
		
		/* setup cash flow */
		organization.setGroupMemberAbbreviation(groupMemberAbbreviation);
		organization.setBranchMneumonic(branchMneumonic);
		organization.setBicCode(branchSwiftBic);
		organization.setEURegulationFlag(EURegulationFlag);
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		cashFlow.setCashFlowReference(cashFlowReference);
		
		Field field50a = mt103Generator.getField50a(cashFlow, paySi);
	
		assertFalse(field50a instanceof Field50A);
		assertTrue(field50a instanceof Field50F);
		assertFalse(field50a instanceof Field50K);
		assertEquals(field50a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField52a_GSCompCashFlowSiOurBankAsOrderingCustomer_returnField52A() {
		
		/* expected test results */
		expectedFieldContent = "HSBCHKH0";
		
		/* test values of cash flow si */
		boolean globalStandardCompliantFlag = true;
		PayWireType payWireType = PayWireType.GENERATE;
		
		/* test values of cash flow */
		String branchSwiftBic = "HSBCHKH0";
		
		/* test values of trade */
		boolean ourBankAsOrderingCustomer = false;
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setGlobalStandardCompliantFlag(globalStandardCompliantFlag);
		paySi.setPayWireType(payWireType);
		
		/* setup trade */
		product.setOurBankAsOrderingCustomer(ourBankAsOrderingCustomer);
		tradeFxSpot.setProduct(product);
		
		/* setup cash flow */
		organization.setBicCode(branchSwiftBic);
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		Field field52a = mt103Generator.getField52a(cashFlow, paySi);
		
		assertEquals(field52a.getValue(), expectedFieldContent);
		
	}
	
	
	
	@Test
	public void getField53a_ourAccountWithThemInTheirBookPresent_returnField53B() {
		
		/* expected test results */
		expectedFieldContent = "/000044407";
		
		/* test values of cash flow si */
		String ourAccountWithThemInTheirBook = "000044407";
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		
		Field field53a = mt103Generator.getField53a(cashFlow, paySi);
		
		assertEquals(field53a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField56a_intermediarySwiftPresentIntermediaryBankCodePresentFtsCHIPS_returnField56A() {
		
		/* expected test results */
		expectedFieldContent = "//CP0001".concat("\r\n").
									concat("MRMDUS30");
		
		/* test values of cash flow si */
		String intermediaryBankSwiftBicCode = "MRMDUS30";
		String intermediaryBankNameLine1 = "ABC";
		String intermediaryBankNameLine2 = "DEF";
		String intermediaryBankClearingBankCode = "0001";
		String intermediaryBankClearingSortCode = "";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setFts(fts);
		paySi.setIntermediaryBankSwiftBicCode(intermediaryBankSwiftBicCode);
		paySi.setIntermediaryBankNameLine1(intermediaryBankNameLine1);
		paySi.setIntermediaryBankNameLine2(intermediaryBankNameLine2);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setIntermediaryBankClearingSortCode(intermediaryBankClearingSortCode);
		
		Field field56a = mt103Generator.getField56a(cashFlow, paySi);
		
		assertTrue(field56a instanceof Field56A);
		assertFalse(field56a instanceof Field56C);
		assertFalse(field56a instanceof Field56D);
		assertEquals(field56a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField57a_beneficiarySwiftBicPresentBeneficiaryBankAndSortCodeAbsentBeneficiaryAccountAbsent_returnField57A() {
		
		/* expected test results */
		String expectedFieldContent = "CITIUS30";
		
		/* test values of cash flow */
		String countryCode = "HK";
		
		/* test values of counterparty */
		String counterpartySwiftBic = "";
		
		/* test values of cash flow si */
		String beneficiaryBankSwiftCode = "CITIUS30";
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "";
		String beneficiaryBankAccountOnIntermediary = "";
		String intermediaryBankClearingBankCode = "";
		
		/* setup counterparty */
		cptyOrganization.setBicCode(counterpartySwiftBic);
		counterparty.setOrganization(cptyOrganization);
		
		/* setup trade */
		tradeFxSpot.setCounterparty(counterparty);
		
		/* setup cash flow */
		organization.setCountryCode(countryCode);
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxSpot);
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setFts(fts);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		
		Field field57a = mt103Generator.getField57a(cashFlow, paySi);
		
		assertEquals(field57a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField59a_erdsInfoPresentGSCompCashFlowSiCptySortCodeIbanAbsentCptySettAccountPresent_returnField59F() {
		
		//String counterpartyAcronym = "ACCHHKH";
		//String counterpartySwiftBic = "";
		//String addressLine1_leng35 = "++Address Line 1 + Address Line 1++";
		//String addressLine2_leng35 = "++Address Line 2 + Address Line 2++";
		//String addressLine3_leng35 = "++Address Line 3 + Address Line 3++";
		//String addressLine4_leng35 = "++Address Line 4 + Address Line 4++";
		//String groupMemberAbbreviation = "HSBC";
		//String branchMneumonic = "HKH";
		
		//String cashFlowReference = "SPTHKH8145767708";
		//String cptyGridId = "0000000000001729672";
		//boolean EURegulationFlag = true;
		
		//organization.setGroupMemberAbbreviation(groupMemberAbbreviation);
		//organization.setBranchMneumonic(branchMneumonic);
		//organization.setBicCode(branchSwiftBic);
		//organization.setEURegulationFlag(EURegulationFlag);
		
		/* expected test results */
		expectedFieldContent = "/30444999".concat("\r\n").
								concat("1/").concat("ATLAS COPCO CHINA/HONG KONG").concat("\r\n").
								concat("1/").concat("LIMITED").concat("\r\n").
								concat("2/").concat("16TH FLOOR, TOWER 2, UNIT 1601").concat("\r\n").
								concat("3/").concat("HK/HONG KONG");
		
		/* test values of legal entities */
		String cptyLegalEntityNameLine1 = "ATLAS COPCO CHINA/HONG KONG";
		String cptyLegalEntityNameLine2 = "LIMITED";
		String cptyLegalEntityNameLine3 = "";
		String cptyLegalEntityAddressLine1 = "16TH FLOOR, TOWER 2, UNIT 1601";
		String cptyLegalEntityAddressLine2 = "GRAND CENTRAL PLAZA, SHA TIN, NT";
		String cptyLegalEntityCountryStateCity = "3/HK/HONG KONG";
		boolean erdsSwiftCompliantFlag = true;
		
		/* test values of counterparty */
		String cptyCountryOfResidence = "HK";
		
		/* test values of cash flow si */
		boolean globalStandardCompliantFlag = true;
		PayWireType payWireType = PayWireType.GENERATE;
		FundTransferSystem fts = new FundTransferSystem("CHIPS");
		String cptyClearingSortCode = "";
		String cptySettlementAccount = "30444999";
		//String ourAccountWithThemInTheirBook = "000044407";
		
		/* test values of product */
		boolean ourBankAsOrderingCustomerFlag = false;
		
		/* setup counterparty legal entity */
		ERDSLegalEntity legalEntity = new ERDSLegalEntity();
		legalEntity.setLegalEntityNameLine1(cptyLegalEntityNameLine1);
		legalEntity.setLegalEntityNameLine2(cptyLegalEntityNameLine2);
		legalEntity.setLegalEntityNameLine3(cptyLegalEntityNameLine3);
		legalEntity.setLegalEntityAddressLine1(cptyLegalEntityAddressLine1);
		legalEntity.setLegalEntityAddressLine2(cptyLegalEntityAddressLine2);
		legalEntity.setCountryStateCity(cptyLegalEntityCountryStateCity);
		legalEntity.setSwiftCompliantFlag(erdsSwiftCompliantFlag);
		
		/* setup counterparty */
		counterparty.setCountryOfResidence(cptyCountryOfResidence);
		counterparty.setErdsLegalEntity(legalEntity);
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setFts(fts);
		paySi.setCounterpartyClearingSortCode(cptyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(cptySettlementAccount);
		paySi.setGlobalStandardCompliantFlag(globalStandardCompliantFlag);
		paySi.setPayWireType(payWireType);
		
		/* setup product */
		product.setOurBankAsOrderingCustomer(ourBankAsOrderingCustomerFlag);
		
		/* setp trade */
		tradeFxSpot.setProduct(product);
		tradeFxSpot.setCounterparty(counterparty);
		
		/* setup cash flow */
		cashFlow.setTrade(tradeFxSpot);
		cashFlow.setCashFlowSi(paySi);
		
		Field field59a = mt103Generator.getField59a(cashFlow, paySi);
		
		assertFalse(field59a instanceof Field59);
		assertFalse(field59a instanceof Field59A);
		assertTrue(field59a instanceof Field59F);
		assertEquals(field59a.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void getField70_paymentDetailsPresentCurrencyNotTHB_returnField70() {
	
		/* test values of cash flow si */
		String paymentDetailsLine1 = "+++++ Payment Details Line 1 ++++++";
		String paymentDetailsLine2 = "+++++ Payment Details Line 2 ++++++";
		
		/* expected test results */
		expectedFieldContent = paymentDetailsLine1.concat("\r\n").
									concat(paymentDetailsLine2);
		
		/* test values of product */
		String productDescription = "OUTRIGHT SPOT";
		
		/* test values of cash flow */
		boolean nettedSettlementIndicator = false;
		
		/* test values of trade */
		boolean fxSwapIndicator = false;
		
		/* setup trade */
		product.setProductCategory(ProductCategory.FX_SPOT);
		product.setProductDescription(productDescription);
		tradeFxSpot.setProduct(product);
		tradeFxSpot.setFxSwapIndicator(fxSwapIndicator);
		
		/* setup cash flow */
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("USD");
		cashFlow.setTrade(tradeFxSpot);
		cashFlow.setNettedSettlementIndicator(nettedSettlementIndicator);
		cashFlow.setCurrency(cashFlowCurrency);
		
		/* setup cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentDetailsLine1(paymentDetailsLine1);
		paySi.setPaymentDetailsLine2(paymentDetailsLine2);
		
		Field field70 = mt103Generator.getField70(cashFlow, paySi);
		
		assertEquals(field70.getValue(), expectedFieldContent);
		
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
		
		Field72 field72 = mt103Generator.getField72(cashFlow, paySi);
		
		assertEquals(field72.getValue(), expectedFieldContent);
		
	}
	
	@Test
	public void generate_case1_returnMT103() {
		
		/* test values of cash flow */
		String countryCode = "HK";
		String groupMemberAbbreviation = "HSBC";
		String branchMneumonic = "HKH";
		String branchSwiftBic = "HSBCHKH0 HKH";
		String cashFlowReference = "FWDHKH8145976701";
		String messageUserReference = "UH1814522463";
		boolean EURegulationFlag = true;
		
		Calendar valueDateCalendar = new GregorianCalendar(2018, 04, 25);
		Date valueDate = valueDateCalendar.getTime();
		
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("USD");
		cashFlowCurrency.setSettlementCurrencyCode("USD");
		
		BigDecimal cashFlowAmount = new BigDecimal(7668.71);
		
		boolean nettedSettlementIndicator = false;
		
		/* test values cash flow si */
		PayWireType payWireType = PayWireType.GENERATE;
		boolean globalStandardCompliantFlag = false;
		FundTransferSystem fts = new FundTransferSystem("");
		String paymentDetailsLine1 = "";
		String paymentDetailsLine2 = "";
		String senderToReceiverNarrativeLine1 = "";
		String senderToReceiverNarrativeLine2 = "";
		String senderToReceiverNarrativeLine3 = "";
		String senderToReceiverNarrativeLine4 = "";
		String senderToReceiverNarrativeLine5 = "";
		String senderToReceiverNarrativeLine6 = "";
		
		String ourSettlementSwiftBic = "MRMDUS30";
		String ourAccountWithThemInTheirBook = "000044407";
		
		String intermediaryBankSwiftBicCode = "";
		String intermediaryBankNameLine1 = "";
		String intermediaryBankNameLine2 = "";
		String intermediaryBankClearingBankCode = "";
		String intermediaryBankClearingSortCode = "";
		
		String beneficiaryBankSwiftCode = "MRMDUS33";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "";
		String beneficiaryBankAccountOnIntermediary = "";
		
		String cptyClearingSortCode = "";
		String cptySettlementAccount = "000012345";
		
		/* test values of trade */
		boolean ourBankAsOrderingCustomerFlag = false;
		String productDescription = "OUTRIGHT FORWARD";
		boolean fxSwapIndicator = false;
		
		/* test values of counterparty legal entity */
		String cptyGridId = "0000000000000260854";
		boolean erdsSwiftCompliantFlag = true;
		String cptyLegalEntityNameLine1 = "ABLE LEADER COMPANY LIMITED";
		String cptyLegalEntityNameLine2 = "";
		String cptyLegalEntityNameLine3 = "";
		String cptyLegalEntityAddressLine1 = "KWONG LOONG TAI BUILDING, 1018 TA";
		String cptyLegalEntityAddressLine2 = "WEST STREET, LAI CHI KOK, 3/F";
		String cptyLegalEntityCountryStateCity = "3/HK/KOWLOON SHAM SHUI PO";
		
		/* test values of counterparty */
		String counterpartyAcronym = "ALCLHKH";
		String counterpartySwiftBic = "";
		String addressLine1_leng35 = "+ALCLHKH ALCLHKH ALCLHKH NAMELINE1+";
		String addressLine2_leng35 = "+ALCLHKH ALCLHKH ALCLHKH NAMELINE2+";
		String addressLine3_leng35 = "+++ALCLHKH ALCLHKH ADDRESS LINE1+++";
		String addressLine4_leng35 = "+++ALCLHKH ALCLHKH ADDRESS LINE2+++";
		String cptyCountryOfResidence = "HK";
		
		/* setup cash cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setOurSettlementSwiftBicCode(ourSettlementSwiftBic);
		paySi.setChargesForIndicator(ChargesForIndicator.OUR);
		paySi.setGlobalStandardCompliantFlag(globalStandardCompliantFlag);
		paySi.setPayWireType(payWireType);
		paySi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		paySi.setFts(fts);
		paySi.setIntermediaryBankSwiftBicCode(intermediaryBankSwiftBicCode);
		paySi.setIntermediaryBankNameLine1(intermediaryBankNameLine1);
		paySi.setIntermediaryBankNameLine2(intermediaryBankNameLine2);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setIntermediaryBankClearingSortCode(intermediaryBankClearingSortCode);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setCounterpartyClearingSortCode(cptyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(cptySettlementAccount);
		paySi.setPaymentDetailsLine1(paymentDetailsLine1);
		paySi.setPaymentDetailsLine2(paymentDetailsLine2);
		paySi.setSenderToReceiverNarrativeLine1(senderToReceiverNarrativeLine1);
		paySi.setSenderToReceiverNarrativeLine2(senderToReceiverNarrativeLine2);
		paySi.setSenderToReceiverNarrativeLine3(senderToReceiverNarrativeLine3);
		paySi.setSenderToReceiverNarrativeLine4(senderToReceiverNarrativeLine4);
		paySi.setSenderToReceiverNarrativeLine5(senderToReceiverNarrativeLine5);
		paySi.setSenderToReceiverNarrativeLine6(senderToReceiverNarrativeLine6);
		
		/* setup cash flow */
		organization.setCountryCode(countryCode);
		organization.setGroupMemberAbbreviation(groupMemberAbbreviation);
		organization.setBranchMneumonic(branchMneumonic);
		organization.setBicCode(branchSwiftBic);
		organization.setEURegulationFlag(EURegulationFlag);
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxForward);
		cashFlow.setMessageUserReference(messageUserReference);
		cashFlow.setCashFlowReference(cashFlowReference);
		cashFlow.setOrganization(organization);
		cashFlow.setValueDate(valueDate);
		cashFlow.setCurrency(cashFlowCurrency);
		cashFlow.setCashFlowAmount(cashFlowAmount);
		cashFlow.setNettedSettlementIndicator(nettedSettlementIndicator);
		
		/* setup counterparty legal entity */
		ERDSLegalEntity legalEntity = new ERDSLegalEntity();
		legalEntity.setGridId(cptyGridId);
		legalEntity.setSwiftCompliantFlag(erdsSwiftCompliantFlag);
		legalEntity.setLegalEntityNameLine1(cptyLegalEntityNameLine1);
		legalEntity.setLegalEntityNameLine2(cptyLegalEntityNameLine2);
		legalEntity.setLegalEntityNameLine3(cptyLegalEntityNameLine3);
		legalEntity.setLegalEntityAddressLine1(cptyLegalEntityAddressLine1);
		legalEntity.setLegalEntityAddressLine2(cptyLegalEntityAddressLine2);
		legalEntity.setCountryStateCity(cptyLegalEntityCountryStateCity);
		
		/* setup counterparty */
		cptyOrganization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		counterparty.setOrganization(cptyOrganization);
		counterparty.setAcronym(counterpartyAcronym);
		counterparty.setCountryOfResidence(cptyCountryOfResidence);
		counterparty.setErdsLegalEntity(legalEntity);
		counterparty.setNameAndAddressLine1(addressLine1_leng35);
		counterparty.setNameAndAddressLine2(addressLine2_leng35);
		counterparty.setNameAndAddressLine3(addressLine3_leng35);
		counterparty.setNameAndAddressLine4(addressLine4_leng35);

		/* setup trade */
		product.setOurBankAsOrderingCustomer(ourBankAsOrderingCustomerFlag);
		product.setProductCategory(ProductCategory.FX_FORWARD);
		product.setProductDescription(productDescription);
		tradeFxForward.setProduct(product);
		tradeFxForward.setCounterparty(counterparty);
		tradeFxForward.setCounterparty(counterparty);
		tradeFxForward.setFxSwapIndicator(fxSwapIndicator);

		MT103 mt103 = mt103Generator.generate(cashFlow, paySi);

		System.out.println(mt103.message());
		
	}

	@Test
	public void generate_case2_returnMT103() {
		
		/* test values of cash flow */
		String countryCode = "HK";
		String groupMemberAbbreviation = "HSBC";
		String branchMneumonic = "HKH";
		String branchSwiftBic = "HSBCHKH0 HKH";
		String cashFlowReference = "FWDHKH8145976601";
		String messageUserReference = "UH1814522465";
		boolean EURegulationFlag = true;
		
		Calendar valueDateCalendar = new GregorianCalendar(2018, 04, 25);
		Date valueDate = valueDateCalendar.getTime();
		
		Currency cashFlowCurrency = new Currency();
		cashFlowCurrency.setCurrencyCode("USD");
		cashFlowCurrency.setSettlementCurrencyCode("USD");
		
		BigDecimal cashFlowAmount = new BigDecimal(3000.00);
		
		boolean nettedSettlementIndicator = false;
		
		/* test values cash flow si */
		PayWireType payWireType = PayWireType.GENERATE;
		boolean globalStandardCompliantFlag = true;
		FundTransferSystem fts = new FundTransferSystem("");
		String paymentDetailsLine1 = "";
		String paymentDetailsLine2 = "";
		String senderToReceiverNarrativeLine1 = "";
		String senderToReceiverNarrativeLine2 = "";
		String senderToReceiverNarrativeLine3 = "";
		String senderToReceiverNarrativeLine4 = "";
		String senderToReceiverNarrativeLine5 = "";
		String senderToReceiverNarrativeLine6 = "";
		
		String ourSettlementSwiftBic = "MRMDUS30";
		String ourAccountWithThemInTheirBook = "000044407";
		
		String intermediaryBankSwiftBicCode = "";
		String intermediaryBankNameLine1 = "";
		String intermediaryBankNameLine2 = "";
		String intermediaryBankClearingBankCode = "";
		String intermediaryBankClearingSortCode = "";
		
		String beneficiaryBankSwiftCode = "MRMDUS30";
		String beneficiaryBankNameLine1 = "HSBC BANK USA NA";
		String beneficiaryBankNameLine2 = "NEW YORK";
		String beneficiaryBankClearingBankCode = "";
		String beneficiaryBankClearingSortCode = "";
		String beneficiaryBankAccountOnIntermediary = "";
		
		String cptyClearingSortCode = "";
		String cptySettlementAccount = "000121703";
		
		/* test values of trade */
		boolean ourBankAsOrderingCustomerFlag = false;
		String productDescription = "OUTRIGHT FORWARD";
		boolean fxSwapIndicator = false;
		
		/* test values of counterparty legal entity */
		String cptyGridId = "0000000000000401324";
		boolean erdsSwiftCompliantFlag = true;
		String cptyLegalEntityNameLine1 = "CATHAY PACIFIC AIRWAYS LIMITED";
		String cptyLegalEntityNameLine2 = "";
		String cptyLegalEntityNameLine3 = "";
		String cptyLegalEntityAddressLine1 = "HK INTRNTNAL AIRPORT, CATHAY PACIF";
		String cptyLegalEntityAddressLine2 = "8 SCENIC ROAD,LANTAU ISLAND, ISLA";
		String cptyLegalEntityCountryStateCity = "3/HK/HONG KONG";
		
		/* test values of counterparty */
		String counterpartyAcronym = "CPAHHKH";
		String counterpartySwiftBic = "";
		String counterpartyNameAndAddressLine1 = "+CPAHHKH CPAHHKH CPAHHKH NAMELINE1+";
		String counterpartyNameAndAddressLine2 = "+CPAHHKH CPAHHKH CPAHHKH NAMELINE2+";
		String counterpartyNameAndAddressLine3 = "+++CPAHHKH CPAHHKH ADDRESS LINE1+++";
		String counterpartyNameAndAddressLine4 = "+++CPAHHKH CPAHHKH ADDRESS LINE2+++";
		String cptyCountryOfResidence = "HK";
		
		/* setup cash cash flow si */
		CashFlowSi paySi = new CashFlowSi();
		paySi.setPaymentIndicator(PaymentIndicator.PAY);
		paySi.setOurSettlementSwiftBicCode(ourSettlementSwiftBic);
		paySi.setChargesForIndicator(ChargesForIndicator.OUR);
		paySi.setGlobalStandardCompliantFlag(globalStandardCompliantFlag);
		paySi.setPayWireType(payWireType);
		paySi.setOurAccountWithThemInTheirBook(ourAccountWithThemInTheirBook);
		paySi.setFts(fts);
		paySi.setIntermediaryBankSwiftBicCode(intermediaryBankSwiftBicCode);
		paySi.setIntermediaryBankNameLine1(intermediaryBankNameLine1);
		paySi.setIntermediaryBankNameLine2(intermediaryBankNameLine2);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setIntermediaryBankClearingSortCode(intermediaryBankClearingSortCode);
		paySi.setIntermediaryBankClearingBankCode(intermediaryBankClearingBankCode);
		paySi.setSettlementBankSwiftBicCode(beneficiaryBankSwiftCode);
		paySi.setSettlementBankClearingBankCode(beneficiaryBankClearingBankCode);
		paySi.setSettlementBankClearingSortCode(beneficiaryBankClearingSortCode);
		paySi.setSettlementBankAccountOnIntermediary(beneficiaryBankAccountOnIntermediary);
		paySi.setCounterpartyClearingSortCode(cptyClearingSortCode);
		paySi.setCounterpartyAccountOnSettlementBank(cptySettlementAccount);
		paySi.setSettlementBankNameLine1(beneficiaryBankNameLine1);
		paySi.setSettlementBankNameLine2(beneficiaryBankNameLine2);
		paySi.setPaymentDetailsLine1(paymentDetailsLine1);
		paySi.setPaymentDetailsLine2(paymentDetailsLine2);
		paySi.setSenderToReceiverNarrativeLine1(senderToReceiverNarrativeLine1);
		paySi.setSenderToReceiverNarrativeLine2(senderToReceiverNarrativeLine2);
		paySi.setSenderToReceiverNarrativeLine3(senderToReceiverNarrativeLine3);
		paySi.setSenderToReceiverNarrativeLine4(senderToReceiverNarrativeLine4);
		paySi.setSenderToReceiverNarrativeLine5(senderToReceiverNarrativeLine5);
		paySi.setSenderToReceiverNarrativeLine6(senderToReceiverNarrativeLine6);
		
		/* setup cash flow */
		organization.setCountryCode(countryCode);
		organization.setGroupMemberAbbreviation(groupMemberAbbreviation);
		organization.setBranchMneumonic(branchMneumonic);
		organization.setBicCode(branchSwiftBic);
		organization.setEURegulationFlag(EURegulationFlag);
		cashFlow.setOrganization(organization);
		cashFlow.setTrade(tradeFxForward);
		cashFlow.setMessageUserReference(messageUserReference);
		cashFlow.setCashFlowReference(cashFlowReference);
		cashFlow.setOrganization(organization);
		cashFlow.setValueDate(valueDate);
		cashFlow.setCurrency(cashFlowCurrency);
		cashFlow.setCashFlowAmount(cashFlowAmount);
		cashFlow.setNettedSettlementIndicator(nettedSettlementIndicator);
		
		/* setup counterparty legal entity */
		ERDSLegalEntity legalEntity = new ERDSLegalEntity();
		legalEntity.setGridId(cptyGridId);
		legalEntity.setSwiftCompliantFlag(erdsSwiftCompliantFlag);
		legalEntity.setLegalEntityNameLine1(cptyLegalEntityNameLine1);
		legalEntity.setLegalEntityNameLine2(cptyLegalEntityNameLine2);
		legalEntity.setLegalEntityNameLine3(cptyLegalEntityNameLine3);
		legalEntity.setLegalEntityAddressLine1(cptyLegalEntityAddressLine1);
		legalEntity.setLegalEntityAddressLine2(cptyLegalEntityAddressLine2);
		legalEntity.setCountryStateCity(cptyLegalEntityCountryStateCity);
		
		/* setup counterparty */
		cptyOrganization.setCountryCode(countryCode);
		cptyOrganization.setBicCode(counterpartySwiftBic);
		counterparty.setOrganization(cptyOrganization);
		counterparty.setAcronym(counterpartyAcronym);
		counterparty.setCountryOfResidence(cptyCountryOfResidence);
		counterparty.setErdsLegalEntity(legalEntity);
		counterparty.setNameAndAddressLine1(counterpartyNameAndAddressLine1);
		counterparty.setNameAndAddressLine2(counterpartyNameAndAddressLine2);
		counterparty.setNameAndAddressLine3(counterpartyNameAndAddressLine3);
		counterparty.setNameAndAddressLine4(counterpartyNameAndAddressLine4);

		/* setup trade */
		product.setOurBankAsOrderingCustomer(ourBankAsOrderingCustomerFlag);
		product.setProductCategory(ProductCategory.FX_FORWARD);
		product.setProductDescription(productDescription);
		tradeFxForward.setProduct(product);
		tradeFxForward.setCounterparty(counterparty);
		tradeFxForward.setCounterparty(counterparty);
		tradeFxForward.setFxSwapIndicator(fxSwapIndicator);

		MT103 mt103 = mt103Generator.generate(cashFlow, paySi);

		System.out.println(mt103.message());
		
	}
	
}
