package model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import model.organization.Organization;

@MappedSuperclass
public abstract class OpasOrganizationObject extends OpasObject {

	/* @Column(name = "ORGANIZATION_ID", nullable = false)
	private Long organizationId; */
	
	@ManyToOne
	@JoinColumn(name = "ORGANIZATION_ID")
	private Organization organization;

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/*
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	 */
}
