package model.organization;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import model.OpasObject;

@Entity
@SequenceGenerator(name = "organization_seq", sequenceName = "organization_seq", allocationSize = 1, initialValue = 1000)
@Table(name = "ORGANIZATION")
public class Organization extends OpasObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5473972005284048769L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "organization_seq")
	@Column(name = "ORGANIZATION_ID", nullable = false)
	private Long organizationId;

	@Column(name = "ORGANIZATION_ACRONYM", nullable = false, length = 8)
	private String organizationAcronym;

	@Column(name = "ORGANIZATION_NAME", nullable = false, length = 64)
	private String organizationName;
	
	@Column(name = "ORGANIZATION_NAME_ADDR_LINE1", nullable = false, length = 35)
	private String organizationNameAndAddressLine1;
	
	@Column(name = "ORGANIZATION_NAME_ADDR_LINE2", length = 35)
	private String organizationNameAndAddressLine2;
	
	@Column(name = "ORGANIZATION_NAME_ADDR_LINE3", length = 35)
	private String organizationNameAndAddressLine3;
	
	@Column(name = "ORGANIZATION_NAME_ADDR_LINE4", length = 35)
	private String organizationNameAndAddressLine4;

	@Column(name = "BIC_CODE", nullable = false, length = 10)
	private String bicCode;

	@Column(name = "COUNTRY_CODE", nullable = false, length = 2)
	private String countryCode;

	@Column(name = "GROUP_MEMBER_ABBR", nullable = false, length = 4)
	private String groupMemberAbbreviation;

	@Column(name = "BRANCH_NO", nullable = false)
	private int branchNumber;
	
	private List<Department> departmentList;
	
	private boolean doddFrankReportingFlag;
	
	public Long getOrganizationId() {
		return organizationId;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public String getOrganizationAcronym() {
		return organizationAcronym;
	}

	public void setOrganizationAcronym(String organizationAcronym) {
		this.organizationAcronym = organizationAcronym;
	}

	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getOrganizationNameAndAddressLine1() {
		return organizationNameAndAddressLine1;
	}

	public void setOrganizationNameAndAddressLine1(String organizationNameAndAddressLine1) {
		this.organizationNameAndAddressLine1 = organizationNameAndAddressLine1;
	}

	public String getOrganizationNameAndAddressLine2() {
		return organizationNameAndAddressLine2;
	}

	public void setOrganizationNameAndAddressLine2(String organizationNameAndAddressLine2) {
		this.organizationNameAndAddressLine2 = organizationNameAndAddressLine2;
	}

	public String getOrganizationNameAndAddressLine3() {
		return organizationNameAndAddressLine3;
	}

	public void setOrganizationNameAndAddressLine3(String organizationNameAndAddressLine3) {
		this.organizationNameAndAddressLine3 = organizationNameAndAddressLine3;
	}

	public String getOrganizationNameAndAddressLine4() {
		return organizationNameAndAddressLine4;
	}

	public void setOrganizationNameAndAddressLine4(String organizationNameAndAddressLine4) {
		this.organizationNameAndAddressLine4 = organizationNameAndAddressLine4;
	}

	public String getBicCode() {
		return bicCode;
	}

	public void setBicCode(String bicCode) {
		this.bicCode = bicCode;
	}

	public String getCountryCode() {
		return countryCode;
	}

	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	public String getGroupMemberAbbreviation() {
		return groupMemberAbbreviation;
	}

	public void setGroupMemberAbbreviation(String groupMemberAbbreviation) {
		this.groupMemberAbbreviation = groupMemberAbbreviation;
	}

	public int getBranchNumber() {
		return branchNumber;
	}

	public void setBranchNumber(int branchNumber) {
		this.branchNumber = branchNumber;
	}

	public List<Department> getDepartmentList() {
		return departmentList;
	}

	public void setDepartmentList(List<Department> departmentList) {
		this.departmentList = departmentList;
	}

	public boolean isDoddFrankReportingFlag() {
		return doddFrankReportingFlag;
	}

	public void setDoddFrankReportingFlag(boolean doddFrankReportingFlag) {
		this.doddFrankReportingFlag = doddFrankReportingFlag;
	}

}
