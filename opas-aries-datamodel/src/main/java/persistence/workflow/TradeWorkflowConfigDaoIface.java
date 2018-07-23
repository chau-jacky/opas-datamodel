package persistence.workflow;

import java.util.List;

import model.workflow.TradeAction;
import model.workflow.TradeStatus;
import model.workflow.TradeWorkflowConfig;
import persistence.OpasPersistenceException;

public interface TradeWorkflowConfigDaoIface {

	public TradeWorkflowConfig select(Long configId) throws OpasPersistenceException;

	public Long insert(TradeWorkflowConfig tradeWorkflowConfig) throws OpasPersistenceException;

	public void update(TradeWorkflowConfig tradeWorkflowConfig) throws OpasPersistenceException;

	public void delete(TradeWorkflowConfig tradeWorkflowConfig) throws OpasPersistenceException;

	public List<TradeWorkflowConfig> selectConfigByOrganizationIdAndTradeClassNameAndFromStatusAndAction(
			Long organizationId, String tradeClassName, TradeStatus fromStatus, TradeAction tradeAction)
			throws OpasPersistenceException;

	public List<TradeWorkflowConfig> selectStpConfigByOrganizationIdAndTradeClassNameAndFromStatusAndAction(
			Long organizationId, String tradeClassName, TradeStatus fromStatus, TradeAction tradeAction)
			throws OpasPersistenceException;

}
