package model.trade;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.OpasOrganizationObject;

@Entity
@Table(name = "TRADE_DEALER")
public class Dealer extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4950480315862355660L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "DEALER_ID", nullable = false)
	private Long dealerId;

	@Column(name = "DEALER_ACRYONM", nullable = false, length = 80)
	private String dealerAcronym;

	@Column(name = "DEALER_NAME", nullable = false, length = 80)
	private String dealerName;

	public Long getDealerId() {
		return dealerId;
	}

	public void setDealerId(Long dealerId) {
		this.dealerId = dealerId;
	}

	public String getDealerAcronym() {
		return dealerAcronym;
	}

	public void setDealerAcronym(String dealerAcronym) {
		this.dealerAcronym = dealerAcronym;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

}
