package model.trade;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Id;

import model.OpasOrganizationObject;

public class DoddFrankReportingInformation extends OpasOrganizationObject implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 760863047841657190L;

	@Id
	@Column(name = "DODD_FRANK_REPORTING_INFO_ID")
	private Long doddFrankReportingInfoId;
	
	private String dealUsi;
	
	private String dealPriorUsi;
	
	private Calendar executionDateTime;

	public Long getDoddFrankReportingInfoId() {
		return doddFrankReportingInfoId;
	}

	public void setDoddFrankReportingInfoId(Long doddFrankReportingInfoId) {
		this.doddFrankReportingInfoId = doddFrankReportingInfoId;
	}

	public String getDealUsi() {
		return dealUsi;
	}

	public void setDealUsi(String dealUsi) {
		this.dealUsi = dealUsi;
	}

	public String getDealPriorUsi() {
		return dealPriorUsi;
	}

	public void setDealPriorUsi(String dealPriorUsi) {
		this.dealPriorUsi = dealPriorUsi;
	}

	public Calendar getExecutionDateTime() {
		return executionDateTime;
	}

	public void setExecutionDateTime(Calendar executionDateTime) {
		this.executionDateTime = executionDateTime;
	}
	
	
}
