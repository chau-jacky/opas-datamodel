package model.organization;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import model.OpasOrganizationObject;

public class Department extends OpasOrganizationObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8828639449888389518L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "DEPARTMENT_ID", nullable = false)
	private Long departmentId;
	
	private BusinessClass businessClass;
	
	@Column(name = "DEPARTMENT_NAME", length = 29)
	private String departmentName;
	
	@Column(name = "FAX_NUMBER", length = 29)
	private String faxNumber;
	
	@Column(name = "CONTACT_PERSON", length = 29)
	private String contactPerson;
	
	@Column(name = "PHONE_NUMBER", length = 29)
	private String phoneNumber;
	
	@Column(name = "TELEX_NUMBER", length = 29)
	private String telexNumber;
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}
	
	public BusinessClass getBusinessClass() {
		return businessClass;
	}

	public void setBusinessClass(BusinessClass businessClass) {
		this.businessClass = businessClass;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTelexNumber() {
		return telexNumber;
	}

	public void setTelexNumber(String telexNumber) {
		this.telexNumber = telexNumber;
	}
	
}
