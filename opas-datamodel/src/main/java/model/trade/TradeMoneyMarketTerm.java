package model.trade;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "TRADE_MONEY_MARKET_TERM")
@PrimaryKeyJoinColumn(name = "ID")
public class TradeMoneyMarketTerm extends Trade implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 5740166353117994843L;

	@Column(name = "PRINCIPAL_CCY_AMOUNT")
	private double principalAmount;

	@Column(name = "PRINCIPAL_CCY_CODE")
	private String principalCcyCode;

	@Column(name = "MATURITY_DATE")
	private Date maturityDate;

	public double getPrincipalAmount() {
		return principalAmount;
	}

	public void setPrincipalAmount(double principalAmount) {
		this.principalAmount = principalAmount;
	}

	public String getPrincipalCcyCode() {
		return principalCcyCode;
	}

	public void setPrincipalCcyCode(String principalCcyCode) {
		this.principalCcyCode = principalCcyCode;
	}

	public Date getMaturityDate() {
		return maturityDate;
	}

	public void setMaturityDate(Date maturityDate) {
		this.maturityDate = maturityDate;
	}

}
