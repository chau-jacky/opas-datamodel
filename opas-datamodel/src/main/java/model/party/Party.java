package model.party;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

import model.OpasOrganizationObject;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Party extends OpasOrganizationObject {

	@Column(name = "ACRONYM", nullable = false, length = 8)
	private String accronym;

	@Column(name = "NAME", nullable = false, length = 64)
	private String name;

	@Column(name = "BIC_CODE", nullable = false, length = 10)
	private String bicCode;

	@Transient
	private List<PartyAttribute> partyAttributes;

	public String getAccronym() {
		return accronym;
	}

	public void setAccronym(String accronym) {
		this.accronym = accronym;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBicCode() {
		return bicCode;
	}

	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}

	public List<PartyAttribute> getPartyAttributes() {
		return partyAttributes;
	}

	public void setPartyAttributes(List<PartyAttribute> partyAttributes) {
		this.partyAttributes = partyAttributes;
	}

}
