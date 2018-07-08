package model.party;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import model.trade.ScopeOfOperation;

@Entity
@Table(name = "PARTY_COUNTERPARTY")
public class Counterparty extends Party implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3649992882039242939L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "COUNTERPARTY_ID", nullable = false)
	private Long counterpartyId;

	@Column(name = "CMS_ID", length = 34)
	private String cmsIdentifier;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "SCOPE_OF_OPERATION")
	private ScopeOfOperation scopeOfOperation;

	@Column(name = "ENTITY_OF_FUND", length = 34)
	private String entityOfFund;

	@Column(name = "NAME_AND_ADDRESS_LINE_1", length = 34)
	private String nameAndAddressLine1;

	@Column(name = "NAME_AND_ADDRESS_LINE_2", length = 34)
	private String nameAndAddressLine2;

	@Column(name = "NAME_AND_ADDRESS_LINE_3", length = 34)
	private String nameAndAddressLine3;

	@Column(name = "NAME_AND_ADDRESS_LINE_4", length = 34)
	private String nameAndAddressLine4;

	public Long getCounterpartyId() {
		return counterpartyId;
	}

	public void setCounterpartyId(Long counterpartyId) {
		this.counterpartyId = counterpartyId;
	}

	public String getCmsIdentifier() {
		return cmsIdentifier;
	}

	public void setCmsIdentifier(String cmsIdentifier) {
		this.cmsIdentifier = cmsIdentifier;
	}
	
	public ScopeOfOperation getScopeOfOperation() {
		return scopeOfOperation;
	}

	public void setScopeOfOperation(ScopeOfOperation scopeOfOperation) {
		this.scopeOfOperation = scopeOfOperation;
	}

	public String getEntityOfFund() {
		return entityOfFund;
	}

	public void setEntityOfFund(String entityOfFund) {
		this.entityOfFund = entityOfFund;
	}

	public String getNameAndAddressLine1() {
		return nameAndAddressLine1;
	}

	public void setNameAndAddressLine1(String nameAndAddressLine1) {
		this.nameAndAddressLine1 = nameAndAddressLine1;
	}

	public String getNameAndAddressLine2() {
		return nameAndAddressLine2;
	}

	public void setNameAndAddressLine2(String nameAndAddressLine2) {
		this.nameAndAddressLine2 = nameAndAddressLine2;
	}

	public String getNameAndAddressLine3() {
		return nameAndAddressLine3;
	}

	public void setNameAndAddressLine3(String nameAndAddressLine3) {
		this.nameAndAddressLine3 = nameAndAddressLine3;
	}

	public String getNameAndAddressLine4() {
		return nameAndAddressLine4;
	}

	public void setNameAndAddressLine4(String nameAndAddressLine4) {
		this.nameAndAddressLine4 = nameAndAddressLine4;
	}

}
