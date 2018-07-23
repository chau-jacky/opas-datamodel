package model.organization;

import java.io.Serializable;

import model.OpasOrganizationObject;

public class OrganizationSsi extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7149776730408347383L;

	private String currencyCode;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

}
