package workflow;

import java.util.List;

import model.backoffice.BoTask;
import model.trade.Trade;
import model.workflow.TradeAction;
import model.workflow.TradeStatus;
import model.workflow.TradeWorkflowConfig;
import model.workflow.TradeWorkflowExecutionException;
import persistence.OpasPersistenceException;
import persistence.workflow.TradeWorkflowConfigDaoHibernateImpl;
import persistence.workflow.TradeWorkflowConfigDaoIface;

public class TradeWorkflowEngine {

	private TradeWorkflowConfigDaoIface tradeWorkflowConfigDao;

	public TradeWorkflowEngine() {
		tradeWorkflowConfigDao = new TradeWorkflowConfigDaoHibernateImpl();
	}

	public void execute(Trade trade, TradeAction tradeAction, List<BoTask> tasks)
			throws TradeWorkflowExecutionException {

		String tradeClassName = trade.getClass().getName();
		TradeStatus fromStatus = trade.getStatus();
		Long organizationId = trade.getOrganization().getOrganizationId();

		TradeWorkflowConfig tradeWorkflowConfig = loadTradeWorkflowConfig(organizationId, tradeClassName, fromStatus,
				tradeAction);
		while (tradeWorkflowConfig != null) {

			TradeStatus nextTradeStatus = tradeWorkflowConfig.getNextTradeStatus();
			TradeAction nextTradeAction = tradeWorkflowConfig.getNextTradeAction();
			tradeWorkflowConfig = loadStpTradeWorkflowConfig(organizationId, tradeClassName, nextTradeStatus,
					nextTradeAction);

		}

		if (fromStatus.equals(trade.getStatus())) {
			throw new TradeWorkflowExecutionException("No trade workflow config applicable");
		}
	}

	private TradeWorkflowConfig loadTradeWorkflowConfig(Long organizationId, String tradeClassName,
			TradeStatus fromStatus, TradeAction tradeAction) throws TradeWorkflowExecutionException {

		TradeWorkflowConfig tradeWorkflowConfig = null;

		try {
			List<TradeWorkflowConfig> tradeWorkflowConfigs = tradeWorkflowConfigDao
					.selectConfigByOrganizationIdAndTradeClassNameAndFromStatusAndAction(organizationId, tradeClassName,
							fromStatus, tradeAction);

			if (tradeWorkflowConfigs.size() == 0) {
				// No valid workflow found; unable determine the next status
				throw new TradeWorkflowExecutionException("No STP Trade Workflow Config Rules found");
			}

			if (tradeWorkflowConfigs.size() > 1) {
				// More than one workflow rule found; unable determine the next status
				throw new TradeWorkflowExecutionException("More than 1 STP trade Workflow Config Rules found");
			}

			tradeWorkflowConfig = tradeWorkflowConfigs.get(0);

		} catch (OpasPersistenceException ope) {
			throw new TradeWorkflowExecutionException(ope.getMessage());
		}

		return tradeWorkflowConfig;

	}

	private TradeWorkflowConfig loadStpTradeWorkflowConfig(Long organizationId, String tradeClassName,
			TradeStatus fromStatus, TradeAction fromAction) throws TradeWorkflowExecutionException {

		TradeWorkflowConfig tradeWorkflowConfig = null;

		try {
			List<TradeWorkflowConfig> tradeWorkflowConfigs = tradeWorkflowConfigDao
					.selectStpConfigByOrganizationIdAndTradeClassNameAndFromStatusAndAction(organizationId,
							tradeClassName, fromStatus, fromAction);

			if (tradeWorkflowConfigs.size() == 0) {
				// No valid workflow found; unable determine the next status
				throw new TradeWorkflowExecutionException("No STP Trade Workflow Config Rules found");
			}

			if (tradeWorkflowConfigs.size() > 1) {
				// More than one workflow rule found; unable determine the next status
				throw new TradeWorkflowExecutionException("More than 1 STP trade Workflow Config Rules found");
			}

			tradeWorkflowConfig = tradeWorkflowConfigs.get(0);

		} catch (OpasPersistenceException ope) {
			throw new TradeWorkflowExecutionException(ope.getMessage());
		}

		return tradeWorkflowConfig;

	}
}
