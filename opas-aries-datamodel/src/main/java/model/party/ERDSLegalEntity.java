package model.party;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.OpasOrganizationObject;

public class ERDSLegalEntity extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1471891100795533711L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ERDS_LEGAL_ENTITY_ID", nullable = false)
	private Long legalEnitytId;
	
	private String gridId;
	
	private boolean swiftCompliantFlag;
	
	private String legalEntityNameLine1;
	
	private String legalEntityNameLine2;
	
	private String legalEntityNameLine3;
	
	private String legalEntityAddressLine1;
	
	private String legalEntityAddressLine2;
	
	private String countryStateCity;
	
	private boolean bankIndicator;

	public Long getLegalEnitytId() {
		return legalEnitytId;
	}

	public void setLegalEnitytId(Long legalEnitytId) {
		this.legalEnitytId = legalEnitytId;
	}

	public String getGridId() {
		return gridId;
	}

	public void setGridId(String gridId) {
		this.gridId = gridId;
	}

	public boolean isSwiftCompliantFlag() {
		return swiftCompliantFlag;
	}

	public void setSwiftCompliantFlag(boolean swiftCompliantFlag) {
		this.swiftCompliantFlag = swiftCompliantFlag;
	}

	public String getLegalEntityNameLine1() {
		return legalEntityNameLine1;
	}

	public void setLegalEntityNameLine1(String legalEntityNameLine1) {
		this.legalEntityNameLine1 = legalEntityNameLine1;
	}

	public String getLegalEntityNameLine2() {
		return legalEntityNameLine2;
	}

	public void setLegalEntityNameLine2(String legalEntityNameLine2) {
		this.legalEntityNameLine2 = legalEntityNameLine2;
	}

	public String getLegalEntityNameLine3() {
		return legalEntityNameLine3;
	}

	public void setLegalEntityNameLine3(String legalEntityNameLine3) {
		this.legalEntityNameLine3 = legalEntityNameLine3;
	}

	public String getLegalEntityAddressLine1() {
		return legalEntityAddressLine1;
	}

	public void setLegalEntityAddressLine1(String legalEntityAddressLine1) {
		this.legalEntityAddressLine1 = legalEntityAddressLine1;
	}

	public String getLegalEntityAddressLine2() {
		return legalEntityAddressLine2;
	}

	public void setLegalEntityAddressLine2(String legalEntityAddressLine2) {
		this.legalEntityAddressLine2 = legalEntityAddressLine2;
	}

	public String getCountryStateCity() {
		return countryStateCity;
	}

	public void setCountryStateCity(String countryStateCity) {
		this.countryStateCity = countryStateCity;
	}

	public boolean isBankIndicator() {
		return bankIndicator;
	}

	public void setBankIndicator(boolean bankIndicator) {
		this.bankIndicator = bankIndicator;
	}
	
	
}
