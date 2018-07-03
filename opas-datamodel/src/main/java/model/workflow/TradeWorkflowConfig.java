package model.workflow;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.OpasOrganizationObject;

@Entity
@SequenceGenerator(name = "tradeWorkflowConfig_seq", sequenceName = "tradeWorkflowConfig_seq", allocationSize = 1, initialValue = 1000)
@Table(name = "TRADE_WORKFLOW_CONFIG")
public class TradeWorkflowConfig extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8363010475108791947L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tradeWorkflowConfig_seq")
	@Column(name = "TRADE_WORKFLOW_CONFIG_ID", nullable = false)
	private Long tradeWorkflowConfigId;

	@Column(name = "TRADE_CLASS_NAME", nullable = false)
	private String tradeClassName;

	@Enumerated(EnumType.STRING)
	@Column(name = "CURRENT_TRADE_ACTION", nullable = false)
	private TradeAction currentTradeAction;

	@Enumerated(EnumType.STRING)
	@Column(name = "CURRENT_TRADE_STATUS", nullable = false)
	private TradeStatus currentTradeStatus;

	@Enumerated(EnumType.STRING)
	@Column(name = "NEXT_TRADE_ACTION", nullable = false)
	private TradeAction nextTradeAction;

	@Enumerated(EnumType.STRING)
	@Column(name = "NEXT_TRADE_STATUS", nullable = false)
	private TradeStatus nextTradeStatus;

	@Column(name = "STP_FLAG", nullable = false)
	private Boolean stp;

	public Long getTradeWorkflowConfigId() {
		return tradeWorkflowConfigId;
	}

	public void setTradeWorkflowConfigId(Long tradeWorkflowConfigId) {
		this.tradeWorkflowConfigId = tradeWorkflowConfigId;
	}

	public String getTradeClassName() {
		return tradeClassName;
	}

	public void setTradeClassName(String tradeClassName) {
		this.tradeClassName = tradeClassName;
	}

	public TradeAction getCurrentTradeAction() {
		return currentTradeAction;
	}

	public void setCurrentTradeAction(TradeAction currentTradeAction) {
		this.currentTradeAction = currentTradeAction;
	}

	public TradeStatus getCurrentTradeStatus() {
		return currentTradeStatus;
	}

	public void setCurrentTradeStatus(TradeStatus currentTradeStatus) {
		this.currentTradeStatus = currentTradeStatus;
	}

	public TradeStatus getNextTradeStatus() {
		return nextTradeStatus;
	}

	public void setNextTradeStatus(TradeStatus nextTradeStatus) {
		this.nextTradeStatus = nextTradeStatus;
	}

	public Boolean getStp() {
		return stp;
	}

	public void setStp(Boolean stp) {
		this.stp = stp;
	}

	public TradeAction getNextTradeAction() {
		return nextTradeAction;
	}

	public void setNextTradeAction(TradeAction nextTradeAction) {
		this.nextTradeAction = nextTradeAction;
	}

}
