package persistence.trade;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.Test;

import model.RecordStatus;
import model.organization.Organization;
import model.party.Counterparty;
import model.trade.Currency;
import model.trade.TradeFxSpot;
import model.workflow.TradeStatus;

public class TestFxSpot {

	@Test
	public void test() {

		EntityManagerFactory sessionFactory = Persistence.createEntityManagerFactory("opas-aries-datamodel");

		EntityManager entityManager = sessionFactory.createEntityManager();
		entityManager.getTransaction().begin();

		Timestamp lastUpdatedDatetime = new Timestamp(System.currentTimeMillis());

		TradeFxSpot trade = new TradeFxSpot();

		Counterparty counterparty = new Counterparty();
		counterparty.setAcronym("ABCDHKH");
		counterparty.setBicCode("ABCDHKHH");
		counterparty.setCounterpartyId(new Long(4));
		counterparty.setLastUpdatedUserId(new Long(1001));
		counterparty.setLastUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
		counterparty.setRecordStatus(RecordStatus.ACTIVE);
		counterparty.setVersion(new Long(0));

		Calendar cal = new GregorianCalendar(2018, Calendar.APRIL, 9);

		trade.setBoughtCurrencyAmount(new BigDecimal(1000000));
		Currency boughtCurrency = new Currency();
		boughtCurrency.setCurrencyCode("USD");
		boughtCurrency.setConfirmationCurrencyCode("USD");
		boughtCurrency.setSettlementCurrencyCode("USD");
		trade.setBoughtCurrency(boughtCurrency);
		trade.setContractRate(new BigDecimal(7.8));
		trade.setSoldCurrencyAmount(new BigDecimal(-7800000));
		Currency soldCurrency = new Currency();
		soldCurrency.setCurrencyCode("HKD");
		soldCurrency.setConfirmationCurrencyCode("HKD");
		soldCurrency.setSettlementCurrencyCode("HKD");
		trade.setSoldCurrency(soldCurrency);
		trade.setValueDate(cal.getTime());

		trade.setBrokerId(new Long(1001));
		trade.setCaptureDateTime(cal.getTime());
		trade.setCounterparty(counterparty);
		//trade.setChannel("PHONE");
		trade.setDealerId(new Long(1001));
		trade.setLastUpdatedDatetime(lastUpdatedDatetime);
		trade.setLastUpdatedUserId(new Long(1001));
		// trade.setOrganizationId(new Long(1001));
		Organization organization = new Organization();
		organization.setOrganizationId(new Long(1001));
		trade.setOrganization(organization);
		trade.setRecordStatus(RecordStatus.ACTIVE);
		trade.setSourceSystemName("SGH BOS");
		trade.setSourceSystemReferenceId("SPT182980000");
		trade.setStatus(TradeStatus.NOR);
		trade.setValueDate(cal.getTime());
		trade.setVersion(new Long(0));

		entityManager.persist(trade);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
