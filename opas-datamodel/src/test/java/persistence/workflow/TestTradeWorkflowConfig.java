package persistence.workflow;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.OptimisticLockException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import model.RecordStatus;
import model.organization.Organization;
import model.trade.TradeFxSpot;
import model.workflow.TradeAction;
import model.workflow.TradeStatus;
import model.workflow.TradeWorkflowConfig;
import persistence.OpasPersistenceException;
import persistence.TestConstants;

public class TestTradeWorkflowConfig {

	private TradeWorkflowConfigDaoIface dao;
	private Long tradeWorkflowConfigId;

	@Before
	public void setUp() throws Exception {
		dao = new TradeWorkflowConfigDaoHibernateImpl();

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws OpasPersistenceException {

		// Create and save a new book
		TradeWorkflowConfig tradeWorkflowConfig1 = new TradeWorkflowConfig();
		tradeWorkflowConfig1.setLastUpdatedDatetime(new Timestamp(System.currentTimeMillis()));
		tradeWorkflowConfig1.setLastUpdatedUserId(TestConstants.USER_ID);
		tradeWorkflowConfig1.setRecordStatus(RecordStatus.ACTIVE);
		// tradeWorkflowConfig1.setOrganizationId(TestConstants.ORGANIZATION_ID);
		Organization organization = new Organization();
		organization.setOrganizationId(TestConstants.ORGANIZATION_ID);
		tradeWorkflowConfig1.setOrganization(organization);

		tradeWorkflowConfig1.setCurrentTradeStatus(TradeStatus.NONE);
		tradeWorkflowConfig1.setStp(Boolean.TRUE);
		tradeWorkflowConfig1.setNextTradeStatus(TradeStatus.FLP);
		tradeWorkflowConfig1.setCurrentTradeAction(TradeAction.NEW);
		tradeWorkflowConfig1.setTradeClassName(TradeFxSpot.class.getName());
		tradeWorkflowConfig1.setNextTradeAction(TradeAction.PROCESS);

		tradeWorkflowConfigId = dao.insert(tradeWorkflowConfig1);

		// Select the Trade Workflow Config created using the tradeWorkflowConfigId
		TradeWorkflowConfig tradeWorkflowConfig2 = dao.select(tradeWorkflowConfigId);

		// Check all attributes
		Assert.assertEquals("LastUpdatedDatetime", tradeWorkflowConfig1.getLastUpdatedDatetime(),
				tradeWorkflowConfig2.getLastUpdatedDatetime());
		Assert.assertEquals("LastUpdatedUserId", tradeWorkflowConfig1.getLastUpdatedUserId(),
				tradeWorkflowConfig2.getLastUpdatedUserId());
		Assert.assertEquals("Version", tradeWorkflowConfig1.getVersion(), tradeWorkflowConfig2.getVersion());
		Assert.assertEquals("RecordStatus", tradeWorkflowConfig1.getRecordStatus().toString(),
				tradeWorkflowConfig2.getRecordStatus().toString());
		Assert.assertEquals("OrganizationId", tradeWorkflowConfig1.getOrganization().getOrganizationId(),
				tradeWorkflowConfig2.getOrganization().getOrganizationId());

		Assert.assertEquals("TradeWorkflowConfigId", tradeWorkflowConfig1.getTradeWorkflowConfigId(),
				tradeWorkflowConfig2.getTradeWorkflowConfigId());
		Assert.assertEquals("FromTradeStatus", tradeWorkflowConfig1.getCurrentTradeStatus(),
				tradeWorkflowConfig2.getCurrentTradeStatus());
		Assert.assertEquals("Stp", tradeWorkflowConfig1.getStp(), tradeWorkflowConfig2.getStp());
		Assert.assertEquals("NextTradeStatus", tradeWorkflowConfig1.getNextTradeStatus(),
				tradeWorkflowConfig2.getNextTradeStatus());
		Assert.assertEquals("TradeAction", tradeWorkflowConfig1.getCurrentTradeAction(),
				tradeWorkflowConfig2.getCurrentTradeAction());
		Assert.assertEquals("TradeClassName", tradeWorkflowConfig1.getTradeClassName(),
				tradeWorkflowConfig2.getTradeClassName());
		Assert.assertEquals("NextTradeAction", tradeWorkflowConfig1.getNextTradeAction(),
				tradeWorkflowConfig2.getNextTradeAction());

		// Update a Trade Workflow Config
		TradeWorkflowConfig tradeWorkflowConfig3 = dao.select(tradeWorkflowConfigId);
		tradeWorkflowConfig3.setStp(Boolean.FALSE);
		dao.update(tradeWorkflowConfig3);

		TradeWorkflowConfig tradeWorkflowConfig4 = dao.select(tradeWorkflowConfigId);

		// Check updated attribute(s)
		Assert.assertEquals("TradeWorkflowConfigDescription", tradeWorkflowConfig3.getStp(),
				tradeWorkflowConfig4.getStp());

		TradeWorkflowConfig tradeWorkflowConfig5 = dao.select(tradeWorkflowConfigId);
		TradeWorkflowConfig tradeWorkflowConfig6 = dao.select(tradeWorkflowConfigId);

		tradeWorkflowConfig5.setNextTradeStatus(TradeStatus.CNL);
		tradeWorkflowConfig6.setNextTradeStatus(TradeStatus.MAT);

		try {

			dao.update(tradeWorkflowConfig5);

			dao.update(tradeWorkflowConfig6);
			// fail if update is successful

			Assert.fail();

		} catch (OptimisticLockException ole) {
			// do nothing
		}

		// delete Trade Workflow Config
		TradeWorkflowConfig tradeWorkflowConfig7 = dao.select(tradeWorkflowConfigId);
		dao.delete(tradeWorkflowConfig7);

		TradeWorkflowConfig tradeWorkflowConfig8 = dao.select(tradeWorkflowConfigId);
		Assert.assertEquals("RecordStatus", tradeWorkflowConfig7.getRecordStatus(),
				tradeWorkflowConfig8.getRecordStatus());

		@SuppressWarnings("unused")
		List<TradeWorkflowConfig> tradeWorkflowConfig1s = dao
				.selectConfigByOrganizationIdAndTradeClassNameAndFromStatusAndAction(TestConstants.ORGANIZATION_ID,
						TradeFxSpot.class.getName(), TestConstants.TRADE_STATUS, TestConstants.TRADE_ACTION);

		@SuppressWarnings("unused")
		List<TradeWorkflowConfig> tradeWorkflowConfig2s = dao
				.selectStpConfigByOrganizationIdAndTradeClassNameAndFromStatusAndAction(TestConstants.ORGANIZATION_ID,
						TradeFxSpot.class.getName(), TestConstants.TRADE_STATUS, TestConstants.TRADE_ACTION);

	}
}
