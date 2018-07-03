package model.trade;

import java.io.Serializable;
import model.OpasOrganizationObject;

public class Currency extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1548169913616699770L;

	private static final String OFFSHORE_RENMINBI_CODE = "CNH";

	private String currencyCode;
	private String settlementCurrencyCode;
	private String confirmationCurrencyCode;
	private boolean nonDeliverableCurrencyIndicator;

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	public String getSettlementCurrencyCode() {
		return settlementCurrencyCode;
	}

	public void setSettlementCurrencyCode(String settlementCurrencyCode) {
		this.settlementCurrencyCode = settlementCurrencyCode;
	}

	public String getConfirmationCurrencyCode() {
		return confirmationCurrencyCode;
	}

	public void setConfirmationCurrencyCode(String confirmationCurrencyCode) {
		this.confirmationCurrencyCode = confirmationCurrencyCode;
	}

	public boolean isNonDeliverableCurrencyIndicator() {
		return nonDeliverableCurrencyIndicator;
	}

	public void setNonDeliverableCurrencyIndicator(boolean nonDeliverableCurrencyIndicator) {
		this.nonDeliverableCurrencyIndicator = nonDeliverableCurrencyIndicator;
	}

	public boolean isOffShoreRenmibi() {
		return OFFSHORE_RENMINBI_CODE.equals(currencyCode);
	}

}
